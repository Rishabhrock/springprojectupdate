package com.ascent.springproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.ArrayList;
import java.util.List;

@Repository
@Entity
@Data
@ToString
@Table(name = "ctc_dto_table")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CtcDto {

    @Id
    private String ecode;
    @Column
    private String ename;
    @Column
    private String loc;
    @Column
    private String state;
    @Column
    private Long hra;
    @Column
    private Long net_take_home;
    @Column
    private Long ctc;
    @Column
    private Long basic;
    @Column
    private Long bonus;
    @Column
    private Long spll;
    @Column
    private Long employer_pf;
    @Column
    private Long employer_esi;
    @Column
    private Long gratuity;
    @Column
    private Long gross;
    @Column
    private Long employee_Pf;
    @Column
    private Long employee_esi;
    @Column
    private Long employee_pt;
    @Column
    private Long employee_lwf;
    @Column
    private Long gross_ded;
    @Column
    private Long diff;
    @Column
    private Long pt_gross;
    @Column
    private Long net_Pay;

    public CtcDto(String ecode, String ename) {
        this.ecode = ecode;
        this.ename = ename;
    }



//    List<CtcDto> list = new ArrayList<>();
//
//
//    public List<CtcDto> getList() {
//        return list;
//    }
//
//    public void setList(List<CtcDto> list) {
//        this.list = list;
//    }


//    public CtcDto(String ecode, String ename, String loc, String state, Long hra, Long net_take_home, Long ctc, Long basic, Long bonus, Long spll, Long employer_pf, Long employer_esi, Long gratuity, Long gross, Long employee_Pf, Long employee_esi, Long employee_pt, Long employee_lwf, Long gross_ded, Long diff, Long pt_gross, Long net_Pay) {
//        this.ecode = ecode;
//        this.ename = ename;
//        this.loc = loc;
//        this.state = state;
//        this.hra = hra;
//        this.net_take_home = net_take_home;
//        this.ctc = ctc;
//        this.basic = basic;
//        this.bonus = bonus;
//        this.spll = spll;
//        this.employer_pf = employer_pf;
//        this.employer_esi = employer_esi;
//        this.gratuity = gratuity;
//        this.gross = gross;
//        this.employee_Pf = employee_Pf;
//        this.employee_esi = employee_esi;
//        this.employee_pt = employee_pt;
//        this.employee_lwf = employee_lwf;
//        this.gross_ded = gross_ded;
//        this.diff = diff;
//        this.pt_gross = pt_gross;
//        this.net_Pay = net_Pay;
//    }


//    public CtcDto() {                                          // changes...
//    }

}
