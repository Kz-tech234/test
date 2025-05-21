/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqp.repositories;

/**
 *
 * @author Tran Quoc Phong
 */
import com.tqp.pojo.TieuChi;
import java.util.List;

public interface TieuChiRepository {
    List<TieuChi> getAll();
    TieuChi addTieuChi(TieuChi tieuChi); 
    
    List<TieuChi> findByKhoa(String khoa);
}
