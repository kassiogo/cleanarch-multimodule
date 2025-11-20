package com.example.infrastructure.entrypoint.controller.person;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.application.gateway.person.CreatePersonGateway;
import com.example.infrastructure.entrypoint.dto.person.CreatePersonRequest;
import com.example.infrastructure.entrypoint.dto.person.PersonResponse;
import com.example.infrastructure.mappers.PersonMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class CreatePersonController {

    private final CreatePersonGateway createPersonGateway;
    private final PersonMapper personMapper;

    @PostMapping
    public ResponseEntity<PersonResponse> createPerson(@RequestBody CreatePersonRequest personRequest) {
        var person = personMapper.toDomain(personRequest);
        var createdPerson = createPersonGateway.createPerson(person);
        return ResponseEntity.ok(personMapper.toResponse(createdPerson));
    }

}
