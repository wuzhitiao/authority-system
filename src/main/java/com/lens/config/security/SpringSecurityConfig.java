package com.lens.config.security;


import com.lens.config.security.filter.CheckTokenFilter;
import com.lens.config.security.handler.AnonymousAuthenticationHandler;
import com.lens.config.security.handler.CustomerAccessDeniedHandler;
import com.lens.config.security.handler.LoginFailureHandler;
import com.lens.config.security.handler.LoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private  CustomerUserDetailService customerUserDetailService;


    @Resource
    private LoginFailureHandler loginFalureHandler;

    @Resource
    private LoginSuccessHandler loginSuccessHandler;


    @Resource
    private AnonymousAuthenticationHandler anonymousAuthenticationHandler;


    @Resource
    private CustomerAccessDeniedHandler customerAccessDeniedHandler;


    @Resource
    private CheckTokenFilter checkTokenFilter;
    /**
     * 注入加密处理类
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    /**
     * 处理登录认证处理器
     * @param http
     * @throws Exception
     */
    @Override
    protected  void   configure(HttpSecurity http) throws  Exception{

        //登录前进行过滤
        http.addFilterBefore(checkTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.formLogin().loginProcessingUrl("/api/user/login")//表单登录
                //设置登录验证成功或失败后的跳转地址
                .successHandler(loginSuccessHandler)  //认证成功处理器
                .failureHandler(loginFalureHandler)  //认证失败处理器
                //禁用csrf防御机制
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //不创建Session
                .and().authorizeRequests()  //设置需要拦截的请求
                .antMatchers("/api/user/login") //登录请求放行
                .permitAll().anyRequest().authenticated() //其他度不放行
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(anonymousAuthenticationHandler) //匿名无权限登录
                .accessDeniedHandler(customerAccessDeniedHandler)  //认证用户无权限登录
                .and().cors();  //开启跨域配置


    }

    /**
     * 配置认证处理器
     */
    @Override
    protected  void  configure(AuthenticationManagerBuilder auth) throws  Exception{
        auth.userDetailsService(customerUserDetailService).passwordEncoder(passwordEncoder());
    }







}
