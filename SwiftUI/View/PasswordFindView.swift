//
//  PasswordFindView.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/02/19.
//

import SwiftUI

struct PasswordFindView: View {
    
    @State var studentCode = ""
    @State var useremail = ""
    @State var authenticationCode = ""
    
    var body: some View {
        VStack{
            VStack(alignment: .leading){
                STDCodeField(studentCode: $studentCode, STDCodeInText: "학번")
                   
                emailField(useremail: $useremail, emailInText: "이메일")
                
                HStack{
                    Text("인증번호가 전송되었습니다.")
                    NextButton()
                        .padding(.leading, 50)
                }.padding(.bottom, 20)
                    
                authenticationField(authenticationCode: $authenticationCode)
                
                HStack{
                    Text("인증번호가 일치합니다.")
                    NextButton()
                        .padding(.leading, 50)
                }
                
            }.padding(.leading, -9)
            .padding(.bottom, 200)
            
        }.navigationBarTitle("비밀번호 찾기")
         .padding()
    }
}

struct PasswordFindView_Previews: PreviewProvider {
    static var previews: some View {
        PasswordFindView()
    }
}

struct authenticationField: View {
    
    @Binding var authenticationCode: String
    
    var body: some View {
        TextField("인증번호", text: $authenticationCode)
            .frame(width:300, height: 35)
            .background(Color("LightGray"))
    }
    
}


