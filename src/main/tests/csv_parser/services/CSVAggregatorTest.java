package csv_parser.services;

import csv_parser.model.CSVData;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class CSVAggregatorTest {

    public static List<CSVData> getTestData() {

        List<String> headers = List.of("Name", "Department", "Age", "Salary", "Country");

        CSVData r1 = new CSVData(headers);
        r1.addData("Alice", 0, 1);
        r1.addData("IT", 1, 1);
        r1.addData("25", 2, 1);
        r1.addData("3200", 3, 1);
        r1.addData("CZ", 4, 1);

        CSVData r2 = new CSVData(headers);
        r2.addData("Bob", 0, 2);
        r2.addData("IT", 1, 2);
        r2.addData("30", 2, 2);
        r2.addData("4100", 3, 2);
        r2.addData("CZ", 4, 2);

        CSVData r3 = new CSVData(headers);
        r3.addData("Charlie", 0, 3);
        r3.addData("HR", 1, 3);
        r3.addData("28", 2, 3);
        r3.addData("2900", 3, 3);
        r3.addData("PL", 4, 3);

        CSVData r4 = new CSVData(headers);
        r4.addData("Diana", 0, 4);
        r4.addData("HR", 1, 4);
        r4.addData("35", 2, 4);
        r4.addData("3600", 3, 4);
        r4.addData("SK", 4, 4);

        CSVData r5 = new CSVData(headers);
        r5.addData("Ethan", 0, 5);
        r5.addData("Finance", 1, 5);
        r5.addData("40", 2, 5);
        r5.addData("5000", 3, 5);
        r5.addData("CZ", 4, 5);

        CSVData r6 = new CSVData(headers);
        r6.addData("Fiona", 0, 6);
        r6.addData("Finance", 1, 6);
        r6.addData("32", 2, 6);
        r6.addData("4700", 3, 6);
        r6.addData("", 4, 6);

        CSVData r7 = new CSVData(headers);
        r7.addData("George", 0, 7);
        r7.addData("IT", 1, 7);
        r7.addData("29", 2, 7);
        r7.addData("3900", 3, 7);
        r7.addData("CZ", 4, 7);

        CSVData r8 = new CSVData(headers);
        r8.addData("Hannah", 0, 8);
        r8.addData("Marketing", 1, 8);
        r8.addData("26", 2, 8);
        r8.addData("3100", 3, 8);
        r8.addData("", 4, 8);

        CSVData r9 = new CSVData(headers);
        r9.addData("Ivan", 0, 9);
        r9.addData("Marketing", 1, 9);
        r9.addData("31", 2, 9);
        r9.addData("4200", 3, 9);
        r9.addData("SK", 4, 9);

        CSVData r10 = new CSVData(headers);
        r10.addData("Julia", 0, 10);
        r10.addData("Finance", 1, 10);
        r10.addData("27", 2, 10);
        r10.addData("4500", 3, 10);
        r10.addData("DE", 4, 10);

        return List.of(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10);
    }


    @Test
    void shouldReturnAverageSalary() {
        CSVAggregator aggregator = new CSVAggregator();

        var averageSalary = aggregator.getAverage(getTestData(), "Salary");

        assertThat(averageSalary).isEqualTo(3920);
    }

    @Test
    void shouldReturnTheBiggestSalary() {
        CSVAggregator aggregator = new CSVAggregator();

        var biggestSalary = aggregator.getMax(getTestData(), "Salary");

        assertThat(biggestSalary).isEqualTo(5000);
    }

    @Test
    void shouldReturnTheSmallestSalary() {
        CSVAggregator aggregator = new CSVAggregator();

        var smallestSalary = aggregator.getMin(getTestData(), "Salary");

        assertThat(smallestSalary).isEqualTo(2900);
    }

    @Test
    void shouldReturnSumOfAllSalaries() {
        CSVAggregator aggregator = new CSVAggregator();

        var sumSalaries = aggregator.getSum(getTestData(), "Salary");

        assertThat(sumSalaries).isEqualTo(39200);
    }

    @Test
    void shouldCountHowManyEntities() {
        CSVAggregator aggregator = new CSVAggregator();

        var count = aggregator.count(getTestData(), "Country");

        assertThat(count).isEqualTo(10L);
    }

    @Test
    void shouldCountHowManyEntitiesThatIsNotEmpty() {
        CSVAggregator aggregator = new CSVAggregator();

        var count = aggregator.countThatIsNotEmpty(getTestData(), "Country");

        assertThat(count).isEqualTo(8L);
    }
}
