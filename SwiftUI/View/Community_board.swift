//
//  Community_board.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/07/10.
//

import SwiftUI

struct Community_board: View {
    
    
    @Namespace var animationID
    @State var currentTab = "문제"
    @State var show = false
    
    @StateObject var viewModel = PostViewModel()
    
    var body: some View {
        
        ZStack(alignment: .bottomTrailing) {
            NavigationView{
                VStack{
                    TopBarView(title: "커뮤니티")

                    ScrollView(.vertical, showsIndicators: false) {
                        LazyVStack{
                            ForEach(viewModel.postings){ posting in
                                // posting Card View...
                                NavigationLink(destination: PasswordFindView()){
                                    PostingCardView(posting: posting)
                                        .accentColor(.black)
                                }
                                .padding()

                                Divider()
                            
                            }
                        }
                        .padding()
                        .navigationBarHidden(true)
                        
                    }
                }
            }
            PopOverMenuView()
                .padding()
           
        }.background(Color.black.opacity(0.1).edgesIgnoringSafeArea(.all))
    }
}

struct Community_board_Previews: PreviewProvider {
    static var previews: some View {
        Community_board()
    }
}
