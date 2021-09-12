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
    
    @Published var data = [PostContent]()
    
    @Published var page = 1
    
    @Published var boardId = 19
    @Published var firstImageUrl = ""
    @Published var secondImageUrl = ""
    @Published var contents = ""
    @Published var title = "Testing"
    
    @Published var postId : Int = 0
    
    @Published var posting = false
    @Environment (\.presentationMode) var presentationMode
    
    @Published var detailResult : DetailResult
    
    init() {
        self.detailResult = DetailResult(accountId: 0, accountNickname: "", boardId: 0, contents: "", firstImageUrl: "", postId: 0, reportCount: 0, secondImageUrl: "", status: "", subjectId: 0, subjectName: "", title: "")
        updateData(19)
    }

//    func changeNumber(_ number : String){
//        boardNumber = number
//        updateData()
//    }
    
    //MARK: - 게시글 무한 조회하는 함수
    func updateData(_ number: Int) {
        let url = "https://prod.soc-project.site/api/posts/boards/\(number)?isAsc=false&page=\(page)&size=5&sortBy=postId"

        let session = URLSession(configuration: .default)

        session.dataTask(with: URL(string: url)!) { (data, _, err) in

            if err != nil{

                print((err?.localizedDescription)!)
                return
            }

            do {
                
                print(number)
                
                let json = try JSONDecoder().decode(PostJson.self, from: data!)
                
                let oldData = self.data

                DispatchQueue.main.async {

                    self.data = oldData + json.result.content
                    
                    self.page += 1
                }


            }
            catch {
                print(error.localizedDescription)
            }
            
        }
        .resume()
    }

    


    //MARK: - 게시글 조회하는 함수
    func searchPosting(completion: @escaping ([PostContent]) -> ()) {

//        let url = "https://prod.soc-project.site/api/posts/boards/"
//        url + boardNumber
        
        let url = "https://prod.soc-project.site/api/posts/boards/\(boardNumber)?isAsc=false&page=\(page)&size=10&sortBy=postId"

        AF.request(url, method: .get, parameters: nil, encoding: URLEncoding.default)
            .responseJSON(completionHandler: { response in
                switch response.result {
                case .success(let jsonData):
                    print("success")

                    debugPrint(jsonData)

                    do {

                        let json = try JSONSerialization.data(withJSONObject: jsonData, options: .prettyPrinted)
                        
                        let PostingInData = try! JSONDecoder().decode(PostJson.self, from: json)

                        let oldData = self.postings
                        
                        DispatchQueue.main.async {
                            
                            self.postings = oldData + PostingInData.result.content
                            
                            self.page += 1
                            
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
    
    
    
    //MARK: - 게시글 자세히 조회하는 함수
    func detailPosting(_ num : Int) {
        let url = "https://prod.soc-project.site/api/posts/\(num)"

        AF.request(url, method: .get, parameters: nil, encoding: URLEncoding.default)
            .responseJSON(completionHandler: { [self] response in
                    switch response.result {
                    case .success(let jsonData):
                        print("success")
//                        debugPrint(jsonData)

                        do {
                            let json = try JSONSerialization.data(withJSONObject: jsonData, options: .prettyPrinted)
                            print("디테일뷰")

                            let jsonData = try! JSONDecoder().decode(DetailJson.self, from: json)
                            
                            self.detailResult = DetailResult(accountId: jsonData.result.accountId, accountNickname: jsonData.result.accountNickname, boardId: jsonData.result.boardId, contents: jsonData.result.contents, firstImageUrl: jsonData.result.firstImageUrl, postId: jsonData.result.postId, reportCount: jsonData.result.reportCount, secondImageUrl: jsonData.result.secondImageUrl, status: jsonData.result.status, subjectId: jsonData.result.subjectId, subjectName: jsonData.result.subjectName, title: jsonData.result.title)
                            
                        }
                        catch (let err) {

                        }


                    case .failure(let values):
                        print("failure")

                        debugPrint(values)
                    }
                })
    }
}
