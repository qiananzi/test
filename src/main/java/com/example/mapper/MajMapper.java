package com.example.mapper;

import com.example.pojo.Maj.Maj;
import com.example.pojo.Maj.MajRes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MajMapper {
    @Select("select * from majres")
    List<MajRes> listMajRes();

    @Select("select * from maj")
    List<Maj> listMaj();
}
