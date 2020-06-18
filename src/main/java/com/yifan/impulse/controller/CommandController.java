package com.yifan.impulse.controller;

import com.yifan.impulse.service.CommandService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommandController {

    private CommandService commandService;

    public CommandController(CommandService commandService){
        this.commandService = commandService;
    }

    @GetMapping("/getId")
    public Long getId(){
        return commandService.getId();
    }

}
