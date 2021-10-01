//
//  TestBoardView.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/09/29.
//

import SwiftUI

struct TestBoardView: View {
    
    @State var posts: [PostContent] = []
    @ObservedObject var viewModel = PostViewModel()
    
    var postId : Int
    
    var body: some View {
        if #available(iOS 15.0, *) {
            List{
                ForEach(posts){ post in
                    NavigationLink(destination: TestDetailView(postId: post.postId)){
                        PostingCardView(posting: post)
                            .accentColor(.black)
                    }
                    .listRowSeparatorTint(.clear) // list 줄 없애기
                    .listRowInsets(EdgeInsets(top: 0, leading: 10, bottom: 0, trailing: -8)) // list 상하좌우 padding 적용
                }
                .listRowBackground(Color.clear)
                
                
            }
            .refreshable {
                DispatchQueue.main.asyncAfter(deadline: .now() + 1){
                    viewModel.searchPosting(postId) { (posts) in
                        self.posts = posts
                        print(posts.count)
                    }
                }
            }
            .listStyle(.plain)
            .navigationTitle("")
            .navigationBarHidden(true)
            .onAppear {
                DispatchQueue.main.async {
                    viewModel.searchPosting(postId) { (posts) in
                        self.posts = posts
                        print(posts.count)
                    }
                }
            }
        } else {
            // Fallback on earlier versions
        }
    }
}

struct TestBoardView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}


// TEST

//ScrollView(.vertical) {
//    LazyVStack{
//        ForEach(posts){ post in
//
//            NavigationLink(destination: DetailPageView(posting: self.viewModel.detailResult, kategorie_1: viewModel.detailKategorie_1, kategorie_2: viewModel.detailKategorie_2)){
//                PostingCardView(posting: post)
//                    .accentColor(.black)
//            }
//            .simultaneousGesture(TapGesture().onEnded{
//
//                viewModel.detailPosting(post.postId)
//
//            })
//
//            Divider()
//        }
//    }
//    .padding()
//}
//.navigationTitle("")
//.navigationBarHidden(true)
//.onAppear {
//    DispatchQueue.main.async {
//        viewModel.searchPosting(postId) { (posts) in
//            self.posts = posts
//            print(posts.count)
//        }
//    }
//}
