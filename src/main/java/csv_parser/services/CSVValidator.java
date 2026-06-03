package csv_parser.services;

import csv_parser.exceptions.MismatchedTypeException;
import csv_parser.exceptions.MissingCSVValueException;
import csv_parser.model.CSVData;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validates parsed CSV rows.
 *
 * Ensures required values are present and that
 * field values satisfy validation constraints.
 *
 * @author davinci-arch
 */
public class CSVValidator {

    public boolean validate(CSVData csvData) {

        var data = csvData.getRowData();
        var isValid = false;
        Pattern wrongNumbers = Pattern.compile("\\d+\\D+$");
        Matcher matcher;
        for (Map.Entry<String, String> entry : data.entrySet()) {
            matcher = wrongNumbers.matcher(entry.getValue());
            if (entry.getValue() == null || entry.getValue().isEmpty()) {
                throw new MissingCSVValueException(String.format("Missing value for column: %s", entry.getKey()));
            }
            if (matcher.find()) {
                throw new MismatchedTypeException(String.format("This value {%s} has wrong number format", entry.getValue()));
            }

        }

        isValid = true;

        return isValid;
    }
}
