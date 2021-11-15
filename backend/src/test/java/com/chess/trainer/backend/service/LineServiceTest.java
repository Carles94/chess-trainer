package com.chess.trainer.backend.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.chess.trainer.backend.constant.FenConstant;
import com.chess.trainer.backend.model.Line;
import com.chess.trainer.backend.model.MoveEvent;
import com.chess.trainer.backend.model.Position;
import com.chess.trainer.backend.repository.LineRepository;
import com.chess.trainer.backend.repository.PositionRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LineServiceTest {

    // Class to test
    private LineService lineService;

    // Mock
    private LineRepository lineRepository;

    @BeforeEach
    public void init(@Mock LineRepository lineRepository, @Mock PositionRepository positionRepository) {
        this.lineRepository = lineRepository;
        lineService = new LineService(lineRepository, positionRepository);
    }

    @Test
    void testGetPositionFromLineByFen() {
        // Arrange
        String inputFenPosition = FenConstant.INITIAL_FEN;
        UUID uuid = UUID.randomUUID();
        Line line = new Line();
        List<Position> positionList = new ArrayList<>();
        var position = new Position();
        position.setFenPosition(FenConstant.INITIAL_FEN);
        positionList.add(position);
        var position2 = new Position();
        position2.setFenPosition("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1");
        positionList.add(position2);
        line.setPositionList(positionList);
        when(lineRepository.findById(uuid)).thenReturn(Optional.of(line));
        // Act
        Position result = lineService.getPositionFromLineByFen(inputFenPosition, uuid);
        // Assert
        Assertions.assertEquals(position, result);
    }

    @Test
    void testGetPositionFromLineByFenUnexistant() {
        // Arrange
        String inputFenPosition = "rnbqkbnr/pppppppp/8/8/3P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1";
        UUID uuid = UUID.randomUUID();
        Line line = new Line();
        List<Position> positionList = new ArrayList<>();
        var position = new Position();
        position.setFenPosition(FenConstant.INITIAL_FEN);
        positionList.add(position);
        var position2 = new Position();
        position2.setFenPosition("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1");
        positionList.add(position2);
        line.setPositionList(positionList);
        when(lineRepository.findById(uuid)).thenReturn(Optional.of(line));
        // Act + Assert
        // TODO customize exceptions
        Assertions.assertThrows(Exception.class, () -> lineService.getPositionFromLineByFen(inputFenPosition, uuid));
    }

    @Test
    void testGetPositionFromLineByFenByTransposition() {
        // Arrange
        String inputFenPosition = "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 2 3";
        UUID uuid = UUID.randomUUID();
        Line line = new Line();
        List<Position> positionList = new ArrayList<>();
        var position = new Position();
        position.setFenPosition(FenConstant.INITIAL_FEN);
        positionList.add(position);
        var position2 = new Position();
        position2.setFenPosition("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1");
        positionList.add(position2);
        line.setPositionList(positionList);
        when(lineRepository.findById(uuid)).thenReturn(Optional.of(line));
        // Act
        Position result = lineService.getPositionFromLineByFen(inputFenPosition, uuid);
        // Assert
        Assertions.assertEquals(position2, result);
    }

    @Test
    void testAddMove() {
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
        Line line = new Line();
        List<Position> positionList = new ArrayList<>();
        Position position = new Position();
        position.setMoveList(new ArrayList<>());
        position.setFenPosition(FenConstant.INITIAL_FEN);
        positionList.add(position);
        line.setPositionList(positionList);
        UUID uuid = UUID.randomUUID();
        Position currentPosition = new Position();
        currentPosition.setFenPosition(FenConstant.INITIAL_FEN);
        when(lineRepository.findById(uuid)).thenReturn(Optional.of(line));
        // Act
        Position result = lineService.addMove(moveEvent, currentPosition, uuid);
        // Assert
        Assertions.assertEquals(moveEvent.getFen(), result.getFenPosition());
        Assertions.assertEquals(Collections.EMPTY_LIST, result.getMoveList());
        Assertions.assertEquals(currentPosition.getFenPosition(), result.getPreviousFenPosition());

        Assertions.assertEquals(moveEvent.getMove(),
                line.getPositionList().get(0).getMoveList().get(0).getMoveToSend());
        Assertions.assertEquals(moveEvent.getFen(),
                line.getPositionList().get(0).getMoveList().get(0).getPositionFENAfter());
        Assertions.assertEquals(moveEvent.getFen(), line.getPositionList().get(1).getFenPosition());
    }
}
