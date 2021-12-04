package co.FindFoodApp.services;

import co.FindFoodApp.enums.EstadoDonacion;
import co.FindFoodApp.models.DonacionModel;
import co.FindFoodApp.models.DonanteModel;
import co.FindFoodApp.models.UsuarioModel;
import co.FindFoodApp.repositories.DonacionRepository;
import co.FindFoodApp.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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
        return this.donacionRepository.findById(donacion.getId()).orElse(new DonacionModel());
    }

    /**
     * Metodo que permite listar la donaciones existente por su estado.
     * @param estado Estado de la donación.
     * @return Lista de donaciones.
     */
    public List<DonacionModel> listarEstado(String estado){
        if (estado != null){
            return this.donacionRepository.findAllByEstado(estado);
        }else{
            return null;
        }
    }


    /**
     * Metodo que permite crear una donación.
     * @param donacion Objeto DonacionModel
     */
    public void crear(DonacionModel donacion){
        donacion.setFecha(new Date().toString());
        donacion.setBeneficiario(null);
        donacion.setEstado(EstadoDonacion.DISPONIBLE.getNombre());
        this.donacionRepository.save(donacion);
    }

    /**
     * Metodo que permite actualizar los datos de una donacion
     * @param donacion Objeto DonacionModel
     */
    public void actualizar(DonacionModel donacion){
        DonacionModel donacionSave = this.buscar(donacion);
        donacion.setDonante(donacionSave.getDonante());
        donacion.setBeneficiario(donacionSave.getBeneficiario());
        donacion.setFecha(donacionSave.getFecha());
        this.donacionRepository.save(donacion);
    }

    /**
     * Metodo que permite eliminar una donacion
     * @param donacion Objeto DonacionModel
     */
    public void borrar(DonacionModel donacion){
        DonacionModel donacionModel = this.buscar(donacion);
        if(donacionModel.getEstado().equals(EstadoDonacion.SELECCIONADA.getNombre())){
            donacion.setEstado(EstadoDonacion.CANCELADA.getNombre());
        }
        this.donacionRepository.delete(donacion);
    }

    /**
     * Metodo que permite a un usuario beneficiario seleccionar una donación disponible
     * @param donacion Objeto Donacion que incluye el beneficiario
     */
    public void seleccionarDonacion(DonacionModel donacion){
        DonacionModel donacionModel = this.buscar(donacion);
        if (donacionModel.getEstado().equals(EstadoDonacion.DISPONIBLE.getNombre())){
            donacionModel.setEstado(EstadoDonacion.SELECCIONADA.getNombre());
            donacionModel.setBeneficiario(donacion.getBeneficiario());
            this.donacionRepository.save(donacionModel);
        }
    }

    /**
     * Metodo que permite a un usuario beneficiario finalizar una donación seleccionada
     * @param donacion Objeto Donacion que incluye el beneficiario
     */
    public void finalizarDonacion(DonacionModel donacion){
        DonacionModel donacionModel = this.buscar(donacion);
        if (donacionModel.getEstado().equals(EstadoDonacion.SELECCIONADA.getNombre())){
            donacionModel.setEstado(EstadoDonacion.FINALIZADA.getNombre());
            this.donacionRepository.save(donacionModel);
        }
    }

}
