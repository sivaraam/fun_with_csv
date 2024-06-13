package org.example;

import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.CsvRecord;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CsvReplDemo {
    private static List<CsvRecord> csvRecords = new ArrayList<>();

    public static void main(String[] args) {
        try {
            Terminal terminal = TerminalBuilder.builder().build();

            LineReader reader = LineReaderBuilder.builder()
                                                 .terminal(terminal)
                                                 .build();

            while (true) {
                printMenu(terminal);
                String option = reader.readLine("Select an option: ");

                switch (option) {
                    case "1":
                        String filePath = reader.readLine("Enter CSV file path: ");
                        parseCsv(filePath.trim(), terminal);
                        break;
                    case "2":
                        printCsv(terminal);
                        break;
                    case "q":
                        terminal.writer().println("Exiting...");
                        terminal.flush();
                        return;
                    default:
                        terminal.writer().println("Invalid option. Please try again.");
                        terminal.flush();
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to get a terminal. Exiting.");
            System.exit(1);
        }
    }

    private static void printMenu(Terminal terminal) {
        terminal.writer().println("Menu:");
        terminal.writer().println("1. Parse a CSV file");
        terminal.writer().println("2. Print the parsed CSV data");
        terminal.writer().println("q. Quit");
        terminal.flush();
    }

    private static void parseCsv(String filePath, Terminal terminal) {
        Path path = Paths.get(filePath);

        if (!Files.exists(path)) {
            terminal.writer().println("File does not exist.");
            terminal.flush();
            return;
        }

        try (CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(path)) {
            csvRecords = csv.stream().collect(Collectors.toList());
            terminal.writer().println("CSV file parsed successfully.");
            terminal.flush();

        } catch (IOException e) {
            terminal.writer().println("Error reading the CSV file: " + e.getMessage());
            terminal.flush();
        }
    }

    private static void printCsv(Terminal terminal) {
        if (csvRecords.isEmpty()) {
            terminal.writer().println("No CSV data available. Please parse a CSV file first.");
            terminal.flush();
            return;
        }

        terminal.writer().println("CSV Data:");
        csvRecords.forEach(row -> terminal.writer().println(row.getFields()));
        terminal.flush();
    }
}
