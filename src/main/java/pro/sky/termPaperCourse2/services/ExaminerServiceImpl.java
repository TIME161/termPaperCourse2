package pro.sky.termPaperCourse2.services;

import org.springframework.stereotype.Service;
import pro.sky.termPaperCourse2.domain.Question;
import pro.sky.termPaperCourse2.interfaces.ExaminerService;
import pro.sky.termPaperCourse2.interfaces.QuestionService;

import java.util.List;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public List<Question> getQuestion(int amount) {
        return questionService.getRandomQuestion(amount);
    }
}
