//
//  post_data.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/07/10.
//

import SwiftUI

// 고유 ID, 닉네임 올린시간, 댓글 수, 글 내용,
struct PostJson: Codable {
    var code: Int
    var isSuccess : Bool
    var message : String
    var result : PostResult
}

struct PostResult: Codable {
    var content : [PostContent]
    var empty : Bool
    var first : Bool
    var last : Bool
    var number : Int
    var numberOfElements : Int
    var pageable : PageableResult
    var size : Int
    var sort : SortResult
    var totalElements : Int
    var totalPages : Int
    
}

struct PostContent: Codable, Identifiable{
    let id: UUID = UUID()
    var postId : Int
    var status : String
    var title: String
    var contents: String
    var author : String
    var reportCount : Int
    var updatedAt : String
    var hasImages : Bool
}


struct PageableResult: Codable {
    var offset : Int
    var pageNumber : Int
    var pageSize : Int
    var paged : Bool
    var sort : SortResult
    var unpaged : Bool
}

struct SortResult: Codable {
    var empty : Bool
    var sorted : Bool
    var unsorted : Bool
}

struct PostingResult: Codable {
    var isSuccess : Bool
    var code : Int
    var message : String
    var result : Int
}

//MARK: - Detail 게시글 Model

struct DetailJson: Codable {
    var code: Int
    var isSuccess : Bool
    var message : String
    var result : DetailResult
}

struct DetailResult: Codable,Identifiable {
    let id: UUID = UUID()
    var accountId : Int
    var accountNickname : String
    var boardId : Int
    var contents : String
    var firstImageUrl : String? //
    var hasImages : Bool
    var postId : Int
    var reportCount : Int
    var secondImageUrl : String? //
    var status : String
    var subjectId : Int? //
    var subjectName : String? //
    var title : String
    var updatedAt : String
}

//MARK: - 댓글 Model

struct RippleJson: Codable, Identifiable {
    let id: UUID = UUID()
    var code : Int
    var isSuccess : Bool
    var message: String
    var result: Int
}

enum Subject : Int {
    //MARK: - 1학년 1학기
    case 일반물리학및실험1 = 1
    case 일반수학1및연습
    case 정보통신전자공학개론
    case 프로그래밍및실습1
    
    //MARK: - 1학년 2학기
    case 일반물리학및실험2
    case 일반수학2및연습
    case 프로그래밍및실습2
    
    //MARK: - 2학년 1학기
    case 공학수학1
    case 자료구조와알고리즘
    case 전자기학
    case 프로그래밍응용
    case 회로이론및실험
    
    //MARK: - 2학년 2학기
    case 객체지향프로그래밍기초
    case 공학수학2
    case 논리회로및실험
    case 신호및시스템
    case 전자회로1및실험
    case 컴퓨터구조
    case 확률과통계
    
    //MARK: - 3학년 1학기
    case 데이터통신
    case 디지털시스템설계
    case 반도체공학
    case 선형대수
    case 전자회로2및실험
    case 통신이론
    
    //MARK: - 3학년 2학기
    case 디지털신호처리
    case 디지털통신
    case 모바일정보통신종합설계
    case 반도체프로세스
    case 정보암호화
    case 초고주파공학
    case 컴퓨터네트워킹
    
    //MARK: - 4학년 1학기
    case 네트워크보안과블록체인
    case 랜덤프로세스
    case 아날로그VLSI설계
    case 영상처리
    case 임베디드시스템
    case 정보통신공학캡스톤디자인
    case 전자공학캡스톤디자인
    case 통신부호화이론
    
    //MARK: - 4학년 2학기
    case 디지털VLSI설계
    case 멀티미디어통신
    case 무선통신시스템
    case 반도체제조기술
    case 보안시스템
    case 종합설계
}
