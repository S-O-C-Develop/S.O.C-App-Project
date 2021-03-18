//
//  SignUpModel.swift
//  SOCAppProject
//
//  Created by 박준혁 on 2021/03/18.
//

import Foundation

//Model
enum PasswordStatus {
    case empty // 비어있을때
    case notStrongEnough // 강력하지 않을 때
    case repeatedPasswrodWrong // 비밀번호가 일치하지 않을때
    case valid // 최종 가능할 때
}
