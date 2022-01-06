package com.chess.trainer.backend.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.chess.trainer.backend.model.Line;
import com.chess.trainer.backend.model.Opening;
import com.chess.trainer.backend.repository.LineRepository;
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
    private LineRepository lineRepository;
    private OpeningRepository openingRepository;

    @BeforeEach
    public void init(@Mock LineRepository lineRepository, @Mock OpeningRepository openingRepository) {
        this.lineRepository = lineRepository;
        this.openingRepository = openingRepository;
        openingService = new OpeningService(lineRepository, openingRepository);
    }

    @Test
    void testCreateLineAndOpening() {
        // Arrange
        String openingName = "openingName";
        String lineColor = "WHITE";
        String lineName = "name";
        when(lineRepository.save(any(Line.class))).thenAnswer((arguments) -> arguments.getArgument(0));
        when(openingRepository.existsById(openingName)).thenReturn(false);
        // Act
        Line result = openingService.createLine(lineName, lineColor, openingName);
        // Assert
        Assertions.assertEquals(lineColor, result.getColor());
        Assertions.assertEquals(lineName, result.getName());
        Assertions.assertNotNull(result.getUuid());
        Assertions.assertTrue(result.getPositionList().isEmpty());
        verify(lineRepository).save(result);
        ArgumentCaptor<Opening> captor = ArgumentCaptor.forClass(Opening.class);
        verify(openingRepository).save(captor.capture());
        Opening opening = captor.getValue();
        Assertions.assertEquals(lineColor, opening.getColor());
        Assertions.assertEquals(openingName, opening.getName());
        Assertions.assertEquals(1, opening.getLineList().size());
        Assertions.assertEquals(result, opening.getLineList().get(0));
    }
}
