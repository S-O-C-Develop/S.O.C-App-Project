//
//  NavigationBarView.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/09/26.
//

import SwiftUI

struct DetailTopBarView: View {
    
    @ObservedObject var viewModel : PostViewModel
    
    var postAccountId : Int
    var kategorie_1 : String
    var kategorie_2 : String
    
    var boradId : Int? = nil
    
    let userAccountId : String = UserDefaults.standard.string(forKey: "userID")!
    
//    @State private var isShowingDialog = false

   
    @Environment(\.presentationMode) private var presentationMode
    
    
    @State var showActionSheet: Bool = false
    @State var actionSheetOption: ActionSheetOption = .isOtherPost
    
    enum ActionSheetOption {
        case isMyPost
        case isOtherPost
    }
    
    var body: some View {
        HStack {
            
            Button(action : {
                       
                presentationMode.wrappedValue.dismiss()
                       
            }) {
                Image(systemName: "chevron.left")
            }
            
            Spacer()
            
            VStack {
                
                Text(kategorie_1)
                    .font(.subheadline.bold())
                
                Text(kategorie_2)
                    .font(.caption)
                
            }
            
            Spacer()
            
            if kategorie_1 == "글쓰기" {
                Button(action: {

                    viewModel.createPosting(boradId!)

                    DispatchQueue.main.asyncAfter(deadline: .now() + 4){
                        if viewModel.posting {
                            presentationMode.wrappedValue.dismiss()
                        }
                    }

                }) {
                        Text("올리기")
                            .font(.subheadline)
                }
            } else {
                Button(action : {
                    
                    if postAccountId == Int(userAccountId) {
                        actionSheetOption = .isMyPost
                        
                    } else {
                        actionSheetOption = .isOtherPost
                    }
                    showActionSheet.toggle()
                    
                }) {
                    Image(systemName: "ellipsis")
                }
                .actionSheet(isPresented: $showActionSheet, content: getActionSheet)
            }
        }
        .padding()
    }
    func getActionSheet() -> ActionSheet {
        
        let title = Text("게시글 메뉴")
        let modifyButton: ActionSheet.Button = .destructive(Text("수정하기"), action: {})
        let reportButton: ActionSheet.Button = .destructive(Text("신고하기"), action: {})
        let deleteButton: ActionSheet.Button = .destructive(Text("게시물 삭제하기"), action: {})
        let cancleButton: ActionSheet.Button = .cancel(Text("취소"))
        
        switch actionSheetOption {
        case .isMyPost:
            return ActionSheet(title: title, message: nil, buttons: [modifyButton, deleteButton, reportButton, cancleButton])
            
        case .isOtherPost:
            return ActionSheet(title: title, message: nil, buttons: [reportButton, cancleButton])
        }
    }
}


//struct NavigationBarView_Previews: PreviewProvider {
//    static var previews: some View {
//        NavigationBarView()
//    }
//}
