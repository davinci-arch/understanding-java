package csv_parser.file_handler;


import java.io.IOException;
import java.util.List;

public interface CSVReader {
    List<String> getLines() throws IOException;
}
