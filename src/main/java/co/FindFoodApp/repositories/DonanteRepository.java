package co.FindFoodApp.repositories;

import co.FindFoodApp.models.DonanteModel;
import co.FindFoodApp.models.UsuarioModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonanteRepository extends MongoRepository<DonanteModel,String> {
//    Pendiente definir metodos de consulta
}
