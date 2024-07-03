package com.example.controller;

import com.example.pojo.Result;
import com.example.pojo.Mem.Mem;
import com.example.pojo.Mem.PageBean;
import com.example.service.MemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class MemController {

    @Autowired
    private MemService memService;

    @GetMapping("/listmem")
    public Result list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pagesize,
                       String name, String gender){
        PageBean pagebean = memService.page(page, pagesize, name, gender);
        return Result.success(pagebean);
    }

    @DeleteMapping("/delmem/{memID}")
    public Result deleteMember(@PathVariable Integer memID) {return Result.success(memService.del_mem(memID));}

    @PutMapping("/update")
    public void updatemem(@RequestBody Mem mem) {
        memService.update_mem(mem);
    }

    @PutMapping("/add")
    public void addmem(@RequestBody Mem mem) {memService.add_mem(mem);}
}
