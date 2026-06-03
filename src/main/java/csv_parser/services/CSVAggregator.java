package csv_parser.services;

import csv_parser.model.CSVData;

import java.util.List;

/**
 * Performs aggregation operations on parsed CSV data.
 *
 * Supports statistical calculations such as average,
 * sum, minimum, maximum and row counting.
 *
 * @author davinci-arch
 */
public class CSVAggregator {

    double getAverage(List<CSVData> data, String columnName) {
        var sumOfNumbers = data.stream()
                .map(v -> Double.parseDouble(v.getRowData().get(columnName)))
                .reduce(0.0, Double::sum);

        return (double) sumOfNumbers / data.size();
    }

    double getMax(List<CSVData> data, String columnName) {
        return data.stream()
                .map(v -> Double.parseDouble(v.getRowData().get(columnName)))
                .max(Double::compare)
                .orElseThrow();
    }

    double getMin(List<CSVData> data, String columnName) {
        return data.stream()
                .map(v -> Double.parseDouble(v.getRowData().get(columnName)))
                .min(Double::compare)
                .orElseThrow();
    }

    double getSum(List<CSVData> data, String columnName) {
        return data.stream()
                .map(v -> Double.parseDouble(v.getRowData().get(columnName)))
                .reduce(0.0, Double::sum);
    }
    long count(List<CSVData> data, String columnName) {
        return data.stream()
                .map(v -> v.getRowData().get(columnName))
                .count();
    }
    long countThatIsNotEmpty(List<CSVData> data, String columnName) {
        return data.stream()
                .map(v -> v.getRowData().get(columnName))
                .filter(v -> !v.isBlank())
                .count();
    }
}
