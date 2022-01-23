package com.chess.trainer.backend.service;

import java.util.UUID;

import com.chess.trainer.backend.model.MoveEvent;
import com.chess.trainer.backend.model.Position;

import org.springframework.stereotype.Service;

@Service
public class PositionService {

    public Position updatePosition(MoveEvent moveEvent, Position position, UUID uuid) {
        return new Position();
    }

}
