package com.safecharge.finalproject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

/**
 * @author Evgeny Borisov
 */
@Data
@Wither
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String name;
    private int age;
}
