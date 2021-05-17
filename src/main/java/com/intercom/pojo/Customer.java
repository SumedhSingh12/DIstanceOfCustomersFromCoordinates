package com.intercom.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Customer {
    double latitude;
    int user_id;
    String name;
    double longitude;
    double distanceFromIntercomOffice;
}