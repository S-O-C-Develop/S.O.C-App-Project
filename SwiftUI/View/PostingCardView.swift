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
                
                Text(posting.title)
                    .font(.caption)
            }
            
            Text(posting.contents)
                .font(.title3)
            
            Text(String(posting.reportCount))
                .font(.caption2)
                .frame(maxWidth: .infinity, alignment: .bottomTrailing)
                
        }
        .padding()
    }
}

struct PostingCardView_Previews: PreviewProvider {
    static var previews: some  View {
        ContentView()
    }
}
