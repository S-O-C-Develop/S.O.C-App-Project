//
//  KeychainToken.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/03/04.
//

import Foundation
import Security
import Alamofire

class TokenUtils {
    
    // Keychain에 값을 저장하는 함수
    func save(_ service: String, account: String, value: String) {
        
        // 키 체인 쿼리
        let keyChainQuery: NSDictionary = [
            kSecClass: kSecClassGenericPassword, // 어떤 타입의 데이터를 저장할지
            kSecAttrService: service, // 서비스 및 앱 번들 아이디
            kSecAttrAccount: account, // 사용자 계정
            kSecValueData: value.data(using: .utf8, allowLossyConversion: false)! // 저장할 값
        ]

        // 현재 저장되어 있는 값 삭제 (기존의 값을 덮어쓰지 못하기 때문)
        SecItemDelete(keyChainQuery)
        
        // 새로운 키 체인 아이템 등록
        let status: OSStatus = SecItemAdd(keyChainQuery, nil)
        assert(status == noErr, "토큰 값 저장에 실패했습니다")
        NSLog("status=\(status)")
    }
    
    func load(_ service: String, account: String) -> String? {
        // 키 체인 쿼리
        let keyChainQuery: NSDictionary = [
            kSecClass: kSecClassGenericPassword,
            kSecAttrService: service,
            kSecAttrAccount: account,
            kSecReturnData: kCFBooleanTrue, // 읽어올 데이터 타입, kCFBooleanTrue는 CFData 형식
            kSecMatchLimit: kSecMatchLimitOne // 쿼리 조건에 일치한 값이 여러개일 경우 설정하는 키, 일치하는 하나만 읽어오는 설정
        ]
        
        // 키 체인에 저장된 값을 읽어옴
        var dataTypeRef: AnyObject?
        let status = SecItemCopyMatching(keyChainQuery, &dataTypeRef)
        
        // 처리 결과가 성공이면 Data 타입으로 변환 - 다시 String 타입으로 변환
        if (status == errSecSuccess) {
            // errSecSuccess는 처리 결과 성공 여부, SecItemCopyMatching() 함수의 성공 결과로 나온다
            let retrievedData = dataTypeRef as! Data
            let value = String(data: retrievedData, encoding: String.Encoding.utf8)
            return value
        } else { // 실패면 nil 반환
            print("Nothing was retrieved from the ketchain. Status code \(status)")
            return nil
        }
    }
    
    // 키 체인에 저장된 값을 삭제
    func delete(_ service: String, account: String) {
        // 키 체인 쿼리
        let keyChainQuery: NSDictionary = [
            kSecClass: kSecClassGenericPassword,
            kSecAttrService: service,
            kSecAttrAccount: account
        ]
        
        // 저장되어 있는 값 삭제
        let status = SecItemDelete(keyChainQuery)
        assert(status == noErr, "토큰 값 삭제에 실패했습니다")
        NSLog("status=\(status)")
    }
    
    // 키 체인에 저장된 액세스 토큰을 이용해 헤더를 만듬
//    func getAuthorizationHeader() -> HTTPHeaders? {
//        let serviceID = "com.nanocode.MyMemory"
//        if let accessToken = self.load(serviceID, account: "accessToken") {
//            return ["Autorization": "Bearer \(accessToken)"] as HTTPHeaders
//        } else {
//            return nil
//        }
//    }
    
}

func TokenSearch() {
    // 키 체인 저장 여부 확인
    let tk = TokenUtils()
    if let accessToken = tk.load("com.SOC", account: "accessToken") {
        print("accessToken=\(accessToken)")
    } else {
        print("accessToken is nil")
    }
    if let refreshToken = tk.load("com.SOC", account: "refreshToken") {
        print("refreshToken=\(refreshToken)")
    } else {
        print("refreshToken is nil")
    }
}

