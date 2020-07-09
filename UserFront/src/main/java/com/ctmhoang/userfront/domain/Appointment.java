package com.ctmhoang.userfront.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
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
    private User user;

    public boolean isConfirmed()
    {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed)
    {
        this.confirmed = confirmed;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    @Override
    public String toString()
    {
        return "Appointment{" +
                "confirmed=" + confirmed +
                ", id=" + id +
                ", date=" + date +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user +
                '}';
    }
}
