//
//  TopBarView.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/07/16.
//

import SwiftUI

struct TopBarView: View {
    
    @State var title: String
    @Namespace var animationID
    @State var currentTab = "문제" // 하단 탭 기준 설정
    
    @StateObject var viewModel = PostViewModel()
    
    var body: some View {
        VStack{
            HStack(alignment: .bottom){
                Text("SOC - " + title) // 게시판 이름
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
            
            // 상단 탭 출력
            HStack(spacing: 0){
                ForEach(["문제", "정보"], id: \.self){tab in
                    Board_TabButton(currentTab: $currentTab, title: tab, animationID: animationID)
                }
            }
            .padding(.top, 17)
        }
        .padding(.top, 25)
    }
}

struct TopBarView_Previews: PreviewProvider {
    static var previews: some View {
        TopBarView(title: "")
    }
}
