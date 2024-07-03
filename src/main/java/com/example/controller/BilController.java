package com.example.controller;

import com.example.pojo.Bil.BilReturn;
import com.example.pojo.Result;
import com.example.service.BilService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class BilController {

    @Autowired
    private BilService bilService;

    @GetMapping("/listbil")
    public Result list(){
        List<BilReturn> listBilReturn = bilService.listBil();
        System.out.println(1234);
        return Result.success(listBilReturn);
    }
}
