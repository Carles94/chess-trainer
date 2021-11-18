package com.chess.trainer.backend.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.chess.trainer.backend.constant.FenConstant;
import com.chess.trainer.backend.model.DeleteMoveBody;
import com.chess.trainer.backend.model.Move;
import com.chess.trainer.backend.model.MoveEvent;
import com.chess.trainer.backend.model.Position;
import com.chess.trainer.backend.model.PostMoveBody;
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
        Position expectedPosition = new Position();
        when(lineService.getPositionFromLineByFen(anyString(), any(UUID.class))).thenReturn(expectedPosition);
        // Act
        Position result = chessController.getPosition(inputFenPosition, lineUuid);
        // Assert
        verify(lineService).getPositionFromLineByFen(expectedFenPosition, lineUuid);
        Assertions.assertEquals(expectedPosition, result);
    }

    @Test
    void testPostMove() {
        // Arrange
        MoveEvent moveEvent = new MoveEvent();
        UUID uuid = UUID.randomUUID();
        var currentPosition = new Position();
        currentPosition.setFenPosition(FenConstant.INITIAL_FEN);
        var expectedPosition = new Position();
        PostMoveBody postMoveBody = new PostMoveBody();
        postMoveBody.setCurrentPosition(currentPosition);
        postMoveBody.setMoveEvent(moveEvent);
        postMoveBody.setLineUuid(uuid.toString());
        when(lineService.addMove(moveEvent, currentPosition, uuid)).thenReturn(expectedPosition);
        // Act
        Position result = chessController.postMove(postMoveBody);
        // Assert
        verify(lineService).addMove(moveEvent, currentPosition, uuid);
        Assertions.assertEquals(expectedPosition, result);
    }

    @Test
    void testDeleteMove() {
        // Arrange
        Move moveToDelete = new Move();
        moveToDelete.setMoveToSend("e2e4");
        UUID uuid = UUID.randomUUID();
        var currentPosition = new Position();
        currentPosition.setFenPosition(FenConstant.INITIAL_FEN);
        List<Move> moveList = new ArrayList<>();
        moveList.add(moveToDelete);
        Move otherMove = new Move();
        otherMove.setMoveToSend("d2d4");
        moveList.add(otherMove);
        currentPosition.setMoveList(moveList);
        DeleteMoveBody deleteMoveBody = new DeleteMoveBody();
        deleteMoveBody.setCurrentPosition(currentPosition);
        deleteMoveBody.setLineUuid(uuid);
        deleteMoveBody.setMove(moveToDelete);
        // Act
        Position result = chessController.deleteMove(deleteMoveBody);
        // Assert
        Assertions.assertEquals(currentPosition.getFenPosition(), result.getFenPosition());
        Assertions.assertEquals(1, result.getMoveList().size());
        Assertions.assertEquals(otherMove, result.getMoveList().get(0));
    }
}