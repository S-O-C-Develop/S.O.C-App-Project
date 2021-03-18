//
//  SignUpViewModel.swift
//  SOCAppProject
//
//  Created by 박준혁 on 2021/03/11.
//

import Foundation
import Combine

// viewModel
class SignUpViewModel : ObservableObject {
    
    @Published var studentId = ""
    @Published var password = ""
    @Published var re_password = ""
    @Published var useremail = ""
    @Published var username = ""
    
    @Published var inlineErrorForPassword = ""
    
    @Published var isValid = false
    
    private var cancellables = Set<AnyCancellable>()
    
    // 비밀번호에 특수문자 입력 되어있는지
    private static let predicate = NSPredicate(format: "SELF MATCHES %@", "^(?=.*[a-z])(?=.*[$@$#!%*?&]).{8,}$")
    
    // 학번 글자수
    private var studentIdValidPublisher: AnyPublisher<Bool, Never> {
        $studentId
            .debounce(for: 0.8, scheduler: RunLoop.main)
            .removeDuplicates()
            .map { $0.count == 9 }
            .eraseToAnyPublisher()
    }
    
    // 비밀번호 칸 비어있는거
    private var passwordEmptyValidPublisher: AnyPublisher<Bool, Never> {
        $password
            .debounce(for: 0.8, scheduler: RunLoop.main)
            .removeDuplicates()
            .map { $0.isEmpty }
            .eraseToAnyPublisher()
    }
    
    // 비밀번호 확인 작업
    private var re_passwordEqualValidPublisher: AnyPublisher<Bool, Never> {
        Publishers.CombineLatest($password, $re_password)
            .debounce(for: 0.2, scheduler: RunLoop.main)
            .map { $0 == $1 }
            .eraseToAnyPublisher()
    }
    
    // 비밀번호가 강력한지
    private var passwordStrongValidPublisher: AnyPublisher<Bool, Never> {
        $password
            .debounce(for: 0.2, scheduler: RunLoop.main)
            .removeDuplicates()
            .map {
                Self.predicate.evaluate(with: $0)
            }
            .eraseToAnyPublisher()
    }
    
    // 여러 로직을 합친 비밀번호
    private var passwordValidPublisher: AnyPublisher<PasswordStatus, Never> {
        Publishers.CombineLatest3(passwordEmptyValidPublisher, passwordStrongValidPublisher, re_passwordEqualValidPublisher)
            .map {
                if $0 { return PasswordStatus.empty }
                if !$1 { return PasswordStatus.notStrongEnough }
                if !$2 { return PasswordStatus.repeatedPasswrodWrong }
                return PasswordStatus.valid
            }
            .eraseToAnyPublisher()
    }
    
    // 닉네임 글자수 제한
    private var usernameValidPublisher: AnyPublisher<Bool, Never> {
        $username
            .debounce(for: 0.8, scheduler: RunLoop.main)
            .removeDuplicates()
            .map { $0.count >= 3 }
            .eraseToAnyPublisher()
    }
    
    private var formValidPublisher: AnyPublisher<Bool, Never> {
        Publishers.CombineLatest(passwordValidPublisher, studentIdValidPublisher)
            .map { $0 == .valid && $1 }
            .eraseToAnyPublisher()
    }
    
    init() {
        formValidPublisher
            .receive(on: RunLoop.main)
            .assign(to: \.isValid, on: self)
            .store(in: &cancellables)
        
        passwordValidPublisher
            .dropFirst()
            .receive(on: RunLoop.main)
            .map { passwordStatus in
                switch passwordStatus {
                case .empty:
                    return "비밀번호를 입력하세요"
                case .notStrongEnough:
                    return "강력한 비밀번호를 입력하세요"
                case .repeatedPasswrodWrong:
                    return "비밀번호가 일치하지 않습니다"
                case .valid:
                    return ""
                }
            }
            .assign(to: \.inlineErrorForPassword, on: self)
            .store(in: &cancellables)
    }
    
    
    
}
