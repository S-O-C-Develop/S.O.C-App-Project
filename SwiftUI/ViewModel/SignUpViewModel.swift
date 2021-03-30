//
//  SignUpViewModel.swift
//  SOCprojectUI
//
//  Created by 박준혁 on 2021/03/15.
//

import Foundation
import Combine
import Alamofire


// MARK: - SignUP
class SignUpViewModel : ObservableObject {
    
    @Published var studentId = ""
    @Published var password = ""
    @Published var re_password = ""
    @Published var useremail = ""
    @Published var username = ""
    
    @Published var inlineErrorForStudentId = ""
    @Published var inlineErrorForPassword = ""
    @Published var inlineErrorForEmail = ""
    @Published var inlineErrorForNickname = ""
    
    @Published var isValid = false
    @Published var isStudentValid = false
    @Published var isPasswordValid = false
    @Published var isEmailValid = false
    @Published var isNicknameValid = false
    
    
    //A-Za-z0-9.- /  A-Za-z
    private var cancellables = Set<AnyCancellable>()
    
    // 비밀번호에 특수문자 입력 되어있는지
    private static let predicate = NSPredicate(format: "SELF MATCHES %@", "^(?=.*[a-z])(?=.*[$@$#!%*?&]).{8,}$")
    
    // MARK: - 학번 유효성 검사
    
    // 학번 글자수
    private var studentIdCountValidPublisher: AnyPublisher<Bool, Never> {
        $studentId
            .debounce(for: 0.8, scheduler: RunLoop.main)
            .removeDuplicates()
            .map { $0.count == 9 }
            .eraseToAnyPublisher()
    }
    
    // 학번 칸 비어있는거
    private var studentIdEmptyValidPublisher: AnyPublisher<Bool, Never> {
        $studentId
            .debounce(for: 0.8, scheduler: RunLoop.main)
            .removeDuplicates()
            .map { $0.isEmpty }
            .eraseToAnyPublisher()
    }
    
    // 학번 입력 조건 결과
    private var studentIdValidPublisher: AnyPublisher<StudentIdStatus, Never> {
        Publishers.CombineLatest(studentIdEmptyValidPublisher, studentIdCountValidPublisher)
            .map {
                if $0 { return StudentIdStatus.empty }
                if !$1 { return StudentIdStatus.studentIdFormWrong }
                return StudentIdStatus.valid
            }
            .eraseToAnyPublisher()
    }
    
    private var formstudentIdValid: AnyPublisher<Bool, Never> {
        Publishers.CombineLatest(studentIdValidPublisher, studentIdCountValidPublisher)
            .map { $0 == .valid && $1 }
            .eraseToAnyPublisher()
    }
    
    // MARK: - 비밀번호 유효성 검사
    
    // 비밀번호 칸 비어있는지 확인
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
    
    // 비밀번호 입력 조건 결과
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
    
    private var formValidPublisher: AnyPublisher<Bool, Never> {
        Publishers.CombineLatest(passwordValidPublisher, re_passwordEqualValidPublisher)
            .map { $0 == .valid && $1 }
            .eraseToAnyPublisher()
    }
    
    // MARK: - 이메일 유효성 검사
    
    // 이메일 칸 비어있는지 확인
    private var emailEmptyValidPublisher: AnyPublisher<Bool, Never> {
        $useremail
            .debounce(for: 0.8, scheduler: RunLoop.main)
            .removeDuplicates()
            .map { !($0.isEmpty) }
            .eraseToAnyPublisher()
    }
    
//    // 이메일 형태로 작성했는지 확인
//    private var emailFromValidPublisher: AnyPublisher<Bool, Never> {
//        $useremail
//            .debounce(for: 0.2, scheduler: RunLoop.main)
//            .removeDuplicates()
//            .map {
//                Self.emailForm.evaluate(with: $0)
//            }
//            .eraseToAnyPublisher()
//    }
    
//    private var emailValidPublisher: AnyPublisher<EmailStatus, Never> {
//        Publishers.CombineLatest(emailEmptyValidPublisher, emailFromValidPublisher)
//            .map {
//                if $0 { return EmailStatus.empty }
//                if !$1 { return EmailStatus.emailFormWrong }
//                return EmailStatus.valid
//            }
//            .eraseToAnyPublisher()
//    }
    
//    private var formemailValidPublisher: AnyPublisher<Bool, Never> {
//        Publishers.CombineLatest(emailValidPublisher, emailFromValidPublisher)
//            .map { $0 == .valid && $1 }
//            .eraseToAnyPublisher()
//    }
    
    // MARK: - 닉네임 유효성 검사
    
    // 닉네임 글자수 제한
    private var usernameValidPublisher: AnyPublisher<Bool, Never> {
        $username
            .debounce(for: 0.8, scheduler: RunLoop.main)
            .removeDuplicates()
            .map { $0.count >= 3 }
            .eraseToAnyPublisher()
    }
    
