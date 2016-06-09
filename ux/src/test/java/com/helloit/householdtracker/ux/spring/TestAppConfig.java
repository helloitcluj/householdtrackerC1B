package com.helloit.householdtracker.ux.spring;

import com.helloit.householdtracker.ux.common.repository.IUserRepository;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan
@Import(PersistenceConfig.class)
@PropertySource("classpath:testapp.properties")
public class TestAppConfig {
/*
    @Bean
    public PropertySourcesPlaceholderConfigurer propertyConfigIn() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public IAccountService register(final IUserRepository userRepository) {
        return new AccountService(userRepository);
    }
*/
}