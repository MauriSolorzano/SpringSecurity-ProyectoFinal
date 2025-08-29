package com.ProyectoFinal.Service;

import com.ProyectoFinal.Entity.Autor;
import com.ProyectoFinal.Entity.Blog;
import com.ProyectoFinal.Repository.AutorRepository;
import com.ProyectoFinal.Repository.BlogRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    public BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public AutorRepository autorRepository;

    public BlogService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }


    // METODO PARA CREAR UN BLOG
    public Blog createBlog(Blog blog, Long autorId){
        Autor autor = autorRepository.findById(autorId)
                .orElseThrow(() -> new RuntimeException("Autor no encontrado"));

        blog.setAutor(autor);
        return blogRepository.save(blog);
    }

    // METODO PARA LISTAR TODOS LOS BLOGS
    public List<Blog> blogList(){
        return blogRepository.findAll();
    }

    // METODO PARA ACTUALIZAR UN BLOG
    public Blog updateBlog(Long blogId, Blog blogDetails, Long autorId){
        Blog existingBlog = blogRepository.findById(blogId)
                .orElseThrow(() -> new RuntimeException("No existe blog con ese ID"));

        if (autorId != null){
            Autor autor = autorRepository.findById(autorId)
                    .orElseThrow(() -> new RuntimeException("Autor no encontrado"));
            existingBlog.setAutor(autor);
        }

        existingBlog.setTitulo(blogDetails.getTitulo());
        existingBlog.setContenido(blogDetails.getContenido());

        return blogRepository.save(existingBlog);
    }

    // METODO PARA ELIMINAR UN BLOG
    public void deleteBlog(Long id){
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe blog con ese ID"));

        blogRepository.delete(blog);
    }


}
