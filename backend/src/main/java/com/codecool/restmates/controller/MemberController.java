package com.codecool.restmates.controller;

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
    public ResponseEntity getMemberById(@PathVariable long memberId) {
        Optional<Member> member = memberService.getMemberById(memberId);

        return member.map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Member with id %s not found", memberId)));
    }

    @PostMapping(path = "")
    public ResponseEntity createMember(@RequestBody Member member) {
        if (memberService.existsEmail(member.getEmail())) {
            throw new EmailAlreadyExistsException(String.format("Email %s already exists", member.getEmail()));
        }
        Member newMember = memberService.saveMember(member);
        return ResponseEntity.ok(newMember);
    }

    @PutMapping(path ="/{memberId}/update")
    public ResponseEntity updateMember(@PathVariable long memberId, @RequestBody Member member) {
        if (memberService.existsEmail(member.getEmail())) {
            throw new EmailAlreadyExistsException(String.format("Email %s already exists", member.getEmail()));
        }
        Member updatedMember = memberService.updateMember(memberId, member);
        return ResponseEntity.ok(updatedMember);
    }

    @DeleteMapping(path ="/{memberId}/delete")
    public ResponseEntity deleteMember(@PathVariable long memberId) {
        boolean isDeleted = memberService.deleteMember(memberId);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        }
        throw new ResourceNotFoundException(String.format("Member with id %s not found", memberId));
    }
}
