//
//  FloatingButtonView.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/08/06.
//

import SwiftUI

struct FloatingButtonView: View {

    @ObservedObject var viewModel = PostViewModel()
    
    @State var show = false
    @State private var showingPopover = false
    @State var writePage = false
    
    var boardId : Int
    var body: some View {
        VStack(spacing : 20) {

            if self.show{
                Button(action: {

                    writePage = true
                    
                }) {

                    Image(systemName: "pencil")
                        .resizable()
                        .frame(width: 25, height: 25)
                        .padding()
                        .background(Color.blue)
                        .foregroundColor(.white)
                        .clipShape(Circle())
                }
                .fullScreenCover(isPresented: $writePage, content: {
                    WritePageView(boardId : boardId)
                })


                Button(action: {

                    showingPopover = true

                }) {

                    Image(systemName: "magnifyingglass")
                        .resizable()
                        .frame(width: 25, height: 25)
                        .padding()
                        .background(Color.blue)
                        .foregroundColor(.white)
                        .clipShape(Circle())

                }.popover(isPresented: $showingPopover) {
                    ConditionSearchView(viewModel: viewModel, selectedView: 2)
                }
            }

            if boardId == 1{
                Button(action: {
                    
                    self.show.toggle()

                }) {

                    Image(systemName: self.show ? "xmark" : "plus")
                        .resizable()
                        .frame(width: 21, height: 22)
                        .padding()
                        .foregroundColor(.white)
                }
                .background(Color.blue)
                .foregroundColor(.white)
                .clipShape(Circle())
//                .rotationEffect(.init(degrees: self.show ? 180 : 0)) // 이미지 180도 변환
            } else {
                Button(action: {
                    
                    writePage = true

                }) {

                    Image(systemName: "pencil")
                        .resizable()
                        .frame(width: 21, height: 22)
                        .padding()
                        .background(Color.blue)
                        .foregroundColor(.white)
                        .clipShape(Circle())
                }
                .background(Color.blue)
                .foregroundColor(.white)
                .clipShape(Circle())
                .fullScreenCover(isPresented: $writePage, content: {
                    WritePageView(boardId : boardId)
                })
    //            .rotationEffect(.init(degrees: self.show ? 180 : 0)) // 이미지 180도 변환
            }
            
        }
        .animation(.spring())
    }
}
