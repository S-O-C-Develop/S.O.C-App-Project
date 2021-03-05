//
//  Test.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/02/26.
//

import SwiftUI

struct Test: View {
    var body: some View {
        NavigationView{
            VStack{
                Text(/*@START_MENU_TOKEN@*/"Hello, World!"/*@END_MENU_TOKEN@*/)
            }.navigationTitle("안녕하세요")
        }
    }
}

struct Test_Previews: PreviewProvider {
    static var previews: some View {
        Test()
    }
}
