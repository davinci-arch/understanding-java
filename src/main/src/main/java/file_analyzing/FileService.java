package main.java.file_analyzing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * FileService provides various methods for working with text file.
 * Specifically analyze text and provides some information such as: how many words,
 * symbols, most often words and so on.
 *
 * @author davinci-arch
 * @version %I%, %G%
 */
public class FileService {

    private final Path file;

    /**
     * Creates an instance of this class and initialize a variable.
     *
     * @param file object Path that leads to text file that going to be use in analyzing
     */
    public FileService(Path file) {
        this.file = file;
    }

    /**
     * Reads all text from file and then saves it in variable for further use.
     * Saves in variable with new line symbol like in file
     *
     * @return string that contains all content of the file
     * @throws IOException if an I/O error occurs
     */
    private String readFileContent() throws IOException {
        StringBuilder content = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(file.toFile()))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!content.isEmpty()) {
                    content.append("\n");
                }
                content.append(line);
            }
        }
        return content.toString();
    }

    /**
     * counts exactly amount of words that file contains.
     *
     * @return amount of words in file
     * @throws IOException if an I/O error occurs
     */
    public int countWords() throws IOException {
        var content = readFileContent();
        return content.split("\\s+").length;
    }

    /**
     * counts how many lines does file contain.
     * Uses the native method of class String to count lines
     *
     * @return number of lines in file
     * @throws IOException if an I/O error occurs
     */
    public int countLines() throws IOException {
        var content = readFileContent();
        return (int) content.lines().count();
    }

    /**
     * counts how many symbols in file. Includes special symbols like \n \t and whitespaces
     *
     * @return amount of symbols in the file
     * @throws IOException
     */
    public int countSymbols() throws IOException {
        var content = readFileContent();
        return content.length();
    }

    /**
     * Finds a specific word's position in the file. Position count starts from 0.
     *
     * @param word uses as a searching word in the file and sensitive to register
     * @return <code>-1</code> if word wasn't found in file, or it's actual position in file
     * @throws IOException if I/O error occurs
     */
    public int posInText(String word) throws IOException {
        var content = readFileContent();
        int pos = 0;
        for (String str : content.split("\\s+")) {
            if (str.equals(word)) {
                return pos;
            }
            pos++;
        }
        return -1;
    }

    /**
     * Scan file for the longest word. If was found a few, returns first the longs word in the file
     *
     * @return first the longest word in the file
     * @throws IOException if I/O error occurs
     */
    public String getTheLongestWord() throws IOException {
        var content = readFileContent().split("\\s+");
        int pos = 0;
        for (int i = 1; i < content.length; i++) {
            if (content[i].length() > content[pos].length()) {
                pos = i;
            }
        }
        return content[pos];
    }

    /**
     * Counts top 10 most used words in file. Does not sensitive to register.
     * Displaying top words goes in order of their appearance in the file.
     * Also, long numbers counts as a word
     *
     * @return array of most used words in the file
     * @throws IOException if I/O error occurs
     */
    public String[] getTopTenWords() throws IOException {
        var content = readFileContent().split("\\s+");

        Map<String, Integer> wordsCount = new HashMap<>();
        for (String str : content) {
            String key = str.toLowerCase(Locale.ROOT);
            int count = wordsCount.getOrDefault(key, 0);
            wordsCount.put(key, count + 1);
        }
        return parseMapToArray(wordsCount);
    }

    /**
     * Converts given Map into String array. After processing the Map, includes the key with the highest value
     * to resulted array. Includes only first ten records, if there is more than ten records, includes only those
     * with the highest value or if they have same value, includes words by their appearing in the text.
     *
     * @param map <code>word</code> as a key and <code>count</code> as value, amount of appearance in the text
     * @return array with top 10 most used words
     */
    //TODO: simplify method
    private String[] parseMapToArray(Map<String, Integer> map) {
        var items = map.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .toList();
        if (items.size() < 10) {
            return items.stream()
                    .map(entry -> entry.getKey() + " - " + entry.getValue())
                    .toArray(String[]::new);
        } else {
            return items.stream()
                    .map(entry -> entry.getKey() + " - " + entry.getValue())
                    .limit(10)
                    .toArray(String[]::new);
        }
    }

    /**
     * Checks if file exist by given absolute path or relative path.
     *
     * @param path absolute or relative path if file exists in the same scope
     * @return <code>true</code> if file exists
     *         <code>else</code> otherwise.
     */
    private boolean isFileExist(String path) {
        var file = Path.of(path);
        boolean res = false;

        if (Files.exists(file)) {
            res = true;
        }
        return res;
    }

}
