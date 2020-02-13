package com.ascent.springproject.repository;

import com.ascent.springproject.model.CtcDto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CtcRepository extends JpaRepository<CtcDto,String> {


    CtcDto findByEcode(String Ecode);
    //CtcDto findByEname(String Ename);
}
