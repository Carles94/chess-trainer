package com.chess.trainer.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.chess.trainer.domain.model.Line;
import com.chess.trainer.domain.model.Opening;
import com.chess.trainer.persistence.repository.OpeningRepository;

import org.springframework.stereotype.Service;

@Service
public class OpeningService {

    private OpeningRepository openingRepository;
    private LineService lineService;

    public OpeningService(OpeningRepository openingRepository, LineService lineService) {
        this.openingRepository = openingRepository;
        this.lineService = lineService;
    }

    public Line createLine(String lineName, String lineColor, String openingName) {
        var createdLine = lineService.createLine(lineColor, lineName);
        addLineToOpening(openingName, createdLine);
        return createdLine;
    }

    private void addLineToOpening(String openingName, Line createdLine) {
        Opening lineOpening = createOrGetOpening(createdLine.getColor(), openingName);
        lineOpening.getLineList().add(createdLine);
        openingRepository.save(lineOpening);
    }

    private Opening createOrGetOpening(String lineColor, String openingName) {
        Opening lineOpening = new Opening();
        if (!openingRepository.existsById(openingName)) {
            lineOpening.setColor(lineColor);
            lineOpening.setLineList(new ArrayList<>());
            lineOpening.setName(openingName);
        } else {
            Optional<Opening> openingFromDatabase = openingRepository.findById(openingName);
            if (openingFromDatabase.isPresent()) {
                lineOpening = openingFromDatabase.get();
            }
        }
        return lineOpening;
    }

    // TODO change if users are implemented
    public List<Opening> getOpenings() {
        return (List<Opening>) openingRepository.findAll();
    }

    public List<Opening> deleteLine(String lineUuid, String openingName) {
        Opening currentOpening = openingRepository.findById(openingName).get();
        UUID lineId = UUID.fromString(lineUuid);
        currentOpening.getLineList().removeIf((line) -> lineId.equals(line.getUuid()));
        lineService.deleteLine(lineId);
        if (currentOpening.getLineList().size() == 0) {
            openingRepository.deleteById(openingName);
        }
        return getOpenings();
    }
}
