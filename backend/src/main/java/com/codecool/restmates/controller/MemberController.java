package com.codecool.restmates.controller;

import com.codecool.restmates.dto.responses.MemberResponseDTO;
import com.codecool.restmates.exception.EmailAlreadyExistsException;
import com.codecool.restmates.exception.ResourceNotFoundException;
import com.codecool.restmates.model.Member;
import com.codecool.restmates.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/member")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping(path = "/{memberId}")
    public MemberResponseDTO getMemberById(@PathVariable long memberId) {
        return memberService.getMemberById(memberId);
    }

    @PostMapping(path = "")
    public Member createMember(@RequestBody Member member) {
        return memberService.saveMember(member);
    }

    @PutMapping(path ="/{memberId}")
    public Member updateMember(@PathVariable long memberId, @RequestBody Member member) {
        return memberService.updateMember(memberId, member);
    }

    @DeleteMapping(path ="/{memberId}/delete")
    public boolean deleteMember(@PathVariable long memberId) {
        return memberService.deleteMember(memberId);
    }
}
