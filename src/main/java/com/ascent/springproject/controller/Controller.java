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

@RestController
public class Controller {


    @Autowired
    Curd curd;


    @Autowired
    RabbitMQSender rabbitMQSender;

    @PostMapping("employee/{Ename}/{Ecode}")

    public ResponseEntity<CtcDto> newUser(@PathVariable(value = "Ename") String Ename, @PathVariable(value = "Ename") String Ecode) throws UserAlreadyExits {
        System.out.println(Ename);
        System.out.println(Ecode);

        return new ResponseEntity(curd.newUser(Ename, Ecode), HttpStatus.OK);
    }


    @RequestMapping("ctc/{ctc}/{state}/{ecode}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CtcDto ctc_page(@PathVariable Long ctc, @PathVariable String state, @PathVariable String ecode) throws UserNotRegistered {
        return curd.ctc_page(ctc, state, ecode);
    }


    @RequestMapping(value = "employee/{ecode}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Object> findUserDetail(@PathVariable String ecode) throws UserNotRegistered {
        System.out.println("check begin");
        System.out.println(curd.findUserDetail(ecode));
        System.out.println("check ended");
        return new ResponseEntity<>(curd.findUserDetail(ecode), HttpStatus.OK);
    }


    @PutMapping("employee/{ecode}")
    public ResponseEntity<CtcDto> updateUser(@PathVariable String ecode, CtcDto ctcDto) {

        return new ResponseEntity<>(curd.updateUser(ecode, ctcDto), HttpStatus.OK);
    }


    @DeleteMapping("employee/{ecode}")
    public ResponseEntity<Object> deleteUser(@PathVariable String ecode) throws UserNotRegistered {
        return new ResponseEntity<>(curd.deleteUser(ecode), HttpStatus.OK);
    }
}
