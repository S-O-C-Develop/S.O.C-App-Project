//
//  SingUpView.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/02/19.
//

import SwiftUI

struct SingUpView: View {
    
    @State var studentCode = ""
    @State var password = ""
    @State var passwordConfirm = ""
    @State var useremail = ""
    @State var nickname = ""
    
    
    var body: some View {
        VStack {
            VStack(alignment: .leading){
                menuText(content: "학번")
                STDCodeField(studentCode: $studentCode, STDCodeInText: "")
                
                menuText(content: "비밀번호")
                PWSecureField(password: $password)
                
                menuText(content: "비밀번호 확인")
                PWCFSecureField(passwordConfirm: $passwordConfirm)
                 
                menuText(content: "이메일")
                emailField(useremail: $useremail, emailInText: "")
                
                menuText(content: "닉네임")
                HStack{
                    nicknameField(nickname: $nickname)
                        .padding(.trailing, 12)
                    Button(action: {
                        print("check")
                    }) {
                        NextButton()
                    }
                }
            }.padding(.leading, -9)
            
            HStack{
                VStack(alignment: .trailing){
                    Button(action: {
                        print("Next Button tapped.")
                    }) {
                        NextButton()
                    }
                }.padding(.leading, 230)
                
            }.padding(.bottom, 150)
            
        }.navigationTitle("회원가입")
        .padding()
    }
}

struct SingUpView_Previews: PreviewProvider {
    static var previews: some View {
        SingUpView()
    }
}

struct menuText: View {
    
    var content = ""
    
    var body: some View {
        Text(content)
            .font(.title2)
            .fontWeight(.bold)
    }
}

struct STDCodeField: View {
    
    @Binding var studentCode: String
    
    var STDCodeInText : String
    
    var body: some View {
        TextField(STDCodeInText , text: $studentCode)
            .frame(width:300, height: 35)
            .background(Color("LightGray"))
    }
}

struct PWSecureField: View {
    
    @Binding var password: String
    
    var body: some View {
        SecureField("", text:$password)
            .frame(width:300, height: 35)
            .background(Color("LightGray"))
    }
}

struct PWCFSecureField: View {
    
    @Binding var passwordConfirm: String
    
    var body: some View {
        SecureField("", text:$passwordConfirm)
            .frame(width:300, height: 35)
            .background(Color("LightGray"))
    }
}

struct emailField : View {
    
    @Binding var useremail: String
    
    var emailInText : String
    
    var body: some View {
        TextField(emailInText , text: $useremail)
            .frame(width:300, height: 35)
            .background(Color("LightGray"))
    }
}

struct nicknameField : View {
    
    @Binding var nickname: String
    
    var body: some View {
        TextField("", text: $nickname)
            .frame(width:220, height: 35)
            .background(Color("LightGray"))
    }
}

struct NextButton: View {
    var body: some View {
        Text("다음")
            .frame(width:60, height: 40)
            .font(.headline)
            .foregroundColor(.white)
            .background(Color.blue)
            .cornerRadius(5)
    }
}
