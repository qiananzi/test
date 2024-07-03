package com.example.service;

import com.example.pojo.Mem.Mem;
import com.example.pojo.Mem.PageBean;

public interface MemService {
    PageBean page(Integer page, Integer pagesize, String name, String gender);

    int del_mem(Integer memID);

    void update_mem(Mem mem);

    void add_mem(Mem mem);
}
