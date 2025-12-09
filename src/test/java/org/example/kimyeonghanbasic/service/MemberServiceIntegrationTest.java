package org.example.kimyeonghanbasic.service;

import org.example.kimyeonghanbasic.domain.Member;
import org.example.kimyeonghanbasic.repository.MemberRepository;
import org.example.kimyeonghanbasic.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

// 스프링 컨테이너를 활용한 테스트 - 통합 테스트

@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {

    // 직접 생성 안하고,
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    // Transactional 어노테이션이면 BeforeEach, AfterEach로 초기화 할 필요 X
    // 테스트 전 트랜잭션을 시작하고, 테스트 완료 후 항상 롤백해주기에
    // 테스트 이후에 DB에 반영 안되어 초기화 가능
    // 기존 Java로만 하는 테스트는 필요없어지나? => 그건 아님, 테스트 시간이 ms 로 끝나기에, 스프링 뜨는걸 기다릴 필요가 없음
    // 순수한 단위테스트 방식이 좋은 테스트일 확률이 높다!
    // 스프링 컨테이너 없이 테스트 하도록 연습하는게 좋다,

    @Test
    void 회원가입(){
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long savedId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(savedId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복_회원_예외(){
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

}
