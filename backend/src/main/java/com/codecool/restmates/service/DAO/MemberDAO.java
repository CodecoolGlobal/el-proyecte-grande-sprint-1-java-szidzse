package com.codecool.restmates.service.DAO;

import com.codecool.restmates.model.Member;

public interface MemberDAO {
    Member getMemberById(long id);
    Member createMember(Member member);
    void deleteMemberById(long id);
    Member updateMember(Member member);
}
