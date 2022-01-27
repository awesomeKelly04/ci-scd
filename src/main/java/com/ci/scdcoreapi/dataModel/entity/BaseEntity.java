package com.ci.scdcoreapi.dataModel.entity;

import com.ci.scdcoreapi.dataModel.audit.DateAudit;
import com.ci.scdcoreapi.dataModel.enumeration.GenericStatusConstant;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public class BaseEntity extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Basic
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GenericStatusConstant status;
    @Basic
    protected Date dateDeactivated;

    public BaseEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GenericStatusConstant getStatus() {
        return status;
    }

    public void setStatus(GenericStatusConstant status) {
        this.status = status;
    }

    public Date getDateDeactivated() {
        return dateDeactivated;
    }

    public void setDateDeactivated(Date dateDeactivated) {
        this.dateDeactivated = dateDeactivated;
    }
}
