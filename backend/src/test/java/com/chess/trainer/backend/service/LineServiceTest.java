package com.chess.trainer.backend.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.chess.trainer.backend.constant.Constants;
import com.chess.trainer.backend.model.Line;
import com.chess.trainer.backend.model.Move;
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
    private PositionRepository positionRepository;
    private PositionService positionService;

    @BeforeEach
    public void init(@Mock LineRepository lineRepository, @Mock PositionRepository positionRepository,
            @Mock PositionService positionService) {
        this.positionRepository = positionRepository;
        this.lineRepository = lineRepository;
        this.positionService = positionService;
        lineService = new LineService(lineRepository, positionRepository, positionService);
    }

    @Test
    void testGetPositionFromLineByFen() {
        // Arrange
        String inputFenPosition = Constants.INITIAL_FEN;
        UUID uuid = UUID.randomUUID();
        Line line = new Line();
        List<Position> positionList = new ArrayList<>();
        var position = new Position();
        position.setFenPosition(Constants.INITIAL_FEN);
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
        position.setFenPosition(Constants.INITIAL_FEN);
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
        position.setFenPosition(Constants.INITIAL_FEN);
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
        position.setFenPosition(Constants.INITIAL_FEN);
        positionList.add(position);
        line.setPositionList(positionList);
        UUID uuid = UUID.randomUUID();
        Position currentPosition = new Position();
        currentPosition.setFenPosition(Constants.INITIAL_FEN);
        when(lineRepository.findById(uuid)).thenReturn(Optional.of(line));
        // Act
        Position result = lineService.addMove(moveEvent, currentPosition, uuid);
        // Assert
        Assertions.assertEquals(moveEvent.getFen(), result.getFenPosition());
        Assertions.assertEquals(uuid, result.getLineUuid());
        Assertions.assertEquals(Collections.EMPTY_LIST, result.getMoveList());

        Assertions.assertEquals(moveEvent.getFen(), line.getPositionList().get(1).getFenPosition());
        verify(positionService).addMoveToPosition(position, moveEvent);
        verify(positionRepository).save(any());
    }

    @Test
    void testAddMoveToExistingPositionAndExistingMove() {
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
        Move repeatedMove = new Move();
        repeatedMove.setMoveToSend("e2e4");
        position.setMoveList(Collections.singletonList(repeatedMove));
        position.setFenPosition(Constants.INITIAL_FEN);
        positionList.add(position);
        line.setPositionList(positionList);
        Position position2 = new Position();
        position2.setMoveList(new ArrayList<>());
        position2.setFenPosition(moveEvent.getFen());
        positionList.add(position2);
        line.setPositionList(positionList);
        UUID uuid = UUID.randomUUID();
        Position currentPosition = new Position();
        currentPosition.setFenPosition(Constants.INITIAL_FEN);
        when(lineRepository.findById(uuid)).thenReturn(Optional.of(line));
        // Act
        Position result = lineService.addMove(moveEvent, currentPosition, uuid);
        // Assert
        Assertions.assertEquals(position2, result);

        Assertions.assertEquals(2, line.getPositionList().size());
        Assertions.assertEquals(1, line.getPositionList().get(0).getMoveList().size());
        Assertions.assertEquals(position.getMoveList(), line.getPositionList().get(0).getMoveList());
        Assertions.assertEquals(position2, line.getPositionList().get(1));
        verify(positionService, never()).addMoveToPosition(position, moveEvent);
    }

    @Test
    void testAddMoveToSameColor() {
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
        line.setColor(Constants.WHITE);
        List<Position> positionList = new ArrayList<>();
        Position position = new Position();
        Move whiteMove = new Move();
        whiteMove.setMoveToSend("d2d4");
        position.setMoveList(Collections.singletonList(whiteMove));
        position.setFenPosition(Constants.INITIAL_FEN);
        positionList.add(position);
        line.setPositionList(positionList);
        UUID uuid = UUID.randomUUID();
        Position currentPosition = new Position();
        currentPosition.setFenPosition(Constants.INITIAL_FEN);
        when(lineRepository.findById(uuid)).thenReturn(Optional.of(line));
        // Act
        Position result = lineService.addMove(moveEvent, currentPosition, uuid);
        // Assert
        Assertions.assertNull(result);
    }
}
