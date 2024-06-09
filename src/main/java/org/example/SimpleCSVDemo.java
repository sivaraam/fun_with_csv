package org.example;

import org.example.data.CustomerData;
import org.example.simple.SimpleCSVReaderWriter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A simple demo class showcasing the working of the SimpleCSVReaderWriter class.
 * It reads the data from a CSV file, transforms each row into an instance of the
 * {@link CustomerData} class and prints the list of instances.
 */
public class SimpleCSVDemo {

    public static void main(String[] args) throws Exception {
        File customerDataFile = null;

        Scanner scanner = new Scanner(System.in);
        boolean validPath = false;

        while (!validPath) {
            // Prompt the user to enter a file path
            System.out.println("Please enter the file path:");
            String customerFilePath = scanner.nextLine();

            // Create a File object with the provided path
            customerDataFile = new File(customerFilePath);

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


            headers = SimpleCSVReaderWriter.parseLine(fr);

            List<String> values = SimpleCSVReaderWriter.parseLine(fr);
            while (values != null) {
                customerCollection.add(CustomerData.constructFromStrings(values));
                values = SimpleCSVReaderWriter.parseLine(fr);
            }
        }

        System.out.println(headers);
        System.out.println(customerCollection);

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
                outputCsvFile = new File(outputFilePath);

                // Check if the file exists and is a file
                if (outputCsvFile.exists() && outputCsvFile.isFile()) {
                    System.out.println("The provided path already exists. Please give a different path.");
                } else {
                    validPath = true;
                    System.out.println("Output file path: " + outputCsvFile.getAbsolutePath());
                }
            }
        }

        try (FileOutputStream fos = new FileOutputStream(outputCsvFile);
             Writer fw = new OutputStreamWriter(fos, StandardCharsets.UTF_8))
        {
            SimpleCSVReaderWriter.writeLine(fw, headers);

            for (CustomerData cd : customers) {
                SimpleCSVReaderWriter.writeLine(fw, cd.toCsvLine());
            }
        }
    }

}
