package com.khamis.QuizApp.Model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "question")
@Data
public class Question {

    @Id
    private Integer id;

    @Column(name = "question_title")
    private String questionTitle;

    @Column(name = "option1")
    private String option1;

    @Column(name = "option2")
    private String option2;

    @Column(name = "option3")
    private String option3;

    @Column(name = "option4")
    private String option4;

    @Column(name = "right_answer")
    private String rightAnswer;

    @Column(name = "difficultylevel")
    private String difficultyLevel;

    @Column(name = "category")
    private String category;
}