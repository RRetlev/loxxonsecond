package com.loxon.javachallenge.challenge.websocket;

import com.loxon.javachallenge.api.MemoryState;
import com.loxon.javachallenge.api.communication.commands.ResponseScan;
import com.loxon.javachallenge.api.communication.commands.ResponseStats;
import com.loxon.javachallenge.api.communication.commands.ResponseSuccessList;
import com.loxon.javachallenge.api.communication.general.Command;
import com.loxon.javachallenge.api.communication.general.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Primary
@Slf4j
public class MyAlgorithm extends Algorithm {
    private Integer i = -1;
    private Integer startingCellOfLastScan;
    private int syscells;
    private int cellcount;
    private int frecells;
    private int allocated;
    private int corrupt;
    private int fortified;
    private List<Integer> success = new ArrayList<>();
    private Set<Integer> usedIndexes = new HashSet<>();
    private Random random = new Random();
    private List<MemoryState> lastScan = new ArrayList<>();


    @Override
    public String getUserId() {
        return "645376773";
    }

    @Override
    public Command getNextCommand() {
        //log.info("Success size: " + success.size() + " and success: " + success);
        i++;
        if (i == 0) {
            return this.buildStatsCommand();
        } else if (i < 60) {
            if (success.size() == 2) {
                return this.buildFortifyCommand(success.get(0), success.get(1));
            }
            Integer a = getNewRandomCell();
            usedIndexes.add(a);
            return this.buildAllocateCommand(a, a + 1);
        } else {
            return this.buildScanCommand(i);
        }

    }

    private Integer getNewRandomCell() {
        if (usedIndexes.size() >= 100)
            return null;
        int randomCell;
        do {
            randomCell = random.nextInt(100);
        } while (usedIndexes.contains(randomCell));
        return randomCell;
    }


    private Command Starkiller() {
        if (lastScan.contains(MemoryState.SYSTEM)) {
            return this.buildScanCommand(startingCellOfLastScan + 4);
        } else {
            if (chooseAction() == MemoryState.FREE){
                return this.buildAllocateCommand(startingCellOfLastScan,startingCellOfLastScan + 1);
            }

        }

        return this.buildAllocateCommand(startingCellOfLastScan, startingCellOfLastScan + 1);
    }

    private MemoryState chooseAction() {
        Map<MemoryState, Integer> blockStates = new HashMap<>();
        for (MemoryState memoryState : lastScan) {
            blockStates.computeIfPresent(memoryState, (key, value) -> value += 1);
            blockStates.putIfAbsent(memoryState, 1);
        }
        for (Map.Entry<MemoryState, Integer> stateIntegerEntry :
                blockStates.entrySet()) {
            if (stateIntegerEntry.getValue() == 2) {
                return stateIntegerEntry.getKey();
            }
        }
        return null;
    }


    @Override
    public void resultArrived(Response result) {
        success.clear();
        if (result instanceof ResponseStats) {
            syscells = ((ResponseStats) result).getSystemCells();
            cellcount = ((ResponseStats) result).getCellCount();
            frecells = ((ResponseStats) result).getFreeCells();
            allocated = ((ResponseStats) result).getAllocatedCells();
            corrupt = ((ResponseStats) result).getCorruptCells();
            fortified = ((ResponseStats) result).getFortifiedCells();
        }
        if (result instanceof ResponseSuccessList) {
            success = ((ResponseSuccessList) result).getSuccessCells();
        }
        if (result instanceof ResponseScan) {
            startingCellOfLastScan = ((ResponseScan) result).getFirstCell();
            lastScan = ((ResponseScan) result).getStates();
        }
    }
}
