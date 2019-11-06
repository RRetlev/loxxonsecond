package com.loxon.javachallenge.api.communication.commands;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.loxon.javachallenge.api.communication.User;
import com.loxon.javachallenge.api.communication.general.CommandGeneral;

import java.util.List;

/**
 * Recover cells.
 */
@JsonTypeInfo( include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME )
public class CommandRecover extends CommandGeneral {

    public CommandRecover() {
    }

    public CommandRecover( final User player, final List<Integer> cells ) {
        super(player, cells);
    }

    @Override
    public String toVisualizeString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Command Recover [ Owner: ");
        if ( this.getPlayer() != null ) {
            sb.append(this.getPlayer().getUserName() + " (" + this.getPlayer().getUserID() + ")");
        }
        sb.append(" Cells: { " + this.getCells());
        sb.append(" } ]");
        return sb.toString();
    }
}
