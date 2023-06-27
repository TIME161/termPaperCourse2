package pro.sky.termPaperCourse2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pro.sky.termPaperCourse2.domain.Question;
import pro.sky.termPaperCourse2.exceptions.BadAmountNumbersException;
import pro.sky.termPaperCourse2.interfaces.ExaminerService;
import java.util.List;

@RestController
@RequestMapping("/exam")
public class ExamController {
    @Autowired
    final ExaminerService examinerService;
    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadAmountNumbersException.class)
    public String handleException(BadAmountNumbersException e) {
        return String.format("%s %s", HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @GetMapping(path = "/get/{amount}")
    @ResponseBody
    public List<Question> getQuestions(@PathVariable(required = false) int amount) {
        return examinerService.getQuestion(amount);
    }
}
