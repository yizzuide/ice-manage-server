package com.yizzuide.ice.api;

import com.github.yizzuide.milkomeda.comet.core.EnableComet;
import com.github.yizzuide.milkomeda.crust.EnableCrust;
import com.github.yizzuide.milkomeda.hydrogen.core.EnableHydrogen;
import com.github.yizzuide.milkomeda.ice.EnableIce;
import com.github.yizzuide.milkomeda.light.EnableLight;
import com.github.yizzuide.milkomeda.pulsar.EnablePulsar;
import com.github.yizzuide.milkomeda.sirius.EnableSirius;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * IceApplication
 *
 * @author yizzuide
 * <br />
 * Create at 2022/10/07 02:28
 */
@EnableSirius
@EnableIce
@EnableCrust
@EnableHydrogen
@EnablePulsar
@EnableComet
@MapperScan("com.yizzuide.ice.api.mapper")
@SpringBootApplication
public class IceApplication {
    public static void main(String[] args) {
        SpringApplication.run(IceApplication.class, args);
    }
}
