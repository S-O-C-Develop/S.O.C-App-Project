//
//  FloatingButtonView.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/08/06.
//

import SwiftUI

struct FloatingButtonView: View {
    
    @State var isLinkActive = false
    @Binding var show : Bool
    
    var body : some View {
        NavigationView {
            
            VStack(spacing : 20) {
                
                if self.show{
                    
                    Button(action: {
                        
                        self.show.toggle()
                        
                    }) {
                        
                        Image(systemName: "pencil").resizable().frame(width: 35, height: 25).padding(18)
                    }
                    .background(Color.blue)
                    .foregroundColor(.white)
                    .clipShape(Circle())
                    
                    
                    Button(action: {
                        
                        self.show.toggle()
                        
                    }) {
                        
                        Image(systemName: "cart.fill").resizable().frame(width: 35, height: 25).padding(18)
                    }
                    .background(Color.blue)
                    .foregroundColor(.white)
                    .clipShape(Circle())
                    
                }
                
                Button(action: {
                    
                    self.show.toggle()
                    
                }) {
                    
                    Image(systemName: "chevron.down").resizable().frame(width: 25, height: 15).padding(22)
                }
                .background(Color.blue)
                .foregroundColor(.white)
                .clipShape(Circle())
                .rotationEffect(.init(degrees: self.show ? 180 : 0))
            }
            .animation(.spring())
            .frame(width: 30, height: 60)
        }
        
            
        }
        
//        VStack(spacing : 20) {
//
//            if self.show{
//
//                Button(action: {
//
//                    self.show.toggle()
//
//                }) {
//
//                    Image(systemName: "pencil").resizable().frame(width: 35, height: 25).padding(18)
//                }
//                .background(Color.blue)
//                .foregroundColor(.white)
//                .clipShape(Circle())
//
//                Button(action: {
//
//                    self.show.toggle()
//
//                }) {
//
//                    Image(systemName: "cart.fill").resizable().frame(width: 35, height: 25).padding(18)
//                }
//                .background(Color.blue)
//                .foregroundColor(.white)
//                .clipShape(Circle())
//
//            }
//
//            Button(action: {
//
//                self.show.toggle()
//
//            }) {
//
//                Image(systemName: "chevron.down").resizable().frame(width: 25, height: 15).padding(22)
//            }
//            .background(Color.blue)
//            .foregroundColor(.white)
//            .clipShape(Circle())
//            .rotationEffect(.init(degrees: self.show ? 180 : 0))
//        }
//        .animation(.spring())
//    }
    
//    @State var show = false
//
//    var body: some View {
//
//        ExpandableFAB(show: $show)
//
//    }
}

//struct FloatingButtonView_Previews: PreviewProvider {
//    static var previews: some View {
//        FloatingButtonView()
//    }
//}

//struct ExpandableFAB : View {
//
//    @Binding var show : Bool
//
//    var body : some View {
//        VStack(spacing : 20) {
//
//            if self.show{
//
//                Button(action: {
//
//                    self.show.toggle()
//
//                }) {
//
//                    Image(systemName: "pencil").resizable().frame(width: 35, height: 25).padding(18)
//                }
//                .background(Color.blue)
//                .foregroundColor(.white)
//                .clipShape(Circle())
//
//                Button(action: {
//
//                    self.show.toggle()
//
//                }) {
//
//                    Image(systemName: "cart.fill").resizable().frame(width: 35, height: 25).padding(18)
//                }
//                .background(Color.blue)
//                .foregroundColor(.white)
//                .clipShape(Circle())
//
//            }
//
//            Button(action: {
//
//                self.show.toggle()
//
//            }) {
//
//                Image(systemName: "chevron.down").resizable().frame(width: 25, height: 15).padding(22)
//            }
//            .background(Color.blue)
//            .foregroundColor(.white)
//            .clipShape(Circle())
//            .rotationEffect(.init(degrees: self.show ? 180 : 0))
//        }
//        .animation(.spring())
//    }
//}
//
//
