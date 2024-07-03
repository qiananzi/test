package com.example.service.impl;

import com.example.mapper.MemMapper;
import com.example.pojo.Mem.Mem;
import com.example.pojo.Mem.PageBean;
import com.example.service.MemService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemServiceA  implements MemService {

    @Autowired
    private MemMapper memMapper;

    @Override
    public PageBean page(Integer page, Integer pagesize, String name, String gender) {
        PageHelper.startPage(page, pagesize);
        Page<Mem> mempage = memMapper.listMem(name, gender);
        mempage.forEach(mem -> {
            String gender_item = mem.getGender();
            if (gender_item.equals("1")) mem.setGender("男");
            else mem.setGender("女");
        });
        return new PageBean(mempage.getTotal(), mempage.getResult());
    }

    public int del_mem(Integer memID) {return memMapper.del_memById(memID);}

    public void update_mem(Mem mem) {memMapper.update_memById(mem);}

    public void add_mem(Mem mem) {memMapper.add_mem(mem);}
}
