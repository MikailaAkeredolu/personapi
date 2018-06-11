package com.mikaila.demospringguidecreatingapi.controller;

import com.mikaila.demospringguidecreatingapi.domain.Hero;
import com.mikaila.demospringguidecreatingapi.exception.ResourceNotFoundException;
import com.mikaila.demospringguidecreatingapi.service.HeroService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class HeroController {

    public static final Logger logger = LoggerFactory.getLogger(HeroController.class);

    @Autowired
    private HeroService heroService;

    protected void verifyID(Long id) throws ResourceNotFoundException {
        Hero hero = heroService.getHero(id);
        if(hero == null) {
            throw new ResourceNotFoundException("Hero with id " + id + " not found");
        }
    }

    @RequestMapping(value = "/heroes", method = RequestMethod.GET)
    public ResponseEntity<List<Hero>> getAllHeroes(){
        heroService.getAllHeroes();
        logger.info("Fetching all heroes",heroService.getAllHeroes());
        return new ResponseEntity<>(heroService.getAllHeroes(), HttpStatus.OK);
    }

    @RequestMapping(value = "/heroes/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getHero(@PathVariable Long id){
            verifyID(id);
        logger.info("Fetching User with id {}", id);
        return new ResponseEntity<>(heroService.getHero(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/heroes", method = RequestMethod.POST)
    public ResponseEntity<?> addHero(@RequestBody Hero hero){
        logger.info("Posted a new hero {}", hero);
        heroService.addHero(hero);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newHeroUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(hero.getId()).toUri();
        responseHeaders.setLocation(newHeroUri);
        return new ResponseEntity<>(hero,responseHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/heroes/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?>updateHero(@RequestBody Hero hero, @PathVariable Long id){
        logger.info("Updating User with id {}", id);
        verifyID(id);
        heroService.updateHero(id, hero);
        if(id == null){
            logger.info("unable to update User with id {}", id);
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newHeroUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(hero.getId()).toUri();
        responseHeaders.setLocation(newHeroUri);
        return new ResponseEntity<>(hero,responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/heroes/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteHero( @PathVariable Long id){
        verifyID(id);
        heroService.deleteHero(id);
        logger.info("Deleted user with ID: "+ id);
        return new ResponseEntity<>("Hero with id "+ id + " deleted successfully!!!", HttpStatus.OK);
    }











/*
    //Special powers
    @RequestMapping(value = "/powers", method = RequestMethod.GET)
    public ResponseEntity<Set<String>> getAllPowers(){
        heroService.allSpecialPowers();
        logger.info("Fetching all special powers",heroService.allSpecialPowers());
        return new ResponseEntity<>(heroService.allSpecialPowers(), HttpStatus.OK);
    }
*/



}
