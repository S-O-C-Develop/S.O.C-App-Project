//
//  Post_ViewModel.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/07/10.
//

import SwiftUI
import Alamofire
import Foundation
import Combine

class PostViewModel: ObservableObject {

    @Published var boardNumber = "19"
    @Published var postings = [PostContent]()
    @Published var TabButtonName = ""
    
    @Published var boardId = 19
    @Published var firstImageUrl = ""
    @Published var secondImageUrl = ""
    @Published var contents = ""
    @Published var title = "Testing"
    
    @Published var posting = false
    @Environment (\.presentationMode) var presentationMode
    


    //MARK: - 게시글 조회하는 함수
    func searchPosting(completion: @escaping ([PostContent]) -> ()) {

        let url = "https://prod.soc-project.site/api/posts/boards/"

        AF.request(url + boardNumber, method: .get, parameters: nil, encoding: URLEncoding.default)
            .responseJSON(completionHandler: { response in
                switch response.result {
                case .success(let jsonData):
                    print("success")

                    debugPrint(jsonData)

                    do {

                        let json = try JSONSerialization.data(withJSONObject: jsonData, options: .prettyPrinted)
                        print("입력2")
                        
                        let PostingInData = try! JSONDecoder().decode(PostJson.self, from: json)

                        DispatchQueue.main.async {
                            completion(PostingInData.result.content)
                        }
                        
//                        let PostingInData = try JSONDecoder().decode(PostJson.self, from: json)
//                        print("입력1")
//                        DispatchQueue.main.async {
//                            print("입력")
//                            completion(PostingInData.result.content)
//                        }
                        
                    } catch (let err) {

                    }


                case .failure(let values):
                    print("failure")

                    debugPrint(values)
                }
            })
    }
    
    //MARK: - 게시글 생성하는 함수
    func createPosting () -> Bool {

        let url = "https://prod.soc-project.site/api/posts"

        let tk = TokenUtils()

        let parameters: Parameters = [
            "boardId": boardId as Any,
            "contents": contents,
            "firstImageUrl": firstImageUrl,
            "secondImageUrl": secondImageUrl,
            "title": title
        ]

        if let accessToken = tk.load("com.SOC", account: "accessToken") {

            AF.request(url, method: .post, parameters: parameters, encoding: JSONEncoding.default, headers: ["X-ACCESS-TOKEN":accessToken])
                .responseJSON(completionHandler: { response in
                    switch response.result {
                    case .success(let value):
                        print("서버 연결 success")
//                        debugPrint(value)
                        do {
                            let json = try JSONSerialization.data(withJSONObject: value, options: .prettyPrinted)
                            let CreatePostingData = try JSONDecoder().decode(PostingResult.self, from: json)

                            if CreatePostingData.code == 1000 {
                                
                                

                                self.posting = true
                                
                            }
                        } catch (let err){
                            print(err.localizedDescription)
                        }

                    case .failure:
                        print("failure")
                    }
                })

        }
        return posting
    }
}
