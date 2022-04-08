package com.chess.trainer.api.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.chess.trainer.domain.constant.Constants;
import com.chess.trainer.domain.model.CreateLineBodyDto;
import com.chess.trainer.domain.model.DeleteLineBodyDto;
import com.chess.trainer.domain.model.DeleteMoveBodyDto;
import com.chess.trainer.domain.model.LineDto;
import com.chess.trainer.domain.model.MoveDto;
import com.chess.trainer.domain.model.MoveEventDto;
import com.chess.trainer.domain.model.OpeningDto;
import com.chess.trainer.domain.model.PositionDto;
import com.chess.trainer.domain.model.PostMoveBodyDto;
import com.chess.trainer.domain.service.LineService;
import com.chess.trainer.domain.service.OpeningService;
import com.chess.trainer.domain.service.PositionService;

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
    private OpeningService openingService;
    private PositionService positionService;

    @BeforeEach
    public void init(@Mock LineService lineService, @Mock OpeningService openingService,
            @Mock PositionService positionService) {
        this.lineService = lineService;
        this.openingService = openingService;
        this.positionService = positionService;
        chessController = new ChessController(lineService, openingService, positionService);
    }

    @Test
    void testGetPosition() {
        // Arrange
        UUID lineUuid = UUID.randomUUID();
        String inputFenPosition = "rnbqkbnr_pppppppp_8_8_4P3_8_PPPP1PPP_RNBQKBNR b KQkq e3 0 1";
        String expectedFenPosition = "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1";
        PositionDto expectedPosition = new PositionDto();
        when(lineService.getPositionFromLineByFen(anyString(), any(UUID.class))).thenReturn(expectedPosition);
        // Act
        PositionDto result = chessController.getPosition(inputFenPosition, lineUuid);
        // Assert
        verify(lineService).getPositionFromLineByFen(expectedFenPosition, lineUuid);
        Assertions.assertEquals(expectedPosition, result);
    }

    @Test
    void testPostMove() {
        // Arrange
        MoveEventDto moveEvent = new MoveEventDto();
        UUID uuid = UUID.randomUUID();
        var currentPosition = new PositionDto();
        currentPosition.setFenPosition(Constants.INITIAL_FEN);
        var expectedPosition = new PositionDto();
        PostMoveBodyDto postMoveBody = new PostMoveBodyDto();
        postMoveBody.setCurrentPosition(currentPosition);
        postMoveBody.setMoveEvent(moveEvent);
        postMoveBody.setLineUuid(uuid.toString());
        when(lineService.addMove(moveEvent, currentPosition, uuid)).thenReturn(expectedPosition);
        // Act
        PositionDto result = chessController.postMove(postMoveBody);
        // Assert
        verify(lineService).addMove(moveEvent, currentPosition, uuid);
        Assertions.assertEquals(expectedPosition, result);
    }

    @Test
    void testDeleteMove() {
        // Arrange
        MoveDto moveToDelete = new MoveDto();
        moveToDelete.setMoveToSend("e2e4");
        var currentPosition = new PositionDto();
        currentPosition.setFenPosition(Constants.INITIAL_FEN);
        List<MoveDto> moveList = new ArrayList<>();
        moveList.add(moveToDelete);
        MoveDto otherMove = new MoveDto();
        otherMove.setMoveToSend("d2d4");
        moveList.add(otherMove);
        currentPosition.setMoveList(moveList);
        DeleteMoveBodyDto deleteMoveBody = new DeleteMoveBodyDto();
        deleteMoveBody.setCurrentPosition(currentPosition);
        deleteMoveBody.setMove(moveToDelete);
        PositionDto expectedResult = new PositionDto();
        when(positionService.deleteMove(currentPosition, moveToDelete)).thenReturn(expectedResult);
        // Act
        PositionDto result = chessController.deleteMove(deleteMoveBody);
        // Assert
        verify(positionService).deleteMove(currentPosition, moveToDelete);
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void testPostCreateLine() {
        // Arrange
        CreateLineBodyDto createLineBody = new CreateLineBodyDto();
        String lineColor = "lineColor";
        createLineBody.setLineColor(lineColor);
        String lineName = "lineName";
        createLineBody.setLineName(lineName);
        String openingName = "openingName";
        createLineBody.setOpeningName(openingName);
        LineDto expectedResult = new LineDto();
        when(openingService.createLine(lineName, lineColor, openingName)).thenReturn(expectedResult);
        // Act
        LineDto result = chessController.postCreateLine(createLineBody);
        // Assert
        verify(openingService).createLine(lineName, lineColor, openingName);
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void testGetOpenings() {
        // Arrange
        List<OpeningDto> expectedResult = new ArrayList<>();
        OpeningDto opening = new OpeningDto();
        expectedResult.add(opening);
        when(openingService.getOpenings()).thenReturn(expectedResult);
        // Act
        List<OpeningDto> result = chessController.getOpenings();
        // Assert
        verify(openingService).getOpenings();
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void testDeleteLine() {
        // Arrange
        List<OpeningDto> expectedResult = new ArrayList<>();
        OpeningDto opening = new OpeningDto();
        expectedResult.add(opening);
        DeleteLineBodyDto deleteLineBody = new DeleteLineBodyDto();
        String uuid = "lineUuid";
        deleteLineBody.setLineUuid(uuid);
        String openingName = "openingName";
        deleteLineBody.setOpeningName(openingName);
        when(openingService.deleteLine(uuid, openingName)).thenReturn(expectedResult);
        // Act
        List<OpeningDto> result = chessController.deleteLine(deleteLineBody);
        // Assert
        verify(openingService).deleteLine(uuid, openingName);
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void testUpdatePosition() {
        // Arrange
        MoveEventDto moveEvent = new MoveEventDto();
        UUID uuid = UUID.randomUUID();
        var currentPosition = new PositionDto();
        currentPosition.setFenPosition(Constants.INITIAL_FEN);
        var expectedPosition = new PositionDto();
        PostMoveBodyDto postMoveBody = new PostMoveBodyDto();
        postMoveBody.setCurrentPosition(currentPosition);
        postMoveBody.setMoveEvent(moveEvent);
        postMoveBody.setLineUuid(uuid.toString());
        when(positionService.updatePosition(moveEvent, currentPosition, uuid)).thenReturn(expectedPosition);
        // Act
        PositionDto result = chessController.updatePosition(postMoveBody);
        // Assert
        verify(positionService).updatePosition(moveEvent, currentPosition, uuid);
        Assertions.assertEquals(expectedPosition, result);
    }
}