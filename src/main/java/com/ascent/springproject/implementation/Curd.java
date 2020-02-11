package com.ascent.springproject.implementation;

import com.ascent.springproject.exception.UserAlreadyExits;
import com.ascent.springproject.exception.UserNotRegistered;
import com.ascent.springproject.model.CtcDto;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;



public interface Curd {

    CtcDto newUser(String Ename, String Ecode) throws UserAlreadyExits;

    CtcDto ctc_page(Long ctc,String state,String ecode) throws UserNotRegistered;

    public CtcDto findUserDetail(String ecode) throws UserNotRegistered;

    CtcDto updateUser(String ecode, CtcDto ctcDto);

    String deleteUser(String ecode) throws UserNotRegistered;

}
