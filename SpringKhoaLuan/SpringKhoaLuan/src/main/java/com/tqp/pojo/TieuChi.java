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

@Entity
@Table(name = "tieuchis")
public class TieuChi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ten_tieuchi", nullable = false)
    private String tenTieuChi;

    @Column(name = "created_by", nullable = false)
    private Integer createdBy;
    
    @Column(name = "status", nullable = false)
    private String status = "active";
    
    @Column(name = "khoa", nullable = false)
    private String khoa;

    // Getters và setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTenTieuChi() { return tenTieuChi; }
    public void setTenTieuChi(String tenTieuChi) { this.tenTieuChi = tenTieuChi; }

    public Integer getCreatedBy() { return createdBy; }
    public void setCreatedBy(Integer createdBy) { this.createdBy = createdBy; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getKhoa() { return khoa; }
    public void setKhoa(String khoa) { this.khoa = khoa; }
}
