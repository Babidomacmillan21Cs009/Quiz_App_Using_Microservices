package com.macmillan.question_service.service;

import com.macmillan.question_service.model.Question;
import com.macmillan.question_service.model.QuestionDTO;
import com.macmillan.question_service.model.Response;
import com.macmillan.question_service.repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepo questionRepo;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try{
            return new ResponseEntity<>(questionRepo.findAll(), HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try{
            return new ResponseEntity<>(questionRepo.findByCategory(category),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> addQuestion(Question question) {
        questionRepo.save(question);
        return new ResponseEntity<>("Successfully added ", HttpStatus.CREATED);
    }

    public ResponseEntity<String> updateQuestion(Question question) {
        try{
            Question question1 = questionRepo.findById(question.getId())
                    .orElse(null);
            if (question1 == null) return new ResponseEntity<>("Question Not Found",HttpStatus.BAD_REQUEST);

            question1.setQuestionTitle(question.getQuestionTitle());
            question1.setCategory(question.getCategory());
            question1.setDifficultylevel(question.getDifficultylevel());
            question1.setOption1(question.getOption1());
            question1.setOption2(question.getOption2());
            question1.setOption3(question.getOption3());
            question1.setOption4(question.getOption4());
            question1.setRightAnswer(question.getRightAnswer());
            questionRepo.save(question1);
            return new ResponseEntity<>("Successfully Updated", HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Error Occured " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> deleteQuestion(Integer quesId) {
        try{
            Question existing = questionRepo.findById(quesId).orElse(null);
            if (existing != null){
                questionRepo.delete(existing);
                return new ResponseEntity<>("Successfully Deleted", HttpStatus.OK);
            }
            else
                return new ResponseEntity<>("Question Not Found", HttpStatus.NOT_FOUND);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Error Occured ",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Integer>> getQuestionForQuiz(String categoryName, Integer numOfQuestion) {
        List<Integer> questions = questionRepo.findRandomQuestionsByCategory(categoryName, numOfQuestion);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionDTO>> getQuestionsFromId(List<Integer> questionIds) {
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

        for (Integer id: questionIds){
            questions.add(questionRepo.findById(id).get());
        }

        for (Question question : questions){
            QuestionDTO dtoList = new QuestionDTO();
            dtoList.setId(question.getId());
            dtoList.setQuestionTitle(question.getQuestionTitle());
            dtoList.setOption1(question.getOption1());
            dtoList.setOption2(question.getOption2());
            dtoList.setOption3(question.getOption3());
            dtoList.setOption4(question.getOption4());
            questionDTOS.add(dtoList);
        }

        return new ResponseEntity<>(questionDTOS, HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int right = 0;

        for (Response response : responses){
            Question question = questionRepo.findById(response.getId()).get();
            if (response.getResponse().equals(question.getRightAnswer())){
                right++;
            }
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}














