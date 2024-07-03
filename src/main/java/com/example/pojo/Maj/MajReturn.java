package com.example.pojo.Maj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MajReturn {
    private Integer TableID;
    private String Status;
    private List<List<Long>> ls_time;

    public MajReturn(Maj maj, List<List<Long>> ls_time) {
        this.TableID = maj.getTableID();
        this.Status = maj.getStatus();
        this.ls_time = ls_time;
    }
}
