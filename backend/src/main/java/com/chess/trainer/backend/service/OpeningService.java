package com.chess.trainer.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.chess.trainer.backend.constant.Constants;
import com.chess.trainer.backend.model.Line;
import com.chess.trainer.backend.model.Opening;
import com.chess.trainer.backend.model.Position;
import com.chess.trainer.backend.repository.LineRepository;
import com.chess.trainer.backend.repository.OpeningRepository;

import org.springframework.stereotype.Service;

@Service
public class OpeningService {

    private LineRepository lineRepository;
    private OpeningRepository openingRepository;
    private PositionService positionService;

    public OpeningService(LineRepository lineRepository, OpeningRepository openingRepository,
            PositionService positionService) {
        this.lineRepository = lineRepository;
        this.openingRepository = openingRepository;
        this.positionService = positionService;
    }

    public Line createLine(String lineName, String lineColor, String openingName) {
        // Create opening if not exists
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

        // Create line
        Line result = new Line();
        result.setColor(lineColor);
        result.setName(lineName);
        UUID lineUuid = UUID.randomUUID();
        result.setUuid(lineUuid);

        result = lineRepository.save(result);

        // Create initial position
        List<Position> positionList = new ArrayList<>();
        Position initialPosition = positionService.createPosition(Constants.INITIAL_FEN, lineUuid);
        positionList.add(initialPosition);
        result.setPositionList(positionList);
        result = lineRepository.save(result);

        lineOpening.getLineList().add(result);
        openingRepository.save(lineOpening);
        return result;
    }

    // TODO change when users are implemented
    public List<Opening> getOpenings() {
        return (List<Opening>) openingRepository.findAll();
    }

    public List<Opening> deleteLine(String lineUuid, String openingName) {
        Opening currentOpening = openingRepository.findById(openingName).get();
        currentOpening.getLineList().removeIf((line) -> UUID.fromString(lineUuid).equals(line.getUuid()));
        openingRepository.save(currentOpening);
        lineRepository.deleteById(UUID.fromString(lineUuid));
        if (currentOpening.getLineList().size() == 0) {
            openingRepository.deleteById(openingName);
        }
        return getOpenings();
    }
}
