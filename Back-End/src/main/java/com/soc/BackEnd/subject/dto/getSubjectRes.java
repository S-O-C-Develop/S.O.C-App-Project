package com.soc.backend.subject.dto;

import com.soc.backend.config.enums.Status;
import com.soc.backend.subject.Subject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetSubjectRes {

    private Long subjectId;

    private Status status;

    private Integer grade;

    private Integer semester;

    private String name;

    public GetSubjectRes(Subject subject) {
        this.subjectId = subject.getSubjectId();
        this.status = subject.getStatus();
        this.grade = subject.getGrade();
        this.semester = subject.getSemester();
        this.name = subject.getName();
    }


}
