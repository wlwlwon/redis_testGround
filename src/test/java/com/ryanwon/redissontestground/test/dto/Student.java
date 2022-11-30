package com.ryanwon.redissontestground.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private String name;

    private int age;

    private String city;

    private List<Integer> marks;
}
