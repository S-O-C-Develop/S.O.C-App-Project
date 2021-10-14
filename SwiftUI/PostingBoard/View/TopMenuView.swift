//
//  TopMenuView.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/10/14.
//

import SwiftUI

struct TopMenuView: View {
    
    @ObservedObject var viewModel = PostViewModel()
    
    @State var communitySelected = 1
    @State var contestSelected = 1
    @State var noticeSelected = 1
    @State var s_studySelected = 1
    
    @State var boardId = 1
    
    var pageNum : Int
    
    var bottomMenu : Int
   
    var body: some View {
        ZStack(alignment: .bottomTrailing){
            if bottomMenu == 1{
                VStack{
                    
                    if pageNum == 1 {
                        CommunityMenuBar(selected: $communitySelected, selectedBoardId: $boardId)
                
                        if communitySelected == 1 {
                            BoardView(boardId: 1)
                            

                        } else {
                            BoardView(boardId: 2)

                        }
                        
                    } else if pageNum == 2 {
                        NoticeMenuBar(selected: $noticeSelected, selectedBoardId: $boardId)
                        
                        if noticeSelected == 1 {
                            BoardView(boardId: 3)

                        } else if noticeSelected == 2 {
                            BoardView(boardId: 4)
                            
                        } else if noticeSelected == 3 {
                            BoardView(boardId: 5)
                            
                        } else {
                            BoardView(boardId: 6)
                        }
                        
                    } else {
                        ContestMenuBar(selected: $contestSelected, selectedBoardId: $boardId)
                        
                        if contestSelected == 1 {
                            BoardView(boardId: 7)

                        } else {
                            BoardView(boardId: 8)
                        }
                    }
                }
            } else {
                VStack{
                    
                    S_StudyMenuBar(selected: $s_studySelected, selectedBoardId: $boardId)
            
                    if s_studySelected == 1 {
                        BoardView(boardId: 9)
                        

                    } else if s_studySelected == 2{
                        BoardView(boardId: 10)

                    } else {
                        BoardView(boardId: 11)
                    }
                }
            }
        }
    }
}
struct TopMenuView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

struct CommunityMenuBar : View {
    
    @Binding var selected : Int
    @Binding var selectedBoardId : Int
    
    var body: some View{
        HStack{
            Button(action: {
                
                self.selected = 1
                self.selectedBoardId = 1
                
                
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
                self.selectedBoardId = 2
                
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

struct NoticeMenuBar : View {
    
    @ObservedObject var viewModel = PostViewModel()
    
    @Binding var selected : Int
    @Binding var selectedBoardId : Int
    
    var body: some View{
        HStack{
            Button(action: {
                
                self.selected = 1
                self.selectedBoardId = 3
                
                
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
                self.selectedBoardId = 4
        
                
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
                self.selectedBoardId = 5
                
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
                self.selectedBoardId = 6
                
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

struct ContestMenuBar : View {
    
    @Binding var selected : Int
    @Binding var selectedBoardId : Int
    
    var body: some View{
        HStack{
            Button(action: {
                
                self.selected = 1
                self.selectedBoardId = 7
                
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
                self.selectedBoardId = 8
                
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

struct S_StudyMenuBar : View {
    
    @Binding var selected : Int
    @Binding var selectedBoardId : Int
    
    var body : some View {
        HStack{
            Button(action: {
                
                self.selected = 1
                self.selectedBoardId = 9
                
            }, label: {
                VStack(spacing: 8) {
                    Text("공모전")
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
                self.selectedBoardId = 10
                
            }, label: {
                VStack(spacing: 8) {
                    Text("스터디")
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
                self.selectedBoardId = 11
                
            }, label: {
                VStack(spacing: 8) {
                    Text("프로젝트")
                        .fontWeight(.semibold)
                        .foregroundColor(self.selected == 3 ? .black : Color.gray.opacity(0.5))
                    
                    Rectangle()
                        .fill(self.selected == 3 ? Color.black : Color.clear)
                        .frame(width: 50, height: 1)
                }.frame(maxWidth: .infinity)
            })
        }
    }
}

