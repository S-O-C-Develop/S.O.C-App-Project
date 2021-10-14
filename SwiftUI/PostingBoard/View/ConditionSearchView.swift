//
//  ConditionSearchView.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/09/20.
//

import SwiftUI

struct ConditionSearchView: View {
    
    @Environment (\.presentationMode) var presentationMode
    @ObservedObject var viewModel : PostViewModel
  
    @State var showGrade = false
    @State var showSemester = false
    @State var showSubject = false
    
    // 각 항목에서 선택된 조건을 저장
    @State var selectedGrade = ""
    @State var selectedSemester = ""
    @State var selectedSubject = ""
    
    // 각 항목이 선택 됐는지
    @State var selectGrade = false
    @State var selectSemester = false
    @State var selectSubject = false
    
    var selectedView : Int // 1 이면 글쓰기, 2면 조건 검색
    
    var body: some View {
        
        ZStack {
            VStack{
                Capsule()
                    .frame(width : 80, height: 5)
//                    .foregroundColor(Color.gray)
                    .padding(.top)
                
                Spacer()
                
                VStack(alignment: .leading){
                    
                    if selectedView == 1{
                        
                        Text("과목 선택")
                            .font(.largeTitle.bold())
                            .padding([.leading, .bottom], 16)
                        
                        Text("질문할 과목을 선택해 주세요.")
                            .font(.headline)
                            .padding(.leading, 16)
                            .padding(.bottom, 3)
                        
                    } else {
                        
                        Text("조건 검색")
                            .font(.largeTitle.bold())
                            .padding([.leading, .bottom], 16)
                        
                        Text("원하는 과목을 선택해 주세요.")
                            .font(.headline)
                            .padding(.leading, 16)
                            .padding(.bottom, 3)
                        
                        Text("해당 과목만 피드에서 조회할 수 있습니다.")
                            .font(.headline)
                            .padding(.leading, 16)
                        
                    }
                    
                    CustomSelectPicker(show: $showGrade, selectedText: "학년", selecteditem: selectedGrade)
                        .padding(.top, 35)
                        
                    CustomSelectPicker(show: $showSemester, selectedText: "학기", selecteditem: selectedSemester)
                    
                    CustomSelectPicker(show: $showSubject, selectedText: "과목", selecteditem: selectedSubject)
                     
                    Spacer()
                }
                .padding(.top, 15)
                
                Button(action: {
                
                    viewModel.selectedSearch(selectedGrade, selectedSemester, selectedSubject)
                    
                    DispatchQueue.main.asyncAfter(deadline: .now() + 0.5) {
                        if viewModel.conditionSearching {
                            presentationMode.wrappedValue.dismiss()
                        }
                    }
                   
                }, label: {
                    
                    HStack{
                        
                        Spacer()
                        
                        Text("완 료")
                            .fontWeight(.bold)
                            .frame(height: 55)
                        
                        Spacer()
                    }
                
                })
                    .frame(height: 55)
                    .background(Color.blue)
                    .foregroundColor(.white)
                    .cornerRadius(10)
                    .padding()
                
            }
            
            
            if showGrade {
                VStack{
                    
                    Spacer()
                    
                    CustomActionSheet(selected: 1, selectedItem: self.$selectedGrade)
                        .offset(y: self.showGrade ? 0 : UIScreen.main.bounds.height)
                }
                .background(self.showGrade ? Color.black.opacity(0.3) : Color.clear)
                .edgesIgnoringSafeArea(.all)
                .onTapGesture {
                    self.showGrade.toggle()
                }
                .edgesIgnoringSafeArea(.bottom)
            }
            
            if showSemester {
                VStack{
                    
                    Spacer()
                    
                    CustomActionSheet(selected: 2, selectedItem: self.$selectedSemester)
                        .offset(y: self.showSemester ? 0 : UIScreen.main.bounds.height)
                }
                .background(self.showSemester ? Color.black.opacity(0.3) : Color.clear)
                .edgesIgnoringSafeArea(.all)
                .onTapGesture {
                    self.showSemester.toggle()
                }
                .edgesIgnoringSafeArea(.bottom)
            }
            
            if showSubject {
                VStack{
                    
                    Spacer()
                    
//                    CustomActionSheet(selected: 3, selectedItem: self.$selectedSubject)
                    SubjectActionSheet(selectedItem: self.$selectedSubject, selectedGrade: self.selectedGrade, selectedSemester: self.selectedSemester)
                        .offset(y: self.showSubject ? 0 : UIScreen.main.bounds.height)
                }
                .background(self.showSubject ? Color.black.opacity(0.3) : Color.clear)
                .edgesIgnoringSafeArea(.all)
                .onTapGesture {
                    self.showSubject.toggle()
                }
                .edgesIgnoringSafeArea(.bottom)
            }
        }
        .animation(.default)
    }
}

//struct ConditionSearchView_Previews: PreviewProvider {
//    static var previews: some View {
//        ConditionSearchView()
//    }
//}

struct CustomActionSheet : View {
    var grade = ["1학년", "2학년", "3학년", "4학년"]
    var semester = ["1학기", "2학기"]

    var selected : Int
    
    var selectedGrade = ""
    var selectedSemester = ""
    
    @Binding var selectedItem : String
    
//    @Binding var selectGrade : Bool
//    @Binding var selectSemester : Bool
    
