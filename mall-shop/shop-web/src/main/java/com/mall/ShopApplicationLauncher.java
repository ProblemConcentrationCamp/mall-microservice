package com.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <pre>
 * Shop Module Launcher  TODO
 * </pre>
 *
 * @author LCN
 * @date 2019-12-10 17:43
 */
@SpringBootApplication
@MapperScan("com.mall.mapper")
public class ShopApplicationLauncher {

    public static void main(String[] args) {
        SpringApplication.run(ShopApplicationLauncher.class, args);
    }
}
