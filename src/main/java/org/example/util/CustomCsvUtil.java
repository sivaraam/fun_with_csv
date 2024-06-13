package org.example.util;

import org.example.data.CustomerData;
import org.example.data.Customers;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * A utility class containing utility method to parse and write CSV data
 * containing customer information.
 */
public class CustomCsvUtil {
    public static Customers parseCustomersFromCsv(File inputFile) throws Exception {
        List<String> headers = new ArrayList<>();
        List<CustomerData> customerData = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(inputFile);
             Reader fr = new InputStreamReader(fis, StandardCharsets.UTF_8))
        {
            headers = CsvReaderWriter.parseLine(fr);

            List<String> values = CsvReaderWriter.parseLine(fr);
            while (values != null) {
                customerData.add(CustomerData.constructFromStrings(values));
                values = CsvReaderWriter.parseLine(fr);
            }
        }

        return new Customers(headers, customerData);
    }

    public static void writeCustomers(File outputCsvFile, List<String> headers, List<CustomerData> customers) throws Exception {
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
