package com.example.sample.service;

import com.example.sample.dto.ClientAddress;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.drafts.Draft_6455;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
//@RequiredArgsConstructor
@SpringBootTest
class NotificationServiceTest {

    @Autowired
    NotificationService notificationService;

    ObjectMapper objectMapper;
/*

    WebSocketUtil webSocketUtil1 = null;
    WebSocketUtil webSocketUtil2 = null;
    WebSocketUtil webSocketUtil3 = null;

    @BeforeEach
    void beforeEach() throws URISyntaxException, InterruptedException {
        URI uri = new URI("ws://localhost:8080/ws/test");
        // 테스트를 시작 할 때 마다 connection 생성
        webSocketUtil1 = new WebSocketUtil(uri, new Draft_6455());
        webSocketUtil2 = new WebSocketUtil(uri, new Draft_6455());
        webSocketUtil3 = new WebSocketUtil(uri, new Draft_6455());

        //웹소켓 커넥팅
        webSocketUtil1.connectBlocking();
        webSocketUtil2.connectBlocking();
        webSocketUtil3.connectBlocking();
    }

    @AfterEach
    void afterEach() {
        // 테스트가 끝날 때 마다 connection 제거
        webSocketUtil1.close();
        webSocketUtil2.close();
        webSocketUtil3.close();
    }
*/

    @Test
    void notifications() throws Exception {

//        URI uri = new URI("ws://localhost:8080/ws/test");
        // 테스트를 시작 할 때 마다 connection 생성
//        WebSocketUtil webSocketUtil1 = new WebSocketUtil(uri, new Draft_6455());
//        WebSocketUtil webSocketUtil2 = new WebSocketUtil(uri, new Draft_6455());
//        WebSocketUtil webSocketUtil3 = new WebSocketUtil(uri, new Draft_6455());

        //웹소켓 커넥팅
//        webSocketUtil1.connectBlocking();
//        webSocketUtil2.connectBlocking();
//        webSocketUtil3.connectBlocking();

        List<ClientAddress> connections1 = notificationService.test();

        List<ClientAddress> connections = notificationService.getConnections();
        assertThat(1).isEqualTo(notificationService.getConnections().size());
        log.info("connections: {}", objectMapper.writeValueAsString(connections));
    }


}