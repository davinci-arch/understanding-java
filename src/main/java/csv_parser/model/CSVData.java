package csv_parser.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CSVData {
    private int rowNumber;
    private Map<String, String> rowData;
    private List<String> headers;
    public CSVData(List<String> headers) {
        rowData = new HashMap<>();
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

        return rowData
                .entrySet()
                .stream()
                .collect(
                        Collectors
                                .toUnmodifiableMap(
                                        Map.Entry<String, String>::getKey, Map.Entry<String, String>::getValue
                                )
                );
    }

    public void addData(String data, int index, int rowNumber) {
        rowData.put(headers.get(index), data);
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
