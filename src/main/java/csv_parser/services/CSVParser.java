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
        List<String> columns = new ArrayList<>();

        return List.of();
    }

    private List<String> getHeaders(List<String> lines) {
        return Arrays.stream(lines.get(0).split(",")).toList();
    }
}
