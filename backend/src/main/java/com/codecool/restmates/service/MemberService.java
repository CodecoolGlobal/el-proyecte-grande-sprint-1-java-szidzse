package com.codecool.restmates.service;

import com.codecool.restmates.repositories.memberDAO.MemberDAO;
import com.codecool.restmates.dto.responses.MemberResponseDTO;
import com.codecool.restmates.dto.requests.NewMemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberDAO memberDao;

    @Autowired
    public MemberService(MemberDAO memberDao) {
        this.memberDao = memberDao;
    }

    public MemberResponseDTO getMemberById(String id) {
        return memberDao.getMemberById(id);
        // A servicben kell DTO/ra alakitani stb. a DAO nem felel ilyenekert.
    }

    public MemberResponseDTO createMember(NewMemberDTO member) {
        return memberDao.createMember(member);
    }

}
