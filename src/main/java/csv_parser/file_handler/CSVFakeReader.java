package csv_parser.file_handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * In-memory CSV reader used primarily for testing.
 *
 * @davinci-arch
 */
public class CSVFakeReader implements CSVReader{
    private List<String> rows;

    public CSVFakeReader() {
        this.rows = new ArrayList<>();
    }

    @Override
    public List<String> getLines() throws IOException {
        return rows;
    }

    public void addRow(String row) {
        rows.add(row);
    }

    public void addAllRows(List<String> list) {
        rows.addAll(list);
    }

}
