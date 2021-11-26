package co.FindFoodApp.services;

import co.FindFoodApp.models.DonanteModel;
import co.FindFoodApp.models.UsuarioModel;
import co.FindFoodApp.repositories.DonanteRepository;
import co.FindFoodApp.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonanteService {

    @Autowired
    DonanteRepository donanteRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    /**
     * Metodo que permite listar todos los donantes creados en el sistema
     * @return Lista de donates.
     */
    public List<DonanteModel> listar(){
        return this.donanteRepository.findAll();
    }

    /**
     * Metodo que permite crear un usuario de tipo donante.
     * @param usuarioModel Objeto UsuarioModel
     */
    public void crear(UsuarioModel usuarioModel){
        DonanteModel donante = new DonanteModel();

        donante.setNombre(usuarioModel.getNombre());
        donante.setCorreo(usuarioModel.getCorreo());
        donante.setTelefono(usuarioModel.getTelefono());
        donante.setUsuario(usuarioModel.getUsername());
        donante.setRol(usuarioModel.getRol());

        this.donanteRepository.save(donante);
    }

    /**
     * Metodo que permite actualizar los datos de un donante
     * @param usuarioModel Objeto UsuarioModel
     */
    public void actualizar(UsuarioModel usuarioModel){

        DonanteModel donante = this.donanteRepository.findByUsuario(usuarioModel.getUsername());
        donante.setId(donante.getId());
        donante.setNombre(usuarioModel.getNombre());
        donante.setCorreo(usuarioModel.getCorreo());
        donante.setTelefono(usuarioModel.getTelefono());
        donante.setUsuario(donante.getUsuario());
        donante.setRol(donante.getRol());

        this.donanteRepository.save(donante);
    }

    /**
     * Metodo que permite eliminar un donante
     * @param usuarioModel Objeto UsuarioModel
     */
    public void borrar(UsuarioModel usuarioModel){
        DonanteModel donante = this.donanteRepository.findByUsuario(usuarioModel.getUsername());
        this.donanteRepository.delete(donante);
    }

}
