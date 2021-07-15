//
//  Board_TabButton.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/07/10.
//

import SwiftUI

struct Board_TabButton: View {
    
    @Binding var currentTab : String
    
    var title: String
    var animationID: Namespace.ID
    
    var body: some View {
        Button {
            
            withAnimation(.spring()){
                currentTab = title
            }
            
        } label: {
            VStack{
                Text(title)
                    .fontWeight(.semibold)
                    .foregroundColor(currentTab == title ? .black : .gray)
                
                if currentTab == title{
                    Rectangle()
                        .fill(Color.black)
                        .matchedGeometryEffect(id: "Tab", in: animationID)
                        .frame(width: 50, height: 1)
                }
                else {
                    Rectangle()
                        .fill(Color.clear)
                        .matchedGeometryEffect(id: "Tab", in: animationID)
                        .frame(width: 50, height: 1)
                }
            }
            // Taking Availbale Width...
            .frame(maxWidth: .infinity)
        }
    }
}
