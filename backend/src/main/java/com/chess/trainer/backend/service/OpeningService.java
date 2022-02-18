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
    private LineService lineService;

    public OpeningService(LineRepository lineRepository, OpeningRepository openingRepository,
            PositionService positionService, LineService lineService) {
        this.lineRepository = lineRepository;
        this.openingRepository = openingRepository;
        this.positionService = positionService;
        this.lineService = lineService;
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

        // TODO rename variable result ?
        // Create line
        Line result = lineService.createLine(lineColor, lineName);
        // Create initial position
        List<Position> positionList = new ArrayList<>();
        Position initialPosition = positionService.createPosition(Constants.INITIAL_FEN, result.getUuid());
        positionList.add(initialPosition);
        result.setPositionList(positionList);
        // TODO see if necessary
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
        // TODO see if necessary
        openingRepository.save(currentOpening);
        lineRepository.deleteById(UUID.fromString(lineUuid));
        if (currentOpening.getLineList().size() == 0) {
            openingRepository.deleteById(openingName);
        }
        return getOpenings();
    }
}
