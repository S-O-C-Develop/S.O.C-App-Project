//
//  WritePageView.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/08/03.
//

import SwiftUI
import PhotosUI

struct WritePageView: View {
    
    @Environment (\.presentationMode) var presentationMode
    @ObservedObject var viewModel = PostViewModel()
    
    @State var textEditor: String = ""
    @FocusState private var isFocused: Bool // 다른 화면 눌렀을 떄 키보드 숨기기
    
    @State var images : [UIImage] = []
    @State var picker = false
    
    @State private var showingPopover = false // 조건 검색
    
    var boardId : Int
    
//    @State var posting : Bool?
    
    var body: some View {
        
        ZStack{
        
            VStack(alignment: .leading) {
                
                DetailTopBarView(viewModel: viewModel, postAccountId: viewModel.detailResult.accountId, kategorie_1: "글쓰기", kategorie_2: getKategorie_2(boardId), boradId: boardId)
                    .foregroundColor(.black)
                
                if boardId == 1 {
                    HStack{
                        
                        Button(action: {
                            
                            showingPopover = true
                            
                        }) {
                            
                            HStack{
                                Text("과목")
                                    .font(.headline)
                                    .padding(.leading, 16)
                                
                                Image(systemName: "chevron.forward")
                                    
                            }
                            .foregroundColor(.black)
                        }
                        .popover(isPresented: $showingPopover) {
                            ConditionSearchView(viewModel: viewModel, selectedView: 1)
                        }
                        
                        if viewModel.conditionSearching {
                            Text("\(viewModel.selectedGrade) - \(viewModel.selectedSemester) - \(viewModel.selectedSubject)")
                        }
                        
                    }
                    .padding([.top, .bottom], 10)
                }
                
                HStack{
                    
                    VStack{
                        TextField("제목", text: $viewModel.title)
                        
                        Divider()
                            .frame(height: 2)
    
                    }
                    
                    Button(action: {
                        
                        picker.toggle()
                        
                    }) {
                        Image(systemName: "camera")
                            .foregroundColor(.black)
                    }
                }
                .padding()
                
                
                CustomTextEditor.init(placeholder: "내용을 입력하세요.", text: $viewModel.contents)
                    .font(.body)
                    .background(Color(UIColor.systemGray6))
                    .accentColor(.green)
                    .frame(height: 200)
                    .cornerRadius(8)
                    .padding()
                    .focused($isFocused)
                
                if !images.isEmpty {
                    VStack(alignment: .leading
                    ){
                        
                        Text("사진")
                            .font(.headline)
                            .padding(.leading, 20)
                        
                        HStack(spacing: 15){
                            
                            ForEach(images, id: \.self) { img in
                                Image(uiImage: img)
                                    .resizable()
                                    .frame(width: 150, height: 150)
                                    .cornerRadius(20)
                            }
                        }
                        .padding()
                    }
                }
            
                Spacer()
            }
            .navigationBarHidden(true)
        
            if viewModel.isLoding {
                GeometryReader{ geo in
                    
                    LoadingScreen()
                        .position(x:geo.frame(in:.global).midX,y:geo.frame(in:.global).midY)
                        
                }
                .background(Color.black.opacity(0.45).edgesIgnoringSafeArea(.all))
            }
        }
        .onTapGesture {
            isFocused = false
        }
        .sheet(isPresented: $picker) {
            ImagePicker(images: $images, picker: $picker)
        }
    }
    
    func getKategorie_2(_ boardId : Int) -> String {
        
        switch boardId {
        case 1:
            return  "문제"
            
        case 2:
            return  "정보"
            
        case 3:
            return  "학사"
            
        case 4:
            return  "장학"
            
        case 5:
            return  "학과"
            
        case 6:
            return  "SOC"
            
        case 7:
            return  "교내"
            
        case 8:
            return  "교외"
            
        case 9:
            return  "공모전"
            
        case 10:
            return  "스터디"
            
        case 11:
            return  "프로젝트"
            
        default :
            return ""
        }
    }
    
}

struct WritePageView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}


struct CustomTextEditor: View {
    
    let placeholder: String
    @Binding var text: String
    let internalPadding: CGFloat = 5
    
    var body: some View {
        ZStack(alignment: .topLeading) {
            if text.isEmpty  {
                Text(placeholder)
                    .foregroundColor(Color.primary.opacity(0.25))
                    .padding(EdgeInsets(top: 7, leading: 4, bottom: 0, trailing: 0))
                    .padding(internalPadding)
            }
            TextEditor(text: $text)
                .padding(internalPadding)
        }.onAppear() {
            UITextView.appearance().backgroundColor = .clear
        }.onDisappear() {
            UITextView.appearance().backgroundColor = nil
        }
    }
}

struct ImagePicker : UIViewControllerRepresentable{
    
    func makeCoordinator() -> Coordinator {
        return ImagePicker.Coordinator(parent1: self)
    }
    
    @Binding var images : [UIImage]
    @Binding var picker : Bool
    
    func makeUIViewController(context: Context) -> PHPickerViewController {
        
        var config = PHPickerConfiguration()
        
        // you can also select videos using this picker....
        config.filter = .images
        
        // 0 is used for multiple selection...
        config.selectionLimit = 0
        let picker = PHPickerViewController(configuration: config)
        
        // addigning delegate...
        picker.delegate = context.coordinator
        return picker
    }
    
    func updateUIViewController(_ uiViewController: PHPickerViewController, context: Context) {
        
    }
    
    class Coordinator: NSObject,PHPickerViewControllerDelegate{
        
        var parent : ImagePicker
        
        init(parent1: ImagePicker) {
            parent = parent1
        }
        
        func picker(_ picker: PHPickerViewController, didFinishPicking results : [PHPickerResult]) {
            
            // closing picker...
            
            parent.picker.toggle()
            
            for img in results {
                
                // checking whether the image can be loaded....
                
                if img.itemProvider.canLoadObject(ofClass: UIImage.self) {
                    
                    // retreving the selected Image...
                    
                    img.itemProvider.loadObject(ofClass: UIImage.self) { (image, err) in
                        
                        guard let image1 = image else {
                            print(err)
                            return
                        }
                        
                        // appending image...
                        
                        self.parent.images.append(image1 as! UIImage)
                    }
                }
                else {
                    
                    // cannot be loaded
                    print("cannot be loaded")
                }
            }
        }
    }
}
