package com.easyms.sampleapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


public class ClientDto {
    private Long id;
    private String fullName;


    public ClientDto(Long id , String fullName){
        this.id = id;
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
