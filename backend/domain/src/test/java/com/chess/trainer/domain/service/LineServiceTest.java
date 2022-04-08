package com.chess.trainer.domain.service;

import static org.mockito.ArgumentMatchers.any;
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
import com.chess.trainer.domain.model.MoveDto;
import com.chess.trainer.domain.model.MoveEventDto;
import com.chess.trainer.domain.model.PositionDto;
import com.chess.trainer.persistence.entity.LineEntity;
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
        LineDto line = new LineDto();
        List<PositionDto> positionList = new ArrayList<>();
        var position = new PositionDto();
        position.setFenPosition(Constants.INITIAL_FEN);
        positionList.add(position);
        var position2 = new PositionDto();
        position2.setFenPosition("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1");
        positionList.add(position2);
        line.setPositionList(positionList);
        when(lineRepository.findById(uuid)).thenReturn(Optional.of(line));
        // Act
        PositionDto result = lineService.getPositionFromLineByFen(inputFenPosition, uuid);
        // Assert
        Assertions.assertEquals(position, result);
    }

    @Test
    void testGetPositionFromLineByFenUnexistant() {
        // Arrange
        String inputFenPosition = "rnbqkbnr/pppppppp/8/8/3P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1";
        UUID uuid = UUID.randomUUID();
        LineDto line = new LineDto();
        List<PositionDto> positionList = new ArrayList<>();
        var position = new PositionDto();
        position.setFenPosition(Constants.INITIAL_FEN);
        positionList.add(position);
        var position2 = new PositionDto();
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
        LineDto line = new LineDto();
        List<PositionDto> positionList = new ArrayList<>();
        var position = new PositionDto();
        position.setFenPosition(Constants.INITIAL_FEN);
        positionList.add(position);
        var position2 = new PositionDto();
        position2.setFenPosition("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1");
        positionList.add(position2);
        line.setPositionList(positionList);
        when(lineRepository.findById(uuid)).thenReturn(Optional.of(line));
        // Act
        PositionDto result = lineService.getPositionFromLineByFen(inputFenPosition, uuid);
        // Assert
        Assertions.assertEquals(position2, result);
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
        LineDto line = new LineDto();
        List<PositionDto> positionList = new ArrayList<>();
        PositionDto position = new PositionDto();
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
        Assertions.assertEquals(expectedPosition, result);
        Assertions.assertEquals(moveEvent.getFen(), line.getPositionList().get(1).getFenPosition());
        verify(positionService).addMoveToPosition(position, moveEvent);
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
        LineDto line = new LineDto();
        List<PositionDto> positionList = new ArrayList<>();
        PositionDto position = new PositionDto();
        MoveDto repeatedMove = new MoveDto();
        repeatedMove.setMoveToSend("e2e4");
        position.setMoveList(Collections.singletonList(repeatedMove));
        position.setFenPosition(Constants.INITIAL_FEN);
        positionList.add(position);
        line.setPositionList(positionList);
        PositionDto position2 = new PositionDto();
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
        verify(positionService, never()).addMoveToPosition(position, moveEvent);
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
        LineDto line = new LineDto();
        line.setColor(Constants.WHITE);
        List<PositionDto> positionList = new ArrayList<>();
        PositionDto position = new PositionDto();
        MoveDto whiteMove = new MoveDto();
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
        verify(lineRepository, times(2)).save(result);
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
