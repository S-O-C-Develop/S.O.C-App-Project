//
//  Community_board.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/07/10.
//

import SwiftUI

struct CommunityBoardView: View {
    
    @ObservedObject var viewModel = PostViewModel()
    
    @State var showModal = false
    
    var body: some View {
        
        NavigationView{
            List(0..<viewModel.data.count, id: \.self) { i in

                if i == self.viewModel.data.count - 1 {
                    PostingCardView(posting: self.viewModel.data[i], isLast: true, viewModel: self.viewModel)
                        .onTapGesture(perform: {
                            showModal = true
                            viewModel.detailPosting(viewModel.data[i].postId)
                            
                        })
                        .fullScreenCover(isPresented: $showModal, content: {
                            DetailPageView(posting: self.viewModel.detailResult)
                        })
                }
                else {
                    PostingCardView(posting: self.viewModel.data[i], isLast: false, viewModel: self.viewModel)
                        .onTapGesture(perform: {
                            showModal = true
                            viewModel.detailPosting(viewModel.data[i].postId)
                        })
                        .fullScreenCover(isPresented: $showModal, content: {
                            DetailPageView(posting: self.viewModel.detailResult)
                        })
                }
            }
            .navigationBarHidden(true)
            
        }
    }
}

struct CommunityBoardView_Previews: PreviewProvider {
    static var previews: some View {
        CommunityBoardView()
    }
}
