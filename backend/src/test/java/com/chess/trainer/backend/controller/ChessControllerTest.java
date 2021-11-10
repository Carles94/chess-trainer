package com.chess.trainer.backend.controller;

import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.chess.trainer.backend.model.Move;
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
        UUID lineUuid = UUID.randomUUID();
        String inputFenPosition = "rnbqkbnr_pppppppp_8_8_4P3_8_PPPP1PPP_RNBQKBNR b KQkq e3 0 1";
        String expectedFenPosition = "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1";
        // Act
        chessController.getPosition(inputFenPosition, lineUuid);
        // Assert
        verify(lineService).getPositionFromLineByFen(expectedFenPosition, lineUuid);
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
        currentPosition.setFenPosition("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
        // Act
        Position result = chessController.postMove(moveEvent, currentPosition, uuid);
        // Assert
        Assertions.assertEquals(moveEvent.getFen(), result.getFenPosition());
        Assertions.assertEquals(Collections.EMPTY_LIST, result.getMoveList());
        Assertions.assertEquals(currentPosition.getFenPosition(), result.getPreviousFenPosition());
    }

    @Test
    void testDeleteMove() {
        // Arrange
        Move moveToDelete = new Move();
        moveToDelete.setMoveToSend("e2e4");
        UUID uuid = UUID.randomUUID();
        var currentPosition = new Position();
        currentPosition.setFenPosition("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
        List<Move> moveList = new ArrayList<>();
        moveList.add(moveToDelete);
        Move otherMove = new Move();
        otherMove.setMoveToSend("d2d4");
        moveList.add(otherMove);
        currentPosition.setMoveList(moveList);
        // Act
        Position result = chessController.deleteMove(moveToDelete, currentPosition, uuid);
        // Assert
        Assertions.assertEquals(currentPosition.getFenPosition(), result.getFenPosition());
        Assertions.assertEquals(1, result.getMoveList().size());
        Assertions.assertEquals(otherMove, result.getMoveList().get(0));
    }
}