//
//  FeedView.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/02/26.
//

import SwiftUI

struct FeedView: View {
    var body: some View {
        NavigationView {
            ScrollView{
                Text("안녕")
                    .navigationBarTitle("SOC - 커뮤니티")
            }
        }
    }
}

struct FeedView_Previews: PreviewProvider {
    static var previews: some View {
        FeedView()
    }
}
