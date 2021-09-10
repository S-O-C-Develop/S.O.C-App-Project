//
//  SignUpModel.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/03/15.
//

import Foundation

enum StudentIdStatus {
    case empty
    case studentIdFormWrong
    case valid
}

enum PasswordStatus {
    case empty // 비어있을때
    case notStrongEnough // 강력하지 않을 때
    case repeatedPasswrodWrong // 비밀번호가 일치하지 않을때
    case valid // 최종 가능할 때
}

enum EmailStatus {
    case empty
    case emailFormWrong
    case valid
}

enum NicknameStatus {
    case empty
    case NicknameFormWrong
    case valid
}

struct signUpUserInfo {
    var studentId : String
    var password : String
    var useremail : String
    var username : String
}
