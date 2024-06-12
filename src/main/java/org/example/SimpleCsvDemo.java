package org.example;

import org.example.data.CustomerData;
import org.example.util.CsvReaderWriter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A simple demo class showcasing the working of the {@link CsvReaderWriter} class.
 * It reads the data from a CSV file, transforms each row into an instance of the
 * {@link CustomerData} class and prints the list of instances.
 */
public class SimpleCsvDemo {

    public static void main(String[] args) throws Exception {
        File customerDataFile = null;

        Scanner scanner = new Scanner(System.in);
        boolean validPath = false;

        while (!validPath) {
            // Prompt the user to enter a file path
            System.out.println("Please enter the file path:");
            String customerFilePath = scanner.nextLine();

            // Create a File object with the provided path
            customerDataFile = new File(customerFilePath.trim());

            // Check if the file exists and is a file
            if (customerDataFile.exists() && customerDataFile.isFile()) {
                validPath = true;
                System.out.println("File path: " + customerDataFile.getAbsolutePath());
            } else {
                System.out.println("The provided path is not a valid file. Please try again.");
            }
        }

        List<String> headers;
        List<CustomerData> customerCollection = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(customerDataFile);
             Reader fr = new InputStreamReader(fis, StandardCharsets.UTF_8))
        {
            headers = CsvReaderWriter.parseLine(fr);

            List<String> values = CsvReaderWriter.parseLine(fr);
            while (values != null) {
                customerCollection.add(CustomerData.constructFromStrings(values));
                values = CsvReaderWriter.parseLine(fr);
            }
        }

        System.out.println(headers);
        customerCollection.forEach(System.out::println);

        writeCustomers(headers, customerCollection);
    }

    private static void writeCustomers(List<String> headers, List<CustomerData> customers) throws Exception {
        File outputCsvFile = null;
        try (Scanner scanner = new Scanner(System.in)) {
            boolean validPath = false;

            while (!validPath) {
                // Prompt the user to enter a file path
                System.out.println("Please enter the file path to save the CSV file:");
                String outputFilePath = scanner.nextLine();

                // Create a File object with the provided path
                outputCsvFile = new File(outputFilePath.trim());

                // Check if the file exists and is a file
                if (outputCsvFile.exists() && outputCsvFile.isFile()) {
                    System.out.println("The provided path already exists. Please give a different path.");
                } else {
                    File parentDir = outputCsvFile.getParentFile();

                    if (parentDir != null && parentDir.exists()) {
                        // Check if the parent directory is writable
                        if (!parentDir.canWrite()) {
                            System.out.println("The parent directory of the given path is not writable. Please give a different path.");
                        } else {
                            validPath = true;
                            System.out.println("Output file path: " + outputCsvFile.getAbsolutePath());
                        }
                    }
                }
            }
        }

        try (FileOutputStream fos = new FileOutputStream(outputCsvFile);
             Writer fw = new OutputStreamWriter(fos, StandardCharsets.UTF_8))
        {
            CsvReaderWriter.writeLine(fw, headers);

            for (CustomerData cd : customers) {
                CsvReaderWriter.writeLine(fw, cd.toCsvLine());
            }
        }
    }

}
