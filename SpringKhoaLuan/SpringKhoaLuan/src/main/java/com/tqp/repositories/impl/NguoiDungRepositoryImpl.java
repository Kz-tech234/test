
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqp.repositories.impl;

/**
 *
 * @author Tran Quoc Phong
 */
import com.tqp.pojo.NguoiDung;
import com.tqp.repositories.NguoiDungRepository;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional

public class NguoiDungRepositoryImpl implements NguoiDungRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public NguoiDung getByUsername(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            Query q = s.createQuery("FROM NguoiDung WHERE username = :username", NguoiDung.class);
            q.setParameter("username", username);
            return (NguoiDung) q.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public NguoiDung getById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        return session.get(NguoiDung.class, id);
    }

    @Override
    public NguoiDung addUser(NguoiDung u) {
        //u.setPassword(passwordEncoder.encode(u.getPassword()));
        Session s = this.factory.getObject().getCurrentSession();
        s.persist(u);
        return u;
    }

    @Override
    public NguoiDung merge(NguoiDung u) {
        Session s = this.factory.getObject().getCurrentSession();
        return (NguoiDung) s.merge(u);  // Dùng merge để cập nhật entity đã tồn tại
    }

    @Override
    public boolean deleteUser(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        NguoiDung u = session.get(NguoiDung.class, id);
        if (u != null) {
            session.delete(u);
            return true;
        }
        return false;
    }

    @Override
    public boolean authenticate(String username, String rawPassword) {
        NguoiDung u = this.getByUsername(username);
        return u != null && passwordEncoder.matches(rawPassword, u.getPassword());
    }

    @Override
    public List<NguoiDung> getAllUsers() {
        Session session = this.factory.getObject().getCurrentSession();
        Query query = session.createQuery("FROM NguoiDung");
        return query.getResultList();
    }

    @Override
    public List<NguoiDung> getGiangVienByKhoa(String khoa) {
        Session session = factory.getObject().getCurrentSession();
        Query query = session.createQuery("FROM NguoiDung WHERE role = 'ROLE_GIANGVIEN' AND khoa = :khoa", NguoiDung.class);
        query.setParameter("khoa", khoa);
        return query.getResultList();
    }

    @Override
    public List<NguoiDung> getSinhVienByKhoaVaKhoaHoc(String khoa, String khoaHoc) {
        Session session = factory.getObject().getCurrentSession();
        Query query = session.createQuery("FROM NguoiDung WHERE role = 'ROLE_SINHVIEN' AND khoa = :khoa AND khoaHoc = :khoaHoc", NguoiDung.class);
        query.setParameter("khoa", khoa);
        query.setParameter("khoaHoc", khoaHoc);
        System.out.println(">>>>> Khoa: " + khoa + " | KhoaHoc: " + khoaHoc);
        return query.getResultList();

    }
}