package com.example.mapper;

import com.example.pojo.Emp.Emp;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmpMapper {

    Emp getEmpByphone(String phone);
}
