package com.khamis.QuizApp.Controller;

import com.khamis.QuizApp.Model.Question;
import com.khamis.QuizApp.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @GetMapping("all")
    public ResponseEntity<List<Question>> getallquestions(){
        return questionService.getallquestions();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getAllCategory(@PathVariable String category){
        return questionService.findByCategory(category);
    }
    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody  Question question){
       return questionService.addQuestion(question);
    }
}
