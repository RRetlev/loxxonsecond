package com.loxon.javachallenge.challenge.websocket;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loxon.javachallenge.api.communication.commands.CommandAllocate;
import com.loxon.javachallenge.api.communication.commands.CommandFortify;
import com.loxon.javachallenge.api.communication.commands.CommandFree;
import com.loxon.javachallenge.api.communication.commands.CommandRecover;
import com.loxon.javachallenge.api.communication.commands.CommandScan;
import com.loxon.javachallenge.api.communication.commands.CommandStats;
import com.loxon.javachallenge.api.communication.commands.CommandSwap;
import com.loxon.javachallenge.api.communication.commands.ResponseScan;
import com.loxon.javachallenge.api.communication.commands.ResponseStats;
import com.loxon.javachallenge.api.communication.commands.ResponseSuccessList;
import com.loxon.javachallenge.api.communication.general.Command;
import com.loxon.javachallenge.api.communication.general.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public abstract class Algorithm {

    protected ObjectMapper mapper;

    {
        mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.enable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);
    }

    /**
     * Get user's identifier.
     * @return user's identifier.
     */
    public abstract String getUserId();

    /**
     * Next command to be executed on server.
     * @return next command to be executed on server.
     */
    public abstract Command getNextCommand();

    /**
     * Callback after command is executed on server.
     * @param result result for last command
     */
    public abstract void resultArrived( Response result );


    public final Response parseResult( final String result ) {
        Exception e = null;
        Response  r = null;

        try {
            r = mapper.readValue(result, ResponseSuccessList.class);
        } catch (Exception ex) {
            //noCare
        }

        try {
            r = mapper.readValue(result, ResponseScan.class);
        } catch (Exception ex) {
            //noCare
        }

        try {
            r = mapper.readValue(result, ResponseStats.class);
        } catch (Exception ex) {
            //noCare
        }

        if ( r == null ) {
            log.error("Failed to parse response.", e == null ? "" : e.getMessage());
        }

        return r;
    }

    protected final CommandAllocate buildAllocateCommand( final Integer cell1, final Integer cell2 ) {
        return new CommandAllocate(null, Arrays.asList(cell1, cell2));
    }

    protected final CommandFree buildFreeCommand( final Integer cell1, final Integer cell2 ) {
        return new CommandFree(null, Arrays.asList(cell1, cell2));
    }

    protected final CommandRecover buildRecoverCommand( final Integer cell1, final Integer cell2 ) {
        return new CommandRecover(null, Arrays.asList(cell1, cell2));
    }

    protected final CommandSwap buildSwapCommand( final int cell1, final int cell2 ) {
        return new CommandSwap(null, Arrays.asList(cell1, cell2));
    }

    protected final CommandStats buildStatsCommand() {
        return new CommandStats(null);
    }

    protected final CommandScan buildScanCommand( final Integer cell ) {
        return new CommandScan(null, cell);
    }

    protected final CommandFortify buildFortifyCommand( final Integer cell1, final Integer cell2 ) {
        return new CommandFortify(null, Arrays.asList(cell1, cell2));
    }

}
