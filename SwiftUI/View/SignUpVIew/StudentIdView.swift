//
//  studentIdView.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/03/17.
//

import SwiftUI

struct StudentIdView: View {
    
    @StateObject var viewModel : SignUpViewModel = SignUpViewModel()
    
    @Environment(\.presentationMode) var presentationMode
    
    @Binding var LoginViewIsActive : Bool
    
    var body: some View {
        VStack{
            VStack(alignment: .leading){
                Text("학번을 입력하세요.")
                    .font(.title)
                    .fontWeight(.bold)
                    .padding(.bottom)
                
                LabelTextField(label: "", placeHolder: $viewModel.studentId)
                
                Text("* 학번 형식에 맞는 9자리만 입력하세요.")
                    .font(.system(size: 13))
                    .font(.headline)
                    .foregroundColor(.gray)
                    .padding(.bottom, 20)
                
                Text(viewModel.inlineErrorForStudentId)
                        .foregroundColor(.red)
            }
            .padding(.horizontal, 30)

            Spacer()
            
            NavigationLink(
                destination: PasswordView(viewModel: viewModel, StudentIdView: self.$LoginViewIsActive),
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
                .disabled(!viewModel.isStudentValid)
        }
    }
}

//struct StudentIdView_Previews: PreviewProvider {
//    static var previews: some View {
//        StudentIdView()
//    }
//}

