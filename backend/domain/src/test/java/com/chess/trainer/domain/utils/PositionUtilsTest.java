package com.chess.trainer.domain.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.chess.trainer.domain.model.MoveDto;
import com.chess.trainer.domain.model.PositionDto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PositionUtilsTest {

    @Test
    public void testExistsMoveInPositionWith2Moves() {
        // Arrange
        String move = "e2e4";
        PositionDto position = new PositionDto();
        List<MoveDto> moveList = new ArrayList<>();
        MoveDto m = new MoveDto();
        m.setMoveToSend("e2e4");
        moveList.add(m);
        MoveDto m2 = new MoveDto();
        m2.setMoveToSend("d2d4");
        moveList.add(m2);
        position.setMoveList(moveList);
        // Act
        boolean result = PositionUtils.existsMove(move, position);
        // Assert
        Assertions.assertTrue(result);
    }

    @Test
    public void testExistsMoveInPositionWithoutMoves() {
        // Arrange
        String move = "e2e4";
        PositionDto position = new PositionDto();
        List<MoveDto> moveList = new ArrayList<>();
        position.setMoveList(moveList);
        // Act
        boolean result = PositionUtils.existsMove(move, position);
        // Assert
        Assertions.assertFalse(result);
    }

    @Test
    public void testExistsMoveInPositionWith1Move() {
        // Arrange
        String move = "e2e4";
        PositionDto position = new PositionDto();
        List<MoveDto> moveList = new ArrayList<>();
        MoveDto m2 = new MoveDto();
        m2.setMoveToSend("d2d4");
        moveList.add(m2);
        position.setMoveList(moveList);
        // Act
        boolean result = PositionUtils.existsMove(move, position);
        // Assert
        Assertions.assertFalse(result);
    }

    @Test
    public void testExistsMoveInNullPosition() {
        // Arrange
        String move = "e2e4";
        // Act
        boolean result = PositionUtils.existsMove(move, null);
        // Assert
        Assertions.assertFalse(result);
    }

    @Test
    public void testExistsMoveInPositionWithoutMoveList() {
        // Arrange
        String move = "e2e4";
        PositionDto position = new PositionDto();
        // Act
        boolean result = PositionUtils.existsMove(move, position);
        // Assert
        Assertions.assertFalse(result);
    }

    @Test
    public void testDeleteMove() {
        // Arrange
        MoveDto moveToDelete = new MoveDto();
        moveToDelete.setMoveToSend("e2e4");
        MoveDto otherMove = new MoveDto();
        otherMove.setMoveToSend("d2d4");
        PositionDto position = new PositionDto();
        position.setMoveList(Arrays.asList(moveToDelete, otherMove));
        // Act
        PositionDto result = PositionUtils.deleteMove(moveToDelete, position);
        // Assert
        Assertions.assertEquals(1, result.getMoveList().size());
        Assertions.assertEquals(otherMove.getMoveToSend(), result.getMoveList().get(0).getMoveToSend());
    }
}
