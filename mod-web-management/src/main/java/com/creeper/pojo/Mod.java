package com.creeper.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mod {
    private String modid;
    private Double modversion;
    private Boolean modavailable;
    private String modinfo;
}
