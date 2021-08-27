//
//  Community_board.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/07/10.
//

import SwiftUI

struct CommunityBoardView: View {
    
    // sample data
    @State var arryData = [" Hello Data 1", "Hello Data 2"]
    @State var refresh = Refresh(started: false, released: false)
    
    @State var posts: [PostContent] = []
    @Namespace var animationID
    @State var currentTab = "문제"
    @State var show = false
    
    @ObservedObject var viewModel = PostViewModel()
    
    var body: some View {
        
        ZStack(alignment: .bottomTrailing){
            
            NavigationView{
                VStack(){
                    TopBarView(title: "커뮤니티")
                    
                    Divider()
                    
                    ScrollView(.vertical, showsIndicators: false) {
                        
                        GeometryReader{reader -> AnyView in
                            
//                            print(reader.frame(in: .global).minY) Y 방향의 크기를 구하는 방식
                            
                            DispatchQueue.main.async {
                                if refresh.startOffset == 0 {
                                    refresh.startOffset = reader.frame(in: .global).minY
                                }
                                
                                refresh.offset = reader.frame(in: .global).minY
                                
                                if refresh.offset - refresh.startOffset > 80 && !refresh.started {
                                    refresh.started = true
                                }
                                
                                if refresh.startOffset == refresh.offset && refresh.started && !refresh.released{
                                    
                                    refresh.released = true
                                    
                                    viewModel.searchPosting() { (posts) in
                                        self.posts = posts
                                    }
                                }
                            }
                            
                            return AnyView(Color.black.frame(width:0, height: 0))
                        }
                        .frame(width:0, height: 0)
                        
                        ZStack(alignment: Alignment(horizontal: .center, vertical: .top)){
                            
                            Image(systemName: "arrow.down")
                                .font(.system(size: 16, weight: .heavy))
                                .foregroundColor(.gray)
                                .rotationEffect(.init(degrees: refresh.started ? 180 : 0))
                                .offset(y: -25)
                                .animation(.easeIn)
                            
                            LazyVStack{
                                ForEach(posts){ post in
                                    NavigationLink(destination: PasswordFindView()){
                                        PostingCardView(posting: post)
                                            .accentColor(.black)
                                            .onAppear()
                                    }
                                    Divider()
                                }
                            }
                            .padding()
                        }
                        .offset(y: refresh.released ? 40 : -10 )
                        
                    }
                    .navigationBarHidden(true)
                    .onAppear {
                        viewModel.searchPosting() { (posts) in
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

struct CommunityBoardView_Previews: PreviewProvider {
    static var previews: some View {
        CommunityBoardView()
    }
}

struct Refresh {
    var startOffset : CGFloat = 0
    var offset : CGFloat = 0
    var started : Bool
    var released : Bool
}
