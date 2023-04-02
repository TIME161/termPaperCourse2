package pro.sky.termPaperCourse2.interfaces;

import pro.sky.termPaperCourse2.domain.Question;
import java.util.List;

public interface ExaminerService {
    List<Question> getQuestion(int amount);
}
