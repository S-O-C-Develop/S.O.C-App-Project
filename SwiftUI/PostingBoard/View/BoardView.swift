//
//  BoardView.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/10/14.
//

import SwiftUI

struct BoardView: View {
    
    @State var posts: [PostContent] = []
    @ObservedObject var viewModel = PostViewModel()
    
    var boardId : Int
    
    var body: some View {
        ZStack(alignment: .bottomTrailing){
            VStack{
                List{
                    ForEach(posts){ post in
                        NavigationLink(destination: DetailPageView(postId: post.postId)){
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
                        viewModel.searchPosting(boardId) { (posts) in
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
                        viewModel.searchPosting(boardId) { (posts) in
                            self.posts = posts
                            print(posts.count)
                        }
                    }
                }
            }
            
            FloatingButtonView(boardId : boardId)
                .padding(.trailing, 20)
                .shadow(color: Color.black.opacity(0.3),
                        radius: 3,
                        x: 3,
                        y: 3)
        }
    }
}

struct BoardView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
