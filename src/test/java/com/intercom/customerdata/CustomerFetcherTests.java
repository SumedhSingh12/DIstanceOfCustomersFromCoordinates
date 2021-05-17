package com.intercom.customerdata;

import com.intercom.common.Constants;
import com.intercom.pojo.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class CustomerFetcherTests {

    @Test
    public void fetchCustomerDataFromUrlTest() throws IOException {
        final String expected = "{\"latitude\": \"52.986375\", \"user_id\": 12, \"name\": \"Christina McArdle\", \"longitude\": \"-6.043701\"}";
        final Scanner scanner = CustomerFetcher.fetchCustomerJsonFromUrl(Constants.CUSTOMER_FILE_URL);
        final String nextLine = scanner.nextLine();
        Assertions.assertEquals(expected, nextLine);
    }

    @Test
    public void fetchCustomerDataFromUrlInvalidUrl() {
        Assertions.assertThrows(IOException.class, () -> CustomerFetcher.fetchCustomerJsonFromUrl("abcd"));
    }

    @Test
    public void getCustomerListTest() throws IOException {
        final Scanner scanner = CustomerFetcher.fetchCustomerJsonFromUrl(Constants.CUSTOMER_FILE_URL);
        final List<Customer> customerList = CustomerFetcher.getCustomerList(scanner);
        Assertions.assertEquals(32, customerList.size());
    }
}
