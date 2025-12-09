package org.example.kimyeonghanbasic.repository;

import org.example.kimyeonghanbasic.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // 인터페이스 이름 만으로도 가능
    // JPQL select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
