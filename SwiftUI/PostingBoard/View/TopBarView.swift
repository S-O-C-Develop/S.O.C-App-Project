//
//  TobBarView.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/09/05.
//

import SwiftUI

struct TopBarView: View {
    
    @ObservedObject var viewModel = PostViewModel()
    
    @Binding var selectedPage : Int
    @State var pageName = "커뮤니티"
    
    var bottomMenu : Int
    
    var body: some View {
        if bottomMenu == 1 {
            HStack{
                Text("SOC - \(pageName)") // 게시판 이름
                    .font(.title2.bold())
                    .foregroundColor(.black)
                    .frame(maxWidth: .infinity, alignment: .leading)
                
                Spacer()

                Menu {
                    
                    Button("커뮤니티", action: {
                        self.pageName = "커뮤니티"
                        self.selectedPage = 1
                        viewModel.detailKategorie_1 = "커뮤니티"
                    })
                    
                    Button("공지사항", action: {
                        self.pageName = "공지사항"
                        self.selectedPage = 2
                        viewModel.detailKategorie_1 = "공지사항"
                    })
                    
                    Button("공모전", action: {
                        self.pageName = "공모전"
                        self.selectedPage = 3
                        viewModel.detailKategorie_1 = "공모전"
                    })
                    
                    
                } label: {
                    Image(systemName: "chevron.down")
                }
            }
        } else {
            HStack{
                Text("SOC - Study") // 게시판 이름
                    .font(.title2.bold())
                    .foregroundColor(.black)
                    .frame(maxWidth: .infinity, alignment: .leading)
                
                Spacer()
            }
        }
        
    }
}

