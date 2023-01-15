package com.example.sample.utils;

import com.example.sample.dto.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

@Slf4j
public class WebSocketUtil extends WebSocketClient {

    private Message message;

    private ObjectMapper objectMapper;


    public WebSocketUtil(URI serverUri, Draft protocolDraft) {
        super(serverUri, protocolDraft);
    }

    @Override
    public void onMessage( String msg ) {


        try {
            message = objectMapper.readValue(msg, Message.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onOpen( ServerHandshake handshake ) {
        log.info( "opened connection" );
    }

    @Override
    public void onClose( int code, String reason, boolean remote ) {
        log.info( "closed connection" );
    }

    @Override
    public void onError( Exception ex ) {
        ex.printStackTrace();
    }

    public Message getResult() {
        return this.message;
    }

}
