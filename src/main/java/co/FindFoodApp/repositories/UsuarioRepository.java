package co.FindFoodApp.repositories;

import co.FindFoodApp.models.UsuarioModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends MongoRepository<UsuarioModel,String> {
    Optional<UsuarioModel> findByUsername(String username);
    Optional<UsuarioModel> findById(String id);
}
