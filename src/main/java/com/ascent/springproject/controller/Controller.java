package com.ascent.springproject.controller;

import com.ascent.springproject.exception.UserAlreadyExits;
import com.ascent.springproject.exception.UserNotRegistered;
import com.ascent.springproject.implementation.Curd;
import com.ascent.springproject.model.CtcDto;
import com.ascent.springproject.service.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class Controller {


    @Autowired
    Curd curd;


//    @Autowired
//    RabbitMQSender rabbitMQSender;

    @RequestMapping(value = "employee-ename-ecode", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<CtcDto> newUser(@RequestBody CtcDto ctcDto) throws UserAlreadyExits {
        return new ResponseEntity(curd.newUser(ctcDto), HttpStatus.OK);
    }


    @RequestMapping("employee-ctc-calculation")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CtcDto ctc_page(@RequestBody CtcDto ctcDto) throws UserNotRegistered, IllegalAccessException, IOException, InstantiationException {
        return curd.ctc_page(ctcDto);
    }


    @RequestMapping(value = "employee/{ecode}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Object> findUserDetail(@PathVariable String ecode) throws UserNotRegistered {
        return new ResponseEntity<>(curd.findUserDetail(ecode), HttpStatus.OK);
    }


    @PutMapping("employee/{ecode}")
    public ResponseEntity<CtcDto> updateUser(@PathVariable String ecode,@RequestBody CtcDto ctcDto) {

        return new ResponseEntity<>(curd.updateUser(ecode, ctcDto), HttpStatus.OK);
    }


    @DeleteMapping("employee/{ecode}")
    public ResponseEntity<Object> deleteUser(@PathVariable String ecode) throws UserNotRegistered {
        return new ResponseEntity<>(curd.deleteUser(ecode), HttpStatus.OK);
    }
}
