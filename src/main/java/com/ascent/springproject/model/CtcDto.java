package com.ascent.springproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;


@Repository
@Entity
@Data
@ToString
//@Table(name = "ctc_dto_table")
@Table(name = "ctc_dto_table_demo")
//@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CtcDto {

    @Id
    private String ecode;

    private String ename;

    private String state;

    private Long ctc;



    @ElementCollection
    private Map<String, CtcDtoData> map = new HashMap<String, CtcDtoData>();


    public CtcDto(String ecode, String ename) {
        this.ecode = ecode;
        this.ename = ename;
    }
}
