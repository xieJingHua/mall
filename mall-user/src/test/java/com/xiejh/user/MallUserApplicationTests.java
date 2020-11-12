package com.xiejh.user;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;
import org.bouncycastle.crypto.generators.BCrypt;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class MallUserApplicationTests {

    @Test
    void contextLoads() {

    }

    public static void main(String[] args) {
        String s = DigestUtils.md5Hex("123456");
        System.out.println(s);
        //md5加密，摘要加密，加盐
        Md5Crypt.md5Crypt("123456".getBytes());

        //spring加密
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode("123456");
        System.out.println(encode);
        System.out.println(encoder.matches("123456",encode));
    }
}
