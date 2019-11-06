package com.loxon.javachallenge.challenge.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loxon.javachallenge.api.communication.general.Command;
import com.loxon.javachallenge.api.communication.general.Response;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Slf4j
@Component
public class MyStompSessionHandler implements StompSessionHandler {

    @Autowired
    private CommandHandler commandHandler;

    @Getter
    private StompSession session;

    @Getter
    private boolean connected = false;

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public void afterConnected( StompSession session, StompHeaders connectedHeaders ) {
        log.info("Client: Im connected.");
        session.subscribe("/user/queue/greetings", new StompFrameHandler() {
            @Override
            public Type getPayloadType( StompHeaders headers ) {
                return String.class;
            }

            @Override
            public void handleFrame( StompHeaders headers, Object payload ) {
                log.info("Message from Server: " + payload.toString());
                commandHandler.connected();
            }
        });
        session.subscribe("/user/queue/commands", new StompFrameHandler() {
            @Override
            public Type getPayloadType( StompHeaders headers ) {
                return String.class;
            }

            @Override
            public void handleFrame( StompHeaders headers, Object payload ) {
                log.info("Message arrived from server: Round nr.: " + payload.toString());
                String  commandJsonString = null;
                Command c                 = commandHandler.nextCommand();
                try {
                    commandJsonString = mapper.writeValueAsString(c);
                } catch (JsonProcessingException ex) {
                    log.error("Failed to convert command.", ex.getMessage());
                }
                log.info("Sending message to Server: " + c.toVisualizeString());
                session.send("/app/command", commandJsonString);
            }
        });
        session.subscribe("/user/queue/results", new StompFrameHandler() {
            @Override
            public Type getPayloadType( StompHeaders headers ) {
                return String.class;
            }

            @Override
            public void handleFrame( StompHeaders headers, Object payload ) {
                log.debug("Message arrived from server: Result object: " + payload.toString());
                Response r = commandHandler.result(payload.toString());
                log.info("Message arrived from server: Result object: " + r.toVisualizeString());
            }
        });
        this.session = session;
        this.connected = true;

    }

    @Override
    public void handleException( StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception ) {
        log.info("Exception thrown.", exception);
    }

    @Override
    public void handleTransportError( StompSession session, Throwable exception ) {
        log.info("TransportError.", exception);
    }

    @Override
    public Type getPayloadType( final StompHeaders stompHeaders ) {
        return null;
    }

    @Override
    public void handleFrame( final StompHeaders stompHeaders, final Object o ) {

    }
}
