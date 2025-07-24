package com.khamis.QuizApp.Service;

import com.khamis.QuizApp.Model.Question;
import com.khamis.QuizApp.Model.QuestionWep;
import com.khamis.QuizApp.Model.Quiz;
import com.khamis.QuizApp.Model.Response;
import com.khamis.QuizApp.Repo.QuestionRepo;
import com.khamis.QuizApp.Repo.QuizRepo;
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
    QuizRepo quizRepo;
    @Autowired
    QuestionRepo questionRepo;


    public ResponseEntity<String> createQUIZ(String category, int numQ, String title) {
        List<Question>questions=questionRepo.findRandomQuestionsByCategoryIgnoreCase(category,numQ);
        Quiz quiz= new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepo.save(quiz);
        return new ResponseEntity<>("good", HttpStatus.OK);
    }



    public ResponseEntity<List<QuestionWep>> getQuiz(int id) {
        Optional<Quiz> quiz=quizRepo.findById(id);
        List<Question>questions=quiz.get().getQuestions();
        List<QuestionWep>questionWeps=new ArrayList<>();
        for (Question q:questions){
            QuestionWep questionWep=new QuestionWep(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionWeps.add(questionWep);
        }
        return new  ResponseEntity<>(questionWeps,HttpStatus.OK);
    }

    public ResponseEntity<Integer> SubmitAnswer(int id, List<Response> responses) {
        Optional<Quiz> quiz=quizRepo.findById(id);
        List<Question>questions=quiz.get().getQuestions();
        int right=0;
        int i=0;
        for (Response r:responses){
            if(r.getResponse().equals(questions.get(i).getRightAnswer())){
                right++;
            }
            i++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
