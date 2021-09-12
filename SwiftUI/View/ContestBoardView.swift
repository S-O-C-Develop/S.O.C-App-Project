//
//  ContestBoardView.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/08/21.
//

import SwiftUI

struct ContestBoardView: View {
    
    @ObservedObject var viewModel = PostViewModel()
    @State var showModal = false

    
    var body: some View {
        GeometryReader{_ in
            NavigationView{
                List(0..<viewModel.data.count, id: \.self) { i in

                    if i == self.viewModel.data.count - 1 {
                        PostingCardView(posting: self.viewModel.data[i], isLast: true, viewModel: self.viewModel)
                            .onTapGesture(perform: {
                                showModal = true
                            })
                            .fullScreenCover(isPresented: $showModal, content: {
                                DetailPageView(posting: self.viewModel.detailResult)
                            })
                    }
                    else {
                        PostingCardView(posting: self.viewModel.data[i], isLast: false, viewModel: self.viewModel)
                            .onTapGesture(perform: {
                                showModal = true
                            })
                            .fullScreenCover(isPresented: $showModal, content: {
                                DetailPageView(posting: self.viewModel.detailResult)
                            })
                    }
                }
    //                .listStyle(DefaultListStyle())
                .navigationBarHidden(true)
            }
        }
        .background(Color.white)
    }
}

struct ContestBoardView_Previews: PreviewProvider {
    static var previews: some View {
        ContestBoardView()
    }
}
