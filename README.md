
# Quiz App - Spring Boot Backend

## Overview
A Spring Boot-based quiz application that allows creating quizzes from questions in specific categories, taking quizzes, and submitting answers for scoring. The system supports full question management with categorization.

## Features
- Create quizzes by selecting a category and number of questions
- Get all available questions or filter by category
- Submit quiz answers and receive a score
- Add new questions to the database
- RESTful API endpoints for all operations

## Technologies Used
- **Spring Boot** 3.x
- **Spring Web** - for REST API development
- **Spring Data JPA** - for database operations
- **PostgreSQL** - relational database
- **Lombok** - for reducing boilerplate code
- **Maven** - dependency management

## API Endpoints

### Quiz Controller (`/quiz`)
| Endpoint | Method | Description | Parameters | Request Body | Response |
|----------|--------|-------------|------------|--------------|----------|
| `/create` | POST | Create a new quiz | `category`: String, `numQ`: int, `title`: String | None | Success message |
| `/get/{id}` | GET | Get questions for a specific quiz | `id`: int (quiz ID) | None | List of `QuestionWep` |
| `/submit/{id}` | POST | Submit answers for a quiz | `id`: int (quiz ID) | List of `Response` objects | Integer score |

### Question Controller (`/questions`)
| Endpoint | Method | Description | Parameters | Request Body | Response |
|----------|--------|-------------|------------|--------------|----------|
| `/all` | GET | Get all questions | None | None | List of `Question` |
| `/category/{category}` | GET | Get questions by category | `category`: String | None | List of `Question` |
| `/add` | POST | Add a new question | None | `Question` object | Success message |

## Models

### Question
```java
public class Question {
    private Integer id;
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String rightAnswer;
    private String difficultyLevel;
    private String category;
}
```

### QuestionWep
```java
public class QuestionWep {
    private Integer id;
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
}
```

### Response
```java
public class Response {
    private Integer id;
    private String response;
}
```

## Example Requests

### Create a Quiz
```bash
curl -X POST "http://localhost:8080/quiz/create?category=Science&numQ=5&title=Science%20Quiz"
```

### Get Quiz Questions
```bash
curl -X GET "http://localhost:8080/quiz/get/1"
```

### Submit Quiz Answers
```bash
curl -X POST -H "Content-Type: application/json" -d '[{"id":1,"response":"A"},{"id":2,"response":"B"}]' "http://localhost:8080/quiz/submit/1"
```

### Get All Questions
```bash
curl -X GET "http://localhost:8080/questions/all"
```

### Add New Question
```bash
curl -X POST -H "Content-Type: application/json" -d '{
    "questionTitle": "What is the capital of France?",
    "option1": "London",
    "option2": "Paris",
    "option3": "Berlin",
    "option4": "Madrid",
    "rightAnswer": "Paris",
    "difficultyLevel": "Easy",
    "category": "Geography"
}' "http://localhost:8080/questions/add"
```

## Setup Instructions
1. Clone the repository
2. Configure PostgreSQL connection in `application.properties`
3. Run the application:
```bash
mvn spring-boot:run
```

## Future Enhancements
- Add user authentication
- Implement time-limited quizzes
- Add question difficulty weighting for scoring
- Implement pagination for question lists
- Add question validation before saving
