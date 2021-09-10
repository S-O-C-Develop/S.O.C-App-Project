//
//  FloatingButtonView.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/08/06.
//

import SwiftUI

struct FloatingButtonView: View {
    
    @State var isLinkActive = false
    @State var show = false
//    @Binding var show : Bool
    @State var tag: Int? = nil
    
    var body : some View {
        ExpandableFAB(show: $show)
    }
}

struct FloatingButtonView_Previews: PreviewProvider {
    static var previews: some View {
        FloatingButtonView()
    }
}

struct ExpandableFAB : View {

    @State private var showingPopover = false
    @State var writePage = false
//    @State var ImageName : String
    @Binding var show : Bool

    var body : some View {
        VStack(spacing : 20) {

            if self.show{
                Button(action: {

                    writePage = true

                }) {

                    Image(systemName: "pencil")
                        .resizable()
                        .frame(width: 25, height: 25)
                        .padding()
                        .background(Color.blue)
                        .foregroundColor(.white)
                        .clipShape(Circle())
                }
                .fullScreenCover(isPresented: $writePage, content: {
                    WritePageView()
                })


                Button(action: {

                    showingPopover = true
//                    self.show.toggle()

                }) {

                    Image(systemName: "magnifyingglass")
                        .resizable()
                        .frame(width: 25, height: 25)
                        .padding()
                        .background(Color.blue)
                        .foregroundColor(.white)
                        .clipShape(Circle())

                }.popover(isPresented: $showingPopover) {
                    Text("Your content here")
                        .font(.headline)
                        .padding()
                }
            }

            Button(action: {

                self.show.toggle()

            }) {

                Image(systemName: self.show ? "xmark" : "plus")
                    .resizable()
                    .frame(width: 21, height: 22)
                    .padding()
                    .foregroundColor(.white)
            }
            .background(Color.blue)
            .foregroundColor(.white)
            .clipShape(Circle())
//            .rotationEffect(.init(degrees: self.show ? 180 : 0)) // 이미지 180도 변환
        }
        .animation(.spring())
    }
}


//struct ButtonImage : View {
//
//    @Binding var ImageName : String
//
//    var body: some View {
//
//        Image(systemName: ImageName)
//            .resizable()
//            .frame(width: 35, height: 25)
//            .padding(18)
//            .background(Color.blue)
//            .foregroundColor(.white)
//            .clipShape(Circle())
//    }
//}
