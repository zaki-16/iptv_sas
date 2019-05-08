package com.hgys.iptv;

import com.hgys.iptv.common.VerifyServlet;
import com.hgys.iptv.model.Cp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;

@SpringBootApplication
        (exclude={
        //org.activiti.spring.boot.SecurityAutoConfiguration.class,
        SecurityAutoConfiguration.class,
        org.activiti.runtime.api.conf.TaskRuntimeAutoConfiguration.class
})
public class IptvSasApplication {
    public static void main(String[] args) {
        SpringApplication.run(IptvSasApplication.class, args);
    }
    /**
     * 注入验证码servlet
     */
    //@Bean
    public ServletRegistrationBean indexServletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new VerifyServlet());
        registration.addUrlMappings("/getVerifyCode");
        return registration;
    }
}
