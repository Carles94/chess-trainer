package com.chess.trainer.api.controller;

import java.util.List;
import java.util.UUID;

import com.chess.trainer.domain.service.*;
import com.chess.trainer.domain.model.*;

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
    private OpeningService openingService;
    private PositionService positionService;

    public ChessController(LineService lineService, OpeningService openingService, PositionService positionService) {
        this.lineService = lineService;
        this.openingService = openingService;
        this.positionService = positionService;
    }

    @GetMapping(value = "/position/{lineUuid}/{fenPosition}")
    public @ResponseBody PositionDto getPosition(@PathVariable String fenPosition, @PathVariable UUID lineUuid) {
        System.out.println("Get  position  called with " + fenPosition + " and " + lineUuid);
        var positionToSearch = fenPosition.replaceAll("_", "/");
        PositionDto positionToSend = lineService.getPositionFromLineByFen(positionToSearch, lineUuid);
        return positionToSend;
    }

    @PostMapping(value = "/move")
    public @ResponseBody PositionDto postMove(@RequestBody PostMoveBodyDto postMoveBody) {
        System.out.println("Post move  called with " + postMoveBody.getMoveEvent() + ", "
                + postMoveBody.getCurrentPosition() + " and " + postMoveBody.getLineUuid());
        PositionDto result = lineService.addMove(postMoveBody.getMoveEvent(), postMoveBody.getCurrentPosition(),
                UUID.fromString(postMoveBody.getLineUuid()));
        return result;
    }

    @DeleteMapping(value = "/move")
    public @ResponseBody PositionDto deleteMove(@RequestBody DeleteMoveBodyDto deleteMoveBody) {
        System.out.println("Delete move called with " + deleteMoveBody.getMove() + " and "
                + deleteMoveBody.getCurrentPosition());
        PositionDto result = positionService.deleteMove(deleteMoveBody.getCurrentPosition(), deleteMoveBody.getMove());
        return result;
    }

    @GetMapping(value = "/openings")
    public @ResponseBody List<OpeningDto> getOpenings() {
        System.out.println("Get openings  called");
        List<OpeningDto> result = this.openingService.getOpenings();
        return result;
    }

    @PostMapping(value = "/line/create")
    public @ResponseBody LineDto postCreateLine(@RequestBody CreateLineBodyDto createLineBody) {
        System.out.println("Post line called with " + createLineBody.getLineColor() + ", "
                + createLineBody.getLineName() + " and " + createLineBody.getOpeningName());
        LineDto result = this.openingService.createLine(createLineBody.getLineName(), createLineBody.getLineColor(),
                createLineBody.getOpeningName());
        return result;
    }

    @DeleteMapping(value = "/line")
    public @ResponseBody List<OpeningDto> deleteLine(@RequestBody DeleteLineBodyDto deleteLineBody) {
        System.out.println("Delete line called with uuid " + deleteLineBody.getLineUuid() + " and "
                + deleteLineBody.getOpeningName());
        List<OpeningDto> result = this.openingService.deleteLine(deleteLineBody.getLineUuid(),
                deleteLineBody.getOpeningName());
        return result;
    }

    @PostMapping(value = "/position")
    public @ResponseBody PositionDto updatePosition(@RequestBody PostMoveBodyDto postMoveBody) {
        System.out.println("Update position called with uuid " + postMoveBody.getLineUuid() + ", "
                + postMoveBody.getCurrentPosition() + " and "
                + postMoveBody.getMoveEvent());
        PositionDto result = this.positionService.updatePosition(postMoveBody.getMoveEvent(),
                postMoveBody.getCurrentPosition(),
                UUID.fromString(postMoveBody.getLineUuid()));
        return result;
    }
}
