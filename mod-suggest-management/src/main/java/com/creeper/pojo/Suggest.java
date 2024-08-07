package com.creeper.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Suggest {
    private Integer suggestid;
    private String username;
    private String modid;
    private String suggest;
}
