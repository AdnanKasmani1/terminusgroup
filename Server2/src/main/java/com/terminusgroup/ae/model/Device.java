package com.terminusgroup.ae.model;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table
@Getter
@Setter
public class Device implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "imei", unique = true)
    private String imei;

    @Column(name = "sim", unique = true)
    private String sim;


    @Column(name = "Enable")
    private Boolean enable;

    @Column(name = "Configured")
    private Boolean configured;




}