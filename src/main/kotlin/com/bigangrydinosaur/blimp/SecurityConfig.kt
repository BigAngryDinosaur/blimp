package com.bigangrydinosaur.blimp

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.web.servlet.invoke

@Configuration
class SecurityConfig: WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        if (http == null) return
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/u/**").permitAll()
                .anyRequest().authenticated()
    }
}