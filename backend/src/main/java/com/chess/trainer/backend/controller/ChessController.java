package com.chess.trainer.backend.controller;

import java.util.ArrayList;

import com.chess.trainer.backend.model.Line;
import com.chess.trainer.backend.model.Move;
import com.chess.trainer.backend.model.MoveEvent;
import com.chess.trainer.backend.model.Position;

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

    private Line line;

    public ChessController() {
        line = new Line();
        line.setPositionList(new ArrayList<>());
        var initialPosition = new Position();
        initialPosition.setFENPosition("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
        initialPosition.setMoveList(new ArrayList<>());
        initialPosition.setPreviousFENPosition("");
        line.getPositionList().add(initialPosition);
    }

    @GetMapping(value = "/position/{FENPosition}")
    public @ResponseBody Position getPosition(@PathVariable String FENPosition) {
        System.out.println("Get  position  called with " + FENPosition);
        var positionToSearch = FENPosition.replaceAll("_", "/");
        var result = line.getPositionList().stream()
                .filter((position) -> (position.getFENPosition()).equals(positionToSearch)).findFirst();
        return result.get();
    }

    @PostMapping(value = "/move")
    public @ResponseBody Position postMove(@RequestBody MoveEvent moveEvent) {
        System.out.println("Post move  called with " + moveEvent);
        return new Position();
    }

    @DeleteMapping(value = "/move")
    public @ResponseBody Position deleteMove(@RequestBody Move move) {
        System.out.println("Delete move called with " + move);
        return new Position();
    }

}
