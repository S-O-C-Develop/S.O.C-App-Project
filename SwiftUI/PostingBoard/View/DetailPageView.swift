//
//  DetailPageView.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/09/09.
//

import SwiftUI

struct DetailPageView: View {
    
    @ObservedObject var viewModel = PostViewModel()
    
    @Environment(\.presentationMode) private var presentationMode
    
    @FocusState private var isFocused: Bool // 화면 터치시 키보드 숨기기
    
    @State var ripples: [RippleResult] = []

    var postId : Int
    
    var body: some View {
        
        DetailTopBarView(viewModel: viewModel, postAccountId: viewModel.detailResult.accountId, kategorie_1: viewModel.detailKategorie_1, kategorie_2: viewModel.detailKategorie_2)
        
        ScrollRefreshable(content: {
            VStack(alignment: .leading, spacing: 20){

                HStack{
                    Text(viewModel.detailResult.accountNickname)
                        .font(.title3.bold())

                    Spacer()

                    Text(viewModel.detailResult.updatedAt)
                        .font(.caption2)
                }

                Text(viewModel.detailResult.title)
                    .font(.title2.bold())

                Text(viewModel.detailResult.contents)
                    .font(.subheadline)


                HStack{

                    Spacer()

                    Image(systemName: "message")

                    Text(String(viewModel.detailResult.reportCount))
                        .font(.caption2)
                }

                Divider()
                    .frame(height: 2)

                VStack{
                    ForEach(ripples){ ripple in
                        RippleCardView(viewModel: viewModel, rippleResult: ripple)

                    }
                }
                .onAppear {
                    DispatchQueue.main.async {
                        viewModel.searchRipple(postId) { (ripples) in
                            self.ripples = ripples
                            print(ripples.count)
                        }
                    }
                }

                Spacer()

            }
            .navigationBarHidden(true)
            .padding()
            .onAppear{
                viewModel.detailPosting(postId)
            }
        }){
            // Refresh
           
            await Task.sleep(2_000_000_000)
            
            DispatchQueue.main.async {
                
                viewModel.detailPosting(postId)
                
                viewModel.searchRipple(postId) { (ripples) in
                    self.ripples = ripples
                    print(ripples.count)
                }
            }
        }
        .onTapGesture {
            isFocused = false
        }
        .navigationBarHidden(true)
    
        VStack{
            HStack{
                TextField("댓글 달기...", text: $viewModel.rippleContents)
                    .focused($isFocused)
           
                Button(action: {
                    viewModel.createRipple(postId)
                }, label: {
                    Image(systemName: "paperplane")
                })
            }
            .frame(height: 5)
            .padding()
            .overlay(RoundedRectangle(cornerRadius: 15).stroke(Color.gray, lineWidth: 1)) // 이미지를 테두리 안에 넣기
        }
        .padding([.bottom, .leading, .trailing], 15)
        
    }
}

struct RippleCardView : View {
    
    @ObservedObject var viewModel : PostViewModel
    var rippleResult : RippleResult
    
    var body : some View {
        VStack(alignment: .leading, spacing: 20){

            HStack {
                Text(rippleResult.accountNickName)
                    .font(.subheadline.bold())
                
                Button(action: {
                    
                    viewModel.deleteRipple(rippleResult.rippleId)
                    
                }, label: {
                    
                    Image(systemName: "ellipsis")
                    
                })
            }

            Text(rippleResult.contents)
                .font(.subheadline)
            
            

            Divider()
                .frame(height: 2)

        }
    }
}

struct DetailPageView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

// 제스쳐로 뒤로 돌아가기
extension UINavigationController: ObservableObject, UIGestureRecognizerDelegate {
    override open func viewDidLoad() {
        super.viewDidLoad()
        interactivePopGestureRecognizer?.delegate = self
    }

    public func gestureRecognizerShouldBegin(_ gestureRecognizer: UIGestureRecognizer) -> Bool {
        return viewControllers.count > 1
    }
}
