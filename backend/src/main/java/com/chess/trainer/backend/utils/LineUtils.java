package com.chess.trainer.backend.utils;

import com.chess.trainer.backend.model.Line;
import com.chess.trainer.backend.model.Position;

public class LineUtils {

    public static Position getPositionFromLineByFen(String fenPosition, Line line) {
        // Only matters if the FEN position is the same but not the move number
        var reducedFenPosition = fenPosition.substring(0, fenPosition.length() - 4);
        var result = line.getPositionList().stream()
                .filter((position) -> (position.getFenPosition().startsWith(reducedFenPosition))).findFirst().get();
        return result;
    }

    public static boolean existsPositionInLine(String fenPosition, Line line) {
        var reducedFenPosition = fenPosition.substring(0, fenPosition.length() - 4);
        return line.getPositionList().stream()
                .anyMatch((position) -> (position.getFenPosition().startsWith(reducedFenPosition)));
    }

}
