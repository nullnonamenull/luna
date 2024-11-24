package com.noname.lnaprocessorapi.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageProcessorController {

    @PostMapping("/process")
    public void processMessage(@RequestBody String messageRequestDTO) {
        System.out.println(messageRequestDTO);
    }

}
