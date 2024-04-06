package com.example.EnergycorpBackend.controller;
import com.example.EnergycorpBackend.dto.Feedback;
import com.example.EnergycorpBackend.dto.Results;
import com.example.EnergycorpBackend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class QuestionnaireController {
    @Autowired
    PlayerService playerService;

    @PostMapping("questionnaire_attempted")
    boolean hasAttempted(@RequestBody Integer playerNo){
        playerService.testConnection();
        return playerService.hasAttempted(playerNo);
    }
    @PostMapping("insert_results")
    Integer results(@RequestBody Results results){
        return playerService.insert_results(results);
    }


    @PostMapping("get_results")
    List<Feedback> getResults(@RequestBody Integer playerID){return playerService.getResults(playerID); }


}
