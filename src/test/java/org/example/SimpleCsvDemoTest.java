package org.example;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.example.data.CustomerData;
import org.example.data.Customers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.example.data.CustomerData.subscriptionDateFormat;
import static org.example.util.CustomCsvUtil.parseCustomersFromCsv;
import static org.example.util.CustomCsvUtil.writeCustomers;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SimpleCsvDemoTest {

    private File inputFile;
    private File outputFile;
    private List<String> headers;
    private List<CustomerData> customerDataList;
    private static final PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

    @BeforeEach
    void setUp() throws Exception {
        inputFile = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("test-customer-data.csv")).toURI());
        outputFile = File.createTempFile("outputData", ".csv");
        outputFile.deleteOnExit();

        headers = List.of("Index","Customer Id","First Name","Last Name","Company","City","Country","Phone 1","Phone 2","Email","Subscription Date","Website");
        customerDataList = new ArrayList<>();

        String phone1Str = "846-790-4623x4715",
               phone2Str = "001-645-334-5514x0786";

        Phonenumber.PhoneNumber phone1 = phoneUtil.parse(phone1Str, "US"),
                                phone2 = phoneUtil.parse(phone2Str, "US");

        phone1.setRawInput(phone1Str);
        phone2.setRawInput(phone2Str);

        customerDataList.add(
                CustomerData.builder()
                            .index(30)
                            .customerId("2B54172c8b65eC3")
                            .firstName("Alice")
                            .lastName("Bose")
                            .email("alice@example.com")
                            .company("X Corp")
                            .city("Wonder City")
                            .country("Wonder land")
                            .phoneNumber1(phone1)
                            .phoneNumber2(phone2)
                            .subscriptionDate(LocalDate.parse("2022-04-22", subscriptionDateFormat))
                            .website(new URI("http://alice.example.com"))
                            .build()

        );
        customerDataList.add(
                CustomerData.builder()
                        .index(25)
                        .customerId("dE014d010c7ab0c")
                        .firstName("Bob")
                        .lastName("Dylan")
                        .email("bob@example.com")
                        .company("Y Corp")
                        .city("Wonder City")
                        .country("Wonder land")
                        .phoneNumber1(phone2)
                        .phoneNumber2(phone1)
                        .subscriptionDate(LocalDate.parse("2023-04-22", subscriptionDateFormat))
                        .website(new URI("http://bob.example.com"))
                        .build()

        );
    }
        @Test
    void testParseCustomersFromCsv() throws Exception {
        // Mock CsvReaderWriter behavior
        Customers customers = parseCustomersFromCsv(inputFile);

        assertEquals(headers, customers.getHeaders());
        assertEquals(customerDataList, customers.getCustomerCollection());
    }

    @Test
    void testWriteCustomers() throws Exception {
        // Write the customer data to the output file
        writeCustomers(outputFile, headers, customerDataList);

        // Read the output file and verify the contents
        compareFiles(inputFile, outputFile);
    }

    private void compareFiles(File inputFile, File outputFile) throws IOException {
        try (
                BufferedReader inputReader = new BufferedReader(new FileReader(inputFile));
                BufferedReader outputReader = new BufferedReader(new FileReader(outputFile))
        ) {
            String inputLine;
            String outputLine;

            while ((inputLine = inputReader.readLine()) != null) {
                outputLine = outputReader.readLine();
                assertEquals(inputLine, outputLine);
            }

            // Ensure that output file has no extra lines
            assertNull(outputReader.readLine());
        }
    }

}
