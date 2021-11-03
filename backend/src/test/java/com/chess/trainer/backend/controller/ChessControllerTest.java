package com.chess.trainer.backend.controller;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChessControllerTest {

    private ChessController chessController;

    @BeforeEach
    public void init() {
        chessController = new ChessController();
    }

    @Test
    void testGetPosition() {
        // Arrange
        String FENPosition = "rnbqkbnr_pppppppp_8_8_8_8_PPPPPPPP_RNBQKBNR w KQkq - 0 1";
        // Act
        chessController.getPosition(FENPosition, UUID.randomUUID());
        // Assert
    }

}