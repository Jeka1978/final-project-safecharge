package com.safecharge.finalproject;

import com.safecharge.finalproject.services.JudgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Evgeny Borisov
 */
@RestController
@RequestMapping("/music")
public class MusicJudgeController {
    @Autowired
    private JudgeService judgeService;


    @GetMapping("/artist")
    public Map<String, Long> topWords(@RequestParam("name") String artist, @RequestParam("x") int x) {
       return judgeService.topWords(artist, x);
    }


    @GetMapping("/ping")
    public String ping(){
        return "OK";
    }
}
