package com.codecool.restmates.service.DAO;

import com.codecool.restmates.model.Member;
import com.codecool.restmates.service.DTO.NewMemberDTO;
import com.codecool.restmates.service.DTO.MemberResponseDTO;

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
    public MemberResponseDTO createMember(NewMemberDTO member) {
        String sql = """
                    INSERT INTO member (member_id, first_name , last_name, email )
                    VALUES (?, ?, ?, ?);
                """;
        try (Connection conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, member.member_id());
            preparedStatement.setString(2, member.first_name());
            preparedStatement.setString(3, member.last_name());
            preparedStatement.setString(4, member.email());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            return new MemberResponseDTO(member.member_id(), member.first_name(),
                    member.last_name(), member.email());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteMemberById(long id) {

    }

    @Override
    public Member updateMember(Member member) {
        return null;
    }
}
