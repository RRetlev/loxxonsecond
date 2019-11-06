package com.loxon.javachallenge.api.communication.general;

import com.loxon.javachallenge.api.communication.User;

/**
 * Base of all handled commands.
 */
public abstract class Command {
    private User player;

    public Command() {
    }

    public Command( final User player ) {
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
