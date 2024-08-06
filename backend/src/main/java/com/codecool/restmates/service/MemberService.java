package com.codecool.restmates.service;

import com.codecool.restmates.exception.EmailAlreadyExistsException;
import com.codecool.restmates.exception.ResourceNotFoundException;
import com.codecool.restmates.model.Member;
import com.codecool.restmates.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Optional<Member> getMemberById(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public Member saveMember(Member member) {
        if (memberRepository.existsByEmail(member.getEmail())) {
            throw new EmailAlreadyExistsException(String.format(" %s already exists.", member.getEmail()));
        }
        return memberRepository.save(member);
    }

    public Member updateMember(Long memberId, Member updatedMember) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("%s does not exist.", memberId)));

        member.setFirstName(updatedMember.getFirstName());
        member.setLastName(updatedMember.getLastName());
        if (existsEmail(member.getEmail())) {
            member.setEmail(updatedMember.getEmail());
        }

        return memberRepository.save(member);
    }

    public Boolean deleteMember(Long memberId) {
        if (!memberRepository.existsById(memberId)) {
            throw new ResourceNotFoundException(String.format(" %s does not exist.", memberId));
        }
        memberRepository.deleteById(memberId);
        return true;
    }

    public Boolean existsEmail(String email) {
        return memberRepository.existsByEmail(email);
    };
}
