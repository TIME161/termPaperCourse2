package pro.sky.termPaperCourse2.services;

import org.springframework.stereotype.Service;
import pro.sky.termPaperCourse2.domain.*;
import pro.sky.termPaperCourse2.exceptions.BadAmountNumbersException;
import pro.sky.termPaperCourse2.exceptions.QuestionAddException;
import pro.sky.termPaperCourse2.interfaces.QuestionService;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;



@Service
public class JavaQuestionService implements QuestionService {
    public static List<Question> questions = new ArrayList<>(Arrays.asList(
            new Question("Первый элемент в таблице Менделеева — это…", "Водород"),
            new Question("Кто открыл Америку?", "Христофор Колумб"),
            new Question("Какая планета расположена ближе всех к Солнцу?", "Меркурий"),
            new Question("Сколько сопряжений имеет глагол в русском языке?", "2"),
            new Question("Сколько клеток на шахматной доске?", "64"),
            new Question("Что быстрее: свет или звук?", "Свет"),
            new Question("Расшифруйте аббревиатуру СССР", "Союз Советских Социалистических Республик"),
            new Question("Как часто проводятся Олимпийские игры?", "Раз в четыре года"),
            new Question("В каком году в России отменили крепостное право?", "В 1861 году"),
            new Question("Где находится мыс Доброй Надежды?", "Африка"),
            new Question("В какой стране национальной валютой является Вона?", "Южная Корея"),
            new Question("Назовите синоним слова «сентиментальный»", "Чувствительный"),
            new Question("13-21=?", "-8"),
            new Question("Что появляется у старого анекдота?", "Борода"),
            new Question("Сколько мышц в теле человека?", "Около 650"),
            new Question("В какой стране родился первооткрыватель Васко да Гама?", "Португалия"),
            new Question("Сколько человек живет на планете Земля?", "Около 7,5 млрд"),
            new Question("Кто является автором психоанализа?", "Зигмунд Фрейд"),
            new Question("Человек состоит из воды на...", "65-70%")
    ));

    @Override
    public Question add(String question, String answer) {
        Question localQuestion = new Question(question, answer);
        if (questions.contains(localQuestion)) {
            throw new QuestionAddException("Такой вопрос уже имеется");
        } else {
            questions.add(localQuestion);
        }
        return localQuestion;
    }

    @Override
    public Question remove(String question, String answer) {
        Question localQuestion = new Question(question, answer);
        if (questions.contains(localQuestion)) {
            questions.remove(localQuestion);
        } else {
            throw new QuestionAddException("Вопроса нет в списке");
        }
        return localQuestion;
    }

    @Override
    public Collection<Question> getAll() {
        return questions;
    }

    @Override
    public List<Question> getRandomQuestion(int amount) {
        if (amount > questions.size()) {throw new BadAmountNumbersException("Столько вопросов нет");
        } else {
            List<Question> generatedQuestions = new ArrayList<>();
            Set<Integer> indexes = new HashSet<>();
            ThreadLocalRandom random = ThreadLocalRandom.current();
            while (indexes.size() < amount) {
                indexes.add(random.nextInt(questions.size()));
            }
            for (int index : indexes) {
                generatedQuestions.add(questions.get(index));
            }
            return generatedQuestions;
        }
    }
}