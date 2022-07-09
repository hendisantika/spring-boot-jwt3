package com.hendisantika.config;

import com.hendisantika.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-jwt3
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 7/9/22
 * Time: 12:41
 * To change this template use File | Settings | File Templates.
 */
@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final JWTUtil util;
    private final UserDetailsService userDetailsService;
}
