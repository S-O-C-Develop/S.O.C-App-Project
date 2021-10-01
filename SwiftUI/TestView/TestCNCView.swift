//
//  TestCNCView.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/09/27.
//

import SwiftUI

struct TestCNCView: View {
    
    @State var selectedPage = 1
    
    var body: some View {
        NavigationView{
            VStack{
                TestTopBarView(selectedPage: $selectedPage)
                    .padding()
                
                TestTopMenuView(pageNum: selectedPage)
                
                Spacer()
            }
        }
    }
}

struct TestCNCView_Previews: PreviewProvider {
    static var previews: some View {
        TestCNCView()
    }
}
