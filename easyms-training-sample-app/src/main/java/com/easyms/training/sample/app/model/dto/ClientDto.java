package com.easyms.training.sample.app.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientDto {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;

}
