package com.example.infrastructure.entrypoint.controller.person;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.infrastructure.entrypoint.dto.person.CreatePersonRequest;
import com.example.infrastructure.entrypoint.dto.person.PersonResponse;
import com.example.infrastructure.mappers.PersonMapper;
import com.example.usecase.person.CreatePersonInteractor;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class CreatePersonController {

    private final CreatePersonInteractor usecase;
    private final PersonMapper mapper;

    @PostMapping
    public ResponseEntity<PersonResponse> createPerson(@RequestBody CreatePersonRequest personRequest) {
        var createdPerson = usecase.execute(mapper.toDomain(personRequest));
        return ResponseEntity.ok(mapper.toResponse(createdPerson));
    }

}
