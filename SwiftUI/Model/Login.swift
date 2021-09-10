//
//  Login.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/02/25.
//

import Foundation
import Alamofire

var userToken = ""

struct loginState {
    var serverState : Bool
}

let AuToken: Parameters = [
    "X-ACCESS-TOKEN" : userToken
]

struct UserInfo {
    var studentId : String
    var password : String
}

struct LoginJson: Codable {
    var isSuccess : Bool
    var code : Int
    var message : String
    var result : ResultJWT
}

struct ResultJWT: Codable {
    var accountId : Int
    var jwt : String
}

//struct JsonResult: Codable {
//    var code: Int
//    var message: String
//    var data: String
//    var success: Bool
//}
//
//let url = "http://soc-project.site/api/sign-in"
//
//let tokenUrl = "http://soc-project.site/api/get-account"
//
//func LoginPost(studentId: String, password: String) {
//
//    let parameters: Parameters = [
//        "password": password,
//        "studentId": studentId
//    ]
//
//    AF.request(url, method: .post, parameters: parameters, encoding: JSONEncoding.default)
//        .responseJSON(completionHandler: { response in
//            switch response.result {
//            case .success(let value):
//                print("서버 연결 success")
//
//                do { //debugPrint(value)
//                    let data = try JSONSerialization.data(withJSONObject: value, options: .prettyPrinted)
//                    let SignInData = try JSONDecoder().decode(JsonResult.self, from: data)
//
//                    if SignInData.code == 0 {
//
//                        userToken = SignInData.data
//
//                        // 토큰 저장
//                        let tk = TokenUtils()
//                        tk.save("com.SOC", account: studentId, value: SignInData.data)
//
//                        print("code: \(SignInData.code)")
//                        print("data: \(SignInData.data)")
//
//
//                    }
//                    else {
////                        print("로그인 불가")
////                        let state = loginState()
////                        state.serverState = true
//
//                    }
//
//                } catch (let err){
//                    print("로그인 불가")
//
//                    print(err.localizedDescription)
//                }
//
//            case .failure:
//                print("failure")
//            }
//        })
//}
//
//func LoginToken() {
//
//    let tk = TokenUtils()
//
//    if let accessToken = tk.load("com.SOC", account: "201620604") {
//        AF.request(tokenUrl, method: .get, headers: ["X-ACCESS-TOKEN":accessToken])
//            .responseJSON(completionHandler: { response in
//                switch response.result {
//                case .success(let value):
//                    print("success")
//                    print(userToken)
//                    debugPrint(value)
//
//                case .failure(let values):
//                    print("failure")
//                    debugPrint(values)
//
//                }
//            })
//    } else {
//        print("accessToken is nil")
//    }
////    if let refreshToken = tk.load("com.SOC", account: "refreshToken") {
////        print("refreshToken=\(refreshToken)")
////    } else {
////        print("refreshToken is nil")
////    }
//
//
//}
////
////// 상태 저장 함수
////
////func loginState (state : Bool) -> Bool {
////
////    if state == false {
////        return false
////    } else {
////        return true
////    }
////}
