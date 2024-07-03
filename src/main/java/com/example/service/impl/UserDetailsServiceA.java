package com.example.service.impl;

import com.example.mapper.EmpMapper;
import com.example.pojo.Emp.Emp;
import com.example.pojo.Emp.LoginEmp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserDetailsServiceA implements UserDetailsService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // 查询员工信息
        Emp emp = empMapper.getEmpByphone(phone);
        // 如果没有查询到用户就抛出异常
        if(Objects.isNull(emp)) {throw new RuntimeException("手机号或密码错误");}
        // TODO 查询对应的权限信息
        return new LoginEmp(emp);
    }
}
