package com.xinshuo.treehole.config;

import com.xinshuo.treehole.filter.JWTFilter;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import javax.swing.text.DefaultEditorKit;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //创建ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(securityManager);

        //添加自己的过滤器JWTFilter
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("jwt", new JWTFilter());
        bean.setFilters(filterMap);


        //添加shiro的内置过滤器
        /*
         * anon:无需认证就可以访问
         * authc:必须认证了才能访问
         * user:必须拥有 记住我 功能才能访问
         * perms:拥有对某个资源的权限才能访问
         * role:拥有某个角色权限才能访问
         */

        Map<String,String> map = new LinkedHashMap<String,String>();
        map.put("/getUsers", "jwt");
        map.put("/getQuestions", "jwt");
        map.put("/**", "jwt");

//        map.put("/noLogin", "anon");

        //logout退出登录过滤器 map.put("/user/logout","logout");
//

        bean.setFilterChainDefinitionMap(map);

        bean.setLoginUrl("/noLogin");

         return bean;
    }

    //创建DefaultWebSecurityManager
    @Bean
    public DefaultWebSecurityManager securityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        //关闭session
        //==========================
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        //============================
        return securityManager;
    }

    //解决shiro重定向到未登录页时拼接上“；”和jsessionID
//    @Bean("sessionManager")
//    public DefaultWebSessionManager webSessionManager() {
//        DefaultWebSessionManager webSessionManager = new DefaultWebSessionManager();
//        webSessionManager.setSessionIdUrlRewritingEnabled(false);
//        return webSessionManager;
//    }

    //创建Realm对象
    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }


    /**
     * 下面的代码是添加注解支持
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        // 强制使用cglib，防止重复代理和可能引起代理出错的问题
        // https://zhuanlan.zhihu.com/p/29161098
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
