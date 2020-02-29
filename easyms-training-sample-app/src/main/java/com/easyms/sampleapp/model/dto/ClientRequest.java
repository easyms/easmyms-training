package com.easyms.sampleapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ClientRequest {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
}
