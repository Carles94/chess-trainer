package com.chess.trainer.domain.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.chess.trainer.domain.model.Move;
import com.chess.trainer.domain.model.Position;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PositionUtilsTest {

    @Test
    public void testExistsMoveInPositionWith2Moves() {
        // Arrange
        String move = "e2e4";
        Position position = new Position();
        List<Move> moveList = new ArrayList<>();
        Move m = new Move();
        m.setMoveToSend("e2e4");
        moveList.add(m);
        Move m2 = new Move();
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
        Position position = new Position();
        List<Move> moveList = new ArrayList<>();
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
        Position position = new Position();
        List<Move> moveList = new ArrayList<>();
        Move m2 = new Move();
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
        Position position = new Position();
        // Act
        boolean result = PositionUtils.existsMove(move, position);
        // Assert
        Assertions.assertFalse(result);
    }

    @Test
    public void testDeleteMove() {
        // Arrange
        Move moveToDelete = new Move();
        moveToDelete.setMoveToSend("e2e4");
        Move otherMove = new Move();
        otherMove.setMoveToSend("d2d4");
        Position position = new Position();
        position.setMoveList(Arrays.asList(moveToDelete, otherMove));
        // Act
        Position result = PositionUtils.deleteMove(moveToDelete, position);
        // Assert
        Assertions.assertEquals(1, result.getMoveList().size());
        Assertions.assertEquals(otherMove.getMoveToSend(), result.getMoveList().get(0).getMoveToSend());
    }
}
