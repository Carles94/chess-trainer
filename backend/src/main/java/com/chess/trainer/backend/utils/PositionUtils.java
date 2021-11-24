package com.chess.trainer.backend.utils;

import com.chess.trainer.backend.model.Position;

public class PositionUtils {

    public static boolean existsMoveInPosition(String move, Position position) {
        if (position != null && position.getMoveList() != null) {
            return position.getMoveList().stream().anyMatch((moveInList) -> moveInList.getMoveToSend().equals(move));
        } else {
            return false;
        }
    }
}
