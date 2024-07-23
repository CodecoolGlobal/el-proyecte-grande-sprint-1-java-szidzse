package com.codecool.restmates.controller;

import com.codecool.restmates.model.Member;
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
    public Member getMember(@PathVariable("memberId") long memberId) {
        return memberService.getMemberById(memberId);
    }

    @PostMapping("/member")
    public Member createMember(@RequestBody Member member) {
        return memberService.createMember(member);
    }

}
