package org.example.expert.config;

import lombok.RequiredArgsConstructor;
import org.example.expert.details.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean // 인가 처리 하는 시큐리티 필터 체인
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        //csrf 사용 안함 이거 사용하면 csrf 토큰이 필요함
        http
                .csrf((csrf) -> csrf.disable());


        // form 로그인 사용 안함
        http
                .formLogin((form) -> form
                        .loginProcessingUrl("/auth/signin")
                        .defaultSuccessUrl("/")
                        .failureUrl("/auth/signin?error=true")
                        .permitAll()
                );

        //httpBasic 사용 안함
        http
                .httpBasic((httpBasic) -> httpBasic.disable());


        // 권한에 맞는 인가 작업
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                );



        //jwt를 사용을 하기 때문에 세션을 stateless 상태로 만듦
        http
                .sessionManagement((sessoin) -> sessoin.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http
                .addFilterBefore(new JwtFilter(jwtUtil, userDetailsService), UsernamePasswordAuthenticationFilter.class);



        return http.build();
    }
}
