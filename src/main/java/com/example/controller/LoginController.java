package com.example.controller;

import com.example.pojo.Emp.Emp;
import com.example.pojo.ResponseResult;
import com.example.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/emplogin")
    public ResponseResult login(String EmpPhone, String EmpPassword) {
        Emp emp = new Emp(EmpPhone, EmpPassword);
        return loginService.login(emp);
    }

    @GetMapping("/emplogout")
    public ResponseResult logout() {
        return loginService.logout();
    }
}
