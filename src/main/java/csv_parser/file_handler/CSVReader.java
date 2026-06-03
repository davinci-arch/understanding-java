package csv_parser.file_handler;


import java.io.IOException;
import java.util.List;

/**
 * Source of CSV content.
 *
 * Implementations may read data from files,
 * memory, network resources or other sources.
 *
 * @author davinci-arch
 */
public interface CSVReader {
    List<String> getLines() throws IOException;
}
