package org.example.data;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.validator.routines.EmailValidator;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class CustomerData {
    int index;
    String customerId,
           firstName,
           lastName,
           company,
           city,
           country;
    Phonenumber.PhoneNumber
           phoneNumber1,
           phoneNumber2;
    String email;
    LocalDate subscriptionDate;
    URI website;

    private static PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
    public static final DateTimeFormatter subscriptionDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static CustomerData constructFromStrings(List<String> customerValues) throws NumberParseException, URISyntaxException {
        CustomerDataBuilder newCustomer = CustomerData.builder();

        newCustomer.index(Integer.parseInt(customerValues.get(0)));
        newCustomer.customerId(customerValues.get(1));
        newCustomer.firstName(customerValues.get(2));
        newCustomer.lastName(customerValues.get(3));
        newCustomer.company(customerValues.get(4));
        newCustomer.city(customerValues.get(5));
        newCustomer.country(customerValues.get(6));

        // TODO: Need to think of a better way to handle
        //  exceptions that occur while parsing a phone number
        String phone1Raw = customerValues.get(7);
        Phonenumber.PhoneNumber phone1 = phoneUtil.parse(phone1Raw, "US");
        phone1.setRawInput(phone1Raw);
        newCustomer.phoneNumber1(phone1);

        String phone2Raw = customerValues.get(8);
        Phonenumber.PhoneNumber phone2 = phoneUtil.parse(phone2Raw, "US");
        phone2.setRawInput(phone2Raw);
        newCustomer.phoneNumber2(phone2);

        boolean validEmail = EmailValidator.getInstance().isValid(customerValues.get(9));
        if (validEmail) {
            newCustomer.email(customerValues.get(9));
        }
        else {
            // Falback to Invalid value.
            newCustomer.email("N/A (error)");
        }

        newCustomer.subscriptionDate(
                LocalDate.parse(customerValues.get(10), subscriptionDateFormat)
        );

        // TODO: Need to think of a better way to handle
        //  exceptions that occur while parsing a website
        newCustomer.website(new URI(customerValues.get(11)));

        return newCustomer.build();
    }

    public List<String> toCsvLine() {
        List<String> csvLine = new ArrayList<>();
        csvLine.add(((Integer) index).toString());
        csvLine.add(customerId);
        csvLine.add(firstName);
        csvLine.add(lastName);
        csvLine.add(company);
        csvLine.add(city);
        csvLine.add(country);
        csvLine.add(phoneNumber1.getRawInput());
        csvLine.add(phoneNumber2.getRawInput());
        csvLine.add(email);
        csvLine.add(subscriptionDate.toString());
        csvLine.add(website.toString());

        return csvLine;
    }
}
