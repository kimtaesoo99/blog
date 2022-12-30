package com.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration      // 빈 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것 (IoC 관리)
@EnableWebSecurity  // 시큐리티 필터 추가 = 스프링 시큐리티가 활성화 되어 있는데, 어떤 설정을 해당 파일에서 하겠다라는 뜻
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다라는 뜻
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // csrf토큰 비활성화 (테스트시에 걸어두는게 좋음)
            .authorizeRequests()
            .antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**")
            .permitAll()    // 위에 접근은 누구나 허용
            .anyRequest()   // 위에가 아닌 모든 요청은 아래로
            .authenticated()
            .and()
            .formLogin()
            .loginPage("/auth/loginForm")
            .loginProcessingUrl("/auth/loginProc")  // 스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인해줌.
            .defaultSuccessUrl("/"); // 로그인 시 이 url로 이동
    }
}
