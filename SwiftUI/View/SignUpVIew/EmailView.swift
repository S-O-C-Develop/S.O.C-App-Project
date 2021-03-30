//
//  EmailView.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/03/18.
//

import SwiftUI

struct EmailView: View {
    
   
    @StateObject var viewModel : SignUpViewModel
    
    @Environment(\.presentationMode) var presentationMode
    
    @Binding var PasswordView : Bool
    
    var body: some View {
        VStack{
            VStack(alignment: .leading){
                Text("이메일을 입력하세요.")
                    .font(.title)
                    .fontWeight(.bold)
                    .padding(.bottom)
                
                HStack {
                    LabelTextField(label: "", placeHolder: $viewModel.useremail)
                    Text("@ catholic.ac.kr")
                }
                
                Text("* 학교 이메일을 입력해주세요.")
                    .font(.system(size: 13))
                    .font(.headline)
                    .foregroundColor(.gray)
                    .padding(.bottom, 20)
                
                Text(viewModel.inlineErrorForEmail)
                        .foregroundColor(.red)
            }
            .padding(.horizontal, 30)

            Spacer()
            
            NavigationLink(
                destination: NicknameView(viewModel: viewModel,EmailView: self.$PasswordView),
                label: {
                    Text("다 음")
                        .fontWeight(.bold)
                        .frame(width: 340 ,height: 55)
                        .background(Color.blue)
                        .foregroundColor(.white)
                        .cornerRadius(10)
                })
                .isDetailLink(false)
                .padding()
                .disabled(!viewModel.isEmailValid)
            
        }
    }
}

//struct EmailView_Previews: PreviewProvider {
//    static var previews: some View {
//        EmailView()
//    }
//}
