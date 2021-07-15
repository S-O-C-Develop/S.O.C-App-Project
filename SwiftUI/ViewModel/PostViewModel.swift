//
//  Post_ViewModel.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/07/10.
//

import SwiftUI

class PostViewModel: ObservableObject {
    
    @Published var postings: [PostModel] = [
    
//        PostModel(contents: "Genesis2010", timing: Date()),
//        PostModel(contents: "catholic", timing: Date()),
        
        PostModel(id: "Genesis2010", contents: "안녕하세요 개발자입니다", timing: "5분전", comment_cnt: "댓글 수 : 2"),
        PostModel(id: "SOC_people", contents: "안녕하세요 SOC 회원입니다", timing: "3분전", comment_cnt: "댓글 수 : 25")
    ]
    
}
