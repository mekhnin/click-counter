package com.example.clickcounter.controller;

import com.example.clickcounter.service.CountServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CountController {

    private final CountServiceImpl countService;

    @Autowired
    public CountController(CountServiceImpl countService) {
        this.countService = countService;
    }

    @GetMapping
    public String greeting(){
        return "index.html";
    }

    @ApiOperation(value = "${CountController.count:Get the current value of the counter}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "There is a current number of clicks in the response body", response = long.class)})
    @GetMapping("/count")
    public @ResponseBody long getCurrentCount() {
        return countService.get().getValue();
    }

    @MessageMapping("/add")
    @SendTo("/topic/counter")
    public long add(){
        return countService.add();
    }
}
