package pro.sky.termPaperCourse2.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pro.sky.termPaperCourse2.domain.Question;
import pro.sky.termPaperCourse2.exceptions.QuestionAddException;
import pro.sky.termPaperCourse2.interfaces.QuestionService;
import java.util.Collection;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {JavaQuestionService.class})
@ExtendWith(SpringExtension.class)
class JavaQuestionServiceTest {

    @Autowired
    QuestionService questionService;

    private static Stream<Arguments> questions() {
        return Stream.of(
                Arguments.of("Вопрос", "Вопрос"),
                Arguments.of("Вопросик", "Вопросик"),
                Arguments.of("Вопросы", "Вопросы")
        );
    }

    private static Stream<Arguments> questionsWeHave() {
        return Stream.of(
                Arguments.of("Что появляется у старого анекдота?", "Борода"),
                Arguments.of("Кто является автором психоанализа?", "Зигмунд Фрейд"),
                Arguments.of("Что быстрее: свет или звук?", "Свет")
        );
    }

    @ParameterizedTest(name = "{index} => question={0}, answer={1}")
    @MethodSource("questions")
    void add(String question, String answer) {
        Question expectedResult = new Question(question, answer);
        Question actualResult = questionService.add(question, answer);
        assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest(name = "{index} => question={0}, answer={1}")
    @MethodSource("questionsWeHave")
    void addWithException(String question, String answer) {
        Exception exception = assertThrows(QuestionAddException.class, () -> questionService.add(question, answer));
        String expectedMessege = "Такой вопрос уже имеется";
        assertEquals(expectedMessege, exception.getMessage());
    }

    @ParameterizedTest(name = "{index} => question={0}, answer={1}")
    @MethodSource("questionsWeHave")
    void remove(String question, String answer) {
        Question expectedResult = new Question(question, answer);
        Question actualResult = questionService.remove(question, answer);
        assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest(name = "{index} => question={0}, answer={1}")
    @MethodSource("questions")
    void removeWithException(String question, String answer) {
        Exception exception = assertThrows(QuestionAddException.class, () -> questionService.remove(question, answer));
        String expectedMessege = "Вопроса нет в списке";
        assertEquals(expectedMessege, exception.getMessage());
    }

    @Test
    void getAll() {
        Collection<Question> expectedResult = pro.sky.termPaperCourse2.services.JavaQuestionService.questions;
        Collection<Question> actualResult = questionService.getAll();
        assertEquals(expectedResult, actualResult);
    }

}