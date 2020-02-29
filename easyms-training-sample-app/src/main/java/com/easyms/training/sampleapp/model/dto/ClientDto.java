package com.easyms.training.sampleapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ClientDto {

    private Long id;
    private String firstname;

}
