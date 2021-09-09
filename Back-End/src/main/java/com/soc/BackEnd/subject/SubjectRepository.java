package com.soc.backend.subject;

import com.soc.backend.config.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    Optional<Subject> findBySubjectIdAndStatus(Long subjectId, Status status);

    Optional<Subject> findByNameAndStatus(String name, Status status);
}
