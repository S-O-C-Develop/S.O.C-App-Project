//
//  BottomTabView.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/03/03.
//

import SwiftUI

struct BottomTabView: View {
    var body: some View {
        TabView {
            HomeView()
                .tabItem {
                    Text("Home")
                }
            FeedView()
                .tabItem {
                    Text("Feed")
                }
            Text("세번째 화면")
                .tabItem {
                    Text("Setting")
                }
        }
    }
}

struct BottomTabView_Previews: PreviewProvider {
    static var previews: some View {
        BottomTabView()
    }
}
