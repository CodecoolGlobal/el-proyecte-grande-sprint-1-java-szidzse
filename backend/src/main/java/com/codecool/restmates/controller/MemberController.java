package com.codecool.restmates.controller;

import com.codecool.restmates.model.Member;
import com.codecool.restmates.service.DTO.MemberResponseDTO;
import com.codecool.restmates.service.DTO.NewMemberDTO;
import com.codecool.restmates.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/member/{memberId}")
    public MemberResponseDTO getMember(@PathVariable("memberId") long memberId) {
        return memberService.getMemberById(memberId);
    }

    @PostMapping("/member")
    public MemberResponseDTO createMember(@RequestBody NewMemberDTO member) {
        return memberService.createMember(member);
    }

}
