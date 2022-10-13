package com.example.slaughterhouse;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
class AnimalController {
    private final AnimalRepository repository;
    private final AnimalModelAssembler assembler;

    AnimalController(AnimalRepository repository, AnimalModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    // Aggregate root
    // tag::get-aggregate-root[]

    // 7animals
    // /animals?origin=Horsens
    @GetMapping("/animals")
    CollectionModel<EntityModel<Animal>> getAnimals(@RequestParam(value = "origin", required = false) String origin, @RequestParam(value = "date", required = false) String date) {
        if(origin!=null) {
            return getByOrigin(origin);
        }
        else if(date != null){
            return getByDate(date);
        }
        else return all();
    }

    CollectionModel<EntityModel<Animal>> all(){
        List<EntityModel<Animal>> animals = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(animals, linkTo(methodOn(AnimalController.class).all()).withSelfRel());
    }

    @GetMapping("/animals/{registrationNumber}")
    EntityModel<Animal> one(@PathVariable Long registrationNumber) {

        Animal animal = repository.findById(registrationNumber) //
                .orElseThrow(() -> new AnimalNotFoundException(registrationNumber));

        return assembler.toModel(animal);
    }


    @PostMapping("/animals")
    ResponseEntity<?> newAnimal(@RequestBody Animal newAnimal){
        EntityModel<Animal> entityModel = assembler.toModel(repository.save(newAnimal));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())//
                .body(entityModel);
    }

    CollectionModel<EntityModel<Animal>> getByOrigin(String origin) {
        List<EntityModel<Animal>> animals = repository.findAll().stream()
                .map(assembler::toModel)
                .filter(animal -> animal.getContent().getOrigin().equals(origin))
                .collect(Collectors.toList());

        return CollectionModel.of(animals, linkTo(methodOn(getClass()).getByOrigin(origin)).withSelfRel());
    }


    CollectionModel<EntityModel<Animal>> getByDate(String date) {
        List<EntityModel<Animal>> animals = repository.findAll().stream()
                .map(assembler::toModel)
                .filter(animal -> animal.getContent().getDate().equals(date))
                .collect(Collectors.toList());

        return CollectionModel.of(animals, linkTo(methodOn(getClass()).getByDate(date)).withSelfRel());
    }
}


