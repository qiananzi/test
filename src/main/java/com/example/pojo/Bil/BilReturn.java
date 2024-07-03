package com.example.pojo.Bil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BilReturn {
    private Integer TableID;
    private String Status;
    private List<List<Long>> ls_time;

    public BilReturn(Bil bil, List<List<Long>> ls_time) {
        this.TableID = bil.getTableID();
        this.Status = bil.getStatus();
        this.ls_time = ls_time;
    }
}
