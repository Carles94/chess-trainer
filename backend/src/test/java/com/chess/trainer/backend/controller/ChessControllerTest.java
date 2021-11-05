package com.chess.trainer.backend.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.chess.trainer.backend.model.Line;
import com.chess.trainer.backend.model.MoveEvent;
import com.chess.trainer.backend.model.Position;
import com.chess.trainer.backend.service.LineService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ChessControllerTest {

    // Class to test
    private ChessController chessController;

    // Mocks
    private LineService lineService;

    @BeforeEach
    public void init(@Mock LineService lineService) {
        this.lineService = lineService;
        chessController = new ChessController(lineService);
    }

    @Test
    void testGetPosition() {
        // Arrange
        String inputFENPosition = "rnbqkbnr_pppppppp_8_8_8_8_PPPPPPPP_RNBQKBNR w KQkq - 0 1";
        UUID uuid = UUID.randomUUID();
        Line line = new Line();
        List<Position> positionList = new ArrayList<>();
        var position = new Position();
        position.setFENPosition("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
        positionList.add(position);
        var position2 = new Position();
        position2.setFENPosition("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1");
        positionList.add(position2);
        line.setPositionList(positionList);
        when(lineService.getLineFromUUID(uuid)).thenReturn(line);
        // Act
        Position result = chessController.getPosition(inputFENPosition, uuid);
        // Assert
        Assertions.assertEquals(position, result);
    }

    @Test
    void testGetPositionUnexistant() {
        // Arrange
        String inputFENPosition = "rnbqkbnr_pppppppp_8_8_3P3_8_PPPP1PPP_RNBQKBNR b KQkq e3 0 1";
        UUID uuid = UUID.randomUUID();
        Line line = new Line();
        List<Position> positionList = new ArrayList<>();
        var position = new Position();
        position.setFENPosition("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
        positionList.add(position);
        var position2 = new Position();
        position2.setFENPosition("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1");
        positionList.add(position2);
        line.setPositionList(positionList);
        when(lineService.getLineFromUUID(uuid)).thenReturn(line);
        // Act + Assert
        // TODO customize exceptions
        Assertions.assertThrows(Exception.class, () -> chessController.getPosition(inputFENPosition, uuid));
    }

    @Test
    void testGetPositionByTransposition() {
        // Arrange
        String inputFENPosition = "rnbqkbnr_pppppppp_8_8_4P3_8_PPPP1PPP_RNBQKBNR b KQkq e3 2 3";
        UUID uuid = UUID.randomUUID();
        Line line = new Line();
        List<Position> positionList = new ArrayList<>();
        var position = new Position();
        position.setFENPosition("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
        positionList.add(position);
        var position2 = new Position();
        position2.setFENPosition("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1");
        positionList.add(position2);
        line.setPositionList(positionList);
        when(lineService.getLineFromUUID(uuid)).thenReturn(line);
        // Act
        Position result = chessController.getPosition(inputFENPosition, uuid);
        // Assert
        Assertions.assertEquals(position2, result);
    }

    @Test
    void testPostMove() {
        // Arrange
        MoveEvent moveEvent = new MoveEvent();
        moveEvent.setCapture(false);
        moveEvent.setCheck(false);
        moveEvent.setCheckmate(false);
        moveEvent.setColor("white");
        moveEvent.setFen("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1");
        moveEvent.setMove("e2e4");
        moveEvent.setPiece("Pawn");
        moveEvent.setStalemate(false);
        UUID uuid = UUID.randomUUID();
        var currentPosition = new Position();
        currentPosition.setFENPosition("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
        // Act
        Position result = chessController.postMove(moveEvent, currentPosition, uuid);
        // Assert
        Assertions.assertEquals(moveEvent.getFen(), result.getFENPosition());
        Assertions.assertEquals(Collections.EMPTY_LIST, result.getMoveList());
        Assertions.assertEquals(currentPosition.getFENPosition(), result.getPreviousFENPosition());
    }
}