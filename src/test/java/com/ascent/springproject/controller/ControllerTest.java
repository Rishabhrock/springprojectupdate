package com.ascent.springproject.controller;

import com.ascent.springproject.exception.UserAlreadyExits;
import com.ascent.springproject.exception.UserNotRegistered;
import com.ascent.springproject.implementation.Curd;
import com.ascent.springproject.model.CtcDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


class ControllerTest {

    @InjectMocks
    Controller controller;

    @InjectMocks
    private ObjectMapper objectMapper;


    @Autowired
    private MockMvc mockMvc;

    @Mock
    Curd curd;


    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);
        curd = org.mockito.Mockito.mock(Curd.class);
        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    void newUser() throws Exception, UserAlreadyExits {

        MockitoAnnotations.initMocks(this);
        CtcDto ctcDto = new CtcDto("rishabh56","rishabh56");
        String Json = objectMapper.writeValueAsString(ctcDto);
        System.out.println(Json);
        when(curd.newUser(ctcDto)).thenReturn(ctcDto);
        MockHttpServletResponse response = mockMvc.perform(post("/employee/{Ename}/{Ecode}")).andReturn().getResponse();
        assertThat(response.getStatus(),is(HttpStatus.OK.value()));

    }

//
//    @Test
//    void ctc_page() throws Exception, UserNotRegistered {
//
//        MockitoAnnotations.initMocks(this);
//        CtcDto ctcDto=new CtcDto("abc123","Rishabh");
//        when(curd.ctc_page(19967L,"BANGALORE","rishabh","rishabh")).thenReturn(ctcDto);
//        MockHttpServletResponse response=mockMvc.perform(get("/ctc/19967/BANGALORE/rishabh/rishabh")).andReturn().getResponse();
//        System.out.println(response.getStatus());
//        assertThat(response.getStatus(),is(HttpStatus.ACCEPTED.value()));
//    }


    @Test
    void findUserDetail() throws Exception, UserNotRegistered {

        MockitoAnnotations.initMocks(this);
        CtcDto ctcDto=new CtcDto("123","Gunjan");

        when(curd.findUserDetail("123")).thenReturn(ctcDto);
        MockHttpServletResponse response=mockMvc.perform(get("/employee/123")).andReturn().getResponse();


        assertThat(response.getStatus(),is(HttpStatus.OK.value()));
        assertThat(response.getContentAsString(),is(objectMapper.writeValueAsString(curd.findUserDetail("123"))));

    }


    @Test
    void updateUser() throws Exception {

        MockitoAnnotations.initMocks(this);
        CtcDto ctcDto=new CtcDto("123","rish");
        CtcDto ctcDto1=new CtcDto("123","rishabh");
        lenient().when(curd.updateUser("123",ctcDto)).thenReturn(ctcDto);
        MockHttpServletResponse response=mockMvc.perform(MockMvcRequestBuilders.put("/employee/123").contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        System.out.println(response.getStatus());
        System.out.println(objectMapper.writeValueAsString(curd.updateUser("123",ctcDto)));
        System.out.println(response.getContentAsString());
        assertThat(response.getStatus(),is(HttpStatus.OK.value()));

    }


    @Test
    void deleteUser() throws Exception, UserNotRegistered {

        lenient().when(curd.deleteUser("rishabh123")).thenReturn("Employee deleted");
        System.out.println("nextLine");
        mockMvc.perform(MockMvcRequestBuilders.delete("/employee/rishabh123"))
                .andExpect(status().isOk());
    }


}