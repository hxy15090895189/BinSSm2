package com.student.entity;

public class Detep {
    private Integer id;

    private String detepid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDetepid() {
        return detepid;
    }

    public void setDetepid(String detepid) {
        this.detepid = detepid == null ? null : detepid.trim();
    }
}