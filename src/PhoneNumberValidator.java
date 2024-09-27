import java.io.*;
import java.util.regex.*;
public class PhoneNumberValidator {
    public static void main(String[] args) {
        String fileName = "file.txt";
        printValidPhoneNumbers(fileName);
    }
    public static void printValidPhoneNumbers(String fileName) {
        String regex = "\\(\\d{3}\\) \\d{3}-\\d{4}|\\d{3}-\\d{3}-\\d{4}";
        // Компіляція регулярного виразу
        Pattern pattern = Pattern.compile(regex);
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            // Читання файлу рядок за рядком
            while ((line = br.readLine()) != null) {
                // Перевірка відповідності регулярному виразу
                Matcher matcher = pattern.matcher(line.trim());
                if (matcher.matches()) {
                    System.out.println(line);  // Виведення валідного номера
                }
            }
        } catch (IOException e) {
            System.out.println("Помилка під час читання файлу: " + e.getMessage());
        }
    }
}
