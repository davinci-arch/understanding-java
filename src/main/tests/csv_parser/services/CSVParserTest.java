package csv_parser.services;

import csv_parser.file_handler.CSVReader;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;


import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CSVParserTest {
    @Mock
    private CSVReader csvReader;

    @InjectMocks
    private CSVParser csvParser;

    @Order(2)
    @Test
    void shouldNotSplitOnCommaInsideQuotes() throws IOException {
        var fakeRows = List.of(
                "id,name,age,email,city,notes",
                "1,John Doe,28,john.doe@example.com,Prague,\"Loves,football\""
        );
        when(csvReader.getLines()).thenReturn(fakeRows);
        var parser = new CSVParser(csvReader);

        var data = parser.parse();

        verify(csvReader).getLines();

        assertThat(data).isNotNull();
        assertThat(data.size()).isEqualTo(fakeRows.size()-1);
        assertThat(data.get(0).findHeader("notes")).isEqualTo("\"Loves,football\"");
    }
    @Test
    void shouldNotSplitOnCommaInsideQuotesMultipleColumns() throws IOException {
        var fakeRows = List.of(
                "id,name,age,email,city,notes",
                "1,\"John,Doe\",28,john.doe@example.com,Prague,\"Loves,football\""
        );
        when(csvReader.getLines()).thenReturn(fakeRows);

        var parse = new CSVParser(csvReader);
        var data = parse.parse();

        verify(csvReader).getLines();

        assertThat(data).isNotNull();
        assertThat(data.size()).isEqualTo(fakeRows.size()-1);
        assertThat(data.get(0).findHeader("notes")).isEqualTo("\"Loves,football\"");
        assertThat(data.get(0).findHeader("name")).isEqualTo("\"John,Doe\"");
    }
    @Order(1)
    @Test
    void shouldSplitEmptyColumns() throws IOException {
        var fakeRows = List.of(
                "id,name,age,email,city,notes",
                "1,John Doe,,john.doe@example.com,,Loves football"
        );
        when(csvReader.getLines()).thenReturn(fakeRows);

        var parse = new CSVParser(csvReader);
        var data = parse.parse();

        verify(csvReader).getLines();
        assertThat(data).isNotNull();
        assertThat(data.size()).isEqualTo(fakeRows.size()-1);
        assertThat(data.get(0).findHeader("age")).isBlank();
        assertThat(data.get(0).findHeader("city")).isBlank();
    }

    @Test
    void shouldSplitLineReplacedWithEscapedQuotes() throws IOException {
        var fakeRows = List.of(
                "id,name,age,email,city,notes",
                "1,\"John\"\"Doe\",28,john.doe@example.com,Prague,Loves football"
        );
        when(csvReader.getLines()).thenReturn(fakeRows);

        var parse = new CSVParser(csvReader);
        var data = parse.parse();

        verify(csvReader).getLines();
        assertThat(data).isNotNull();
        assertThat(data.size()).isEqualTo(fakeRows.size()-1);
        assertThat(data.get(0).findHeader("name")).isEqualTo("\"John\\\"Doe\"");
    }
    @Test
    void shouldNotRemoveSpaces() throws IOException {
        var fakeRows = List.of(
                "id,name,age,email,city,notes",
                "1,\"John, Doe\",28,john.doe@example.com, Prague,Loves football"
        );
        when(csvReader.getLines()).thenReturn(fakeRows);

        var parse = new CSVParser(csvReader);
        var data = parse.parse();

        verify(csvReader).getLines();

        assertThat(data).isNotNull();
        assertThat(data.size()).isEqualTo(fakeRows.size()-1);
        assertThat(data.get(0).findHeader("name")).isEqualTo("\"John, Doe\"");
        assertThat(data.get(0).findHeader("city")).isEqualTo(" Prague");
    }

}