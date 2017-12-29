package com.kennexcorp.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kennexcorp.model.Question;
import com.kennexcorp.model.User;
import com.kennexcorp.service.LoginService;
import com.kennexcorp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/questions_api")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping(value = "/add_question", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public @ResponseBody void addQuestion(@ModelAttribute Question question, HttpServletResponse response) {

        questionService.addQuestion(question);

        try {
            response.sendRedirect("http://localhost:8081/#/question_upload");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping(value = "/edit_question")
    public @JsonFormat Question editQuestion(@RequestParam String id) {


        return questionService.getQuestion(Long.parseLong(id));
    }

    @GetMapping(value = "/get_questions")
    public @JsonFormat List<Question> getQuestions(@RequestParam String id) {
        return questionService.getUserQuestions(id);
    }

    @GetMapping(value = "/delete_question")
    public @ResponseBody String deleteQuestion(@RequestParam String id, HttpServletResponse response) {
        questionService.deleteQuestion(id);


        return "Question Deleted";
    }

    @PostMapping(value = "/update_question", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String updateQuestion(@ModelAttribute Question question, HttpServletResponse response) {
        questionService.updateQuestion(question);

        try {
            response.sendRedirect("http://localhost:8081/#/question_upload");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Question updated";
    }

    @GetMapping(value = "/test")
    public @ResponseBody String test() {
        return "It works";
    }
}
