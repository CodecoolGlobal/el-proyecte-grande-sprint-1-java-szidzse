package com.codecool.restmates.repositories.memberDAO;

import com.codecool.restmates.model.Member;
import com.codecool.restmates.dto.requests.NewMemberDTO;
import com.codecool.restmates.dto.responses.MemberResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class MemberDAOImpl implements MemberDAO {

    private DataSource dataSource;

    @Autowired
    public MemberDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public MemberResponseDTO getMemberById(String memberId) {
        String sql = "SELECT first_name, last_name, email FROM Member WHERE member_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, memberId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");

                return new MemberResponseDTO(memberId, firstName, lastName, email);
                // nem DTO, teljes obj.
            } else {
                throw new RuntimeException("Member with ID " + memberId + " not found.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public MemberResponseDTO createMember(NewMemberDTO member) {
        String sql = """
                    INSERT INTO member (member_id, first_name , last_name, email )
                    VALUES (?, ?, ?, ?);
                """;
        try (Connection conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, member.member_id());
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
