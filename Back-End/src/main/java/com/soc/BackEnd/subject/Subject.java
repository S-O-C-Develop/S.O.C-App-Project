package com.soc.backend.subject;

import com.soc.backend.config.BaseTimeEntity;
import com.soc.backend.config.enums.Status;
import com.soc.backend.subject.dto.CreateSubjectReq;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.soc.backend.config.enums.Status.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Subject extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subjectId;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Integer grade;

    private Integer semester;

    private String name;

    public Subject(CreateSubjectReq dto) {
        this.status = VALID;
        this.grade = dto.getGrade();
        this.semester = dto.getSemester();
        this.name = dto.getName();
    }

}
