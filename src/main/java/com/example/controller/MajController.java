package com.example.controller;

import com.example.pojo.Maj.MajReturn;
import com.example.pojo.Result;
import com.example.service.MajService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class MajController {

    @Autowired
    private MajService majService;

    @RequestMapping("/listmaj")
    public Result list(){
        List<MajReturn> listMajReturn = majService.listMaj();
        return Result.success(listMajReturn);
    }
}
