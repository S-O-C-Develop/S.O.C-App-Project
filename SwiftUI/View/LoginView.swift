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
    
    @State var loginState = false
    
    var body: some View {
        NavigationView() {
            VStack {
                SocImage()
                UseremailTextField(studentId: $studentId)
                PasswordSecureField(password: $password)
                Button(action: {
                    LoginPost()
                }) {
                    LoginButton()
                }
                
                Button(action: {
                    LoginToken()
                }) {
                    LoginButton()
                }
                HStack{
                    LinkSingUpView()
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
        LoginView()
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

struct LinkSingUpView: View {
    var body: some View {
        NavigationLink(destination: SingUpView()){
            Text("회원가입")
                .font(.headline)
                .foregroundColor(.black)
        }.padding(.trailing, 30)
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

struct LoginButton: View {
    var body: some View {
        Text("로그인")
            .frame(width:264, height: 40)
            .font(.headline)
            .foregroundColor(.white)
            .background(Color.blue)
            .cornerRadius(5)
    }
}
