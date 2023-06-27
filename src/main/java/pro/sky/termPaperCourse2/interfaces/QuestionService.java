package pro.sky.termPaperCourse2.interfaces;

import pro.sky.termPaperCourse2.domain.Question;
import java.util.Collection;
import java.util.List;

public interface QuestionService {
    Question add(String question, String answer);
    Question remove(String question, String answer);
    Collection<Question> getAll();
    List<Question> getRandomQuestion(int amount);
}
