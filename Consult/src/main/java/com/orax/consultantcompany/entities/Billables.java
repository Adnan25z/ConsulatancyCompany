/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orax.consultantcompany.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author adnan
 */
@Entity
@Table(name = "billables")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Billables.findAll", query = "SELECT b FROM Billables b"),
    @NamedQuery(name = "Billables.findById", query = "SELECT b FROM Billables b WHERE b.id = :id"),
    @NamedQuery(name = "Billables.findByConsultant", query = "SELECT b FROM Billables b WHERE b.consultant.email = :consultant_email"),
    @NamedQuery(name = "Billables.findByStartDate", query = "SELECT b FROM Billables b WHERE b.startDate = :startDate"),
    @NamedQuery(name = "Billables.findByEndDate", query = "SELECT b FROM Billables b WHERE b.endDate = :endDate"),
    @NamedQuery(name = "Billables.findByHours", query = "SELECT b FROM Billables b WHERE b.hours = :hours"),
    @NamedQuery(name = "Billables.findByHourlyRate", query = "SELECT b FROM Billables b WHERE b.hourlyRate = :hourlyRate"),
    @NamedQuery(name = "Billables.findByBillableHourlyRate", query = "SELECT b FROM Billables b WHERE b.billableHourlyRate = :billableHourlyRate"),
    @NamedQuery(name = "Billables.findByDescription", query = "SELECT b FROM Billables b WHERE b.description = :description")})
public class Billables implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Column(name = "hours")
    private Integer hours;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "hourly_rate")
    private Float hourlyRate;
    @Column(name = "billable_hourly_rate")
    private Float billableHourlyRate;
    @Size(max = 245)
    @Column(name = "description")
    private String description;
    @Lob
    @Size(max = 65535)
    @Column(name = "artifacts")
    private String artifacts;
    
    @JoinColumn(name = "project", referencedColumnName = "id")
    @ManyToOne
    private Projects project;
    
    @JoinColumn(name = "consultant", referencedColumnName = "id")
    @ManyToOne
    private Users consultant;

    public Billables() {
    }

    public Billables(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Float getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(Float hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public Float getBillableHourlyRate() {
        return billableHourlyRate;
    }

    public void setBillableHourlyRate(Float billableHourlyRate) {
        this.billableHourlyRate = billableHourlyRate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getArtifacts() {
        return artifacts;
    }

    public void setArtifacts(String artifacts) {
        this.artifacts = artifacts;
    }

    public Projects getProject() {
        return project;
    }

    public void setProject(Projects project) {
        this.project = project;
    }

    public Users getConsultant() {
        return consultant;
    }

    public void setConsultant(Users consultant) {
        this.consultant = consultant;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Billables)) {
            return false;
        }
        Billables other = (Billables) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return description;
    }
    
}
