package com.ascent.springproject.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "branch_db")
@Getter
@Setter
@ToString
@Repository
public class BranchDto {

        @Id
        String state;
        @Column
        String state_code;
        @Column
        Long minimum_wages;
        @Column
        Long hra_per;



}
