package com.codecool.restmates.repositories.memberDAO;

import com.codecool.restmates.model.Member;
import com.codecool.restmates.dto.responses.MemberResponseDTO;
import com.codecool.restmates.dto.requests.NewMemberDTO;

public interface MemberDAO {
    MemberResponseDTO getMemberById(String id);
    MemberResponseDTO createMember(NewMemberDTO member);
    void deleteMemberById(long id);
    Member updateMember(Member member);
}
