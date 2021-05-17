package com.intercom.controller;

import com.google.gson.Gson;
import com.intercom.customerdata.CustomerFetcher;
import com.intercom.common.Constants;
import com.intercom.pojo.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Controller
public class ViewCustomers {

    final Gson gson = new Gson();

    @GetMapping("/viewCustomers")
    public void viewCustomers(@RequestParam (defaultValue = Constants.CUSTOMER_FILE_URL, required = false)
                                  final String url, final Model model) {
        try {
            model.addAttribute("url", url);
            final Scanner scanner = CustomerFetcher.fetchCustomerJsonFromUrl(url);
            final List<String> customers = new ArrayList<>();
            for(Customer customer: CustomerFetcher.getCustomerList(scanner)) {
                customers.add(gson.toJson(customer));
            }
            model.addAttribute("customers", customers);

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
