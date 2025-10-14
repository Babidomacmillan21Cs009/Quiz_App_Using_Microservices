package com.macmillan.question_service.repository;

import com.macmillan.question_service.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {

    List<Question> findByCategory(String category);


    @Query(value = "SELECT q.id FROM question q WHERE q.category = :categoryName ORDER BY RAND() LIMIT :numOfQuestion", nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(@Param("categoryName") String categoryName, @Param("numOfQuestion") int numOfQuestion);
}
