package com.chess.trainer.backend.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

import com.chess.trainer.backend.model.Line;
import com.chess.trainer.backend.model.Opening;
import com.chess.trainer.backend.repository.LineRepository;
import com.chess.trainer.backend.repository.OpeningRepository;

import org.springframework.stereotype.Service;

@Service
public class OpeningService {

    private LineRepository lineRepository;
    private OpeningRepository openingRepository;

    public OpeningService(LineRepository lineRepository, OpeningRepository openingRepository) {
        this.lineRepository = lineRepository;
        this.openingRepository = openingRepository;
    }

    public Line createLine(String lineName, String lineColor, String openingName) {
        // Create opening if not exists
        Opening lineOpening = new Opening();
        if (!openingRepository.existsById(openingName)) {
            lineOpening.setColor(lineColor);
            lineOpening.setLineList(new ArrayList<>());
            lineOpening.setName(openingName);
        }
        // Create line
        Line result = new Line();
        result.setColor(lineColor);
        result.setName(lineName);
        result.setPositionList(Collections.EMPTY_LIST);
        result.setUuid(UUID.randomUUID());
        lineOpening.getLineList().add(result);

        // Update database
        openingRepository.save(lineOpening);
        result = lineRepository.save(result);

        // TODO add initial position to position list
        return result;
    }
}
