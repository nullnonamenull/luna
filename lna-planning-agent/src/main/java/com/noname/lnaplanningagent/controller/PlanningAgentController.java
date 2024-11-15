package com.noname.lnaplanningagent.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/agent/planning")
public class PlanningAgentController {




    @PostMapping("/process")
    public ResponseEntity<Object> process() {
      return null;
    }
}
