package com.chess.trainer.backend.service;

import java.util.UUID;

import com.chess.trainer.backend.model.Line;
import com.chess.trainer.backend.model.MoveEvent;
import com.chess.trainer.backend.model.Position;

import org.springframework.stereotype.Service;

@Service
public class LineService {

    public Line getLineFromUUID(UUID uuid) {
        // TODO implementation
        return new Line();
    }

    public void addMove(MoveEvent moveEvent, Position currentPosition, UUID uuid) {
        // TODO implementation
    }
}
