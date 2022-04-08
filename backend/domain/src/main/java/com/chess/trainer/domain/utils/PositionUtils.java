package com.chess.trainer.domain.utils;

import java.util.List;
import java.util.stream.Collectors;

import com.chess.trainer.domain.model.MoveDto;
import com.chess.trainer.domain.model.PositionDto;

public class PositionUtils {

    public static boolean existsMove(String move, PositionDto position) {
        if (position != null && position.getMoveList() != null) {
            return position.getMoveList().stream().anyMatch((moveInList) -> moveInList.getMoveToSend().equals(move));
        } else {
            return false;
        }
    }

    public static PositionDto deleteMove(MoveDto moveToDelete, PositionDto position) {
        if (moveToDelete != null && position != null) {
            List<MoveDto> filteredPositionMoveList = position.getMoveList().stream()
                    .filter((move) -> !move.getMoveToSend().equals(moveToDelete.getMoveToSend()))
                    .collect(Collectors.toList());
            position.setMoveList(filteredPositionMoveList);
        }
        return position;
    }
}
