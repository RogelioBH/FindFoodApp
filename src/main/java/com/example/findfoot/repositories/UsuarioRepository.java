package com.example.findfoot.repositories;

import com.example.findfoot.models.UsuarioModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository


public interface UsuarioRepository extends CrudRepository<UsuarioModel, Long>{
    
}
