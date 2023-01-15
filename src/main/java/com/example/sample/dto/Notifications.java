package com.example.sample.dto;

import lombok.Data;

import java.util.List;

@Data
public class Notifications {
    Message message;
    List<ClientAddress> clientAddresses;
}
