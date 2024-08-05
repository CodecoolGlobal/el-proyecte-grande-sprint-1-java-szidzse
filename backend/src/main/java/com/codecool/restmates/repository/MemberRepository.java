package com.codecool.restmates.repository;

import com.codecool.restmates.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findById(long Id);
    Member save(Member member);
    Boolean deleteByPassword(String password);
    Boolean existsByEmail(String email);
}
