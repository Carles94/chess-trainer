package com.chess.trainer.backend.service;

import java.util.UUID;

import com.chess.trainer.backend.model.Line;
import com.chess.trainer.backend.model.MoveEvent;
import com.chess.trainer.backend.model.Position;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LineServiceTest {

    private LineService lineService;

    @BeforeEach
    public void init() {
        lineService = new LineService();
    }

    @Test
    // TODO
    void testAddMove() {
        // Arrange
        MoveEvent moveEvent = new MoveEvent();
        Line line = new Line();
        UUID uuid = UUID.randomUUID();
        Position currentPosition = new Position();
        // Act
        lineService.addMove(moveEvent, currentPosition, uuid);
        // Assert
        Assertions.assertEquals(moveEvent.getMove(),
                line.getPositionList().get(0).getMoveList().get(0).getMoveToSend());
        Assertions.assertEquals(moveEvent.getFen(),
                line.getPositionList().get(0).getMoveList().get(0).getPositionFENAfter());
        Assertions.assertEquals(moveEvent.getFen(), line.getPositionList().get(1).getFENPosition());
    }
}
