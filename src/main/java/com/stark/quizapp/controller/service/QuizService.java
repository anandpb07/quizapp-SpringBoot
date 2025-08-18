package com.stark.quizapp.controller.service;


import com.stark.quizapp.dao.QuestionDao;
import com.stark.quizapp.dao.QuizDao;
import com.stark.quizapp.model.Question;
import com.stark.quizapp.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;


import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        PageRequest pageable = PageRequest.of(0, numQ); // Page 0, with size = numQ
        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, pageable);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

    return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }
}


