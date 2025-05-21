/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqp.pojo;

/**
 *
 * @author Tran Quoc Phong
 */
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "hoiDongs")

public class HoiDong implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 100)
    private String name;
    
    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "status")
    private String status;
    
    @Column(name = "khoa")
    private String khoa;

    public HoiDong() {}

    public HoiDong(Integer id, String name, Integer createdBy, String status, String khoa) {
        this.id = id;
        this.name = name;
        this.createdBy = createdBy;
        this.status = status;
         this.khoa = khoa;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public Integer getCreatedBy() { return createdBy; }  // Getter và setter cho createdBy
    public void setCreatedBy(Integer createdBy) { this.createdBy = createdBy; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getKhoa() { return khoa; }  // Getter và setter cho khoa
    public void setKhoa(String khoa) { this.khoa = khoa; }

    @Override
    public int hashCode() {
        return (id != null ? id.hashCode() : 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof HoiDong)) return false;
        HoiDong other = (HoiDong) obj;
        return this.id != null && this.id.equals(other.id);
    }

    @Override
    public String toString() {
        return "HoiDong[ id=" + id + " ]";
    }
}
