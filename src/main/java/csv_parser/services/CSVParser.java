package csv_parser.services;

import csv_parser.file_handler.CSVReader;
import csv_parser.model.CSVData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVParser {

    private final CSVReader csvReader;

    public CSVParser(CSVReader csvReader) {
        this.csvReader = csvReader;
    }

    public List<CSVData> parse() throws IOException {
        var rawData = csvReader.getLines();
        var headers = getHeaders(rawData);
        List<CSVData> data = new ArrayList<>();
        for (int i = 1; i < rawData.size(); i++) {
            var rows = rawData.get(i).split(",");
            var dataRow = new CSVData(headers);
            for (int j = 0; j < rows.length; j++) {
                dataRow.addData(rows[j], j, i-1);
            }
            data.add(dataRow);
        }
        return data;
    }

    private List<String> getHeaders(List<String> lines) {
        return Arrays.stream(lines.get(0).split(",")).toList();
    }
}
