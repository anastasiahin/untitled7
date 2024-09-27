import java.io.*;
import java.util.ArrayList;
import java.util.List;

class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // Метод для перетворення об'єкта User у формат JSON
    public String toJson() {
        return "{\"name\": \"" + name + "\", \"age\": " + age + "}";
    }
}

public class FileToJsonConverter {

    public static void main(String[] args) {
        String inputFileName = "file.txt";
        String outputFileName = "user.json";
        List<User> users = readUsersFromFile(inputFileName);
        writeUsersToJsonFile(users, outputFileName);
    }

    // Метод для читання користувачів із файлу
    public static List<User> readUsersFromFile(String fileName) {
        List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // Пропускаємо заголовок
                    continue;
                }

                // Поділ рядка на поля
                String[] fields = line.split(" ");
                String name = fields[0];
                int age = Integer.parseInt(fields[1]);

                // Створення об'єкта User і додавання його до списку
                User user = new User(name, age);
                users.add(user);
            }
        } catch (IOException e) {
            System.out.println("Помилка під час читання файлу: " + e.getMessage());
        }
        return users;
    }

    // Метод для запису списку користувачів у форматі JSON
    public static void writeUsersToJsonFile(List<User> users, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("[\n");
            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                writer.write(user.toJson());

                // Додаємо кому, якщо це не останній елемент
                if (i < users.size() - 1) {
                    writer.write(",");
                }
                writer.write("\n");
            }
            writer.write("]");
            System.out.println("Дані успішно записані у " + fileName);
        } catch (IOException e) {
            System.out.println("Помилка під час запису файлу: " + e.getMessage());
        }
    }
}
