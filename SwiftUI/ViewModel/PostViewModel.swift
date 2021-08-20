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


    //MARK: - 게시글 조회하는 함수
    func boardsPosting(completion: @escaping ([PostContent]) -> ()) {

        let url = "https://prod.soc-project.site/api/posts/boards/"

        AF.request(url + boardNumber, method: .get, parameters: nil, encoding: URLEncoding.default)
            .responseJSON(completionHandler: { response in
                switch response.result {
                case .success(let jsonData):
                    print("success")

                    debugPrint(jsonData)

                    do {

                        let json = try JSONSerialization.data(withJSONObject: jsonData, options: .prettyPrinted)
                        let PostingInData = try JSONDecoder().decode(PostJson.self, from: json)

                        DispatchQueue.main.async {
                            completion(PostingInData.result.content)
                        }
                        
                    } catch (let err) {

                    }


                case .failure(let values):
                    print("failure")

                    debugPrint(values)
                }
            })
    }
}
