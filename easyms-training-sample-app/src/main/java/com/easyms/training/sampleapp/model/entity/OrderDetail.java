package com.easyms.training.sampleapp.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.apache.commons.lang3.builder.EqualsExclude;
import org.apache.commons.lang3.builder.HashCodeExclude;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode(exclude="order")
@Table(name = "Orders_detail")
public class OrderDetail  implements Serializable {


    @Id
    @ManyToOne
    @JoinColumn
    @JsonBackReference
    Order order;

    @Id
    @ManyToOne
    @JoinColumn
    @EqualsExclude
    @HashCodeExclude
    @JsonManagedReference
    Product product;

    @NotNull
    private int quanity;

    private double amount;




}
