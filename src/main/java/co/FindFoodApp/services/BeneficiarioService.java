package co.FindFoodApp.services;

import co.FindFoodApp.models.BeneficiarioModel;
import co.FindFoodApp.models.DonanteModel;
import co.FindFoodApp.models.UsuarioModel;
import co.FindFoodApp.repositories.BeneficiarioRepository;
import co.FindFoodApp.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeneficiarioService {

    @Autowired
    BeneficiarioRepository beneficiarioRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    /**
     * Metodo que permite listar todos los beneficiarios creados en el sistema
     * @return Lista de beneficiarios.
     */
    public List<BeneficiarioModel> listar(){
        return this.beneficiarioRepository.findAll();
    }

    /**
     * Metodo que permite crear un usuario de tipo beneficiario.
     * @param usuarioModel Objeto UsuarioModel
     */
    public void crear(UsuarioModel usuarioModel){
        BeneficiarioModel beneficiario = new BeneficiarioModel();

        beneficiario.setNombre(usuarioModel.getNombre());
        beneficiario.setCorreo(usuarioModel.getCorreo());
        beneficiario.setTelefono(usuarioModel.getTelefono());
        beneficiario.setUsuario(usuarioModel.getUsername());
        beneficiario.setRol(usuarioModel.getRol());

        this.beneficiarioRepository.save(beneficiario);
    }

    /**
     * Metodo que permite actualizar los datos de un beneficiario
     * @param usuarioModel Objeto UsuarioModel
     */
    public void actualizar(UsuarioModel usuarioModel){

        BeneficiarioModel beneficiario = this.beneficiarioRepository.findByUsuario(usuarioModel.getUsername());
        beneficiario.setId(beneficiario.getId());
        beneficiario.setNombre(usuarioModel.getNombre());
        beneficiario.setCorreo(usuarioModel.getCorreo());
        beneficiario.setTelefono(usuarioModel.getTelefono());
        beneficiario.setUsuario(beneficiario.getUsuario());
        beneficiario.setRol(beneficiario.getRol());

        this.beneficiarioRepository.save(beneficiario);
    }

    /**
     * Metodo que permite eliminar un beneficiario
     * @param usuarioModel Objeto UsuarioModel
     */
    public void borrar(UsuarioModel usuarioModel){
        BeneficiarioModel beneficiario = this.beneficiarioRepository.findByUsuario(usuarioModel.getUsername());
        this.beneficiarioRepository.delete(beneficiario);
    }
}
