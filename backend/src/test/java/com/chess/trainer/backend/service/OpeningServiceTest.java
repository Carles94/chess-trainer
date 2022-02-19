package com.chess.trainer.backend.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.chess.trainer.backend.constant.Constants;
import com.chess.trainer.backend.model.Line;
import com.chess.trainer.backend.model.Opening;
import com.chess.trainer.backend.model.Position;
import com.chess.trainer.backend.repository.OpeningRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OpeningServiceTest {
    // Classe à tester
    private OpeningService openingService;

    // Mocks
    private OpeningRepository openingRepository;
    private PositionService positionService;
    private LineService lineService;

    @BeforeEach
    public void init(@Mock OpeningRepository openingRepository,
            @Mock PositionService positionService, @Mock LineService lineService) {
        this.openingRepository = openingRepository;
        this.positionService = positionService;
        this.lineService = lineService;
        openingService = new OpeningService(openingRepository, positionService, lineService);
    }

    @Test
    void testCreateLineAndOpening() {
        // Arrange
        String openingName = "openingName";
        String lineColor = "WHITE";
        String lineName = "name";
        when(openingRepository.existsById(openingName)).thenReturn(false);
        Line line = new Line();
        line.setColor(lineColor);
        line.setUuid(UUID.randomUUID());
        line.setName(lineName);
        when(lineService.createLine(lineColor, lineName)).thenReturn(line);
        Position expectedPosition = new Position();
        when(positionService.createPosition(eq(Constants.INITIAL_FEN), any(UUID.class))).thenReturn(expectedPosition);
        // Act
        Line result = openingService.createLine(lineName, lineColor, openingName);
        // Assert
        Assertions.assertEquals(lineColor, result.getColor());
        Assertions.assertEquals(lineName, result.getName());
        Assertions.assertNotNull(result.getUuid());
        Assertions.assertEquals(1, result.getPositionList().size());
        Assertions.assertEquals(expectedPosition, result.getPositionList().get(0));

        ArgumentCaptor<Opening> captor = ArgumentCaptor.forClass(Opening.class);
        verify(openingRepository).save(captor.capture());
        Opening opening = captor.getValue();
        Assertions.assertEquals(lineColor, opening.getColor());
        Assertions.assertEquals(openingName, opening.getName());
        Assertions.assertEquals(1, opening.getLineList().size());
        Assertions.assertEquals(result, opening.getLineList().get(0));

        verify(positionService).createPosition(eq(Constants.INITIAL_FEN), any(UUID.class));
        verify(lineService).createLine(lineColor, lineName);
    }

    @Test
    void testCreateLineWithExistingOpening() {
        // Arrange
        String openingName = "openingName";
        String lineColor = "WHITE";
        String lineName = "name";
        Opening openingFromDatabase = new Opening();
        openingFromDatabase.setColor(lineColor);
        openingFromDatabase.setName(openingName);
        List<Line> lineList = new ArrayList<>();
        lineList.add(new Line());
        openingFromDatabase.setLineList(lineList);

        Line line = new Line();
        line.setColor(lineColor);
        line.setUuid(UUID.randomUUID());
        line.setName(lineName);
        when(lineService.createLine(lineColor, lineName)).thenReturn(line);
        when(openingRepository.existsById(openingName)).thenReturn(true);
        when(openingRepository.findById(openingName)).thenReturn(Optional.of(openingFromDatabase));
        Position expectedPosition = new Position();
        when(positionService.createPosition(eq(Constants.INITIAL_FEN), any(UUID.class))).thenReturn(expectedPosition);
        // Act
        Line result = openingService.createLine(lineName, lineColor, openingName);
        // Assert
        Assertions.assertEquals(lineColor, result.getColor());
        Assertions.assertEquals(lineName, result.getName());
        Assertions.assertNotNull(result.getUuid());
        Assertions.assertEquals(1, result.getPositionList().size());
        Assertions.assertEquals(expectedPosition, result.getPositionList().get(0));

        ArgumentCaptor<Opening> captor = ArgumentCaptor.forClass(Opening.class);
        verify(openingRepository).save(captor.capture());
        Opening opening = captor.getValue();
        Assertions.assertEquals(lineColor, opening.getColor());
        Assertions.assertEquals(openingName, opening.getName());
        Assertions.assertEquals(2, opening.getLineList().size());
        Assertions.assertEquals(result, opening.getLineList().get(1));

        verify(positionService).createPosition(eq(Constants.INITIAL_FEN), any(UUID.class));
        verify(lineService).createLine(lineColor, lineName);
    }

    @Test
    public void testGetOpenings() {
        // Arrange
        List<Opening> openingList = new ArrayList<>();
        openingList.add(new Opening());
        when(openingRepository.findAll()).thenReturn(openingList);
        // Act
        List<Opening> result = openingService.getOpenings();
        // Assert
        Assertions.assertEquals(openingList, result);
    }

    @Test
    public void testDeleteLineAndOpening() {
        // Arrange
        String lineUuid = UUID.randomUUID().toString();
        String openingName = "openingName";
        Opening opening = new Opening();
        opening.setLineList(new ArrayList<>());
        List<Opening> openingList = new ArrayList<>();
        openingList.add(new Opening());
        when(openingRepository.findById(openingName)).thenReturn(Optional.of(opening));
        when(openingRepository.findAll()).thenReturn(openingList);
        // Act
        List<Opening> result = openingService.deleteLine(lineUuid, openingName);
        // Assert
        verify(lineService).deleteLine(UUID.fromString(lineUuid));
        verify(openingRepository).deleteById(openingName);
        Assertions.assertEquals(openingList, result);
    }

    @Test
    public void testDeleteLine() {
        // Arrange
        String lineUuid = UUID.randomUUID().toString();
        String openingName = "openingName";
        Opening opening = new Opening();
        List<Line> lineList = new ArrayList<>();
        lineList.add(new Line());
        opening.setLineList(lineList);
        List<Opening> openingList = new ArrayList<>();
        openingList.add(new Opening());
        when(openingRepository.findById(openingName)).thenReturn(Optional.of(opening));
        when(openingRepository.findAll()).thenReturn(openingList);
        // Act
        List<Opening> result = openingService.deleteLine(lineUuid, openingName);
        // Assert
        verify(lineService).deleteLine(UUID.fromString(lineUuid));
        verify(openingRepository, never()).deleteById(openingName);
        Assertions.assertEquals(openingList, result);
    }
}
