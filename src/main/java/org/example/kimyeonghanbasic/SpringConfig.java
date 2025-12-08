package org.example.kimyeonghanbasic;

import org.example.kimyeonghanbasic.repository.MemberRepository;
import org.example.kimyeonghanbasic.repository.MemoryMemberRepository;
import org.example.kimyeonghanbasic.service.MemberService;
import org.springframework.context.annotation.Bean;

public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

}
