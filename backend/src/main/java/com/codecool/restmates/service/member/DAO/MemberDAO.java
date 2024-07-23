package com.codecool.restmates.service.member.DAO;

import com.codecool.restmates.model.Member;
import com.codecool.restmates.service.member.DTO.MemberResponseDTO;
import com.codecool.restmates.service.member.DTO.NewMemberDTO;

public interface MemberDAO {
    Member getMemberById(long id);
    MemberResponseDTO createMember(NewMemberDTO member);
    void deleteMemberById(long id);
    Member updateMember(Member member);
}
