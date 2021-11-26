package co.FindFoodApp.services;

import co.FindFoodApp.models.DonacionModel;
import co.FindFoodApp.models.DonanteModel;
import co.FindFoodApp.models.UsuarioModel;
import co.FindFoodApp.repositories.DonacionRepository;
import co.FindFoodApp.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonacionService {

    @Autowired
    DonacionRepository donacionRepository;

    @Autowired
    DonanteService donanteService;

    @Autowired
    BeneficiarioService beneficiarioService;

    /**
     * Metodo que permite listar todos los donaciones creados en el sistema
     * @return Lista de donaciones.
     */
    public List<DonacionModel> listar(){
        return this.donacionRepository.findAll();
    }

    /**
     * Metodo que permite obtener una donación mediante su ID
     * @param donacion Objeto Donación a buscar
     * @return Objeto DonacionModel
     */
    public DonacionModel buscar(DonacionModel donacion){
        return this.donacionRepository.findById(donacion.getId()).get();
    }


    /**
     * Metodo que permite crear una donación.
     * @param donacion Objeto DonacionModel
     */
    public void crear(DonacionModel donacion){
        this.donacionRepository.save(donacion);
    }

    /**
     * Metodo que permite actualizar los datos de una donacion
     * @param donacion Objeto DonacionModel
     */
    public void actualizar(DonacionModel donacion){
        this.donacionRepository.save(donacion);
    }

    /**
     * Metodo que permite eliminar una donacion
     * @param donacion Objeto DonacionModel
     */
    public void borrar(DonacionModel donacion){
        this.donacionRepository.delete(donacion);
    }
}
