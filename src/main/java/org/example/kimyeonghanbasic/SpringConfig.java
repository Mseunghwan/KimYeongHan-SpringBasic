package org.example.kimyeonghanbasic;

import org.example.kimyeonghanbasic.repository.JdbcMemberRepository;
import org.example.kimyeonghanbasic.repository.MemberRepository;
import org.example.kimyeonghanbasic.repository.MemoryMemberRepository;
import org.example.kimyeonghanbasic.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);
        // 생성자 주입의 장점, JdbcMemberRepository 구현체 만들어서, Config 파일만 고쳐도 O
        // 다형성을 활용한다, 즉 인터페이스를 두고 구현체를 갈아낀다
        // 스프링 컨테이너가 DI를 도와주는 덕분
        // 이런게 없으면 Member Service가 바뀌고,, 연쇄적으로 수정을 해야했으나 그러지 않아도 O
    }


}
