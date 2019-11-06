package com.loxon.javachallenge.api.communication.general;

import com.loxon.javachallenge.api.communication.User;

import java.util.List;

/**
 * Abstract command affecting number of cells.
 */
public abstract class CommandGeneral extends Command {
    private List<Integer> cells;

    public CommandGeneral(){
    }

    public CommandGeneral(final User player, final List<Integer> cells) {
        super(player);
        this.cells = cells;
    }

    public List<Integer> getCells() {
        return cells;
    }

    public void setCells( final List<Integer> cells ) {
        this.cells = cells;
    }
}
