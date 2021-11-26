package com.chess.trainer.backend.utils;

import java.util.List;
import java.util.stream.Collectors;

import com.chess.trainer.backend.model.Move;
import com.chess.trainer.backend.model.Position;

public class PositionUtils {

    public static boolean existsMove(String move, Position position) {
        if (position != null && position.getMoveList() != null) {
            return position.getMoveList().stream().anyMatch((moveInList) -> moveInList.getMoveToSend().equals(move));
        } else {
            return false;
        }
    }

    public static Position deleteMove(Move moveToDelete, Position position) {
        if (moveToDelete != null && position != null) {
            List<Move> filteredPositionMoveList = position.getMoveList().stream()
                    .filter((move) -> !move.getMoveToSend().equals(moveToDelete.getMoveToSend()))
                    .collect(Collectors.toList());
            position.setMoveList(filteredPositionMoveList);
        }
        return position;
    }
}
