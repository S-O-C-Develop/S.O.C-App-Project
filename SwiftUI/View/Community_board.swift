//
//  Community_board.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/07/10.
//

import SwiftUI

struct Community_board: View {
    
    @State var posts: [PostContent] = []
    @Namespace var animationID
    @State var currentTab = "문제"
    @State var show = false
    
    @ObservedObject var viewModel = PostViewModel()
    
    var body: some View {
        
        ZStack(alignment: .bottomTrailing){
            
            NavigationView{
                VStack{
                    TopBarView(title: "커뮤니티")
                    
                    ScrollView(.vertical, showsIndicators: false) {
                        LazyVStack{
                            ForEach(posts){ post in
                                NavigationLink(destination: PasswordFindView()){
                                    PostingCardView(posting: post)
                                        .accentColor(.black)
                                }
                                Divider()
                            }
                        }
                        .padding()
                    }
                    .navigationBarHidden(true)
                    .onAppear {
                        viewModel.boardsPosting() { (posts) in
                            self.posts = posts
                        }
                    }
                }
            }
            FloatingButtonView()
                .padding()
        }
    }
}

struct Community_board_Previews: PreviewProvider {
    static var previews: some View {
        Community_board()
    }
}
