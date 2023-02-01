package com.asdc.pawpals.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Animal {
    private Long id;
    private String name;
    private String type;
    private Integer age;
    private String gender;
    private String photoUrl;
}
