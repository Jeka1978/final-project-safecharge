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

    @GetMapping("/compare/{artist1}/{artist2}/{x}")
    public long compare(@PathVariable("artist1") String artist1,
                        @PathVariable("artist2") String artist2,
                        @PathVariable("x") int x) {
        return judgeService.compare(artist1, artist2, x);

    }


    @GetMapping("/ping")
    public String ping(){
        return "OK";
    }
}
