
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqp.repositories;

/**
 *
 * @author Tran Quoc Phong
 */
import com.tqp.pojo.NguoiDung;
import java.util.List;

public interface NguoiDungRepository {
    NguoiDung getByUsername(String username);
    NguoiDung getById(int id);
    NguoiDung addUser(NguoiDung u);
    NguoiDung merge(NguoiDung u);
    boolean deleteUser(int id);
    boolean authenticate(String username, String rawPassword);
    List<NguoiDung> getAllUsers();
    List<NguoiDung> getGiangVienByKhoa(String khoa);
    List<NguoiDung> getSinhVienByKhoaVaKhoaHoc(String khoa, String khoaHoc);
}