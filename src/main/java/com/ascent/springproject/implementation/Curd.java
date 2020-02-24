package com.ascent.springproject.implementation;

import com.ascent.springproject.exception.UserAlreadyExits;
import com.ascent.springproject.exception.UserNotRegistered;
import com.ascent.springproject.model.CtcDto;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;


public interface Curd {

    CtcDto newUser(CtcDto ctcDto) throws UserAlreadyExits;

    CtcDto ctc_page(CtcDto ctcDto) throws UserNotRegistered, IOException, IllegalAccessException, InstantiationException;

    public CtcDto findUserDetail(String ecode) throws UserNotRegistered;

    CtcDto updateUser(String ecode, CtcDto ctcDto);

    String deleteUser(String ecode) throws UserNotRegistered;

}
