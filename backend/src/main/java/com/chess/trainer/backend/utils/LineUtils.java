package com.chess.trainer.backend.utils;

import com.chess.trainer.backend.model.Line;
import com.chess.trainer.backend.model.Position;

public class LineUtils {

    public static Position getPositionFromLineByFen(String fenPosition, Line line) {
        // Only matters if the FEN position is the same but not the move number or if en
        // passant is possible
        var reducedFenPosition = fenPosition.substring(0, fenPosition.length() - 6);
        var result = line.getPositionList().stream()
                .filter((position) -> (position.getFenPosition().startsWith(reducedFenPosition))).findFirst().get();
        return result;
    }

    public static boolean existsPositionInLine(String fenPosition, Line line) {
        var reducedFenPosition = fenPosition.substring(0, fenPosition.length() - 6);
        return line.getPositionList().stream()
                .anyMatch((position) -> (position.getFenPosition().startsWith(reducedFenPosition)));
    }

    // TODO apply that
    public static boolean canAddMove(String fenPositionToAddMove, String color, Line line) {
        if (LineUtils.existsPositionInLine(fenPositionToAddMove, line) && line.getColor() == color) {
            Position positionToAddMove = LineUtils.getPositionFromLineByFen(fenPositionToAddMove, line);
            return positionToAddMove.getMoveList().size() == 0;
        }
        return true;
    }
}
