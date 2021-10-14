//
//  CNCBoardView.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/09/04.
//

import SwiftUI

struct CNCBoardView: View {
    @State var selectedPage = 1
    
    var bottomMenu : Int
    
    var body: some View {
        NavigationView{
            VStack{
                TopBarView(selectedPage: $selectedPage, bottomMenu: bottomMenu)
                    .padding()
                
                TopMenuView(pageNum: selectedPage, bottomMenu: bottomMenu)
                
                Spacer()
            }
        }
    }
}

struct CNCBoardView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
