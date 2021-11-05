package com.chess.trainer.backend.controller;

import java.util.ArrayList;
import java.util.UUID;

import com.chess.trainer.backend.model.Line;
import com.chess.trainer.backend.model.Move;
import com.chess.trainer.backend.model.MoveEvent;
import com.chess.trainer.backend.model.Position;
import com.chess.trainer.backend.service.LineService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/chess-trainer")
@CrossOrigin(origins = "http://localhost:4200")
public class ChessController {

    private LineService lineService;

    public ChessController(LineService lineService) {
        this.lineService = lineService;
    }

    @GetMapping(value = "/position/{uuid}/{FENPosition}")
    public @ResponseBody Position getPosition(@PathVariable String FENPosition, @PathVariable UUID uuid) {
        System.out.println("Get  position  called with " + FENPosition + " and " + uuid);
        var positionToSearch = FENPosition.replaceAll("_", "/");
        var reducedPositionToSearch = positionToSearch.substring(0, positionToSearch.length() - 4);
        Line currentLine = lineService.getLineFromUUID(uuid);
        var result = currentLine.getPositionList().stream()
                .filter((position) -> (position.getFENPosition().startsWith(reducedPositionToSearch))).findFirst();
        return result.get();
    }

    @PostMapping(value = "/move")
    public @ResponseBody Position postMove(@RequestBody MoveEvent moveEvent, @RequestBody Position currentPosition,
            @RequestBody UUID uuid) {
        System.out.println("Post move  called with " + moveEvent + ", " + currentPosition + " and " + uuid);
        Position result = new Position();
        result.setFENPosition(moveEvent.getFen());
        result.setMoveList(new ArrayList<>());
        result.setPreviousFENPosition(currentPosition.getFENPosition());
        lineService.addMove(moveEvent, currentPosition, uuid);
        return result;
    }

    @DeleteMapping(value = "/move")
    public @ResponseBody Position deleteMove(@RequestBody Move move) {
        System.out.println("Delete move called with " + move);
        return new Position();
    }

}
