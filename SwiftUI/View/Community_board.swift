//
//  Community_board.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/07/10.
//

import SwiftUI

struct Community_board: View {
    
    @Namespace var animationID
    @State var currentTab = "Upcoming"
    
    @StateObject var viewModel = PostViewModel()
    
    var body: some View {
        ScrollView(.vertical, showsIndicators: false) {
            
            VStack(spacing: 15){
                HStack(alignment: .bottom){
                    Text("SOC - 커뮤니티")
                        .font(.title.bold())
                        .frame(maxWidth: .infinity, alignment: .leading)
                    
                    Button {
                        
                    } label: {
                        Text("ADD")
                            .foregroundColor(.black)
                            .padding(.vertical, 10)
                            .padding(.horizontal, 20)
                            .background(
                                Capsule()
                                    .stroke(Color.black,lineWidth: 1)
                            )
                    }
                }
                .padding(.top,8)
                
                // Custom Segment tab View...
                HStack(spacing: 0){
                    
                    // simply creating array of tabs and iterating over it...
                    
                    ForEach(["문제", "정보"], id: \.self){tab in
                        Board_TabButton(currentTab: $currentTab, title: tab, animationID: animationID)
                    }
                }
                .padding(.top, 25)
                
                VStack(spacing: 15) {
                    
                    ForEach(viewModel.postings){ posting in
                        
                        // posting Card View...
                        PostingCardView(posting: posting)
                    }
                }
                .padding(.top, 20)
            }
            .padding()
        }
//        .background(Color("Bg"))
    }
}

struct Community_board_Previews: PreviewProvider {
    static var previews: some View {
        Community_board()
    }
}
