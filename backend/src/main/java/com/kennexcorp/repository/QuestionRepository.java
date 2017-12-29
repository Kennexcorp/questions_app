package com.kennexcorp.repository;

import com.kennexcorp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by kennexcorp on 12/22/17.
 */
public interface  QuestionRepository extends JpaRepository<Question, Long> {
}
