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
@Table(name = "deTaiKhoaLuan_HoiDong")

public class DeTaiKhoaLuan_HoiDong implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "deTaiKhoaLuan_id")
    private Integer deTaiKhoaLuanId;

    @Column(name = "hoiDong_id")
    private Integer hoiDongId;
    
    @Column(name = "locked")
    private boolean locked = false;

    public DeTaiKhoaLuan_HoiDong() {}

    public DeTaiKhoaLuan_HoiDong(Integer id, Integer deTaiKhoaLuanId, Integer hoiDongId) {
        this.id = id;
        this.deTaiKhoaLuanId = deTaiKhoaLuanId;
        this.hoiDongId = hoiDongId;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getDeTaiKhoaLuanId() { return deTaiKhoaLuanId; }
    public void setDeTaiKhoaLuanId(Integer deTaiKhoaLuanId) { this.deTaiKhoaLuanId = deTaiKhoaLuanId; }

    public Integer getHoiDongId() { return hoiDongId; }
    public void setHoiDongId(Integer hoiDongId) { this.hoiDongId = hoiDongId; }
    
    public boolean isLocked() { return locked; }
    public void setLocked(boolean locked) { this.locked = locked; }

    @Override
    public int hashCode() {
        return (id != null ? id.hashCode() : 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DeTaiKhoaLuan_HoiDong)) return false;
        DeTaiKhoaLuan_HoiDong other = (DeTaiKhoaLuan_HoiDong) obj;
        return this.id != null && this.id.equals(other.id);
    }

    @Override
    public String toString() {
        return "DeTaiKhoaLuan_HoiDong[ id=" + id + " ]";
    }
}
