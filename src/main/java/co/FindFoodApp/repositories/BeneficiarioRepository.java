package co.FindFoodApp.repositories;

import co.FindFoodApp.models.BeneficiarioModel;
import co.FindFoodApp.models.DonanteModel;
import co.FindFoodApp.models.UsuarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeneficiarioRepository extends MongoRepository<BeneficiarioModel,String> {

    BeneficiarioModel findByUsuario(String usuario);
}
