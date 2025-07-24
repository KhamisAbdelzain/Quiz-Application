package com.khamis.QuizApp.Service;

import com.khamis.QuizApp.Model.Question;
import com.khamis.QuizApp.Repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionRepo questionRepo;
    public ResponseEntity<List<Question>>getallquestions(){
        try {
            return new ResponseEntity<>(questionRepo.findAll(), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(questionRepo.findAll(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<List<Question>> findByCategory(String category) {
        try {
            return new ResponseEntity<>(questionRepo.findByCategoryIgnoreCase(category),HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(questionRepo.findByCategoryIgnoreCase(category),HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String>  addQuestion(Question question) {
        questionRepo.save(question);
        try {
            return new ResponseEntity<>("Sucess",HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Faild",HttpStatus.BAD_REQUEST);

    }
}
