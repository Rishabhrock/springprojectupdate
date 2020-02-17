package com.ascent.springproject.implementation;

import com.ascent.springproject.exception.UserAlreadyExits;
import com.ascent.springproject.exception.UserNotRegistered;
import com.ascent.springproject.model.BranchDto;
import com.ascent.springproject.model.CtcDto;
import com.ascent.springproject.repository.BranchRepository;
import com.ascent.springproject.repository.CtcRepository;
import com.ascent.springproject.service.DomainImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CurdImplementationTest {


    @InjectMocks
    CurdImplementation curdImplementation;


    @MockBean
    CtcRepository ctcRepository;
    @MockBean
    CtcDto ctcDto;
    @MockBean
    BranchRepository branchRepository;
    @MockBean
    DomainImplementation domainImplementation;

    @MockBean
    BranchDto branchDto;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);


        ctcRepository = org.mockito.Mockito.mock(CtcRepository.class);
        ctcDto = org.mockito.Mockito.mock(CtcDto.class);
        branchRepository = org.mockito.Mockito.mock(BranchRepository.class);
        domainImplementation = org.mockito.Mockito.mock(DomainImplementation.class);
        branchDto = org.mockito.Mockito.mock(BranchDto.class);
    }

    @Test
    void newUser() throws UserAlreadyExits {

        MockitoAnnotations.initMocks(this);
        CtcDto ctcDto = new CtcDto("Rishabhkr","Rishabhkr");
        when(ctcRepository.existsById("Rishabhkr")).thenReturn(false);
      //  System.out.println(ctcRepository.existsById("RishabhKrdfdf"));
        when(ctcRepository.save(ctcDto)).thenReturn(ctcDto);
        CtcDto ctcDto1 = curdImplementation.newUser("Rishabhkr","Rishabhkr");
        assertEquals(ctcDto,ctcDto1);

    }

    @Test
    void newUserException() throws UserAlreadyExits {
        MockitoAnnotations.initMocks(this);
        try {
            //MockitoAnnotations.initMocks(this);
            CtcDto ctcDto = new CtcDto("Rishabhkr", "Rishabhkr");
            when(ctcRepository.existsById("Rishabhkr")).thenReturn(true);
            //  System.out.println(ctcRepository.existsById("RishabhKrdfdf"));
            when(ctcRepository.save(ctcDto)).thenReturn(ctcDto);
            CtcDto ctcDto1 = curdImplementation.newUser("Rishabhkr", "Rishabhkr");
            assertEquals(ctcDto, ctcDto1);
        }
        catch (UserAlreadyExits e)
        {
            e.printStackTrace();
        }
    }


    @Test
    void ctc_page() throws UserNotRegistered {

//        MockitoAnnotations.initMocks(this);
//        String name = "Rishabhkr";
//        when((ctcRepository.existsById("RishabhKr"))).thenReturn(true);
//        when(ctcRepository.getOne("Rishabhkr")).thenReturn(ctcDto);
//        when(ctcDto.getEname()).thenReturn(name);
//        System.out.println(ctcDto.getEname());
////        when(ctcRepository.findById("Rishabhkr")).thenReturn(java.util.Optional.ofNullable(ctcDto));
//       // ctcRepository.findById(ecode).orElse(null)
//        System.out.println(ctcDto);
//        System.out.println("heloo");
//        CtcDto ctcDto = new CtcDto("Rishabhkr","Rishabhkr");
//        System.out.println("hello");
//
//
//        System.out.println("hello2");
//
//        System.out.println(ctcDto.getEname());
//
//     //   when(ctcDto.getEname()).thenReturn("Rishabhkr");
//     //   when((ctcDto.setEname("Rishabhkr"))).thenReturn("Rishabhkr");
//
////        ctcDto.setEname("Rishabhkr");
////        ctcDto.setCtc(15509L);
////        ctcDto.setLoc("Hyderabad");
////        ctcDto.setState("Hyderabad");
//
//
//        BranchDto branchDto = new BranchDto();
//        System.out.println("hello3");
//        when((branchRepository.findById("Hyderabad"))).thenReturn(java.util.Optional.of(branchDto));
//
//
//        when((domainImplementation.basic_ctc(15509L, 12345L))).thenReturn(5454L);
//        when((domainImplementation.bonus_ctc(5454L))).thenReturn(5454L);
//        when((domainImplementation.employer_pf_contribution(5454L))).thenReturn(5454L);
//        when((domainImplementation.gratuity_from_ctc(5454L))).thenReturn(5454L);
//        when((domainImplementation.gross_total(15509L, 5454L, 5454L))).thenReturn(5454L);
//        when((domainImplementation.employer_esi_contribution(5454L))).thenReturn(5454L);
//        when((domainImplementation.employee_pf_contribution(5454L))).thenReturn(5454L);
//        when((domainImplementation.employee_esi_contribution(5454L))).thenReturn(5454L);
//        when((domainImplementation.netpay(5454L, 5454L, 5454L))).thenReturn(5454L);
//        when((domainImplementation.grossDed(5454L, 5454L))).thenReturn(5454L);
//        when((domainImplementation.nettakehome(5454L, 5454L))).thenReturn(5454L);
//        when((domainImplementation.difference(5454L, 5454L))).thenReturn(5454L);
//        when((domainImplementation.ptgross(5454L, 5454L))).thenReturn(5454L);
//        when((domainImplementation.homerentallowance(5454L, 5454L, 5454L, 5454L, branchDto.getHra_per()))).thenReturn(5454L);
//
//        when(ctcRepository.save(ctcDto)).thenReturn(ctcDto);
//        when(ctcRepository.getOne("Rishabhkr")).thenReturn(ctcDto);
//
//
//
//        CtcDto ctcDto1 = curdImplementation.ctc_page(15509L,"Hyderabad","RishabhKr");
//        assertEquals(ctcDto,ctcDto1);
//
//



    }

    @Test
    void findUserDetail() throws UserNotRegistered {
        MockitoAnnotations.initMocks(this);
        CtcDto ctcDto = new CtcDto("Rishabhkr","Rishabhkr");
        when(ctcRepository.existsById("Rishabhkr")).thenReturn(true);
        when(ctcRepository.findByEcode("Rishabhkr")).thenReturn(ctcDto);

       CtcDto ctcDto1 = curdImplementation.findUserDetail("Rishabhkr");
        assertEquals(ctcDto,ctcDto1);
    }

    @Test
    void findUserDetails()
    {
        MockitoAnnotations.initMocks(this);
        try {
            CtcDto ctcDto = new CtcDto("Rishabhkr","Rishabhkr");
            when(ctcRepository.existsById("Rishabhkr")).thenReturn(false);
            when(ctcRepository.findByEcode("Rishabhkr")).thenReturn(ctcDto);

            CtcDto ctcDto1 = curdImplementation.findUserDetail("Rishabhkr");
            assertEquals(ctcDto,ctcDto1);
        }
         catch (UserNotRegistered userNotRegistered) {
            userNotRegistered.printStackTrace();
        }
    }


    @Test
    void updateUser() {

        MockitoAnnotations.initMocks(this);

        CtcDto ctcDto = new CtcDto("123", "as");
        CtcDto ctcDto1 = new CtcDto("123", "Ank");
        when(ctcRepository.existsById("123")).thenReturn(true);
        when(ctcRepository.findById("123")).thenReturn(java.util.Optional.of(ctcDto));
        when(ctcRepository.save(ctcDto)).thenReturn(ctcDto);

        CtcDto ctcDto11 = curdImplementation.updateUser("123",ctcDto1);

        assertEquals(ctcDto,ctcDto11);
    }

    @Test
    void deleteUser() throws UserNotRegistered {

        MockitoAnnotations.initMocks(this);
        CtcDto ctcDto = new CtcDto("Rishabhkr","Rishabhkr");
        when(ctcRepository.existsById("Rishabhkr")).thenReturn(true);
        when(ctcRepository.getOne("Rishabhkr")).thenReturn(ctcDto);
        String response = curdImplementation.deleteUser("Rishabhkr");
        assertEquals("Employee deleted",response);

    }
}