//
//  Community_board.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/07/10.
//

import SwiftUI

struct CommunityBoardView: View {
    
    @State var posts: [PostContent] = []
    @ObservedObject var viewModel = PostViewModel()
    
    @State var showModal = false
    
//    var posting : PostContent
    
    var body: some View {
        
        NavigationView{
            ScrollView(.vertical, showsIndicators: false) {
                LazyVStack{
                    ForEach(posts){ post in
                        
                        NavigationLink(destination: TestDetailView(posting: self.viewModel.detailResult)){
                            TestCardView(posting: post)
                                .accentColor(.black)
                        }
                        .simultaneousGesture(TapGesture().onEnded{
                            
                            viewModel.detailPosting(post.postId)
//                            self.show.toggle()
                        })
                        
//                        .onAppear{
//                            viewModel.detailPosting(post.postId)
//                        }
                        
                        Divider()
                    }
                }
                .padding()
            }
            .navigationBarHidden(true)
            .onAppear {
                DispatchQueue.main.async {
                    viewModel.searchPosting(19) { (posts) in
                        self.posts = posts
                        print(posts.count)
                    }
                }
            }
        
        }
        
        
        
        
        
//        NavigationView{
//            List(0..<viewModel.data.count, id: \.self) { i in
//
//                if i == self.viewModel.data.count - 1 {
//                    PostingCardView(posting: self.viewModel.data[i], isLast: true, viewModel: self.viewModel)
//                        .onTapGesture(perform: {
//                            showModal = true
//                            viewModel.detailPosting(viewModel.data[i].postId)
//
//                        })
//                        .fullScreenCover(isPresented: $showModal, content: {
//                            DetailPageView(posting: self.viewModel.detailResult)
//                        })
//                }
//                else {
//                    PostingCardView(posting: self.viewModel.data[i], isLast: false, viewModel: self.viewModel)
//                        .onTapGesture(perform: {
//                            showModal = true
//                            viewModel.detailPosting(viewModel.data[i].postId)
//                        })
//                        .fullScreenCover(isPresented: $showModal, content: {
//                            DetailPageView(posting: self.viewModel.detailResult)
//                        })
//                }
//            }
//            .navigationBarHidden(true)
//            .onAppear{
//                viewModel.updateData(19)
//            }
//
//        }
    }
}

struct CommunityBoardView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
