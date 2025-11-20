package com.example.infrastructure.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.application.gateway.person.CreatePersonGateway;
import com.example.application.gateway.person.FindByNameGateway;
import com.example.application.usecaseimpl.person.CreatePersonInteractorImpl;
import com.example.application.usecaseimpl.person.FindByNameInteractorImpl;
import com.example.usecase.person.CreatePersonInteractor;
import com.example.usecase.person.FindByNameInteractor;

@Configuration
public class PersonConfig {

    @Bean
    FindByNameInteractor findByNameInteractor(FindByNameGateway findByNameGateway) {
        return new FindByNameInteractorImpl(findByNameGateway);
    }

    @Bean
    public CreatePersonInteractor createPersonInteractor(
            CreatePersonGateway createPersonGateway,
            FindByNameInteractor findByNameInteractor) {
        return new CreatePersonInteractorImpl(createPersonGateway, findByNameInteractor);
    }
}
