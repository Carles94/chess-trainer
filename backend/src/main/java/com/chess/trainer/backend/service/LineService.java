package com.chess.trainer.backend.service;

import java.util.UUID;

import com.chess.trainer.backend.model.Line;
import com.chess.trainer.backend.model.MoveEvent;
import com.chess.trainer.backend.model.Position;
import com.chess.trainer.backend.repository.LineRepository;

import org.springframework.stereotype.Service;

@Service
public class LineService {

    private LineRepository lineRepository;

    public LineService(LineRepository lineRepository) {
        this.lineRepository = lineRepository;
    }

    public Position getPositionFromLineByFen(String FenPosition, UUID lineUuid) {
        Line line = lineRepository.findById(lineUuid).get();
        var reducedFenPosition = FenPosition.substring(0, FenPosition.length() - 4);
        var result = line.getPositionList().stream()
                .filter((position) -> (position.getFenPosition().startsWith(reducedFenPosition))).findFirst();
        return result.get();
    }

    public void addMove(MoveEvent moveEvent, Position currentPosition, UUID uuid) {
        // TODO implementation
    }
}
