package com.hendisantika.repository;

import com.hendisantika.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-jwt3
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 7/9/22
 * Time: 12:09
 * To change this template use File | Settings | File Templates.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}