    // 닉네임 칸 비어있는지 확인
    private var usernameEmptyValidPublisher: AnyPublisher<Bool, Never> {
        $username
            .debounce(for: 0.8, scheduler: RunLoop.main)
            .removeDuplicates()
            .map { $0.isEmpty }
            .eraseToAnyPublisher()
    }
    
    // 닉네임 입력 조건 결과
    private var nicknameValidPublisher: AnyPublisher<NicknameStatus, Never> {
        Publishers.CombineLatest(usernameEmptyValidPublisher, usernameValidPublisher)
            .map {
                if $0 { return NicknameStatus.empty }
                if !$1 { return NicknameStatus.NicknameFormWrong }
                return NicknameStatus.valid
            }
            .eraseToAnyPublisher()
    }
    
    private var formNicknameValidPublisher: AnyPublisher<Bool, Never> {
        Publishers.CombineLatest(nicknameValidPublisher, usernameValidPublisher)
            .map { $0 == .valid && $1 }
            .eraseToAnyPublisher()
    }
    
    
    
    // MARK: - 버튼 활성화 및 오류 문구
    init() {
        formstudentIdValid
            .receive(on: RunLoop.main)
            .assign(to: \.isStudentValid, on: self)
            .store(in: &cancellables)
        
        formValidPublisher
            .receive(on: RunLoop.main)
            .assign(to: \.isPasswordValid, on: self)
            .store(in: &cancellables)
        
        emailEmptyValidPublisher
            .receive(on: RunLoop.main)
            .assign(to: \.isEmailValid, on: self)
            .store(in: &cancellables)
        
        formNicknameValidPublisher
            .receive(on: RunLoop.main)
            .assign(to: \.isNicknameValid, on: self)
            .store(in: &cancellables)
        
        
        studentIdValidPublisher
            .dropFirst()
            .receive(on: RunLoop.main)
            .map { studentIdStatus in
                switch studentIdStatus {
                case .empty:
                    return "학번을 입력하세요."
                case .studentIdFormWrong:
                    return "알맞은 학번을 입력하세요."
                case .valid:
                    return ""
                }
            }
            .assign(to: \.inlineErrorForStudentId, on: self)
            .store(in: &cancellables)
        
        passwordValidPublisher
            .dropFirst()
            .receive(on: RunLoop.main)
            .map { passwordStatus in
                switch passwordStatus {
                case .empty:
                    return "비밀번호를 입력하세요."
                case .notStrongEnough:
                    return "강력한 비밀번호를 입력하세요."
                case .repeatedPasswrodWrong:
                    return "비밀번호가 일치하지 않습니다."
                case .valid:
                    return ""
                }
            }
            .assign(to: \.inlineErrorForPassword, on: self)
            .store(in: &cancellables)
        
//        emailValidPublisher
//            .dropFirst()
//            .receive(on: RunLoop.main)
//            .map { EmailStatus in
//                switch EmailStatus {
//                case .empty:
//                    return "학교 이메일을 입력하세요."
//                case .emailFormWrong:
//                    return "이메일 형식에 맞춰 입력하세요."
//                case .valid:
//                    return ""
//                }
//            }
//            .assign(to: \.inlineErrorForEmail, on: self)
//            .store(in: &cancellables)
        
        nicknameValidPublisher
            .dropFirst()
            .receive(on: RunLoop.main)
            .map { NicknameStatus in
                switch NicknameStatus {
                case .empty:
                    return "닉네임을 입력하세요."
                case .NicknameFormWrong:
                    return "닉네임을 3자 이상 입력하세요."
                case .valid:
                    return ""
                }
            }
            .assign(to: \.inlineErrorForNickname, on: self)
            .store(in: &cancellables)
    }
    
    // MARK: - 회원 가입
    func signUp() {
        
        let singUpurl = "http://soc-project.site/api/sign-up"
        
        let parameters: Parameters = [
              "email": useremail + "@catholic.ac.kr",
              "nickname": username,
              "password": password,
              "studentId": studentId
        ]
        
        AF.request(singUpurl, method: .post, parameters: parameters, encoding: JSONEncoding.default)
            .responseJSON(completionHandler: { response in
                switch response.result {
                case .success(let value):
                    print("서버 연결 success")
                    debugPrint(value)
                    do {
                        let data = try JSONSerialization.data(withJSONObject: value, options: .prettyPrinted)
                        let SignInData = try JSONDecoder().decode(JsonResult.self, from: data)
                        
                        if SignInData.code == 0 {
                            
                            print("message: \(SignInData.message)")
                            print("message: \(SignInData.success)")
                            
                        }
                        //debugPrint(value)
                    } catch (let err){
                        print(err.localizedDescription)
                    }
                    
                case .failure:
                    print("failure")
                }
            })
    }
    
}

