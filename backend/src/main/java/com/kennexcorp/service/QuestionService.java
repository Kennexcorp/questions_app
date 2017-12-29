package com.kennexcorp.service;

import com.kennexcorp.model.Question;
import com.kennexcorp.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by kennexcorp on 12/22/17.
 */
@Service
@Transactional
public class QuestionService {

    @PersistenceContext
    private EntityManager entityManager;

    public void addQuestion(Question question) {

        entityManager.persist(question);
    }

    public Question getQuestion(Long id) {
        Question question = entityManager.find(Question.class, id);

        return question;
    }

    public List<Question> getUserQuestions(String id) {
        Query query = entityManager.createQuery("select question from Question question where question.user_id = :id");
        query.setParameter("id", id);

        return query.getResultList();
    }

    public void deleteQuestion(String id) {
        Question question = getQuestion(Long.parseLong(id));
        entityManager.remove(question);
    }

    public void updateQuestion(Question question) {
        entityManager.merge(question);
    }
}
