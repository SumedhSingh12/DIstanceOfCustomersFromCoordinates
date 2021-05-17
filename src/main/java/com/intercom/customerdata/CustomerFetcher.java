package com.intercom.customerdata;

import com.google.gson.Gson;
import com.intercom.pojo.Customer;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerFetcher {

    public static Scanner fetchCustomerJsonFromUrl(final String url) throws IOException {
        final CloseableHttpClient httpclient = HttpClients.createDefault();
        final HttpGet httpGet = new HttpGet(url);
        final HttpResponse httpResponse = httpclient.execute(httpGet);
        return new Scanner(httpResponse.getEntity().getContent());
    }

    public static List<Customer> getCustomerList(final Scanner scanner) {
        final Gson gson = new Gson();
        final List<Customer> customers = new ArrayList<>();
        while (scanner.hasNext()) {
            final Customer customer = gson.fromJson(scanner.nextLine(), Customer.class);
            customers.add(customer);
        }
        return customers;
    }
}
