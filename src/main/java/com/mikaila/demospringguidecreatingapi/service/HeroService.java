package com.mikaila.demospringguidecreatingapi.service;

import com.mikaila.demospringguidecreatingapi.domain.Hero;
import com.mikaila.demospringguidecreatingapi.exception.ResourceNotFoundException;
import com.mikaila.demospringguidecreatingapi.repository.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HeroService {

        @Autowired
        private HeroRepository heroRepository;

        public List<Hero> getAllHeroes(){
            List<Hero> heroes = new ArrayList<>();
            heroRepository.findAll().forEach(heroes::add);
            return heroes;
        }

        public void addHero(Hero hero){
            heroRepository.save(hero);
        }


        public Hero getHero(Long id) {
            Optional<Hero> heroOptional = heroRepository.findById(id);
            if(!heroOptional.isPresent()){
                throw new ResourceNotFoundException();
            }
            return heroOptional.get();
        }


        public void updateHero(Long id, Hero hero){
            heroRepository.save(hero);
        }

        public void deleteHero(Long id){
            heroRepository.deleteById(id);

        }















/*
    public Set<String> allSpecialPowers(){
        Set<String> powers = new HashSet<>();
        heroRepository.findAll().forEach(();
        return powers;
    }

*/

}




















/*
//            for(int x = 0; x < heroes.size(); x++){
//                Hero hero = heroes.get(x);
//                if(hero.getId().equals(id)){
//                    return hero;
//                }
//            }
//            return null;

  //      }
 */
