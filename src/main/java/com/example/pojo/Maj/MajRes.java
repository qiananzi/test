package com.example.pojo.Maj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MajRes {
    private String ResID;
    private Integer TableID;
    private Integer CusID;
    private LocalDateTime StartTime;
    private LocalDateTime EndTime;
}
