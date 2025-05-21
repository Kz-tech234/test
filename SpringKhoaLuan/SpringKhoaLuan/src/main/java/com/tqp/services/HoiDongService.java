/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqp.services;

/**
 *
 * @author Tran Quoc Phong
 */
import com.tqp.pojo.HoiDong;
import com.tqp.pojo.NguoiDung;
import java.util.List;

public interface HoiDongService {
    List<HoiDong> getAllHoiDong();
    HoiDong getById(int id);
    HoiDong addHoiDong(HoiDong hoiDong);
    void deleteHoiDong(int id);
    
    List<NguoiDung> getThanhVienHoiDong(int hoiDongId);
}