    var body : some View {
        
        VStack(spacing: 15) {
            Picker(selection: self.$selectedItem, label: Text("")) {
                if selected == 1 {
                    ForEach(0 ..< grade.count) {
                        Text(self.grade[$0])
                            .tag(self.grade[$0])
                    }
                    
                } else if selected == 2 {
                    ForEach(0 ..< semester.count) {
                        Text(self.semester[$0])
                            .tag(self.semester[$0])
                    }
                }
//                else {
//                    ForEach(0 ..< gr1_se1.count) {
//                        Text(self.gr1_se1[$0])
//                            .tag(self.gr1_se1[$0])
//                    }
//                }
                
            }
            .pickerStyle(.wheel)
        }
        .padding(.bottom, (UIApplication.shared.windows.last?.safeAreaInsets.bottom)! + 10)
        .padding(.horizontal)
        .padding(.top, 20)
        .background(Color.white)
        .cornerRadius(25)
    }
}

struct SubjectActionSheet : View {
    
    @Binding var selectedItem : String
    
    var gr1_se1 = ["일반물리학및실험1", "일반수학1및연습","프로그래밍및실습1"]
    var gr1_se2 = ["일반물리학및실험2", "일반수학2및연습","프로그래밍및실습2"]
    
    var gr2_se1 = ["공학수학1", "자료구조와알고리즘", "전자기학", "프로그래밍 응용", "회로이론및실험"]
    var gr2_se2 = ["객체지향프로그래밍기초", "공학수학2", "논리회로및실험", "신호및시스템", "전자회로1및실험", "컴퓨터구조", "확률과통계"]
    
    var gr3_se1 = ["데이터통신", "디지털시스템설계", "반도체공학", "선형대수", "전자회로2및실험", "통신이론"]
    var gr3_se2 = ["디지털신호처리", "디지털통신", "모바일정보통신종합설계", "반도체프로세스", "정보암호화", "초고주파공학", "컴퓨터네트워킹"]
    
    var gr4_se1 = ["네트워크보안과블록체인", "랜덤프로세스", "아날로그VLSI설계", "영상처리", "임베디드시스템", "정보통신공학캡스톤디자인", "전자공학캡스톤디자인", "통신부호화이론"]
    var gr4_se2 = ["디지털VLSI설계", "멀티미디어통신", "무선통신시스템", "반도체제조기술", "보안시스템", "종합설계"]
    
    var selectedGrade = ""
    var selectedSemester = ""
    
    
    var body : some View {
        VStack(spacing: 15) {
            Picker(selection: self.$selectedItem, label: Text("")) {
                if selectedGrade == "1학년" {
                    if selectedSemester == "1학기"{
                        ForEach(0 ..< gr1_se1.count) {
                            Text(self.gr1_se1[$0])
                                .tag(self.gr1_se1[$0])
                        }
                    } else {
                        ForEach(0 ..< gr1_se2.count) {
                            Text(self.gr1_se2[$0])
                                .tag(self.gr1_se2[$0])
                        }
                    }
                } else if selectedGrade == "2학년" {
                    if selectedSemester == "1학기"{
                        ForEach(0 ..< gr2_se1.count) {
                            Text(self.gr2_se1[$0])
                                .tag(self.gr2_se1[$0])
                        }
                    } else {
                        ForEach(0 ..< gr2_se2.count) {
                            Text(self.gr2_se2[$0])
                                .tag(self.gr2_se2[$0])
                        }
                    }
                } else if selectedGrade == "3학년" {
                    if selectedSemester == "1학기"{
                        ForEach(0 ..< gr3_se1.count) {
                            Text(self.gr3_se1[$0])
                                .tag(self.gr3_se1[$0])
                        }
                    } else {
                        ForEach(0 ..< gr3_se2.count) {
                            Text(self.gr3_se2[$0])
                                .tag(self.gr3_se2[$0])
                        }
                    }
                } else {
                    if selectedSemester == "1학기"{
                        ForEach(0 ..< gr4_se1.count) {
                            Text(self.gr4_se1[$0])
                                .tag(self.gr4_se1[$0])
                        }
                    } else {
                        ForEach(0 ..< gr4_se2.count) {
                            Text(self.gr4_se2[$0])
                                .tag(self.gr4_se2[$0])
                        }
                    }
                }
            }
            .pickerStyle(.wheel)
        }
        .padding(.bottom, (UIApplication.shared.windows.last?.safeAreaInsets.bottom)! + 10)
        .padding(.horizontal)
        .padding(.top, 20)
        .background(Color.white)
        .cornerRadius(25)
    }
}
    
struct CustomSelectPicker : View {
    
    @Binding var show : Bool
    
    var selectedText : String
    var selecteditem : String
    
    var body : some View {
        VStack(alignment: .leading){
            Text(selectedText)
                .font(.headline)
                .padding(.leading, 20)
                
                
            Button(action: {
                
                self.show.toggle()
                
            }) {
                
                HStack{
                    
                    Text(selecteditem)
                        .foregroundColor(.black)
                        .padding()
                    
                    Spacer()
                    
                    Image(systemName: "chevron.down")
                        .foregroundColor(.black)
                        .padding()
                }
             
            }
            .border(Color.gray, width: 1.5)
            .cornerRadius(3)
            .padding([.leading, .trailing, .bottom])
            
        }
    }
}
