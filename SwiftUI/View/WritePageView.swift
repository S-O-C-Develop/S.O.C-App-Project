//
//  WritePageView.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/08/03.
//

import SwiftUI

struct WritePageView: View {
    
    @State var textEditor: String = ""
    
    var body: some View {
        NavigationView {
            VStack(alignment: .leading) {
                
                Text("1학년 > 1학기 > 일반수학")
                
                HStack{
                    Text("내용")
                        .font(.title)
                    
                    Spacer()
                    
                    Button(action: {
                        
                    }) {
                        Image(systemName: "camera")
                            .foregroundColor(.black)
                            
                    }
                }
                .padding()
                
                TextEditor(text: $textEditor)
                    
        
                    
                    
                .navigationBarTitle("글쓰기")
                .navigationBarTitleDisplayMode(.inline)
                .navigationBarItems(trailing:
                    Button(action: {
                    
                    }) {
                            Text("올리기")
                                .font(.subheadline)
                                .foregroundColor(.white)
                                .background(Color.blue)
                                .cornerRadius(20)
                        
                    }
                
                )
            }
            .padding(20)
        }
    }
}

struct WritePageView_Previews: PreviewProvider {
    static var previews: some View {
        WritePageView()
    }
}
