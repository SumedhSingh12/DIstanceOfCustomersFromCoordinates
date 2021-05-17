package com.intercom.customerdata;

import com.google.gson.Gson;
import com.intercom.calculations.DistanceCalculator;
import com.intercom.common.Constants;
import com.intercom.exceptions.InvalidCoordinatesException;
import com.intercom.pojo.Customer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class CustomerFilterAndSorter {

    final Gson gson = new Gson();

    public List<Customer> getCustomerList(final Scanner scanner) throws InvalidCoordinatesException {
        final Comparator<Customer> customerUserIdComparator = Comparator.comparing(Customer::getUser_id);
        final Queue<Customer> customers = new PriorityQueue<>(customerUserIdComparator);

        while(scanner.hasNext()) {
            final Customer customer = gson.fromJson(scanner.nextLine(), Customer.class);
            customer.setDistanceFromIntercomOffice(DistanceCalculator.getDistance(customer.getLatitude(), customer.getLongitude()));
            if (checkIfWithinDistanceThreshold(customer.getDistanceFromIntercomOffice())) {
                customers.add(customer);
            }
        }
        return sortCustomersUsingUserId(customers);
    }

    private boolean checkIfWithinDistanceThreshold(final double distanceFromIntercomOffice) {
        return Double.compare(distanceFromIntercomOffice, Constants.THRESHOLD_DISTANCE) <= 0;
    }

    private List<Customer> sortCustomersUsingUserId(Queue<Customer> customers) {
        final List<Customer> customerList = new ArrayList<>();
        while (!customers.isEmpty()) {
            customerList.add(customers.remove());
        }
        return customerList;
    }

}
