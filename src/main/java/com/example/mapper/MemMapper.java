package com.example.mapper;

import com.example.pojo.Mem.Mem;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemMapper {

    Page<Mem> listMem(String name, String gender);

    @Delete("DELETE FROM mem WHERE memID = #{memID}")
    int del_memById(Integer memID);

    void update_memById(Mem mem);

    void add_mem(Mem mem);
}
