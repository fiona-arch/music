package cn.tedu.music.config;

import cn.tedu.music.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;
@Configuration
public class LoginInterceptorConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //创建拦截器对象
        HandlerInterceptor loginInterceptor=new LoginInterceptor();
        //注册拦截器
        List<String> encludepaths=new ArrayList<>();
        encludepaths.add("/web/register.html");
        encludepaths.add("/web/login.html");
        encludepaths.add("/users/reg");
        encludepaths.add("/users/login");
        encludepaths.add("/bootstrap3/**");
        encludepaths.add("/css/**");
        encludepaths.add("/images/**");
        encludepaths.add("/js/**");
        encludepaths.add("/districts/");
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns(encludepaths);


    }
}
