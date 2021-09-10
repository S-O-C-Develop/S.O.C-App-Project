//
//  LoginView.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/02/19.
//
import SwiftUI

struct LoginView: View {
    
    @State var studentId = ""
    @State var password = ""
    @State var isActive : Bool = false
    
    @ObservedObject var viewModel : LoginViewModel
//    @EnvironmentObject private var viewModel : LoginViewModel
    
    var body: some View {
        NavigationView() {
            VStack {
                SocImage()
                UseremailTextField(studentId: $viewModel.LoginId)
                PasswordSecureField(password: $viewModel.LoginPassword)
                
//                UseremailTextField(studentId: self.$studentId)
//                PasswordSecureField(password: self.$password)
                
//                LoginToken. LoginPost
                Button(action: viewModel.LoginPost, label: {
                    Text("로그인")
                        .fontWeight(.bold)
                        .frame(width:264, height: 40)
                        .background(Color.blue)
                        .foregroundColor(.white)
                        .cornerRadius(10)
                })
                .alert(isPresented: $viewModel.error, content: {
                    Alert(title: Text(viewModel.errorMsg))
                })
                .overlay(
                    ZStack{
                        if viewModel.isLoading{ LoadingScreen() }
                    }
                )
                .environmentObject(viewModel)
                
                HStack{
                    LinkSingUpView(isActive: $isActive)
                    LinkPasswordFindView()
                }
                .padding(.top, 20)
            }
            .padding(.bottom, 140)
        }
    }
}

struct LoginView_Previews: PreviewProvider {
    static var previews: some View {
        LoginView(viewModel: LoginViewModel() )
    }
}

struct SocImage: View {
    var body: some View {
        Image("SOCICON")
            .resizable()
            .frame(width:210, height: 210)
            .padding(.bottom, 30)
    }
}


struct UseremailTextField: View{
    
    @Binding var studentId: String
    
    var body: some View {
        TextField("StudentID", text: $studentId)
            .padding()
            .frame(width:264, height: 40)
            .background(Color("LightGray"))
            .cornerRadius(10)
            .padding(.bottom, 15)
            .keyboardType(.emailAddress)
    }
}

struct PasswordSecureField: View {
    
    @Binding var password: String
    
    var body: some View {
        SecureField("Password", text:$password)
            .padding()
            .frame(width:264, height: 40)
            .background(Color("LightGray"))
            .cornerRadius(10)
            .padding(.bottom, 15)
    }
}
//StudentIdView(LoginViewIsActive: $isActive),isActive: self.$isActive

struct LinkSingUpView: View {
    
    @Binding var isActive : Bool
    
    var body: some View {
        NavigationLink(destination:StudentIdView(LoginViewIsActive: $isActive),isActive: self.$isActive){
            Text("회원가입")
                .font(.headline)
                .foregroundColor(.black)
        }.isDetailLink(false)
         .padding(.trailing, 30)
        
    }
}

struct LinkPasswordFindView: View {
    var body: some View {
        NavigationLink(destination: PasswordFindView()){
            Text("비밀번호 찾기")
                .font(.headline)
                .foregroundColor(.black)
        }
    }
}
//
//struct LoginButton: View {
//    var body: some View {
//        NavigationLink(destination: HomeView(), isActive:
//                        .navigationBarHidden(false)
//                        .navigationBarBackButtonHidden(true)
//        ){
//            Text("로그인")
//                .fontWeight(.bold)
//                .frame(width:264, height: 40)
//                .background(Color.blue)
//                .foregroundColor(.white)
//                .cornerRadius(10)
//        }
//        Text("로그인")
//            .frame(width:264, height: 40)
//            .font(.headline)
//            .foregroundColor(.white)
//            .background(Color.blue)
//            .cornerRadius(5)
//    }
//}
