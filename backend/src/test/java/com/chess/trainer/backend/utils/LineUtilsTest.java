package com.chess.trainer.backend.utils;

import java.util.ArrayList;
import java.util.List;

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
        String fenPosition = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
        Line line = new Line();
        List<Position> positionList = new ArrayList<>();
        var position = new Position();
        position.setFenPosition("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
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
        position.setFenPosition("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
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
        position.setFenPosition("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
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
}
