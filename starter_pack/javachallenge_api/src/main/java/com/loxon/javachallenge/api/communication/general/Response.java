package com.loxon.javachallenge.api.communication.general;

import com.loxon.javachallenge.api.communication.User;

/**
 * Base class for responses.
 */
public abstract class Response {
    private User player;

    public Response() {
    }

    public Response( final User player ) {
        this.player = player;
    }

    public User getPlayer() {
        return player;
    }

    public void setPlayer( final User player ) {
        this.player = player;
    }

    public abstract String toVisualizeString();
}
