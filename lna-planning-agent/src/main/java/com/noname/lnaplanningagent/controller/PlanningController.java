package com.noname.lnaplanningagent.controller;

import com.noname.lnaplanningagent.service.PlanningService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/agent/planning")
public class PlanningController {

    private final PlanningService planningService;

    //TODO: przyjmuje stringa jako prompt od uztkownika
    //TODO: zwracam obiekt zmapowany z jsona
    @PostMapping("/process")
    public ResponseEntity<Object> process() {
      return null;
    }

}