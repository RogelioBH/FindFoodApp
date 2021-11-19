package co.FindFoodApp.repositories;

import co.FindFoodApp.models.DonacionModel;
import co.FindFoodApp.models.UsuarioModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonacionRepository extends MongoRepository<DonacionModel,String> {
//    Pendiente definir metodos de consulta
}
