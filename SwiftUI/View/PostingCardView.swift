//
//  PostingCardView.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/07/10.
//

import SwiftUI

struct PostingCardView: View {
    
    var posting : PostContent

    var body: some View {
        
        VStack(alignment: .leading, spacing: 20){
            
            HStack{
                
                Text(posting.author)
                    .font(.title3.bold())
                
                Spacer()
                
                Text(posting.updatedAt)
                    .font(.caption)
            }
            
            Text(posting.title)
                .font(.title2.bold())
            
            Text(posting.contents)
                .font(.title3)
        
            
            Divider()
                .frame(height: 2)
        }
        .padding()
        
        
    }
}

struct PostingCardView_Previews: PreviewProvider {
    static var previews: some  View {
        ContentView()
    }
}

// Refresh

//            HStack{
//
//                if self.isLast {
//
//                    Spacer()
//                    Image(systemName: "message")
//
//
//                    Text(String(posting.reportCount))
//                        .font(.caption2)
//                        .onAppear {
//
//                            print("isLoding")
//                            DispatchQueue.main.asyncAfter(deadline: .now() + 0.5){
//                                self.viewModel.updateData(19)
//                            }
//                        }
//                }
//                else {
//
//                    Spacer()
//                    Image(systemName: "message")
//
//
//                    Text(String(posting.reportCount))
//                        .font(.caption2)
//                }
//            }
