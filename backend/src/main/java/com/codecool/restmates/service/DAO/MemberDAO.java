package com.codecool.restmates.service.DAO;

import com.codecool.restmates.model.Member;
import com.codecool.restmates.service.DTO.MemberResponseDTO;
import com.codecool.restmates.service.DTO.NewMemberDTO;

public interface MemberDAO {
    MemberResponseDTO getMemberById(long id);
    MemberResponseDTO createMember(NewMemberDTO member);
    void deleteMemberById(long id);
    Member updateMember(Member member);
}
