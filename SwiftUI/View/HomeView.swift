//
//  HomeView.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/02/26.
//

import SwiftUI

struct HomeView: View {
    var body: some View {
        NavigationView {
            ScrollView{
                Text("안녕")
                    .navigationTitle("SOC")
            }
        }
    }
}

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        HomeView()
    }
}
