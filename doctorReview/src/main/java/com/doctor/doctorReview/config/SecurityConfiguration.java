package com.doctor.doctorReview.config;

import com.doctor.doctorReview.Misc.MyUserServiceDetails;
import com.doctor.doctorReview.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    private MyUserServiceDetails myUserServiceDetails;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//       auth.inMemoryAuthentication().withUser("vlad").password("ana").roles("USER");
        auth.userDetailsService(myUserServiceDetails)
        .passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                return bCryptPasswordEncoder.encode(charSequence.toString());
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                return bCryptPasswordEncoder.matches(charSequence,s);
            }
        });
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .anyRequest()
                .fullyAuthenticated()
                .and()
                .httpBasic();
        http.csrf().disable().logout().logoutUrl("/logout").logoutSuccessUrl("/user/registration");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // exclude registration endpoints from security (everyone can register)
        web.ignoring().mvcMatchers("/user/registration");
    }


}
