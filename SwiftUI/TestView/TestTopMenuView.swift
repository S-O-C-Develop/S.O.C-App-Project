//
//  TestTobMenuView.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/09/27.
//

import SwiftUI

struct TestTopMenuView: View {
    
    @ObservedObject var viewModel = PostViewModel()
    
    @State var communitySelected = 1
    @State var contestSelected = 1
    @State var noticeSelected = 1
    
    var pageNum : Int
   
    var body: some View {
        ZStack(alignment: .bottomTrailing){
            VStack{
                
                if pageNum == 1 {
                    communityMenuBar(selected: $communitySelected)
            
                    if communitySelected == 1 {
                        TestBoardView(postId: 19)

                    } else {
                        TestBoardView(postId: 22)

                    }
                    
                } else if pageNum == 2 {
                    contestMenuBar(selected: $contestSelected)
                    
                    if contestSelected == 1 {
                        TestBoardView(postId: 19)

                    } else if contestSelected == 2 {
                        TestBoardView(postId: 22)
                        
                    } else if contestSelected == 3 {
                        TestBoardView(postId: 19)
                        
                    } else {
                        TestBoardView(postId: 22)
                    }
                    
                } else {
                    NoticeMenuBar(selected: $noticeSelected)
                    
                    if noticeSelected == 1 {
                        TestBoardView(postId: 19)

                    } else {
                        TestBoardView(postId: 22)
                    }
                }
            }
            
            FloatingButtonView()
                .padding(.trailing, 20)
                .shadow(color: Color.black.opacity(0.3),
                        radius: 3,
                        x: 3,
                        y: 3)
        }
    }
}


struct communityMenuBar : View {
    
    @StateObject var viewModel = PostViewModel()
    
    @Binding var selected : Int
    
    var body: some View{
        HStack{
            Button(action: {
                
                self.selected = 1
                
            }, label: {
                VStack(spacing: 8) {
                    Text("문제")
                        .fontWeight(.semibold)
                        .foregroundColor(self.selected == 1 ? .black : Color.gray.opacity(0.5))
                    
                    Rectangle()
                        .fill(self.selected == 1 ? Color.black : Color.clear)
                        .frame(width: 50, height: 1)
                
                }.frame(maxWidth: .infinity)
            })
            
            Spacer(minLength: 8)
            
            Button(action: {
                
                self.selected = 2
                
            }, label: {
                VStack(spacing: 8) {
                    Text("정보")
                        .fontWeight(.semibold)
                        .foregroundColor(self.selected == 2 ? .black : Color.gray.opacity(0.5))
                    
                    Rectangle()
                        .fill(self.selected == 2 ? Color.black : Color.clear)
                        .frame(width: 50, height: 1)
                }.frame(maxWidth: .infinity)
            })
        }
    }
}

struct contestMenuBar : View {
    
    @ObservedObject var viewModel = PostViewModel()
    
    @Binding var selected : Int
    
    var body: some View{
        HStack{
            Button(action: {
                
                self.selected = 1
                
                
            }, label: {
                VStack(spacing: 8) {
                    Text("학사")
                        .fontWeight(.semibold)
                        .foregroundColor(self.selected == 1 ? .black : Color.gray.opacity(0.5))
                    
                    Rectangle()
                        .fill(self.selected == 1 ? Color.black : Color.clear)
                        .frame(width: 50, height: 1)
                
                }.frame(maxWidth: .infinity)
            })
            
            Spacer(minLength: 8)
            
            Button(action: {
                
                self.selected = 2
        
                
            }, label: {
                VStack(spacing: 8) {
                    Text("장학")
                        .fontWeight(.semibold)
                        .foregroundColor(self.selected == 2 ? .black : Color.gray.opacity(0.5))
                    
                    Rectangle()
                        .fill(self.selected == 2 ? Color.black : Color.clear)
                        .frame(width: 50, height: 1)
                }.frame(maxWidth: .infinity)
            })
            
            Spacer(minLength: 8)
            
            Button(action: {
                
                self.selected = 3
                
            }, label: {
                VStack(spacing: 8) {
                    Text("학과")
                        .fontWeight(.semibold)
                        .foregroundColor(self.selected == 3 ? .black : Color.gray.opacity(0.5))
                    
                    Rectangle()
                        .fill(self.selected == 3 ? Color.black : Color.clear)
                        .frame(width: 50, height: 1)
                }.frame(maxWidth: .infinity)
            })
            
            Spacer(minLength: 8)
            
            Button(action: {
                
                self.selected = 4
                
            }, label: {
                VStack(spacing: 8) {
                    Text("SOC")
                        .fontWeight(.semibold)
                        .foregroundColor(self.selected == 4 ? .black : Color.gray.opacity(0.5))
                    
                    Rectangle()
                        .fill(self.selected == 4 ? Color.black : Color.clear)
                        .frame(width: 50, height: 1)
                }.frame(maxWidth: .infinity)
            })
        }
    }
}

struct NoticeMenuBar : View {
    
    @Binding var selected : Int
    
    var body: some View{
        HStack{
            Button(action: {
                
                self.selected = 1
                
            }, label: {
                VStack(spacing: 8) {
                    Text("교내")
                        .fontWeight(.semibold)
                        .foregroundColor(self.selected == 1 ? .black : Color.gray.opacity(0.5))
                    
                    Rectangle()
                        .fill(self.selected == 1 ? Color.black : Color.clear)
                        .frame(width: 50, height: 1)
                
                }.frame(maxWidth: .infinity)
            })
            
            Spacer(minLength: 8)
            
            Button(action: {
                
                self.selected = 2
                
            }, label: {
                VStack(spacing: 8) {
                    Text("교외")
                        .fontWeight(.semibold)
                        .foregroundColor(self.selected == 2 ? .black : Color.gray.opacity(0.5))
                    
                    Rectangle()
                        .fill(self.selected == 2 ? Color.black : Color.clear)
                        .frame(width: 50, height: 1)
                }.frame(maxWidth: .infinity)
            })
        }
    }
}
