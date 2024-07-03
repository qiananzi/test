package com.example.service;

import com.example.pojo.Emp.Emp;
import com.example.pojo.ResponseResult;

public interface LoginService {
    ResponseResult login(Emp emp);

    ResponseResult logout();
}
