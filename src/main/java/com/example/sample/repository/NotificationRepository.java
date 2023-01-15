package com.example.sample.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;

@Repository
public class NotificationRepository {
    private List<WebSocketSession> sessionList = new ArrayList<>();

    public List<WebSocketSession> getSessions() {
        return sessionList;
    }

    public void add(WebSocketSession session) {
        sessionList.add(session);
    }
    public void remove(WebSocketSession session) {
        sessionList.remove(session);
    }
}
