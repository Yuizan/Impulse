package com.yifan.impulse.controller;

import com.yifan.impulse.service.CommandService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/command")
public class CommandController {

    private CommandService commandService;

    public CommandController(CommandService commandService){
        this.commandService = commandService;
    }

    @GetMapping("/getId")
    public Long getId(){
        return commandService.getId();
    }

//    @PutMapping("/thread/{activeThread}")
//    public String setThread(@PathVariable int activeThread){
//        return commandService.setThread(activeThread);
//    }
//
//    @PutMapping("/capacity/{times}")
//    public String capacity(@PathVariable int times){
//        return commandService.capacity(times);
//    }
}
