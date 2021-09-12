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
    var firstImageUrl : String?
    var postId : Int
    var reportCount : Int
    var secondImageUrl : String?
    var status : String
    var subjectId : Int?
    var subjectName : String?
    var title : String
}


