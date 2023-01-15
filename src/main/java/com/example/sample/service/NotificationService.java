package com.example.sample.service;

import com.example.sample.common.service.AsyncService;
import com.example.sample.dto.ClientAddress;
import com.example.sample.dto.Notifications;
import com.example.sample.repository.NotificationRepository;
import com.example.sample.utils.WebSocketUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.drafts.Draft_6455;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    private final ObjectMapper objectMapper;

    private final AsyncService asyncService;

    public List<ClientAddress> test() throws InterruptedException, URISyntaxException {
        URI uri = new URI("ws://localhost:8080/ws/test");
        // 테스트를 시작 할 때 마다 connection 생성
        WebSocketUtil webSocketUtil1 = new WebSocketUtil(uri, new Draft_6455());
//        WebSocketUtil webSocketUtil2 = new WebSocketUtil(uri, new Draft_6455());
//        WebSocketUtil webSocketUtil3 = new WebSocketUtil(uri, new Draft_6455());

        //웹소켓 커넥팅
        webSocketUtil1.connectBlocking();
//        webSocketUtil2.connectBlocking();
//        webSocketUtil3.connectBlocking();

        Thread.sleep(3000);

        return getConnections();
    }

    public List<ClientAddress> getConnections() {
        List<WebSocketSession> sessions = notificationRepository.getSessions();

        List<ClientAddress> clientAddresses = new ArrayList<>();

        for (WebSocketSession session : sessions) {
            InetSocketAddress remoteAddress = session.getRemoteAddress();
            if (remoteAddress != null) {
                ClientAddress clientAddress = new ClientAddress();
                clientAddress.setSessionId(session.getId());
                clientAddress.setClientIp(remoteAddress.getAddress().getHostAddress());
                clientAddresses.add(clientAddress);
            }
        }
        return clientAddresses;
    }

    public int sendNotifications(Notifications notifications) throws Exception {
        int clientCount = 0;
        String message = objectMapper.writeValueAsString(notifications.getMessage());
        List<ClientAddress> clientAddresses = notifications.getClientAddresses();

        List<WebSocketSession> sessions = notificationRepository.getSessions();

        for (WebSocketSession session : sessions) {
            Optional<ClientAddress> first = clientAddresses.stream()
                    .filter(clientAddress -> clientAddress.getSessionId().equals(session.getId()))
                    .findFirst();
            if (first.isPresent()) {
                try {
                    asyncService.sendMessage(session, message);
                    clientCount++;
                } catch (IOException e) {
                    log.info("{} 송수신 오류 발생", session.getId());
                    remove(session);
                }
            }
        }
        return clientCount;
    }
/*
    public void sendMessage(WebSocketSession session, Message message) {
        session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
    }
    */
    public void add(WebSocketSession session) {
        notificationRepository.add(session);
    }
    public void remove(WebSocketSession session) {
        notificationRepository.remove(session);
    }
}
