package com.loxon.javachallenge.api.communication.commands;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.loxon.javachallenge.api.communication.User;
import com.loxon.javachallenge.api.communication.general.Response;

import java.util.Objects;

/**
 * Response for @{@link CommandStats} command.
 */
@JsonTypeInfo(include= JsonTypeInfo.As.WRAPPER_OBJECT, use= JsonTypeInfo.Id.NAME)
public class ResponseStats extends Response {

    private int cellCount;
    private int ownedCells;
    private int freeCells;
    private int allocatedCells;
    private int corruptCells;
    private int fortifiedCells;
    private int systemCells;
    private int remainingRounds;

    public ResponseStats() {
    }

    public ResponseStats( final User player ) {
        super(player);
    }

    public int getCellCount() {
        return cellCount;
    }

    public void setCellCount( final int cellCount ) {
        this.cellCount = cellCount;
    }

    public int getOwnedCells() {
        return ownedCells;
    }

    public void setOwnedCells( final int ownedCells ) {
        this.ownedCells = ownedCells;
    }

    public int getFreeCells() {
        return freeCells;
    }

    public void setFreeCells( final int freeCells ) {
        this.freeCells = freeCells;
    }

    public int getAllocatedCells() {
        return allocatedCells;
    }

    public void setAllocatedCells( final int allocatedCells ) {
        this.allocatedCells = allocatedCells;
    }

    public int getCorruptCells() {
        return corruptCells;
    }

    public void setCorruptCells( final int corruptCells ) {
        this.corruptCells = corruptCells;
    }

    public int getFortifiedCells() {
        return fortifiedCells;
    }

    public void setFortifiedCells( final int fortifiedCells ) {
        this.fortifiedCells = fortifiedCells;
    }

    public int getSystemCells() {
        return systemCells;
    }

    public void setSystemCells( final int systemCells ) {
        this.systemCells = systemCells;
    }

    public int getRemainingRounds() {
        return remainingRounds;
    }

    public void setRemainingRounds( final int remainingRounds ) {
        this.remainingRounds = remainingRounds;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        ResponseStats that = (ResponseStats) o;
        return cellCount == that.cellCount &&
            ownedCells == that.ownedCells &&
            freeCells == that.freeCells &&
            allocatedCells == that.allocatedCells &&
            corruptCells == that.corruptCells &&
            fortifiedCells == that.fortifiedCells &&
            systemCells == that.systemCells &&
            remainingRounds == that.remainingRounds;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cellCount, ownedCells, freeCells, allocatedCells, corruptCells, fortifiedCells, systemCells, remainingRounds);
    }

    @Override
    public String toVisualizeString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Response Stats [ ");
        sb.append("{ cellCount = " + cellCount + " }");
        sb.append("{ freeCells = " + freeCells + " }");
        sb.append("{ allocatedCells = " + allocatedCells + " }");
        sb.append("{ corruptCells = " + corruptCells + " }");
        sb.append("{ fortifiedCells = " + fortifiedCells + " }");
        sb.append("{ systemCells = " + systemCells + " }");
        sb.append("{ remainingRounds = " + remainingRounds + " } ]  Owner : ");
        if ( this.getPlayer() != null ) {
            sb.append(this.getPlayer().getUserName() + " (" + this.getPlayer().getUserID() + ")");
        }

        return sb.toString();
    }

}
