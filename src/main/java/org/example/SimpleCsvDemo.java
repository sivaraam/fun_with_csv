package org.example;

import org.example.data.CustomerData;
import org.example.data.Customers;
import org.example.util.CsvReaderWriter;

import java.io.*;
import java.util.Scanner;

import static org.example.util.CustomCsvUtil.parseCustomersFromCsv;
import static org.example.util.CustomCsvUtil.writeCustomers;

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

        /*
         * Step 1. Get the input file path from the user
         */
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

        /*
         *  Step 2: Parse the input file path from the user and get the CustomerData instances
         */
        Customers parsedCustomers = parseCustomersFromCsv(customerDataFile);

        System.out.println(parsedCustomers.getHeaders());
        parsedCustomers.getCustomerCollection().forEach(System.out::println);

        /*
         * Step 3: Get the output file path from the user
         */
        File outputCsvFile = null;
        validPath = false;
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

        /*
         * Step 4:  Write the customer data to the path given by the user.
         */
        writeCustomers(outputCsvFile, parsedCustomers.getHeaders(), parsedCustomers.getCustomerCollection());
    }


}
