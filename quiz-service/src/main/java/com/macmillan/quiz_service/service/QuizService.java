package com.macmillan.quiz_service.service;

import com.macmillan.quiz_service.model.QuestionDTO;
import com.macmillan.quiz_service.model.Quiz;
import com.macmillan.quiz_service.model.Response;
import com.macmillan.quiz_service.quizInterface.QuizInterface;
import com.macmillan.quiz_service.repository.QuizRepo;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizInterface quizInterface;

    @Autowired
    private QuizRepo quizRepo;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Integer> questions = quizInterface.getQuestionsForQuiz(category,numQ).getBody();

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizRepo.save(quiz);

        return new ResponseEntity<>("successfully created quiz", HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionDTO>> getQuizQuestions(int id) {
        Quiz quiz = quizRepo.findById(id).get();
        List<Integer> questionIds = quiz.getQuestionIds();
        ResponseEntity<List<QuestionDTO>> questions = quizInterface.getQuestionsFromId(questionIds);
        return questions;
    }

    public ResponseEntity<Integer> calculate(Integer id, List<Response> responses) {
        Optional<Quiz> existingQuiz = quizRepo.findById(id);
        if(existingQuiz.isPresent()){
            ResponseEntity<Integer> score = quizInterface.getScore(responses);
            return score;
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
