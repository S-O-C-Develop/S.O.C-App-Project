//
//  PostingCardView.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/07/10.
//

import SwiftUI

struct PostingCardView: View {
    var posting : PostModel
    
    var body: some View {
        
        VStack(alignment: .leading, spacing: 20){
            
            HStack{
                Text(posting.id)
                    .font(.title3.bold())
                
                Spacer()
                
                Text(posting.timing)
                    .font(.caption)
            }
            
            Text(posting.contents)
                .font(.title3)
            
            Text(posting.comment_cnt)
                .font(.caption2)
                .frame(maxWidth: .infinity, alignment: .bottomTrailing)
                
        }
        .padding()
        .overlay(
                    RoundedRectangle(cornerRadius: 12)
                        .stroke(Color.blue, lineWidth: 1)
                )
       
    }
}

struct PostingCardView_Previews: PreviewProvider {
    static var previews: some  View {
        ContentView()
    }
}
