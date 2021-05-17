package com.intercom.controller;

import com.google.gson.Gson;
import com.intercom.common.Constants;
import com.intercom.customerdata.CustomerFetcher;
import com.intercom.customerdata.CustomerFilterAndSorter;
import com.intercom.exceptions.InvalidCoordinatesException;
import com.intercom.pojo.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GenerateInvitationList {

    final Gson gson = new Gson();

    @GetMapping("/result")
    public void result(@RequestParam(defaultValue = Constants.CUSTOMER_FILE_URL, required = false)
                           final String url, final Model model) {
        List<String> result = new ArrayList<>();
        List<Customer> customers = null;
        try {
            customers = new CustomerFilterAndSorter().getCustomerList(CustomerFetcher.fetchCustomerJsonFromUrl(url));
        } catch (final IOException | InvalidCoordinatesException e) {
            e.printStackTrace();
        }
        for(Customer customer: customers) {
            result.add(gson.toJson(customer));
        }
        model.addAttribute("result", result.toString());
    }
}
