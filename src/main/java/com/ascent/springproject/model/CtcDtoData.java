package com.ascent.springproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.Embeddable;

@Repository
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CtcDtoData {

    
    private String loc;
    
    private String state;
    
    private Long hra;
    
    private Long net_take_home;
    
    private Long ctc;
    
    private Long basic;
    
    private Long bonus;
    
    private Long spll;
    
    private Long employer_pf;
    
    private Long employer_esi;
    
    private Long gratuity;
    
    private Long gross;
    
    private Long employee_Pf;
    
    private Long employee_esi;
    
    private Long employee_pt;
    
    private Long employee_lwf;
    
    private Long gross_ded;
    
    private Long diff;
    
    private Long pt_gross;
    
    private Long net_Pay;


}
