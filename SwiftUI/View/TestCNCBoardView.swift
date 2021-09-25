//
//  TestCNCBoardView.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/09/20.
//

import SwiftUI

struct TestCNCBoardView: View {
    
    @ObservedObject var viewModel = PostViewModel()
    @State var number = ""
    @State var selected = 1
    @State var offset : CGFloat = UIScreen.main.bounds.width
    @State var show = false
    
    var body: some View {
//        NavigationView {
            ZStack{
                VStack(spacing: 0) {
                    
                    if !(show){
                        CommunityTabBarView(selected: $selected, offset: self.$offset)
                            .padding(.top, 8)
                    }
                   
                    if selected == 1 {
                        TestBoard()
                            
                    } else {
                        TestBoard2()
                           
                    }
                }
            }
            .navigationBarHidden(true)
//        }
        .edgesIgnoringSafeArea(.all)
    }
}

struct TestCNCBoardView_Previews: PreviewProvider {
    static var previews: some View {
        TestCNCBoardView()
    }
}
