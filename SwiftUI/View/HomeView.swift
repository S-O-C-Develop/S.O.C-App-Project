//
//  HomeView.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/02/26.
//

import SwiftUI

struct HomeView: View {
    var body: some View {
        VStack{
            HStack(alignment: .bottom){
                Text("SOC")
                    .font(.title.bold())
                    .frame(maxWidth: .infinity, alignment: .leading)
                
                Button {
                    
                } label: { // 게시판 종류 바꾸는 버튼
                    Text("ADD")
                        .foregroundColor(.black)
                        .padding(.vertical, 10)
                        .padding(.horizontal, 20)
                        .background(
                            Capsule()
                                .stroke(Color.black,lineWidth: 1)
                        )
                }
            }
            .padding(.horizontal, 20)
        }
    }
}

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        HomeView()
    }
}

