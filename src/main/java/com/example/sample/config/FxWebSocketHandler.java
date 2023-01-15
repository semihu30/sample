package com.example.sample.config;

import com.example.sample.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Component
@RequiredArgsConstructor
public class FxWebSocketHandler extends TextWebSocketHandler {

    private final NotificationService notificationService;

    // 클라이언트와 연결 이후에 실행되는 메서드
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        notificationService.add(session);
        log.info("{} 연결됨", session.getId());
    }

    // 클라이언트가 서버로 메시지를 전송했을 때 실행되는 메서드
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        log.info("{}로 부터 {} 받음", session.getId(), message.getPayload());
    }

    // 클라이언트와 연결을 끊었을 때 실행되는 메소드
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        notificationService.remove(session);
        log.info("{} 연결 끊김", session.getId());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        notificationService.remove(session);
        log.info("{} 송수신 오류 발생", session.getId());
    }
}
