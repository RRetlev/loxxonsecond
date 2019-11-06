package com.loxon.javachallenge.challenge.websocket;

import com.loxon.javachallenge.api.communication.general.Command;
import com.loxon.javachallenge.api.communication.general.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CommandHandler {

    @Autowired
    Algorithm gameLogic;

    public Command nextCommand() {
        return gameLogic.getNextCommand();
    }

    public Response result( final String result ) {
        Response r = gameLogic.parseResult(result);
        gameLogic.resultArrived(r);
        return r;
    }

    public void connected() {

    }

}
