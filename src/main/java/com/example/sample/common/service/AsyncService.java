package com.example.sample.common.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

@Service
public class AsyncService {
    @Async
    public void sendMessage(WebSocketSession session, String message) throws IOException {

            session.sendMessage(new TextMessage(message));

    }
}
