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
        VStack{
            if !logStatus {
                LoginView(viewModel: LoginViewModel())
            }
            else {
                HomeView()
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
