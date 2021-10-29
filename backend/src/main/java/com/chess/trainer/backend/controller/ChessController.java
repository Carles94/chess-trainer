package com.chess.trainer.backend.controller;

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

    @GetMapping(value = "/position/{FENPosition}")
    public @ResponseBody Position getPosition(@PathVariable String FENPosition) {
        var position = new Position();
        System.out.println("Get  position  called with " + FENPosition);
        return position;
    }

    @PostMapping(value = "/move")
    public void postMove(@RequestBody MoveEvent moveEvent) {
        System.out.println("Post move  called with " + moveEvent);
    }

    @DeleteMapping(value = "/move")
    public void deleteMove(@RequestBody Move move) {
        System.out.println("Delete move called with " + move);
    }

}
