package com.chess.trainer.backend.controller;

import java.util.List;
import java.util.UUID;

import com.chess.trainer.backend.model.CreateLineBody;
import com.chess.trainer.backend.model.DeleteLineBody;
import com.chess.trainer.backend.model.DeleteMoveBody;
import com.chess.trainer.backend.model.Line;
import com.chess.trainer.backend.model.Opening;
import com.chess.trainer.backend.model.Position;
import com.chess.trainer.backend.model.PostMoveBody;
import com.chess.trainer.backend.service.LineService;
import com.chess.trainer.backend.service.OpeningService;
import com.chess.trainer.backend.service.PositionService;

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
    public @ResponseBody Position getPosition(@PathVariable String fenPosition, @PathVariable UUID lineUuid) {
        System.out.println("Get  position  called with " + fenPosition + " and " + lineUuid);
        var positionToSearch = fenPosition.replaceAll("_", "/");
        Position positionToSend = lineService.getPositionFromLineByFen(positionToSearch, lineUuid);
        return positionToSend;
    }

    @PostMapping(value = "/move")
    public @ResponseBody Position postMove(@RequestBody PostMoveBody postMoveBody) {
        System.out.println("Post move  called with " + postMoveBody.getMoveEvent() + ", "
                + postMoveBody.getCurrentPosition() + " and " + postMoveBody.getLineUuid());
        Position result = lineService.addMove(postMoveBody.getMoveEvent(), postMoveBody.getCurrentPosition(),
                UUID.fromString(postMoveBody.getLineUuid()));
        return result;
    }

    @DeleteMapping(value = "/move")
    public @ResponseBody Position deleteMove(@RequestBody DeleteMoveBody deleteMoveBody) {
        System.out.println("Delete move called with " + deleteMoveBody.getMove() + ", "
                + deleteMoveBody.getCurrentPosition() + " and  " + deleteMoveBody.getLineUuid());
        Position result = lineService.deleteMove(deleteMoveBody.getMove(), deleteMoveBody.getCurrentPosition(),
                UUID.fromString(deleteMoveBody.getLineUuid()));
        return result;
    }

    @GetMapping(value = "/openings")
    public @ResponseBody List<Opening> getOpenings() {
        System.out.println("Get openings  called");
        List<Opening> result = this.openingService.getOpenings();
        return result;
    }

    @PostMapping(value = "/line/create")
    public @ResponseBody Line postCreateLine(@RequestBody CreateLineBody createLineBody) {
        System.out.println("Post line called with " + createLineBody.getLineColor() + ", "
                + createLineBody.getLineName() + " and " + createLineBody.getOpeningName());
        Line result = this.openingService.createLine(createLineBody.getLineName(), createLineBody.getLineColor(),
                createLineBody.getOpeningName());
        return result;
    }

    @DeleteMapping(value = "/line")
    public @ResponseBody List<Opening> deleteLine(@RequestBody DeleteLineBody deleteLineBody) {
        System.out.println("Delete line called with uuid " + deleteLineBody.getLineUuid() + " and "
                + deleteLineBody.getOpeningName());
        List<Opening> result = this.openingService.deleteLine(deleteLineBody.getLineUuid(),
                deleteLineBody.getOpeningName());
        return result;
    }

    @PostMapping(value = "/position")
    public @ResponseBody Position updatePosition(@RequestBody PostMoveBody postMoveBody) {
        System.out.println("Update position called with uuid " + postMoveBody.getLineUuid() + ", "
                + postMoveBody.getCurrentPosition() + " and "
                + postMoveBody.getMoveEvent());
        Position result = this.positionService.updatePosition(postMoveBody.getMoveEvent(),
                postMoveBody.getCurrentPosition(),
                UUID.fromString(postMoveBody.getLineUuid()));
        return result;
    }
}
