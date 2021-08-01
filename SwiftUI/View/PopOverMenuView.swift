//
//  PopOverMenuView.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/08/01.
//

import SwiftUI

struct PopOverMenuView: View {
    
    @State var show = false
    
    
    var body: some View {
        VStack(alignment: .center, spacing: 15){
            
            if self.show{
                
                PopOver(show: $show)
                    .background(Color.white)
                    .clipShape(ArrowShape())
                    .cornerRadius(15)
                    .offset(y: 15)
                
            }
            
            Button(action: {
                
                withAnimation(.spring()){
                    
                    self.show.toggle()
                    
                }
                
            }) {
                
                Image(systemName: self.show ? "xmark" : "plus")
                    .resizable()
                    .frame(width: 20, height: 22)
                    .padding()
                    .foregroundColor(.white)
            }.background(Color.blue)
            .clipShape(Circle())
            
        }
    }
}

struct PopOverMenuView_Previews: PreviewProvider {
    static var previews: some View {
        PopOverMenuView()
    }
}

struct PopOver : View {
    
    @State private var showingPopover = false
    @Binding var show : Bool
    
    var body : some View {
        
        VStack(alignment: .leading, spacing: 18) {
            
            Button(action: {
                
            }){
                
                HStack(spacing: 15) {
                    Image(systemName: "pencil").imageScale(.large)
                        
                    Text("글쓰기")
                }
            }
            
            
            Divider()
            
            
            Button("조건 검색") {
                showingPopover = true
                //show = false
            }
            .popover(isPresented: $showingPopover) {
                Text("Your content here")
                    .font(.headline)
                    .padding()
            }
            
            Divider()
            
            Button(action: {
                
            }) {
                
                HStack(spacing: 15) {
                    Text("공모전")
                }
            }
        }
        .foregroundColor(.black)
        .frame(width: 140)
        .padding()
        .padding(.bottom, 20)
        
    }
}

struct ArrowShape : Shape {
    
    
    func path(in rect: CGRect) -> Path {
        
        let center = rect.width / 2
        
        return Path{ path in
            
            path.move(to: CGPoint(x: 0, y: 0))
            path.addLine(to: CGPoint(x: rect.width, y: 0))
            path.addLine(to: CGPoint(x: rect.width, y: rect.height - 20))
            
            path.addLine(to: CGPoint(x: center - 15, y: rect.height - 20))
            path.addLine(to: CGPoint(x: center, y: rect.height - 5))
            path.addLine(to: CGPoint(x: center + 15, y: rect.height - 20))
            
            path.addLine(to: CGPoint(x: 0, y: rect.height - 20))
        }
    }
}
