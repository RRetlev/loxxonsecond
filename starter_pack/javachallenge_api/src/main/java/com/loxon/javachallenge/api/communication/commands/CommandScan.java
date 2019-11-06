package com.loxon.javachallenge.api.communication.commands;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.loxon.javachallenge.api.communication.User;
import com.loxon.javachallenge.api.communication.general.Command;

/**
 * Scan a block in the memory.
 */
@JsonTypeInfo( include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME )
public class CommandScan extends Command {
    private Integer cell;

    public CommandScan() {
    }

    public CommandScan( final User player, final Integer cell ) {
        super(player);
        this.cell = cell;
    }

    public Integer getCell() {
        return cell;
    }

    public void setCell( final Integer cell ) {
        this.cell = cell;
    }

    @Override
    public String toVisualizeString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Command Scan [ Owner: ");
        if ( this.getPlayer() != null ) {
            sb.append(this.getPlayer().getUserName() + " (" + this.getPlayer().getUserID() + ")");
        }
        sb.append(" Cells: { " + cell + " } ]");
        return sb.toString();
    }
}
