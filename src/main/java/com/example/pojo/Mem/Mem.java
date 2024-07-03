package com.example.pojo.Mem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mem {
    private int MemID;
    private float Money;
    private LocalDate TimeIn;
    private String Name;
    private String Gender;
    private String Phone;
}
