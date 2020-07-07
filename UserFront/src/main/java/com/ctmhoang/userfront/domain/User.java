package com.ctmhoang.userfront.domain;

import java.util.List;

public class User
{
    private boolean enabled = true;

    private Long usrID;
    private String usrName;
    private String pwd;
    private String fstName;
    private String lstName;
    private String email;
    private String phone;

    private PrimaryAccount primAcc;
    private SavingsAccount saveAcc;

    private List<Appointment> appointmentList;
    private List<Recipient> recipientList;

    public boolean isEnabled()
    {
        return enabled;
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    public Long getUsrID()
    {
        return usrID;
    }

    public void setUsrID(Long usrID)
    {
        this.usrID = usrID;
    }

    public String getUsrName()
    {
        return usrName;
    }

    public void setUsrName(String usrName)
    {
        this.usrName = usrName;
    }

    public String getPwd()
    {
        return pwd;
    }

    public void setPwd(String pwd)
    {
        this.pwd = pwd;
    }

    public String getFstName()
    {
        return fstName;
    }

    public void setFstName(String fstName)
    {
        this.fstName = fstName;
    }

    public String getLstName()
    {
        return lstName;
    }

    public void setLstName(String lstName)
    {
        this.lstName = lstName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public PrimaryAccount getPrimAcc()
    {
        return primAcc;
    }

    public void setPrimAcc(PrimaryAccount primAcc)
    {
        this.primAcc = primAcc;
    }

    public SavingsAccount getSaveAcc()
    {
        return saveAcc;
    }

    public void setSaveAcc(SavingsAccount saveAcc)
    {
        this.saveAcc = saveAcc;
    }

    public List<Appointment> getAppointmentList()
    {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList)
    {
        this.appointmentList = appointmentList;
    }

    public List<Recipient> getRecipientList()
    {
        return recipientList;
    }

    public void setRecipientList(List<Recipient> recipientList)
    {
        this.recipientList = recipientList;
    }

    @Override
    public String toString()
    {
        return "User{" +
                "enabled=" + enabled +
                ", usrID=" + usrID +
                ", usrName='" + usrName + '\'' +
                ", pwd='" + pwd + '\'' +
                ", fstName='" + fstName + '\'' +
                ", lstName='" + lstName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", primAcc=" + primAcc +
                ", saveAcc=" + saveAcc +
                ", appointmentList=" + appointmentList +
                ", recipientList=" + recipientList +
                '}';
    }
}
