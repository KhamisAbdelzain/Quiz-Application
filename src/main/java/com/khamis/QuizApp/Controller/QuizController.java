package com.khamis.QuizApp.Controller;

import com.khamis.QuizApp.Model.Question;
import com.khamis.QuizApp.Model.QuestionWep;
import com.khamis.QuizApp.Model.Quiz;
import com.khamis.QuizApp.Model.Response;
import com.khamis.QuizApp.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizService quizService;
    @PostMapping("create")
    public ResponseEntity<String>createQuiz(@RequestParam String category,@RequestParam int numQ,@RequestParam String title){
        quizService.createQUIZ(category,numQ,title);
        return new  ResponseEntity<>("Secuess",HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWep>> findQuizById(@PathVariable int id){

        return quizService.getQuiz(id);
    }
    @PostMapping("submit/{id}")
    public ResponseEntity<Integer>submitAnswers(@PathVariable int id, @RequestBody   List<Response>responses){
        return quizService.SubmitAnswer(id,responses);
    }

}
