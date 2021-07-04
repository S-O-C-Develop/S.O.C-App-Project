//
//  HomeView.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/02/26.
//

import SwiftUI

struct HomeView: View {
    
    @State var selectedTab = "house.fill"
    var edges = UIApplication.shared.windows.first?.safeAreaInsets
    
    var body: some View {
        
        VStack(spacing: 0) {
            
            TopBarView()
            
            GeometryReader{_ in
                
                ZStack {
                    // Tab ..
                    //FeedView()
                }
            }
            
            HStack(spacing: 0) {
                ForEach(tabs,id: \.self){ tab in
                    TabButton(title: tab, selectedTab: $selectedTab)
                    
                    if tab != tabs.last{
                        Spacer(minLength: 0)
                    }
                }
            }
            .padding(.horizontal, 30)
            .padding(.bottom, edges!.bottom == 0 ? 15 : edges!.bottom)
            
        }
        .ignoresSafeArea(.all, edges: .bottom)
        .background(Color.black.opacity(0.06).ignoresSafeArea(.all, edges: .all))
    }
}

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        HomeView()
    }
}

struct TabButton : View {
    
    var title : String
    
    @Binding var selectedTab : String
    
    var body: some View {
        
        Button(action: {
            withAnimation{selectedTab = title}
        }){
            VStack(spacing: 6){
                
                // 커스텀 탭바
                CustomShape()
                    .fill(Color("tint").opacity(selectedTab == title ? 1 : 0))
                    .frame(width: 45, height: 6)
                    .padding(.bottom, 10)
                
                Image(systemName: title)
                    .renderingMode(.template)
                    .resizable()
                    .foregroundColor(selectedTab == title ? Color("tint") : Color.black.opacity(0.2))
                    .frame(width:24, height: 24)
                
//                Text(title)
//                    .font(.caption)
//                    .foregroundColor(Color.black.opacity(selectedTab == title ? 0.6 : 0.2))
                
            }
        }
    }
}

// 탭바의 선택바
struct CustomShape : Shape {
    
    func path(in rect: CGRect) -> Path {
        
        let path = UIBezierPath(roundedRect: rect, byRoundingCorners: [.bottomLeft, .bottomRight], cornerRadii: CGSize(width:10, height: 10))
        
        return Path(path.cgPath)
    }
    
}


var tabs = ["house.fill", "doc.text", "person.fill"]

var tabsText = ["Home", "Feed", "Setting"]
