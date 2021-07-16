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
    
    @StateObject var viewModel = PostViewModel()
    
    var body: some View {
        
        VStack{
            TopBarView(title: "커뮤니티")
            
            ScrollView(.vertical, showsIndicators: false) {
                
                
                VStack(spacing: 15) {
                    
                    ForEach(viewModel.postings){ posting in
                        // posting Card View...
                        PostingCardView(posting: posting)
                    }
                }
                .padding()
            }
        }
    }
}

struct Community_board_Previews: PreviewProvider {
    static var previews: some View {
        Community_board()
    }
}
