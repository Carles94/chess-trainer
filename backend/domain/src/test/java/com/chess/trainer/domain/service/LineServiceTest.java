package com.chess.trainer.domain.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.chess.trainer.domain.constant.Constants;
import com.chess.trainer.domain.model.LineDto;
import com.chess.trainer.domain.model.MoveEventDto;
import com.chess.trainer.domain.model.PositionDto;
import com.chess.trainer.persistence.entity.LineEntity;
import com.chess.trainer.persistence.entity.MoveEntity;
import com.chess.trainer.persistence.entity.PositionEntity;
import com.chess.trainer.persistence.repository.LineRepository;

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
    private PositionService positionService;

    @BeforeEach
    public void init(@Mock LineRepository lineRepository, @Mock PositionService positionService) {
        this.lineRepository = lineRepository;
        this.positionService = positionService;
        lineService = new LineService(lineRepository, positionService);
    }

    @Test
    void testGetPositionFromLineByFen() {
        // Arrange
        String inputFenPosition = Constants.INITIAL_FEN;
        UUID uuid = UUID.randomUUID();
        LineEntity line = new LineEntity();
        List<PositionEntity> positionList = new ArrayList<>();
        var position = new PositionEntity();
        position.setFenPosition(inputFenPosition);
        positionList.add(position);
        var position2 = new PositionEntity();
        position2.setFenPosition("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1");
        positionList.add(position2);
        line.setPositionList(positionList);
        when(lineRepository.findById(uuid)).thenReturn(Optional.of(line));
        // Act
        PositionDto result = lineService.getPositionFromLineByFen(inputFenPosition, uuid);
        // Assert
        Assertions.assertEquals(inputFenPosition, result.getFenPosition());
    }

    @Test
    void testGetPositionFromLineByFenUnexistant() {
        // Arrange
        String inputFenPosition = "rnbqkbnr/pppppppp/8/8/3P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1";
        UUID uuid = UUID.randomUUID();
        LineEntity line = new LineEntity();
        List<PositionEntity> positionList = new ArrayList<>();
        var position = new PositionEntity();
        position.setFenPosition(Constants.INITIAL_FEN);
        positionList.add(position);
        var position2 = new PositionEntity();
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
        LineEntity line = new LineEntity();
        List<PositionEntity> positionList = new ArrayList<>();
        var position = new PositionEntity();
        position.setFenPosition(Constants.INITIAL_FEN);
        positionList.add(position);
        var position2 = new PositionEntity();
        position2.setFenPosition("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1");
        positionList.add(position2);
        line.setPositionList(positionList);
        when(lineRepository.findById(uuid)).thenReturn(Optional.of(line));
        // Act
        PositionDto result = lineService.getPositionFromLineByFen(inputFenPosition, uuid);
        // Assert
        Assertions.assertEquals(position2.getFenPosition(), result.getFenPosition());
    }

    @Test
    void testAddMove() {
        // Arrange
        MoveEventDto moveEvent = new MoveEventDto();
        moveEvent.setCapture(false);
        moveEvent.setCheck(false);
        moveEvent.setCheckmate(false);
        moveEvent.setColor("white");
        moveEvent.setFen("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1");
        moveEvent.setMove("e2e4");
        moveEvent.setPiece("Pawn");
        moveEvent.setStalemate(false);
        LineEntity line = new LineEntity();
        List<PositionEntity> positionList = new ArrayList<>();
        PositionEntity position = new PositionEntity();
        position.setFenPosition(Constants.INITIAL_FEN);
        positionList.add(position);
        line.setPositionList(positionList);
        UUID uuid = UUID.randomUUID();
        PositionDto currentPosition = new PositionDto();
        currentPosition.setFenPosition(Constants.INITIAL_FEN);
        PositionDto expectedPosition = new PositionDto();
        expectedPosition.setFenPosition(moveEvent.getFen());
        when(lineRepository.findById(uuid)).thenReturn(Optional.of(line));
        when(positionService.createPosition(moveEvent.getFen(), uuid)).thenReturn(expectedPosition);
        // Act
        PositionDto result = lineService.addMove(moveEvent, currentPosition, uuid);
        // Assert
        Assertions.assertEquals(expectedPosition.getFenPosition(), result.getFenPosition());
        Assertions.assertEquals(moveEvent.getFen(), line.getPositionList().get(1).getFenPosition());
        verify(positionService).addMoveToPosition(any(PositionDto.class), eq(moveEvent));
        verify(positionService).createPosition(moveEvent.getFen(), uuid);
    }

    @Test
    void testAddMoveToExistingPositionAndExistingMove() {
        // Arrange
        MoveEventDto moveEvent = new MoveEventDto();
        moveEvent.setCapture(false);
        moveEvent.setCheck(false);
        moveEvent.setCheckmate(false);
        moveEvent.setColor("white");
        moveEvent.setFen("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1");
        moveEvent.setMove("e2e4");
        moveEvent.setPiece("Pawn");
        moveEvent.setStalemate(false);
        LineEntity line = new LineEntity();
        List<PositionEntity> positionList = new ArrayList<>();
        PositionEntity position = new PositionEntity();
        MoveEntity repeatedMove = new MoveEntity();
        repeatedMove.setMoveToSend("e2e4");
        position.setMoveList(Collections.singletonList(repeatedMove));
        position.setFenPosition(Constants.INITIAL_FEN);
        positionList.add(position);
        line.setPositionList(positionList);
        PositionEntity position2 = new PositionEntity();
        position2.setMoveList(new ArrayList<>());
        position2.setFenPosition(moveEvent.getFen());
        positionList.add(position2);
        line.setPositionList(positionList);
        UUID uuid = UUID.randomUUID();
        PositionDto currentPosition = new PositionDto();
        currentPosition.setFenPosition(Constants.INITIAL_FEN);
        when(lineRepository.findById(uuid)).thenReturn(Optional.of(line));
        // Act
        PositionDto result = lineService.addMove(moveEvent, currentPosition, uuid);
        // Assert
        Assertions.assertEquals(position2, result);

        Assertions.assertEquals(2, line.getPositionList().size());
        Assertions.assertEquals(1, line.getPositionList().get(0).getMoveList().size());
        Assertions.assertEquals(position.getMoveList(), line.getPositionList().get(0).getMoveList());
        Assertions.assertEquals(position2, line.getPositionList().get(1));
        verify(positionService, never()).addMoveToPosition(any(PositionDto.class), eq(moveEvent));
    }

    @Test
    void testAddMoveToSameColor() {
        // Arrange
        MoveEventDto moveEvent = new MoveEventDto();
        moveEvent.setCapture(false);
        moveEvent.setCheck(false);
        moveEvent.setCheckmate(false);
        moveEvent.setColor("white");
        moveEvent.setFen("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1");
        moveEvent.setMove("e2e4");
        moveEvent.setPiece("Pawn");
        moveEvent.setStalemate(false);
        LineEntity line = new LineEntity();
        line.setColor(Constants.WHITE);
        List<PositionEntity> positionList = new ArrayList<>();
        PositionEntity position = new PositionEntity();
        MoveEntity whiteMove = new MoveEntity();
        whiteMove.setMoveToSend("d2d4");
        position.setMoveList(Collections.singletonList(whiteMove));
        position.setFenPosition(Constants.INITIAL_FEN);
        positionList.add(position);
        line.setPositionList(positionList);
        UUID uuid = UUID.randomUUID();
        PositionDto currentPosition = new PositionDto();
        currentPosition.setFenPosition(Constants.INITIAL_FEN);
        when(lineRepository.findById(uuid)).thenReturn(Optional.of(line));
        // Act
        PositionDto result = lineService.addMove(moveEvent, currentPosition, uuid);
        // Assert
        Assertions.assertNull(result);
    }

    @Test
    void testCreateLine() {
        // Arrange
        String lineColor = "lineColor";
        String lineName = "lineName";
        when(lineRepository.save(any(LineEntity.class))).thenAnswer(parameter -> parameter.getArgument(0));
        // Act
        LineDto result = lineService.createLine(lineColor, lineName);
        // Assert
        Assertions.assertEquals(lineColor, result.getColor());
        Assertions.assertEquals(lineName, result.getName());
        Assertions.assertNotNull(result.getUuid());
        Assertions.assertEquals(1, result.getPositionList().size());
        verify(lineRepository, times(2)).save(any(LineEntity.class));
        verify(positionService).createPosition(Constants.INITIAL_FEN, result.getUuid());
    }

    @Test
    void testDeleteLine() {
        // Arrange
        UUID lineUuid = UUID.randomUUID();
        // Act
        lineService.deleteLine(lineUuid);
        // Assert
        verify(lineRepository).deleteById(lineUuid);
    }
}
