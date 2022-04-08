package com.chess.trainer.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.chess.trainer.domain.mapper.OpeningMapper;
import com.chess.trainer.domain.model.LineDto;
import com.chess.trainer.domain.model.OpeningDto;
import com.chess.trainer.persistence.entity.OpeningEntity;
import com.chess.trainer.persistence.repository.OpeningRepository;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service
public class OpeningService {

    private OpeningRepository openingRepository;
    private LineService lineService;
    private OpeningMapper openingMapper = Mappers.getMapper(OpeningMapper.class);

    public OpeningService(OpeningRepository openingRepository, LineService lineService) {
        this.openingRepository = openingRepository;
        this.lineService = lineService;
    }

    public LineDto createLine(String lineName, String lineColor, String openingName) {
        var createdLine = lineService.createLine(lineColor, lineName);
        addLineToOpening(openingName, createdLine);
        return createdLine;
    }

    private void addLineToOpening(String openingName, LineDto createdLine) {
        OpeningDto lineOpening = createOrGetOpening(createdLine.getColor(), openingName);
        lineOpening.getLineList().add(createdLine);
        openingRepository.save(openingMapper.dtoToEntity(lineOpening));
    }

    private OpeningDto createOrGetOpening(String lineColor, String openingName) {
        OpeningDto lineOpening = new OpeningDto();
        if (!openingRepository.existsById(openingName)) {
            lineOpening.setColor(lineColor);
            lineOpening.setLineList(new ArrayList<>());
            lineOpening.setName(openingName);
        } else {
            Optional<OpeningEntity> openingFromDatabase = openingRepository.findById(openingName);
            if (openingFromDatabase.isPresent()) {
                lineOpening = openingMapper.entityToDto(openingFromDatabase.get());
            }
        }
        return lineOpening;
    }

    // TODO change if users are implemented
    public List<OpeningDto> getOpenings() {
        var openingsFromDatabase = (List<OpeningEntity>) openingRepository.findAll();
        return openingsFromDatabase.stream().map(openingEntity -> openingMapper.entityToDto(openingEntity))
                .collect(Collectors.toList());
    }

    public List<OpeningDto> deleteLine(String lineUuid, String openingName) {
        OpeningEntity currentOpening = openingRepository.findById(openingName).get();
        UUID lineId = UUID.fromString(lineUuid);
        currentOpening.getLineList().removeIf((line) -> lineId.equals(line.getUuid()));
        lineService.deleteLine(lineId);
        if (currentOpening.getLineList().size() == 0) {
            openingRepository.deleteById(openingName);
        }
        return getOpenings();
    }
}
