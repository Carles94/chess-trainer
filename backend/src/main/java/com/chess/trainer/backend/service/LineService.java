package com.chess.trainer.backend.service;

import java.util.UUID;

import com.chess.trainer.backend.model.Line;

import org.springframework.stereotype.Service;

@Service
public class LineService {

    public Line getLineFromUUID(UUID uuid) {
        // TODO implementation
        return new Line();
    }
}
