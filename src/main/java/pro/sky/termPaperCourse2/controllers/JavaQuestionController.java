package pro.sky.termPaperCourse2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pro.sky.termPaperCourse2.domain.Question;
import pro.sky.termPaperCourse2.exceptions.QuestionAddException;
import pro.sky.termPaperCourse2.interfaces.QuestionService;

import java.util.Collection;

@RestController
@RequestMapping("/exam")
public class JavaQuestionController {
    @Autowired
    private final QuestionService questionService;
    public JavaQuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(QuestionAddException.class)
    public String handleException(QuestionAddException e) {
        return String.format("%s %s", HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @GetMapping(path = "/java/add")
    @ResponseBody
    public Question addQuestion(@RequestParam("question") String question, @RequestParam("answer") String answer) {
        return questionService.add(question, answer);
    }

    @GetMapping(path = "/java/remove")
    @ResponseBody
    public Question removeQuestion(@RequestParam("question") String question, @RequestParam("answer") String answer) {
        return questionService.remove(question, answer);
    }

    @GetMapping(path = "/java")
    @ResponseBody
    public Collection<Question> all() {
        return questionService.getAll();
    }
}
