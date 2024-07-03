package com.example.service.impl;

import com.example.pojo.Emp.Emp;
import com.example.pojo.Emp.LoginEmp;
import com.example.pojo.ResponseResult;
import com.example.service.LoginService;
import com.example.utils.JwtUtil;
import com.example.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceA implements LoginService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;
    @Override
    public ResponseResult login(Emp emp) {
        // 1、封装Authentication对象
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(emp.getEmpPhone(), emp.getEmpPassword());

        // 2、进行校验
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        // 3、如果认证失败，则给出相应提示
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登录失败");
        }

        // 4、放入的用户信息
        LoginEmp loginEmp = (LoginEmp) authenticate.getPrincipal();

        // 5、获取EmpID
        String EmpID = loginEmp.getEmp().getEmpID().toString();

        // 6、使用EmpID生成一个jwt，将jwt存入ResponseResult返回
        String jwt = JwtUtil.createJWT(EmpID);
        Map<String,String> map = new HashMap<>();
        map.put("token", jwt);
        // 7、把完整的用户信息存入redis，EmpID作为key
        redisCache.setCacheObject("login:" + EmpID, loginEmp);
        return new ResponseResult(200,"登陆成功",map);
    }

    @Override
    public ResponseResult logout() {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken)
                SecurityContextHolder.getContext().getAuthentication();
        LoginEmp loginEmp = (LoginEmp) authentication.getPrincipal();
        Integer empID = loginEmp.getEmp().getEmpID();
        redisCache.deleteObject("login:" + empID);
        return new ResponseResult(200,"退出成功");
    }
}
