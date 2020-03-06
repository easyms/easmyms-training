package com.easyms.training.sampleapp.model.dto;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class ClientDto {

    //private Long id;
	@Size(min = 2, max = 15, message= "first name lenght has to be between 2 and 15 characters")
    private String firstname;
	@Size(min = 2, max = 15, message= "last name lenght has to be between 2 and 15 characters")
    private String lastname;

}
