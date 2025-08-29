package com.ProyectoFinal.Service;

import com.ProyectoFinal.Entity.Autor;
import com.ProyectoFinal.Entity.Blog;
import com.ProyectoFinal.Repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    private AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    // Metodo para crear un Autor
    public Autor createAutor(Autor autor){
        return autorRepository.save(autor);
    }

    // Metodo para listar un Autor
    public List<Autor> autorList(){
        return autorRepository.findAll();
    }

    // Metodo para actualizar un Autor
    public Autor updateAutor(Long autorId, Autor autorModify){
        Autor autorCheck = autorRepository.findById(autorId)
                .orElseThrow(() -> new RuntimeException("Autor no encontrado"));

        autorCheck.setName(autorModify.getName());

        return autorRepository.save(autorCheck);
    }

    // Metodo para eliminar un Autor
    public void deleteAutor(Long autorId){
        Autor autor = autorRepository.findById(autorId)
                .orElseThrow(() -> new RuntimeException("No existe autor con es ID"));

        autorRepository.delete(autor);
    }


}
