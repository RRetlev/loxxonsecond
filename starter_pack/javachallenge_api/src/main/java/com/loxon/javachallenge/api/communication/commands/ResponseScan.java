package com.loxon.javachallenge.api.communication.commands;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.loxon.javachallenge.api.MemoryState;
import com.loxon.javachallenge.api.communication.User;
import com.loxon.javachallenge.api.communication.general.Response;

import java.util.List;
import java.util.Objects;

/**
 * Response for @{@link CommandScan} command.
 */
@JsonTypeInfo(include= JsonTypeInfo.As.WRAPPER_OBJECT, use= JsonTypeInfo.Id.NAME)
public class ResponseScan extends Response {
    private int firstCell;
    private List<MemoryState> states;

    public ResponseScan() {
    }

    public ResponseScan(final User player, final int firstCell, final List<MemoryState> states) {
        super(player);
        this.firstCell = firstCell;
        this.states = states;
    }

    public int getFirstCell() {
        return firstCell;
    }

    public void setFirstCell( final int firstCell ) {
        this.firstCell = firstCell;
    }

    public List<MemoryState> getStates() {
        return states;
    }

    public void setStates( final List<MemoryState> states ) {
        this.states = states;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ResponseScan that = (ResponseScan) o;
        return firstCell == that.firstCell &&
                Objects.equals(states, that.states);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstCell, states);
    }

    @Override
    public String toVisualizeString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Response Scan [ First cell: { " + firstCell +" } , Call states { ");
        sb.append(states + " } ] Owner :");
        if ( this.getPlayer() != null ) {
            sb.append(this.getPlayer().getUserName() + " (" + this.getPlayer().getUserID() + ")");
        }
        return sb.toString();
    }

}
