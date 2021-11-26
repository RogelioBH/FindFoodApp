package co.FindFoodApp.services;

import co.FindFoodApp.models.BeneficiarioModel;
import co.FindFoodApp.models.DonanteModel;
import co.FindFoodApp.models.UsuarioModel;
import co.FindFoodApp.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    DonanteService donanteService;

    @Autowired
    BeneficiarioService beneficiarioService;

    /**
     * Metodo que permite listar todos los usuarios del sistema.
     * @return Lista de UsuarioModel
     */
    public List<UsuarioModel> listar(){
        List<DonanteModel> donantes = donanteService.listar();
        List<BeneficiarioModel> beneficiarios = beneficiarioService.listar();
        List<UsuarioModel> usuarioModels = usuarioRepository.findAll();
        List<UsuarioModel> usuarios = new ArrayList<>();

        for (UsuarioModel user:usuarioModels) {
            for (DonanteModel donante : donantes) {
                if (donante.getUsuario().equals(user.getUsername())){
                    UsuarioModel userList = new UsuarioModel();
                    userList.setId(user.getId());
                    userList.setUsername(user.getUsername());
                    userList.setRol(user.getRol());
                    userList.setNombre(donante.getNombre());
                    userList.setCorreo(donante.getCorreo());
                    userList.setTelefono(donante.getTelefono());
                    usuarios.add(userList);
                }
            }
            for (BeneficiarioModel beneficiario : beneficiarios) {
                if (beneficiario.getUsuario().equals(user.getUsername())){
                    UsuarioModel userList = new UsuarioModel();
                    userList.setId(user.getId());
                    userList.setUsername(user.getUsername());
                    userList.setRol(user.getRol());
                    userList.setNombre(beneficiario.getNombre());
                    userList.setCorreo(beneficiario.getCorreo());
                    userList.setTelefono(beneficiario.getTelefono());
                    usuarios.add(userList);
                }
            }
        }
        return usuarios;
    }

    /**
     * Metodo que permito encontrar un usuario por el username
     * @param username String username
     * @return Objeto UsuarioModel
     */
    public UsuarioModel buscarPorNombreUsuario(String username){
        return this.usuarioRepository.findByUsername(username).orElse(new UsuarioModel());
    }


    /**
     * Metodo que permite guardar un usuario
     * @param usuario Objeto UsuarioModel
     */
    public void crear(UsuarioModel usuario){
        switch (usuario.getRol()){
            case "Donante":
                this.donanteService.crear(usuario);
            case  "Beneficiario":
                this.beneficiarioService.crear(usuario);
        }
        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setUsername(usuario.getUsername());
        usuarioModel.setPassword(usuario.getPassword());
        usuarioModel.setRol(usuario.getRol());
        this.usuarioRepository.save(usuarioModel);
    }

    /**
     * Metodo que permite actualizar un usuario.
     * @param usuario Objeto UsuarioModel
     */
    public void actualizar(UsuarioModel usuario){
        switch (usuario.getRol()){
            case "Donante":
                this.donanteService.actualizar(usuario);
            case  "Beneficiario":
                this.beneficiarioService.actualizar(usuario);
        }
        UsuarioModel usuarioModel = this.usuarioRepository.findById(usuario.getId()).get();
        usuarioModel.setId(usuarioModel.getId());
        usuarioModel.setUsername(usuarioModel.getUsername());
        usuarioModel.setPassword(usuario.getPassword());
        usuarioModel.setRol(usuarioModel.getRol());
        this.usuarioRepository.save(usuarioModel);
    }

    /**
     * Metodo que permite eliminar un usuario.
     * @param usuario Objeto UsuarioModel
     */
    public void borrar(UsuarioModel usuario){
        switch (usuario.getRol()){
            case "Donante":
                this.donanteService.borrar(usuario);
            case  "Beneficiario":
                this.beneficiarioService.borrar(usuario);
        }
        UsuarioModel usuarioModel = this.usuarioRepository.findById(usuario.getId()).get();
        this.usuarioRepository.delete(usuarioModel);
    }

}
