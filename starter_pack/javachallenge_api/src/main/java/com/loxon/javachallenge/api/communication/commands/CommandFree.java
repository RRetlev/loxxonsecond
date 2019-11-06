package com.loxon.javachallenge.api.communication.commands;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.loxon.javachallenge.api.communication.User;
import com.loxon.javachallenge.api.communication.general.CommandGeneral;

import java.util.List;

/**
 * Free cells.
 */
@JsonTypeInfo( include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME )
public class CommandFree extends CommandGeneral {

    public CommandFree() {
    }

    public CommandFree( final User player, final List<Integer> cells ) {
        super(player, cells);
    }

    @Override
    public String toVisualizeString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Command Free [ Owner: ");
        if ( this.getPlayer() != null ) {
            sb.append(this.getPlayer().getUserName() + " (" + this.getPlayer().getUserID() + ")");
        }
        sb.append(" Cells: { " + this.getCells());
        sb.append(" } ]");
        return sb.toString();
    }
}
