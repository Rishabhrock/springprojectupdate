package com.ascent.springproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Repository
@Entity
@Data
@ToString
@Table(name="ctc_dto_table")
@AllArgsConstructor
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

    public CtcDto() {
    }

}
