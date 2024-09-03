package com.codecool.restmates.service;

import com.codecool.restmates.model.dto.requests.member.IDMemberDTOResponse;
import com.codecool.restmates.model.dto.requests.member.NewMemberDTO;
import com.codecool.restmates.model.dto.responses.AccommodationDTO;
import com.codecool.restmates.model.dto.responses.LocationCityAndCountryDTO;
import com.codecool.restmates.model.dto.responses.MemberResponseDTO;
import com.codecool.restmates.exception.EmailAlreadyExistsException;
import com.codecool.restmates.exception.ResourceNotFoundException;
import com.codecool.restmates.model.entity.Accommodation;
import com.codecool.restmates.model.entity.Member;
import com.codecool.restmates.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberResponseDTO getMemberById(String memberEmail) {
        Optional<Member> member = memberRepository.findByEmail(memberEmail);

        if(member.isPresent()) {
            Member memberEntity = member.get();
            List<AccommodationDTO> memberAccommodations =  memberEntity.getAccommodations()
                    .stream()
                    .map(accommodation -> new AccommodationDTO(accommodation.getName(),
                            accommodation.getDescription(),
                            accommodation.getRoomNumber(),
                            accommodation.getPricePerNight(),
                            accommodation.getMaxGuests(),
                            new LocationCityAndCountryDTO(accommodation.getLocation().getCity(), accommodation.getLocation().getCountry())))
                    .toList();

            return new MemberResponseDTO(memberEntity.getFirstName(),
                    memberEntity.getLastName(), memberEntity.getEmail(), memberEntity.getPhoneNumber(), memberAccommodations);
        } else {
            throw new ResourceNotFoundException(String.format("Member with id %s not found", memberEmail));
        }
    }

    public Long saveMember(NewMemberDTO memberDTO) {
        if (memberRepository.existsByEmail(memberDTO.email())) {
            throw new EmailAlreadyExistsException(String.format(" %s already exists.", memberDTO.email()));
        }
        Member memberEntity = new Member(memberDTO.firstName(),memberDTO.lastName(), memberDTO.email(), memberDTO.phoneNumber(), memberDTO.password());
        memberRepository.save(memberEntity);
        return memberEntity.getId();
    }

    public Long updateMember(Long memberId, NewMemberDTO updatedMember) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("%s does not exist.", memberId)));

        member.setFirstName(updatedMember.firstName());
        member.setLastName(updatedMember.lastName());
        if (existsEmail(member.getEmail())) {
            member.setEmail(updatedMember.email());
        }
         memberRepository.save(member);
        return member.getId();
    }

    public Boolean deleteMember(Long memberId) {
        if (memberRepository.existsById(memberId)) {
            memberRepository.deleteById(memberId);
            return true;
        }
        throw new ResourceNotFoundException(String.format(" %s does not exist.", memberId));
    }

    private Boolean existsEmail(String email) {
        return memberRepository.existsByEmail(email);
    };

    public IDMemberDTOResponse authenticateLogin(String email, String password) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member != null && member.get().getPassword().equals(password)) {
            return new IDMemberDTOResponse(member.get().getId());
        } else {
            throw new ResourceNotFoundException("Email or password is incorrect.");
        }
    }
}
