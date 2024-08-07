package com.codecool.restmates.controller;

import com.codecool.restmates.dto.requests.NewMemberDTO;
import com.codecool.restmates.dto.responses.MemberResponseDTO;
import com.codecool.restmates.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
    public Long createMember(@RequestBody NewMemberDTO memberDTO) {
        return memberService.saveMember(memberDTO);
    }

    @PutMapping(path ="/{memberId}")
    public Long updateMember(@PathVariable long memberId, @RequestBody NewMemberDTO member) {
        return memberService.updateMember(memberId, member);
    }

    @DeleteMapping(path ="/{memberId}")
    public boolean deleteMember(@PathVariable long memberId) {
        return memberService.deleteMember(memberId);
    }
}
