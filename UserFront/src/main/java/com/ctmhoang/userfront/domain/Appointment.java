package com.ctmhoang.userfront.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Appointment
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private boolean confirmed;

    private Date date;
    private String location;
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User usr;
}
