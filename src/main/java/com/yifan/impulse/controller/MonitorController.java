package com.yifan.impulse.controller;

import com.yifan.impulse.service.MonitorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/monitor")
public class MonitorController {

    private MonitorService monitorService;

    public MonitorController(MonitorService monitorService){
        this.monitorService = monitorService;
    }

    @GetMapping("/exist")
    public Integer exist(){
        return monitorService.exist();
    }

    @GetMapping("/thread")
    public Integer setThread(){
        return monitorService.thread();
    }
}
