//
//  ScrollRefreshable.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/10/01.
//

import SwiftUI

struct ScrollRefreshable<Content: View>: View {
    
    var content: Content
    var onRefresh: () -> ()
    
    init(@ViewBuilder content: @escaping () -> Content, onRefresh: @escaping () -> ()){
        self.content = content()
        self.onRefresh = onRefresh
    }
    
    var body: some View {
        if #available(iOS 15.0, *) {
            List{
                content
                    .listRowSeparatorTint(.clear)
                    .listRowBackground(Color.clear)
                    .listRowInsets(EdgeInsets(top: 0, leading: 0, bottom: 0, trailing: 0))
            }
            .listStyle(.plain)
            .refreshable {
                onRefresh()
            }
        } else {
            // Fallback on earlier versions
        }
    }
}

struct ScrollRefreshable_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
