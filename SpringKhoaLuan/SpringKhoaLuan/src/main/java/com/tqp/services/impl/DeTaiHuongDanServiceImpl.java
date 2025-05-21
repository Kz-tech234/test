/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqp.services.impl;

/**
 *
 * @author Tran Quoc Phong
 */
import com.tqp.pojo.DeTaiKhoaLuan_GiangVienHuongDan;
import com.tqp.pojo.NguoiDung;
import com.tqp.repositories.DeTaiHuongDanRepository;
import com.tqp.services.DeTaiHuongDanService;
import com.tqp.services.NguoiDungService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeTaiHuongDanServiceImpl implements DeTaiHuongDanService{
    @Autowired
    private DeTaiHuongDanRepository repo;
    
    @Autowired
    private NguoiDungService nguoiDungService;


    @Override
    public List<DeTaiKhoaLuan_GiangVienHuongDan> getAll() {
        return repo.getAll();
    }

    @Override
    public DeTaiKhoaLuan_GiangVienHuongDan getById(int id) {
        return repo.getById(id);
    }

    @Override
    public DeTaiKhoaLuan_GiangVienHuongDan add(DeTaiKhoaLuan_GiangVienHuongDan d) {
        return repo.save(d);
    }

    @Override
    public void delete(int id) {
        repo.delete(id);
    }
    
    @Override
    public void assign(int deTaiId, int giangVienId) {
        DeTaiKhoaLuan_GiangVienHuongDan dtgv = new DeTaiKhoaLuan_GiangVienHuongDan();
        dtgv.setDeTaiKhoaLuanId(deTaiId); 
        dtgv.setGiangVienHuongDanId(giangVienId); 
        repo.save(dtgv);
    }
    
    @Override
    public NguoiDung findByDeTaiId(int deTaiId) {
        var huongDan = repo.findByDeTaiId(deTaiId);
        return huongDan != null ? nguoiDungService.getById(huongDan.getGiangVienHuongDanId()) : null;
    }
    
    @Override
    public List<DeTaiKhoaLuan_GiangVienHuongDan> findAllByDeTaiId(int deTaiId) {
        return repo.findAllByDeTaiId(deTaiId);
    }
}
