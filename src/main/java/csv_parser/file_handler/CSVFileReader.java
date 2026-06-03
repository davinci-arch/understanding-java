package csv_parser.file_handler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads CSV content from a file system path.
 *
 * @author davinci-arch
 */
public class CSVFileReader implements CSVReader {
    private final Path path;

    public CSVFileReader(Path path) {
        this.path = path;
    }

    @Override
    public List<String> getLines() throws IOException {
        List<String> data = new ArrayList<>();
        if (Files.exists(path)) {
            try (var bufferedReader = Files.newBufferedReader(path)) {
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    data.add(line);
                }
            }
        }
        return data;
    }
}
