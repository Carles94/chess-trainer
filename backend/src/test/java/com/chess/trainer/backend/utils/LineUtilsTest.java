package com.chess.trainer.backend.utils;

import java.util.ArrayList;
import java.util.List;

import com.chess.trainer.backend.constant.FenConstant;
import com.chess.trainer.backend.model.Line;
import com.chess.trainer.backend.model.Position;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LineUtilsTest {

    @Test
    void testGetPositionFromLineByFen() {
        // Arrange
        String fenPosition = FenConstant.INITIAL_FEN;
        Line line = new Line();
        List<Position> positionList = new ArrayList<>();
        var position = new Position();
        position.setFenPosition(FenConstant.INITIAL_FEN);
        positionList.add(position);
        var position2 = new Position();
        position2.setFenPosition("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1");
        positionList.add(position2);
        line.setPositionList(positionList);
        // Act
        Position result = LineUtils.getPositionFromLineByFen(fenPosition, line);
        // Assert
        Assertions.assertEquals(position, result);
    }

    @Test
    void testGetPositionFromLineByFenUnexistant() {
        // Arrange
        String fenPosition = "rnbqkbnr/pppppppp/8/8/3P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1";
        Line line = new Line();
        List<Position> positionList = new ArrayList<>();
        var position = new Position();
        position.setFenPosition(FenConstant.INITIAL_FEN);
        positionList.add(position);
        var position2 = new Position();
        position2.setFenPosition("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1");
        positionList.add(position2);
        line.setPositionList(positionList);
        // Act + Assert
        // TODO customize exceptions
        Assertions.assertThrows(Exception.class, () -> LineUtils.getPositionFromLineByFen(fenPosition, line));
    }

    @Test
    void testGetPositionFromLineByFenByTransposition() {
        // Arrange
        String fenPosition = "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 2 3";
        Line line = new Line();
        List<Position> positionList = new ArrayList<>();
        var position = new Position();
        position.setFenPosition(FenConstant.INITIAL_FEN);
        positionList.add(position);
        var position2 = new Position();
        position2.setFenPosition("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1");
        positionList.add(position2);
        line.setPositionList(positionList);
        // Act
        Position result = LineUtils.getPositionFromLineByFen(fenPosition, line);
        // Assert
        Assertions.assertEquals(position2, result);
    }

    @Test
    void testGetPositionFromLineByFenByTranspositionWithEnPassant() {
        // Arrange
        String fenPosition = "rnbqkbnr/ppp1pppp/8/3p4/3P4/5N2/PPP1PPPP/RNBQKB1R b KQkq d3 0 2";
        Line line = new Line();
        List<Position> positionList = new ArrayList<>();
        var position = new Position();
        position.setFenPosition(FenConstant.INITIAL_FEN);
        positionList.add(position);
        var position2 = new Position();
        position2.setFenPosition("rnbqkbnr/ppp1pppp/8/3p4/3P4/5N2/PPP1PPPP/RNBQKB1R b KQkq - 0 2");
        positionList.add(position2);
        line.setPositionList(positionList);
        // Act
        Position result = LineUtils.getPositionFromLineByFen(fenPosition, line);
        // Assert
        Assertions.assertEquals(position2, result);
    }

    @Test
    void testGetPositionFromLineByFenByTranspositionWithEnPassant2() {
        // Arrange
        String fenPosition = "rnbqkbnr/ppp1pppp/8/3p4/3P4/5N2/PPP1PPPP/RNBQKB1R b KQkq - 0 2";
        Line line = new Line();
        List<Position> positionList = new ArrayList<>();
        var position = new Position();
        position.setFenPosition(FenConstant.INITIAL_FEN);
        positionList.add(position);
        var position2 = new Position();
        position2.setFenPosition("rnbqkbnr/ppp1pppp/8/3p4/3P4/5N2/PPP1PPPP/RNBQKB1R b KQkq d3 0 2");
        positionList.add(position2);
        line.setPositionList(positionList);
        // Act
        Position result = LineUtils.getPositionFromLineByFen(fenPosition, line);
        // Assert
        Assertions.assertEquals(position2, result);
    }

    @Test
    void testExistsPositionInLine() {
        // Arrange
        String fenPosition = FenConstant.INITIAL_FEN;
        Line line = new Line();
        List<Position> positionList = new ArrayList<>();
        var position = new Position();
        position.setFenPosition(FenConstant.INITIAL_FEN);
        positionList.add(position);
        var position2 = new Position();
        position2.setFenPosition("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1");
        positionList.add(position2);
        line.setPositionList(positionList);
        // Act
        boolean result = LineUtils.existsPositionInLine(fenPosition, line);
        // Assert
        Assertions.assertTrue(result);
    }

    @Test
    void testExistsPositionInLineUnexistant() {
        // Arrange
        String fenPosition = "rnbqkbnr/pppppppp/8/8/3P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1";
        Line line = new Line();
        List<Position> positionList = new ArrayList<>();
        var position = new Position();
        position.setFenPosition(FenConstant.INITIAL_FEN);
        positionList.add(position);
        var position2 = new Position();
        position2.setFenPosition("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1");
        positionList.add(position2);
        line.setPositionList(positionList);
        // Act
        // TODO customize exceptions
        boolean result = LineUtils.existsPositionInLine(fenPosition, line);
        // Assert
        Assertions.assertFalse(result);
    }

    @Test
    void testExistsPositionInLineByTransposition() {
        // Arrange
        String fenPosition = "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 2 3";
        Line line = new Line();
        List<Position> positionList = new ArrayList<>();
        var position = new Position();
        position.setFenPosition(FenConstant.INITIAL_FEN);
        positionList.add(position);
        var position2 = new Position();
        position2.setFenPosition("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1");
        positionList.add(position2);
        line.setPositionList(positionList);
        // Act
        boolean result = LineUtils.existsPositionInLine(fenPosition, line);
        // Assert
        Assertions.assertTrue(result);
    }

    @Test
    void testExistsPositionInLineByTranspositionWithEnPassant() {
        // Arrange
        String fenPosition = "rnbqkbnr/ppp1pppp/8/3p4/3P4/5N2/PPP1PPPP/RNBQKB1R b KQkq d3 0 2";
        Line line = new Line();
        List<Position> positionList = new ArrayList<>();
        var position = new Position();
        position.setFenPosition(FenConstant.INITIAL_FEN);
        positionList.add(position);
        var position2 = new Position();
        position2.setFenPosition("rnbqkbnr/ppp1pppp/8/3p4/3P4/5N2/PPP1PPPP/RNBQKB1R b KQkq - 0 2");
        positionList.add(position2);
        line.setPositionList(positionList);
        // Act
        boolean result = LineUtils.existsPositionInLine(fenPosition, line);
        // Assert
        Assertions.assertTrue(result);
    }

    @Test
    void testExistsPositionInLineByTranspositionWithEnPassant2() {
        // Arrange
        String fenPosition = "rnbqkbnr/ppp1pppp/8/3p4/3P4/5N2/PPP1PPPP/RNBQKB1R b KQkq - 0 2";
        Line line = new Line();
        List<Position> positionList = new ArrayList<>();
        var position = new Position();
        position.setFenPosition(FenConstant.INITIAL_FEN);
        positionList.add(position);
        var position2 = new Position();
        position2.setFenPosition("rnbqkbnr/ppp1pppp/8/3p4/3P4/5N2/PPP1PPPP/RNBQKB1R b KQkq d3 0 2");
        positionList.add(position2);
        line.setPositionList(positionList);
        // Act
        boolean result = LineUtils.existsPositionInLine(fenPosition, line);
        // Assert
        Assertions.assertTrue(result);
    }
}
