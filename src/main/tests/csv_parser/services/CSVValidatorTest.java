package csv_parser.services;


import csv_parser.exceptions.MissMatchingTypes;
import csv_parser.exceptions.MissingCSVValueException;
import csv_parser.model.CSVData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class CSVValidatorTest {

    private List<String> headers;
    private CSVData csvFakeData;
    private CSVData csvFakeData2;
    @BeforeEach
    void setUp() {
        headers = new ArrayList<>(List.of(
                "id",
                "name",
                "age",
                "email",
                "city",
                "notes"
        ));
        csvFakeData = new CSVData(headers);
        csvFakeData.addData("1", 0, 1);
        csvFakeData.addData("Joe Doe", 1, 1);
        csvFakeData.addData("", 2, 1);
        csvFakeData.addData("john.doe@example.com", 3, 1);
        csvFakeData.addData("Prague", 4, 1);
        csvFakeData.addData("Loves,football", 5, 1);

        csvFakeData2 = new CSVData(headers);
        csvFakeData2.addData("1", 0, 1);
        csvFakeData2.addData("Joe5 Doe", 1, 1);
        csvFakeData2.addData("28", 2, 1);
        csvFakeData2.addData("john.doe@example.com", 3, 1);
        csvFakeData2.addData("Prague", 4, 1);
        csvFakeData2.addData("Loves,football", 5, 1);

    }

    @Test
    void shouldReturnMissingValueException() {
        CSVValidator validator = new CSVValidator();

        assertThatExceptionOfType(MissingCSVValueException.class)
                .isThrownBy(() -> validator.validate(csvFakeData))
                .withMessage("Missing value for column: age");
    }
    @Test
    void shouldReturnMissMatchTypeException() {
        CSVValidator validator = new CSVValidator();

        assertThatExceptionOfType(MissMatchingTypes.class)
                .isThrownBy(() -> validator.validate(csvFakeData2))
                .withMessage("This value {Joe5 Doe} has wrong number format");
    }

}