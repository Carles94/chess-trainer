package com.chess.trainer.domain.utils;

import com.chess.trainer.domain.model.LineDto;
import com.chess.trainer.domain.model.PositionDto;

public class LineUtils {

    public static PositionDto getPositionFromLineByFen(String fenPosition, LineDto line) {
        // Only matters if the FEN position is the same but not the move number or if en
        // passant is possible
        var reducedFenPosition = fenPosition.substring(0, fenPosition.length() - 6);
        var result = line.getPositionList().stream()
                .filter((position) -> (position.getFenPosition().startsWith(reducedFenPosition))).findFirst().get();
        return result;
    }

    public static boolean existsPositionInLine(String fenPosition, LineDto line) {
        var reducedFenPosition = fenPosition.substring(0, fenPosition.length() - 6);
        return line.getPositionList().stream()
                .anyMatch((position) -> (position.getFenPosition().startsWith(reducedFenPosition)));
    }

    public static boolean canAddMove(String move, String fenPositionToAddMove, String color, LineDto line) {
        if (color.equals(line.getColor())) {
            PositionDto positionToAddMove = LineUtils.getPositionFromLineByFen(fenPositionToAddMove, line);
            return positionToAddMove.getMoveList().size() == 0 || (positionToAddMove.getMoveList().size() == 1
                    && positionToAddMove.getMoveList().get(0).getMoveToSend().equals(move));
        }
        return true;
    }
}
