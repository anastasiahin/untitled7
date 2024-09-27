import java.io.*;
import java.util.*;

public class WordFrequencyCounter {

    public static void main(String[] args) {
        String fileName = "words.txt";
        Map<String, Integer> wordCountMap = countWordFrequency(fileName);
        printWordFrequency(wordCountMap);
    }

    // Метод для підрахунку частоти кожного слова у файлі
    public static Map<String, Integer> countWordFrequency(String fileName) {
        Map<String, Integer> wordCountMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Розділяємо рядок на слова, використовуючи будь-яку кількість пробілів
                String[] words = line.trim().split("\\s+");
                for (String word : words) {
                    wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
                }
            }
        } catch (IOException e) {
            System.out.println("Помилка під час читання файлу: " + e.getMessage());
        }
        return wordCountMap;
    }

    // Метод для виведення частоти слів у порядку спадання частоти
    public static void printWordFrequency(Map<String, Integer> wordCountMap) {
        // Створюємо список з елементів Map і сортуємо його за значенням (частотою)
        List<Map.Entry<String, Integer>> list = new ArrayList<>(wordCountMap.entrySet());
        list.sort((a, b) -> b.getValue().compareTo(a.getValue()));  // сортуємо за частотою

        // Виводимо результат у консоль
        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
