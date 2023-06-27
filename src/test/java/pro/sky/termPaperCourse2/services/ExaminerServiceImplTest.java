package pro.sky.termPaperCourse2.services;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pro.sky.termPaperCourse2.domain.Question;
import pro.sky.termPaperCourse2.exceptions.BadAmountNumbersException;
import pro.sky.termPaperCourse2.interfaces.ExaminerService;
import pro.sky.termPaperCourse2.interfaces.QuestionService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {ExaminerServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ExaminerServiceImplTest {
@Autowired
    private ExaminerService examinerService;
@MockBean
private QuestionService questionService;

    private static Stream<Arguments> numbers() {
        return Stream.of(
                Arguments.of(1),
                Arguments.of(2),
                Arguments.of(3)
        );
    }

    @ParameterizedTest(name = "{index} => amount={0}")
    @MethodSource("numbers")
    void getQuestion(int amount) {
        List<Question> localQuestions = new ArrayList<>();
        List<Question> expectedResult = new ArrayList<>();
        Question one = new Question("Что появляется у старого анекдота?", "Борода");
        Question two = new Question("Кто является автором психоанализа?", "Зигмунд Фрейд");
        Question tree = new Question("Что быстрее: свет или звук?", "Свет");
        if (amount == 1) {
            localQuestions.add(one);
            expectedResult.add(one);
        }
        if (amount == 2) {
            localQuestions.add(one);
            localQuestions.add(two);
            expectedResult.add(one);
            expectedResult.add(two);
        }
        if (amount == 3) {
            localQuestions.add(one);
            localQuestions.add(two);
            localQuestions.add(tree);
            expectedResult.add(one);
            expectedResult.add(two);
            expectedResult.add(tree);
        }

        when(questionService.getRandomQuestion(amount)).thenReturn(localQuestions);
        List<Question> actualResult = examinerService.getQuestion(amount);
        assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest(name = "{index} => numbers={0}")
    @MethodSource("numbers")
    void getQuestionWithBadAmountNumbersException(int numbers) {
        when(questionService.getRandomQuestion(numbers))
                .thenThrow(new BadAmountNumbersException("Столько вопросов нет"));
        String expectedMessage = "Столько вопросов нет";
        BadAmountNumbersException exception = assertThrows(BadAmountNumbersException.class,
                () -> questionService.getRandomQuestion(numbers));
        assertEquals(expectedMessage, exception.getMessage());
    }
}