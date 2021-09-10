//
//  ContentView.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/02/19.
//

import SwiftUI

struct ContentView: View {
    
    @StateObject var viewModel = LoginViewModel()
    @AppStorage("log_Status") var logStatus = false
    
    var body: some View {
//        Testbar()
        CNCBoardView()
//        TestList()
////        BottomTabView()
////        Community_board()
//        VStack{
//            if !logStatus {
////                Community_board()
//                LoginView(viewModel: LoginViewModel())
//            }
//            else {
//                BottomTabView()
//            }
//        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
