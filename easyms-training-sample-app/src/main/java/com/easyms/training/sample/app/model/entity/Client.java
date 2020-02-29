package com.easyms.training.sample.app.model.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Client {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
}
