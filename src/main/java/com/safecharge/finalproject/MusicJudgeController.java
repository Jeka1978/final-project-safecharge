package com.safecharge.finalproject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Evgeny Borisov
 */
@RestController
@RequestMapping("/music")
public class MusicJudgeController {

    @GetMapping("/ping")
    public String ping(){
        return "OK";
    }
}
