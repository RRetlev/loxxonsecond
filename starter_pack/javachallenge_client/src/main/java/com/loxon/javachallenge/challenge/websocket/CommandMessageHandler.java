package com.loxon.javachallenge.challenge.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.Scanner;

@Component
public class CommandMessageHandler implements CommandLineRunner {

    @Autowired
    private MyStompSessionHandler sessionHandler;

    @Autowired
    private Algorithm algorithm;

    @Override
    public void run( String... args ) throws InterruptedException {
        connect();
        while ( !sessionHandler.isConnected() ) {
            Thread.sleep(100);
        }
        sessionHandler.getSession().send("/app/connect", algorithm.getUserId());

        new Scanner(System.in).nextLine();
    }

    private void connect() {
        WebSocketClient client = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new StringMessageConverter());
        stompClient.connect("ws://javachallenge.loxon.eu:8080/gs-guide-websocket", sessionHandler);
    }
}
