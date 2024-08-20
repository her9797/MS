package com.ms.back.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    /** DTO <-> ENTITY 간 데이터 전환을 쉽게 하기 위함 : 추후 Builder 패턴 혼용 예정 */
    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setFieldMatchingEnabled(true);
        modelMapper.getConfiguration()
                .setAmbiguityIgnored(true);

        return modelMapper;
    }

}
