package com.mikaila.demospringguidecreatingapi.repository;

import com.mikaila.demospringguidecreatingapi.domain.Hero;
import org.springframework.data.repository.CrudRepository;

public interface HeroRepository extends CrudRepository<Hero,Long>{

}
