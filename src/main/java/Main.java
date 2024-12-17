import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, представляющий человека.
 */
class Person {
    private int id;
    private String name;
    private String gender;
    private String birthdate; // Исправлено на "birthdate"
    private Division division;
    private double salary;

    /**
     * Конструктор класса Person.
     *
     * @param id        уникальный идентификатор человека
     * @param name      имя человека
     * @param gender    пол человека
     * @param birthdate дата рождения человека
     * @param division  подразделение, к которому принадлежит человек
     * @param salary    зарплата человека
     */
    public Person(int id, String name, String gender, String birthdate, Division division, double salary) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthdate = birthdate;
        this.division = division;
        this.salary = salary;
    }

    /**
     * Возвращает строковое представление объекта Person.
     *
     * @return строка с информацией о человеке
     */
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Gender: " + gender + ", Birthdate: " + birthdate +
                ", Division: " + division.getName() + ", Salary: " + salary;
    }
}

/**
 * Класс, представляющий подразделение.
 */
class Division {
    private int id;
    private String name;

    /**
     * Конструктор класса Division.
     *
     * @param name название подразделения
     */
    public Division(String name) {
        this.id = (int) (Math.random() * 100); // Генерация случайного ID
        this.name = name;
    }

    /**
     * Возвращает название подразделения.
     *
     * @return название подразделения
     */
    public String getName() {
        return name;
    }
}

/**
 * Главный класс приложения для чтения и отображения данных о людях.
 */
public class Main {
    private List<Person> personList = new ArrayList<>();

    /**
     * Читает данные из CSV-файла и заполняет список людей.
     *
     * @param filePath путь к CSV-файлу
     */
    public void readCsvFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Пропустить заголовок, если он есть
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                if (values.length >= 6) { // Убедитесь, что строка содержит достаточно столбцов
                    int id = Integer.parseInt(values[0].trim());
                    String name = values[1].trim();
                    String gender = values[2].trim();
                    String birthdate = values[3].trim();
                    Division division = new Division(values[4].trim());
                    double salary = Double.parseDouble(values[5].trim());
                    Person person = new Person(id, name, gender, birthdate, division, salary);
                    personList.add(person);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Выводит информацию о всех людях в списке.
     */
    public void printPersons() {
        for (Person person : personList) {
            System.out.println(person);
        }
    }

    /**
     * Точка входа в программу.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        Main main = new Main();
        main.readCsvFile("C:\\Users\\Admin\\IdeaProjects\\Laba_4\\src\\main\\resources\\foreign_names.csv");
        main.printPersons();
    }
}
