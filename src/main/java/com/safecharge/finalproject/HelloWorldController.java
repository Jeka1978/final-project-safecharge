package com.safecharge.finalproject;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

/**
 * @author Evgeny Borisov
 */
@RestController
@RequestMapping("/api")
public class HelloWorldController {
    private Random random = new Random();


    @PostMapping("/predict")
    public Person deathPrediction(@RequestBody Person person) {
        System.out.println("person = " + person);
        Person person1 = person.withAge(random.nextInt(10) + person.getAge());
        return person1;
    }

    @GetMapping("/vat")
    public double getVAT(@RequestParam("country") String country) {
        if (country.equalsIgnoreCase("israel")) {
            return 0.18;
        }else {
            return 0.25;
        }
    }

    @GetMapping("/hi/{name}")
    public String hi(@PathVariable String name) {
        return "hi " + name;
    }

    @PostMapping("/hello")
    public String hello(){
        return "hello";
    }


    @SneakyThrows
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        Person moshe = new Person("Moshe", 120);
        String json = objectMapper.writeValueAsString(moshe);
        System.out.println("json = " + json);
        Person person = objectMapper.readValue(json, Person.class);

        System.out.println("person = " + person);
    }












}
