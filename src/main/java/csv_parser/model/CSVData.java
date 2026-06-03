package csv_parser.model;

import java.util.*;

/**
 * Represents a single row of CSV data.
 *
 * Stores column-value mappings together with
 * the row number and header definitions.
 *
 * @author davinci-arch
 */
public class CSVData {
    private int rowNumber;
    private Map<String, String> rowData;
    private List<String> headers;
    public CSVData(List<String> headers) {
        rowData = new LinkedHashMap<>();
        this.headers = headers;
        for (String header : headers) {
            rowData.put(header, "");
        }
    }

    public String findHeader(String header) {
        return rowData.getOrDefault(header, "");

    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public Map<String, String> getRowData() {

        return Collections.unmodifiableMap(rowData);
    }

    public void addData(String data, int index, int rowNumber) {
        if (index >= headers.size()) {
            rowData.put("empty", data);
        }else {
            rowData.put(headers.get(index), data);

        }
        this.rowNumber = rowNumber;
    }

    @Override
    public String toString() {
        return "CSVData{" +
                "rowNumber=" + rowNumber +
                ", rowData=" + rowData.toString() +
                ", headers=" + headers.toString() +
                '}';
    }
}
