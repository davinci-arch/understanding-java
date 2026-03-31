package main.java.file_analyzing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class FileService {

    //TODO: take path via constructor
    private final String PATH_TO_FILE = "/Users/oleksandrparfesa/Documents/testfile.txt";

    private Path file = Path.of(PATH_TO_FILE);


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

    public int countWords() throws IOException {
        var content = readFileContent();
        return content.split("\\s+").length;
    }

    public int countLines() throws IOException {
        var content = readFileContent();
        return (int) content.lines().count();
    }
    public int countSymbols() throws IOException {
        var content = readFileContent();
        return content.length();
    }

    public int posInText(String word) throws IOException {
        var content = readFileContent();
        int pos = 0;
        for (String str : content.split("\\s+")) {
            if  (str.equals(word)) {
                return pos;
            }
            pos++;
        }
        return -1;
    }

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

    private boolean isFileExist(String path) {
        var file = Path.of(path);
        boolean res = false;

        if (Files.exists(file)) {
            res = true;
        }
        return res;
    }

}
