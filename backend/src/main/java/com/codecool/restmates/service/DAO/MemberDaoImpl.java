package com.codecool.restmates.service.DAO;

import com.codecool.restmates.model.Member;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MemberDaoImpl implements MemberDAO {

    private DataSource dataSource;

    public MemberDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Member getMemberById(long id) {
        return null;
    }

    @Override
    public Member createMember(Member member) {
        return null;
    }

    @Override
    public void deleteMemberById(long id) {

    }

    @Override
    public Member updateMember(Member member) {
        return null;
    }
}
