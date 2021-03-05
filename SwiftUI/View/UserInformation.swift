//
//  UserInformation.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/02/22.
//

import Foundation

struct loginInfo : Codable {
    var password: String
    var studentId: String
}

//struct userInfo : Codable {
//    var email: String
//    var nickname: String
//    var password: String
//    var studentId: String
//}


let login = loginInfo(password: "123456789", studentId: "201821315")

func loginConnection() {
    let jsonEncoder = JSONEncoder()
    jsonEncoder.outputFormatting = .prettyPrinted
    
    do {
        let data = try jsonEncoder.encode(login)
        print(String(data: data, encoding: .utf8)!)
        
    } catch {
        print(error)
    }
    
}

// "password": “123456789”,"studentId": “201821315"

