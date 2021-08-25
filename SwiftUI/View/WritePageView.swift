//
//  WritePageView.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/08/03.
//

import SwiftUI

struct WritePageView: View {
    
    @Environment (\.presentationMode) var presentationMode
    @State var textEditor: String = ""
    @ObservedObject var viewModel = PostViewModel()
    
//    @State var posting : Bool?
    
    var body: some View {
        NavigationView {
            VStack(alignment: .leading) {
                
                Text("1학년 > 1학기 > 일반수학")
                    .padding()
                
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
                
                CustomTextEditor.init(placeholder: "내용을 입력하세요.", text: $viewModel.contents)
                    .font(.body)
                    .background(Color(UIColor.systemGray6))
                    .accentColor(.green)
                    .frame(height: 400)
                    .cornerRadius(8)
                    .padding()
            }
            .navigationBarTitle("글쓰기", displayMode: .inline)
            .toolbar {
                ToolbarItem(placement: .navigationBarTrailing) {
                    Button(action: {
                        
                        print(viewModel.createPosting())
//                        if  {
//                            presentationMode.wrappedValue.dismiss()
//                        }
                        
                    }) {
                            Text("올리기")
                                .font(.subheadline)
                    }
                }
                
                ToolbarItem(placement: .navigationBarLeading){
                    Button(action: {
                        
                        presentationMode.wrappedValue.dismiss()
                        
                    }) {
                        Image(systemName: "chevron.left")

                    }
                }
                
            }
            .padding(.top, -50)
        }
        .accentColor(.black)
    }
}

struct WritePageView_Previews: PreviewProvider {
    static var previews: some View {
        WritePageView()
    }
}


struct CustomTextEditor: View {
    
    let placeholder: String
    @Binding var text: String
    let internalPadding: CGFloat = 5
    
    var body: some View {
        ZStack(alignment: .topLeading) {
            if text.isEmpty  {
                Text(placeholder)
                    .foregroundColor(Color.primary.opacity(0.25))
                    .padding(EdgeInsets(top: 7, leading: 4, bottom: 0, trailing: 0))
                    .padding(internalPadding)
            }
            TextEditor(text: $text)
                .padding(internalPadding)
        }.onAppear() {
            UITextView.appearance().backgroundColor = .clear
        }.onDisappear() {
            UITextView.appearance().backgroundColor = nil
        }
    }
}
