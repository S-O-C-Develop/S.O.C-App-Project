//
//  BottomTabView.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/03/03.
//

import SwiftUI

struct BottomTabView: View {
    
    init() { // 하단바 배경색 변경
        UITabBar.appearance().barTintColor = UIColor(Color(.white))
    }
    
    private enum Tabs {
        case Home, Board, S_Study, Notice, Setting
    }
    
    @State private var selectedTab : Tabs  = .Home
    
    var body: some View {
        ZStack {
            TabView(selection: $selectedTab) {
                Group{
                    HomeUI
                    BoardUI
                    S_StudyUI
                    NoticeUI
                    SettingUI
                }
            }
            .accentColor(.black)
        }
    }
}

struct BottomTabView_Previews: PreviewProvider {
    static var previews: some View {
        BottomTabView()
    }
}

private extension BottomTabView{
    var HomeUI: some View {
        HomeView()
            .tag(Tabs.Board)
            .tabItem { Image(systemName: "house").imageScale(.large)}
            .navigationBarHidden(false)
        
    }
}

private extension BottomTabView{
    var BoardUI: some View{
        Community_board()
            .tag(Tabs.Board)
            .tabItem { Image(systemName: "doc").imageScale(.large)}
            .navigationBarHidden(false)
    }
}

private extension BottomTabView{
    var S_StudyUI: some View{
        Text("스터디 뷰 입니다")
            .tag(Tabs.Board)
            .tabItem { Image(systemName: "book").imageScale(.large)}
            .navigationBarHidden(false)
    }
}

private extension BottomTabView{
    var NoticeUI: some View{
        Text("알림 뷰 입니다")
            .tag(Tabs.Board)
            .tabItem { Image(systemName: "lightbulb").imageScale(.large)}
            .navigationBarHidden(false)
    }
}

private extension BottomTabView{
    var SettingUI: some View{
        Text("개인화면 뷰 입니다")
            .tag(Tabs.Board)
            .tabItem { Image(systemName: "person").imageScale(.large)}
            .navigationBarHidden(false)
    }
}
