package com.chess.trainer.domain.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.chess.trainer.domain.constant.Constants;
import com.chess.trainer.domain.model.LineDto;
import com.chess.trainer.domain.model.MoveDto;
import com.chess.trainer.domain.model.PositionDto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LineUtilsTest {

    @Test
    void testGetPositionFromLineByFen() {
        // Arrange
        String fenPosition = Constants.INITIAL_FEN;
        LineDto line = new LineDto();
        List<PositionDto> positionList = new ArrayList<>();
        var position = new PositionDto();
        position.setFenPosition(Constants.INITIAL_FEN);
        positionList.add(position);
        var position2 = new PositionDto();
        position2.setFenPosition("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1");
        positionList.add(position2);
        line.setPositionList(positionList);
        // Act
        PositionDto result = LineUtils.getPositionFromLineByFen(fenPosition, line);
        // Assert
        Assertions.assertEquals(position, result);
    }

    @Test
    void testGetPositionFromLineByFenUnexistant() {
        // Arrange
        String fenPosition = "rnbqkbnr/pppppppp/8/8/3P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1";
        LineDto line = new LineDto();
        List<PositionDto> positionList = new ArrayList<>();
        var position = new PositionDto();
        position.setFenPosition(Constants.INITIAL_FEN);
        positionList.add(position);
        var position2 = new PositionDto();
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
        LineDto line = new LineDto();
        List<PositionDto> positionList = new ArrayList<>();
        var position = new PositionDto();
        position.setFenPosition(Constants.INITIAL_FEN);
        positionList.add(position);
        var position2 = new PositionDto();
        position2.setFenPosition("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1");
        positionList.add(position2);
        line.setPositionList(positionList);
        // Act
        PositionDto result = LineUtils.getPositionFromLineByFen(fenPosition, line);
        // Assert
        Assertions.assertEquals(position2, result);
    }

    @Test
    void testGetPositionFromLineByFenByTranspositionWithEnPassant() {
        // Arrange
        String fenPosition = "rnbqkbnr/ppp1pppp/8/3p4/3P4/5N2/PPP1PPPP/RNBQKB1R b KQkq d3 0 2";
        LineDto line = new LineDto();
        List<PositionDto> positionList = new ArrayList<>();
        var position = new PositionDto();
        position.setFenPosition(Constants.INITIAL_FEN);
        positionList.add(position);
        var position2 = new PositionDto();
        position2.setFenPosition("rnbqkbnr/ppp1pppp/8/3p4/3P4/5N2/PPP1PPPP/RNBQKB1R b KQkq - 0 2");
        positionList.add(position2);
        line.setPositionList(positionList);
        // Act
        PositionDto result = LineUtils.getPositionFromLineByFen(fenPosition, line);
        // Assert
        Assertions.assertEquals(position2, result);
    }

    @Test
    void testGetPositionFromLineByFenByTranspositionWithEnPassant2() {
        // Arrange
        String fenPosition = "rnbqkbnr/ppp1pppp/8/3p4/3P4/5N2/PPP1PPPP/RNBQKB1R b KQkq - 0 2";
        LineDto line = new LineDto();
        List<PositionDto> positionList = new ArrayList<>();
        var position = new PositionDto();
        position.setFenPosition(Constants.INITIAL_FEN);
        positionList.add(position);
        var position2 = new PositionDto();
        position2.setFenPosition("rnbqkbnr/ppp1pppp/8/3p4/3P4/5N2/PPP1PPPP/RNBQKB1R b KQkq d3 0 2");
        positionList.add(position2);
        line.setPositionList(positionList);
        // Act
        PositionDto result = LineUtils.getPositionFromLineByFen(fenPosition, line);
        // Assert
        Assertions.assertEquals(position2, result);
    }

    @Test
    void testExistsPositionInLine() {
        // Arrange
        String fenPosition = Constants.INITIAL_FEN;
        LineDto line = new LineDto();
        List<PositionDto> positionList = new ArrayList<>();
        var position = new PositionDto();
        position.setFenPosition(Constants.INITIAL_FEN);
        positionList.add(position);
        var position2 = new PositionDto();
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
        LineDto line = new LineDto();
        List<PositionDto> positionList = new ArrayList<>();
        var position = new PositionDto();
        position.setFenPosition(Constants.INITIAL_FEN);
        positionList.add(position);
        var position2 = new PositionDto();
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
        LineDto line = new LineDto();
        List<PositionDto> positionList = new ArrayList<>();
        var position = new PositionDto();
        position.setFenPosition(Constants.INITIAL_FEN);
        positionList.add(position);
        var position2 = new PositionDto();
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
        LineDto line = new LineDto();
        List<PositionDto> positionList = new ArrayList<>();
        var position = new PositionDto();
        position.setFenPosition(Constants.INITIAL_FEN);
        positionList.add(position);
        var position2 = new PositionDto();
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
        LineDto line = new LineDto();
        List<PositionDto> positionList = new ArrayList<>();
        var position = new PositionDto();
        position.setFenPosition(Constants.INITIAL_FEN);
        positionList.add(position);
        var position2 = new PositionDto();
        position2.setFenPosition("rnbqkbnr/ppp1pppp/8/3p4/3P4/5N2/PPP1PPPP/RNBQKB1R b KQkq d3 0 2");
        positionList.add(position2);
        line.setPositionList(positionList);
        // Act
        boolean result = LineUtils.existsPositionInLine(fenPosition, line);
        // Assert
        Assertions.assertTrue(result);
    }

    @Test
    void testCanAddMoveSameColorDifferentMove() {
        // Arrange
        String color = Constants.WHITE;
        String fenPosition = Constants.INITIAL_FEN;
        LineDto line = new LineDto();
        line.setColor(color);
        List<PositionDto> positionList = new ArrayList<>();
        var position = new PositionDto();
        position.setFenPosition(Constants.INITIAL_FEN);
        MoveDto move = new MoveDto();
        move.setMoveToSend("e2e4");
        position.setMoveList(Collections.singletonList(move));
        positionList.add(position);
        var position2 = new PositionDto();
        position2.setFenPosition("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1");
        positionList.add(position2);
        line.setPositionList(positionList);
        // Act
        boolean result = LineUtils.canAddMove("d2d4", fenPosition, color, line);
        // Assert
        Assertions.assertFalse(result);
    }

    @Test
    void testCanAddMoveWithoutMove() {
        // Arrange
        String color = Constants.WHITE;
        String fenPosition = Constants.INITIAL_FEN;
        LineDto line = new LineDto();
        line.setColor(color);
        List<PositionDto> positionList = new ArrayList<>();
        var position = new PositionDto();
        position.setFenPosition(Constants.INITIAL_FEN);
        position.setMoveList(Collections.emptyList());
        positionList.add(position);
        line.setPositionList(positionList);
        // Act
        boolean result = LineUtils.canAddMove("e2e4", fenPosition, color, line);
        // Assert
        Assertions.assertTrue(result);
    }

    @Test
    void testCanAddMoveDifferentColor() {
        // Arrange
        String color = Constants.WHITE;
        String fenPosition = Constants.INITIAL_FEN;
        LineDto line = new LineDto();
        line.setColor(Constants.BLACK);
        List<PositionDto> positionList = new ArrayList<>();
        var position = new PositionDto();
        position.setFenPosition(Constants.INITIAL_FEN);
        MoveDto move = new MoveDto();
        move.setMoveToSend("e2e4");
        position.setMoveList(Collections.singletonList(move));
        positionList.add(position);
        var position2 = new PositionDto();
        position2.setFenPosition("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1");
        positionList.add(position2);
        line.setPositionList(positionList);
        // Act
        boolean result = LineUtils.canAddMove("d7d5", fenPosition, color, line);
        // Assert
        Assertions.assertTrue(result);
    }

    @Test
    void testCanAddMoveSameColorAndSameMove() {
        // Arrange
        String color = Constants.WHITE;
        String fenPosition = Constants.INITIAL_FEN;
        String moveToSend = "e2e4";
        LineDto line = new LineDto();
        line.setColor(color);
        List<PositionDto> positionList = new ArrayList<>();
        var position = new PositionDto();
        position.setFenPosition(Constants.INITIAL_FEN);
        MoveDto move = new MoveDto();
        move.setMoveToSend(moveToSend);
        position.setMoveList(Collections.singletonList(move));
        positionList.add(position);
        var position2 = new PositionDto();
        position2.setFenPosition("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1");
        positionList.add(position2);
        line.setPositionList(positionList);
        // Act
        boolean result = LineUtils.canAddMove(moveToSend, fenPosition, color, line);
        // Assert
        Assertions.assertTrue(result);
    }
}
