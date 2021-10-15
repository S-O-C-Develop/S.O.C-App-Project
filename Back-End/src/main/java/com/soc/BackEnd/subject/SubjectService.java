package com.soc.backend.subject;

import com.soc.backend.board.dto.PostBoardReq;
import com.soc.backend.config.enums.Status;
import com.soc.backend.config.response.exception.CustomException;
import com.soc.backend.config.response.exception.CustomExceptionStatus;
import com.soc.backend.subject.dto.CreateSubjectReq;
import com.soc.backend.subject.dto.GetSubjectRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.soc.backend.config.enums.Status.*;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    @Transactional
    public Long createSubject(CreateSubjectReq dto) {
        Optional<Subject> optionalSubject = subjectRepository.findByNameAndStatus(dto.getName(), VALID);
        if (optionalSubject.isPresent()) throw new CustomException(CustomExceptionStatus.ALREADY_EXIST_SUBJECT);
        Subject subject = new Subject(dto);
        Subject save = subjectRepository.save(subject);
        return save.getSubjectId();
    }

    public GetSubjectRes getSubject(Long subjectId) {
        Subject subject = subjectRepository.findBySubjectIdAndStatus(subjectId, VALID)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.NOT_EXIST_SUBJECT));
        return new GetSubjectRes(subject);
    }
}
