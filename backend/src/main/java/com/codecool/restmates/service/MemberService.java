package com.codecool.restmates.service;

import com.codecool.restmates.exception.EmailAlreadyExistsException;
import com.codecool.restmates.model.Member;
import com.codecool.restmates.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Optional<Member> findMemberByEmail(long Id) {
        return memberRepository.findById(Id);
    }

    public Member saveMember(Member member) {
        if (memberRepository.existsByEmail(member.getEmail())) {
            throw new EmailAlreadyExistsException(String.format(" %s already exists.", member.getEmail()));
        }
        return memberRepository.save(member);
    }
}
