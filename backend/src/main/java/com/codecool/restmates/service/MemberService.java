package com.codecool.restmates.service;

import com.codecool.restmates.model.Member;
import com.codecool.restmates.service.member.DAO.MemberDAO;
import com.codecool.restmates.service.member.DTO.MemberResponseDTO;
import com.codecool.restmates.service.member.DTO.NewMemberDTO;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberDAO memberDao;


    public MemberService(MemberDAO memberDao) {
        this.memberDao = memberDao;
    }

    public Member getMemberById(long id) {
        return memberDao.getMemberById(id);
    }

    public MemberResponseDTO createMember(NewMemberDTO member) {
        return memberDao.createMember(member);
    }

}
