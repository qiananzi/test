package com.example.pojo.Emp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emp implements Serializable {
    @Serial
    private static final long serialVersionUID = -40356785423868312L;
    private Integer EmpID;
    private String EmpName;
    private String EmpPhone;
    private String EmpPassword;
    private Short EmpGender;
    private LocalDate EntryTime;
    private Integer EmpMoney;
    private Short EmpType;
    private Short EmpStatus;

    public Emp(String empPhone, String empPassword) {
        this.EmpPassword = empPassword;
        this.EmpPhone = empPhone;
    }
}
