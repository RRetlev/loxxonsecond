package com.loxon.javachallenge.api.communication.commands;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.loxon.javachallenge.api.communication.User;
import com.loxon.javachallenge.api.communication.general.Response;

import java.util.List;
import java.util.Objects;

/**
 * General response for cases when response is a list of cell indexes.
 */
@JsonTypeInfo(include= JsonTypeInfo.As.WRAPPER_OBJECT, use= JsonTypeInfo.Id.NAME)
public class ResponseSuccessList extends Response {

    private List<Integer> successCells;

    public ResponseSuccessList() {
    }

    public ResponseSuccessList( final User player, final List<Integer> successCells ) {
        super(player);
        this.successCells = successCells;
    }

    public List<Integer> getSuccessCells() {
        return successCells;
    }

    public void setSuccessCells( final List<Integer> successCells ) {
        this.successCells = successCells;
    }

    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        final ResponseSuccessList that = (ResponseSuccessList) o;
        return Objects.equals(successCells, that.successCells);
    }

    @Override
    public int hashCode() {
        return Objects.hash(successCells);
    }

    @Override
    public String toString() {
        return "ResponseSuccessList{" +
            "successCells=" + successCells +
            '}';
    }

    @Override
    public String toVisualizeString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Response Success List [ ");
        sb.append("Success cells : { " + successCells + " } ] Owner: ");
        if ( this.getPlayer() != null ) {
            sb.append(this.getPlayer().getUserName() + " (" + this.getPlayer().getUserID() + ")");
        }
        return sb.toString();
    }

}
