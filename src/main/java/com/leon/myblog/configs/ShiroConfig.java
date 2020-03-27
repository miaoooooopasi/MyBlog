package com.leon.myblog.configs;

import com.leon.myblog.filters.shiroFilters.ShiroFormAuthenticationFilter;
import com.leon.myblog.filters.shiroFilters.ShiroLogoutFilter;
import com.leon.myblog.filters.shiroFilters.ShiroPermissionsAuthorizationFilter;
import com.leon.myblog.filters.shiroFilters.ShiroRolesAuthorizationFilter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ author ：leon
 * @ date ：Created in 2019-11-21 11:48
 * @ description：shiro配置文件
 * @ modified By：
 * @ version: 1.0
 */

@Configuration
public class ShiroConfig {


    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 重要的地方：使用自定义的过滤器
        LinkedHashMap<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("authc", new ShiroFormAuthenticationFilter());
        filterMap.put("roles",new ShiroRolesAuthorizationFilter());
        filterMap.put("perms",new ShiroPermissionsAuthorizationFilter());
        filterMap.put("logout",new ShiroLogoutFilter());
        // 添加过滤器
        shiroFilterFactoryBean.setFilters(filterMap);

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/login","anon");
        filterChainDefinitionMap.put("/front","anon");
        filterChainDefinitionMap.put("/front/**", "anon");
        filterChainDefinitionMap.put("/logout","logout");

        filterChainDefinitionMap.put("/admin/**", "roles[admin]");
        filterChainDefinitionMap.put("/user/**", "authc");
        //主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截 剩余的都需要认证
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    /**
     * 凭证匹配器（由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了）
     * create by: leon
     * create time: 2019/7/3 14:30
     *
     * @return hashedCredentialsMatcher
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//md5散列算法
        hashedCredentialsMatcher.setHashIterations(1);//散列次数
        return hashedCredentialsMatcher;
    }

    /**
     * 将自己的验证方式加入容器
     * create by: leon
     * create time: 2019/7/3 14:30
     *
     * @return customRealm
     */
    @Bean
    public CustomRealm customRealm() {
        CustomRealm customRealm = new CustomRealm();
        customRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return customRealm;
    }


    /**
     * 自定义sessionManager，用户的唯一标识，即Token或Authorization的认证
     */
    @Bean
    public SessionManager sessionManager() {
        MySessionManager mySessionManager;
        mySessionManager = new MySessionManager();
        mySessionManager.setSessionDAO(redisSessionDAO());
        return mySessionManager;
    }

    /**
     * create by: leon
     * description: 权限管理，配置主要是Realm的管理认证
     * create time: 2019/7/1 10:09
     *
     * @return SecurityManager
     */
    @Bean
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager  defaultSecurityManager=new DefaultWebSecurityManager ();
        defaultSecurityManager.setRealm(customRealm());
        // 自定义session管理 使用redis
        defaultSecurityManager.setSessionManager(sessionManager());
        // 自定义缓存实现 使用redis
        defaultSecurityManager.setCacheManager(cacheManager());
        ThreadContext.bind(defaultSecurityManager);
        return defaultSecurityManager;
    }

    /**
     * 配置shiro redisManager, 使用的是shiro-redis开源插件
     * <br/>
     * create by: leigq
     * <br/>
     * create time: 2019/7/3 14:33
     *
     * @return RedisManager
     */
    private RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost("127.0.0.1:6379");
        //redisManager.setTimeout((int) timeout.toMillis());
        //redisManager.setPassword("123456");
        return redisManager;
    }

    /**
     * cacheManager 缓存 redis实现, 使用的是shiro-redis开源插件
     * <br/>
     * create by: leon
     * <br/>
     * create time: 2019/7/3 14:33
     *
     * @return RedisCacheManager
     */
    @Bean
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        // 必须要设置主键名称，shiro-redis 插件用过这个缓存用户信息
        //redisCacheManager.setPrincipalIdFieldName("username");
        redisCacheManager.setExpire(1800*4);
        return redisCacheManager;
    }

    /**
     * Session ID 生成器
     * <br/>
     * create by: leon
     * <br/>
     * create time: 2019/7/3 16:08
     *
     * @return JavaUuidSessionIdGenerator
     */
    @Bean
    public JavaUuidSessionIdGenerator sessionIdGenerator() {
        return new JavaUuidSessionIdGenerator();
    }



    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis, 使用的是shiro-redis开源插件
     * create by: leon
     * create time: 2019/7/3 14:30
     *
     * @return RedisSessionDAO
     */
    @Bean
    public RedisSessionDAO redisSessionDAO(){
        RedisSessionDAO redisSessionDAO=new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        redisSessionDAO.setSessionIdGenerator(sessionIdGenerator());
        return redisSessionDAO;
    }



    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * *
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * *
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * * @return
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public MethodInvokingFactoryBean methodInvokingFactoryBean() {
        MethodInvokingFactoryBean bean = new MethodInvokingFactoryBean();
        bean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        bean.setArguments(securityManager());
        return bean;
    }



}
