package com.intercom.customerdata;

import com.google.gson.Gson;
import com.intercom.common.Constants;
import com.intercom.exceptions.InvalidCoordinatesException;
import com.intercom.pojo.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class CustomerFilterAndSorterTests {

    @Test
    public void getCustomerListTest() throws IOException, InvalidCoordinatesException {
        final Scanner scanner = CustomerFetcher.fetchCustomerJsonFromUrl(Constants.CUSTOMER_FILE_URL);
        final List<Customer> customerList = new CustomerFilterAndSorter().getCustomerList(scanner);
        Assertions.assertEquals(16, customerList.size());
    }

    @Test
    public void getCustomerListTestSingleCustomer() throws IOException, InvalidCoordinatesException {
        final String json = "{\"latitude\": 53.2451022,\"user_id\": 4,\"name\": \"Ian Kehoe\",\"longitude\": -6.238335}";
        final Scanner scanner = new Scanner(json);
        final List<Customer> customerList = new CustomerFilterAndSorter().getCustomerList(scanner);
        Assertions.assertEquals(1, customerList.size());
        Assertions.assertEquals(53.2451022, customerList.get(0).getLatitude());
        Assertions.assertEquals(-6.238335, customerList.get(0).getLongitude());
        Assertions.assertEquals(4, customerList.get(0).getUser_id());
        Assertions.assertEquals("Ian Kehoe", customerList.get(0).getName());
    }

    @Test
    public void getCustomerListTestInvalidCoordinatesException() {
        final String json = "{\"latitude\": 100.00,\"user_id\": 4,\"name\": \"Ian Kehoe\",\"longitude\": -6.238335}";
        final Scanner scanner = new Scanner(json);
        Assertions.assertThrows(InvalidCoordinatesException.class, () ->
                new CustomerFilterAndSorter().getCustomerList(scanner));
    }
}
