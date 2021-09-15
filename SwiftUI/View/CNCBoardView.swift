//
//  CNCBoardView.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/09/04.
//

import SwiftUI

struct CNCBoardView: View {

    @ObservedObject var viewModel = PostViewModel()
    @State var number = ""
    @State var selected = 1
    @State var offset : CGFloat = UIScreen.main.bounds.width
    var width = UIScreen.main.bounds.width

    var body: some View {
        ZStack{
            VStack(spacing: 0){
                CommunityTabBarView(selected: $selected, offset: self.$offset)
                    .padding(.top, 8)

                GeometryReader{g in

                    
                    HStack(spacing: 0){

                        CommunityBoardView()
                            .frame(width: g.frame(in: .global).width)
                            .onAppear{
                                viewModel.updateData(19)
                            }

                        ContestBoardView()
                            .frame(width: g.frame(in: .global).width)
                            .onAppear{
                                viewModel.updateData(22)
                            }
                        
                    }
                    .offset(x: self.offset)
                    
                    VStack{
                        
                        Spacer()
                        HStack{
                            Spacer()
                            
                            FloatingButtonView()
                                .padding(.bottom, 100)
                                .padding(.trailing, 20)
                                .shadow(color: Color.black.opacity(0.3),
                                        radius: 3,
                                        x: 3,
                                        y: 3)
                        }
                    }
                    
                    
                }
                
            }
        }
        .edgesIgnoringSafeArea(.all)
    }
}

struct CNCBoardView_Previews: PreviewProvider {
    static var previews: some View {
        CNCBoardView()
    }
}
