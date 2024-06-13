package org.example.data;

import lombok.Getter;

import java.util.List;

@Getter
public class Customers {
    private final List<String> headers;
    private final List<CustomerData> customerCollection;

    public Customers(List<String> headers, List<CustomerData> customerCollection) {
        this.headers = headers;
        this.customerCollection = customerCollection;
    }
}