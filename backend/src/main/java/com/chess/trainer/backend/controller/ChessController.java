package com.chess.trainer.backend.controller;

import com.chess.trainer.backend.model.Line;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/api")
@CrossOrigin(origins = "http://localhost:4200")
public class ChessController {

    @GetMapping(value = "/line")
    public @ResponseBody Line getLine() {
        var line = new Line();
        line.setColor("color");
        line.setName("name");
        return line;
    }
}
