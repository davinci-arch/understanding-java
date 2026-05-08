package csv_parser.services;

import csv_parser.file_handler.CSVFakeReader;
import csv_parser.model.CSVData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
class CSVParserTest {
    private List<String> fakeRows;
    @BeforeEach
    void setUp() {
        fakeRows = new ArrayList<>();
        fakeRows.add("id,name,age,email,city,notes");
        fakeRows.add("1,John Doe,28,john.doe@example.com,Prague,\"Loves,football\"");
    }

    @Test
    void shouldNotSplitOnCommaInsideQuotes() throws IOException {
        var csvFakeReader = new CSVFakeReader();
        csvFakeReader.addAllRows(fakeRows);
        var parser = new CSVParser(csvFakeReader);
        var data = parser.parse();
        data.stream().map(CSVData::getRowData).forEach(System.out::println);
    }
}