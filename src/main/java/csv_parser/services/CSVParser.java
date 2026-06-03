package csv_parser.services;

import csv_parser.file_handler.CSVReader;
import csv_parser.model.CSVData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Parses CSV content provided by a {@link CSVReader}
 * into structured {@link CSVData} objects.
 *
 * Supports quoted values and delegates validation
 * to {@link CSVValidator}.
 *
 * @author davinci-arch
 */
public class CSVParser {

    private final CSVReader csvReader;
    private final CSVStateMachine csvStateMachine;
    private final CSVValidator csvValidator;
    private List<CSVData> parsedData;
    public CSVParser(CSVReader csvReader) {
        this(csvReader, new CSVValidator());
    }
    public CSVParser(CSVReader csvReader, CSVValidator csvValidator) {
        this.csvReader = csvReader;
        this.csvStateMachine = new CSVStateMachine();
        this.csvValidator = csvValidator;
    }

    public CSVParser parse() throws IOException {
        var rawData = csvReader.getLines();
        var headers = getHeaders(rawData);
        List<CSVData> data = new ArrayList<>();
        for (int i = 1; i < rawData.size(); i++) {
            var rows = csvStateMachine.splitLine(rawData.get(i));
            var dataRow = new CSVData(headers);
            for (int j = 0; j < rows.size(); j++) {
                dataRow.addData(rows.get(j), j, i-1);
            }
            data.add(dataRow);
        }
        parsedData = data;
        return this;
    }
    public CSVParser withValidation() {
        for (CSVData data : parsedData) {
            csvValidator.validate(data);
        }
        return this;
    }

    public List<CSVData> getParsedData() {
        if (parsedData == null) {
            throw new IllegalStateException("Parser has not parsed any data");
        }
        return parsedData;
    }

    private List<String> getHeaders(List<String> lines) {
        return csvStateMachine.splitLine(lines.get(0));
    }
}
