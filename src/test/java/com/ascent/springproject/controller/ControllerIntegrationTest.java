package com.ascent.springproject.controller;

import com.ascent.springproject.model.CtcDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;



@RunWith(SpringRunner.class)

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ControllerIntegrationTest {

    @LocalServerPort
    private int port = 8081;

    @Autowired
    private TestRestTemplate restTemplate;


    HttpHeaders headers = new HttpHeaders();


    @Test
    void newUser() {

        CtcDto employee = new CtcDto();
        employee.setEcode("Rishabh033");
        employee.setEname("Rishabh033");

        ResponseEntity<String> responseEntity = this.restTemplate
                .postForEntity("http://localhost:" + this.port + "/employee/"+employee.getEname()
                                +"/"+employee.getEcode()
                        , employee, String.class);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    void ctc_page() {

        CtcDto employee = new CtcDto();
        employee.setEcode("Rishabh033");
        employee.setEname("Rishabh033");
        employee.setCtc(15509L);
        employee.setState("Hyderabad");

        ResponseEntity<String> responseEntity = this.restTemplate
                .postForEntity("http://localhost:" + this.port + "/employee/"+employee.getCtc()
                                +"/"+employee.getState()+"/"+employee.getEcode()+"/"+employee.getEname()
                        , employee, String.class);
        assertEquals(202, responseEntity.getStatusCodeValue());



    }

    @Test
    void findUserDetail() {

        String expected =
                "{\"ecode\":\"Rishabh033\",\"ename\":\"Rishabh033\",\"state\":\"Hyderabad\",\"ctc\":15509,\"map\":{\"Rishabh033\":{\"loc\":\"Hyderabad\",\"state\":\"Hyderabad\",\"hra\":3400,\"net_take_home\":12002,\"ctc\":15509,\"basic\":9171,\"bonus\":764,\"spll\":0,\"employer_pf\":1100,\"employer_esi\":633,\"gratuity\":441,\"gross\":13335,\"employee_Pf\":1100,\"employee_esi\":233,\"employee_pt\":0,\"employee_lwf\":0,\"gross_ded\":1333,\"diff\":0,\"pt_gross\":13335,\"net_Pay\":12002}}}";




        String employeeCode =  "Rishabh033";
        ResponseEntity<String> responseEntity = this.restTemplate
                .getForEntity("http://localhost:" + this.port + "/employee/"+employeeCode, String.class );

        assertEquals(200, responseEntity.getStatusCodeValue());
        System.out.println(expected);
        System.out.println(responseEntity.getBody());
        if(expected.equals(responseEntity.getBody())){
            System.out.println("Your Test Passed");
        }
        else{
            System.out.println("not");
        }
        assertEquals(expected, responseEntity.getBody());

    }

    @Test
    void updateUser() {


        CtcDto employee = new CtcDto();
        employee.setEname("Rishabh033");
        Map<String, String> params = new HashMap<>();

        params.put("ename", "Rishabh033");

        this.restTemplate
                .put("http://localhost:" + this.port + "/employee/"+employee.getEcode()

                        , employee, String.class);
    }

    @Test
    void deleteUser() {

        CtcDto employee = new CtcDto();
        String Ecode = "Rishabh033";



        this.restTemplate.delete("http://localhost:" + this.port + "/employee/"+"Rishabh033" , employee, String.class);

    }
}