package com.example.sample.controller;

import com.example.sample.dto.ClientAddress;
import com.example.sample.dto.Notifications;
import com.example.sample.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notification")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping("/hello")
    public String hello() {
        return "hello!~";
    }

    @GetMapping("/connections")
    public List<ClientAddress> connections() throws Exception {
        return notificationService.getConnections();
    }

    @PostMapping("/notifications")
    public int notifications(@RequestBody Notifications notifications) throws Exception {
        return notificationService.sendNotifications(notifications);
    }


}
