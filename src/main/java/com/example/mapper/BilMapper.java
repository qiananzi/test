package com.example.mapper;

import com.example.pojo.Bil.Bil;
import com.example.pojo.Bil.BilRes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BilMapper {
    @Select("select * from bil")
    List<Bil> listBil();

    @Select("select * from bilres")
    List<BilRes> listBilRes();
}