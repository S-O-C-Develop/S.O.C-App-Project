//
//  NicknameView.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/03/16.
//

import SwiftUI

struct NicknameView: View {
    
    @StateObject var viewModel : SignUpViewModel
    
    @Environment(\.presentationMode) var presentationMode
    
    @Binding var EmailView : Bool
    
    var body: some View {
        VStack{
            VStack(alignment: .leading){
                Text("닉네임을 입력하세요.")
                    .font(.title)
                    .fontWeight(.bold)
                    .padding(.bottom)
                    
                LabelTextField(label: "", placeHolder: $viewModel.username)
                
                Text("* 닉네임은 3~10자리 까지 가능합니다.")
                    .font(.system(size: 13))
                    .font(.headline)
                    .foregroundColor(.gray)
                    .padding(.bottom, 20)
                
                Text(viewModel.inlineErrorForNickname)
                        .foregroundColor(.red)
            }
            .padding(.horizontal, 30)

            Text(viewModel.studentId)
            Text(viewModel.useremail)
            
            Spacer()
            
            Button(action: {
                viewModel.signUp()
                self.EmailView = false
            }){
                Text("완 료")
                    .fontWeight(.bold)
                    .frame(width: 340 ,height: 55)
                    .background(Color.blue)
                    .foregroundColor(.white)
                    .cornerRadius(10)
            }
//            Button(action: {
//                viewModel.signUp()
//                self.EmailView = false
//            }) {
//                NavigationLink(destination: LoginView()
//
//                ){
//                    Text("완 료")
//                        .fontWeight(.bold)
//                        .frame(width: 340 ,height: 55)
//                        .background(Color.blue)
//                        .foregroundColor(.white)
//                        .cornerRadius(10)
//                }
//                .disabled(!viewModel.isNicknameValid)
//            }
            
            
            
//            NavigationLink(
//                destination: LoginView(),
//                label: {
//                    Text("완 료")
//                        .fontWeight(.bold)
//                        .frame(width: 340 ,height: 55)
//                        .background(Color.blue)
//                        .foregroundColor(.white)
//                        .cornerRadius(10)
//                })
//                .padding()
//                .navigationBarHidden(false)
//                .navigationBarBackButtonHidden(true)
//                .disabled(!viewModel.isNicknameValid)
        }
    }
}

//struct NicknameView_Previews: PreviewProvider {
//    static var previews: some View {
//        NicknameView()
//    }
//}

