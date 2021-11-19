package co.FindFoodApp.repositories;

import co.FindFoodApp.models.BeneficiarioModel;
import co.FindFoodApp.models.UsuarioModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiarioRepository extends MongoRepository<BeneficiarioModel,String> {
//    Pendiente definir metodos de consulta
}
