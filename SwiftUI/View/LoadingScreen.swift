//
//  LoadingScreen.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/03/27.
//

import SwiftUI

struct LoadingScreen: View {
    var body: some View {
        ZStack{
            
            Color.primary
                .opacity(0.2)
                .ignoresSafeArea()
            
            ProgressView()
                .frame(width: 50, height: 50)
                .cornerRadius(8)
            
        }
    }
}

struct LoadingScreen_Previews: PreviewProvider {
    static var previews: some View {
        LoadingScreen()
    }
}
