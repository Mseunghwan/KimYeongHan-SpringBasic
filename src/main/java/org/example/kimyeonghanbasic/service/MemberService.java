package org.example.kimyeonghanbasic.service;

import org.example.kimyeonghanbasic.domain.Member;
import org.example.kimyeonghanbasic.repository.MemberRepository;
import org.example.kimyeonghanbasic.repository.MemoryMemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    // DI 측면에서 생성자 주입 방식을 사용. 유지보수성에서 좋다.
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    //    회원가입
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

//    전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

//    단일 회원 조회
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
