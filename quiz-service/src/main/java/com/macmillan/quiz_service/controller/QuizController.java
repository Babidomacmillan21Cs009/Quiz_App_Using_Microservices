package com.macmillan.quiz_service.controller;


import com.macmillan.quiz_service.model.QuestionDTO;
import com.macmillan.quiz_service.model.QuizDto;
import com.macmillan.quiz_service.model.Response;
import com.macmillan.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto) {

        return quizService.createQuiz(quizDto.getCategory(), quizDto.getNumQuestions(), quizDto.getTitle());
    }

    @GetMapping("/getQuiz/{id}")
    public ResponseEntity<List<QuestionDTO>> getQuizQuestions(@PathVariable int id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> getResponse(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizService.calculate(id,responses);
    }
}
