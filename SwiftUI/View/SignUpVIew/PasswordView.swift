//
//  PasswordView.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/03/17.
//

import SwiftUI

struct PasswordView: View {
    
    @StateObject var viewModel : SignUpViewModel
    
    @Environment(\.presentationMode) var presentationMode
    
    @Binding var StudentIdView : Bool
    
    var body: some View {
        VStack{
            VStack(alignment: .leading){
                Text("비밀번호를 입력하세요.")
                    .font(.title)
                    .fontWeight(.bold)
                    .padding(.bottom)
                    
                PasswordLabelTextField(label: "", placeHolder: $viewModel.password)
                
                Text("* 대,소문자, 특수문자를 포함하여 8자리 이상 입력하세요.")
                    .font(.system(size: 13))
                    .font(.headline)
                    .foregroundColor(.gray)
                    .padding(.bottom, 20)
                
                PasswordLabelTextField(label: "", placeHolder: $viewModel.re_password)
                
                Text("* 비밀번호 확인을 위해 다시 입력하세요.")
                    .font(.system(size: 13))
                    .font(.headline)
                    .foregroundColor(.gray)
                    .padding(.bottom, 20)
                
                Text(viewModel.inlineErrorForPassword)
                    .foregroundColor(.red)
            }
            .padding(.horizontal, 30)

            Spacer()
            
            NavigationLink(
                destination: EmailView(viewModel: viewModel, PasswordView: self.$StudentIdView),
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
                .disabled(!viewModel.isPasswordValid)
        }
    }
}

//struct PasswordView_Previews: PreviewProvider {
//    static var previews: some View {
//        PasswordView()
//    }
//}

struct PasswordLabelTextField : View {
    
    var label: String
    @Binding var placeHolder: String
    
    var body: some View{
        VStack(alignment: .leading) {
            Text(label)
                .font(.headline)
            SecureField("", text: $placeHolder)
                .autocapitalization(.none)
            
            Rectangle()
                .fill(self.placeHolder == "" ? Color.black.opacity(0.08) : Color(.blue))
                .frame(height: 1.5)
            //Divider()
        }
        
        .padding(.bottom)
    }
}
