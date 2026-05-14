package csv_parser.services;

import java.util.ArrayList;
import java.util.List;

public final class CSVStateMachine {


    public List<String> splitLine(String line) {

        StringBuilder res = new StringBuilder();
        List<String> parts = new ArrayList<>();

        boolean inQuotes = false;

        int quotesCounter = 0;
        for (char c : line.toCharArray()) {

            if (c == '"') {
                inQuotes = !inQuotes;
                res.append(c);
                quotesCounter++;

                if (quotesCounter % 3 == 0) {
                    res.delete(res.length() - 2, res.length());
                    res.append("\\");
                    res.append("\"");
                }
                continue;
            }

            if (c == ',' && !inQuotes) {
                parts.add(res.toString());
                res.setLength(0);
                quotesCounter = 0;
                continue;
            }
            res.append(c);
        }
        parts.add(res.toString());

        return parts;
    }
}
