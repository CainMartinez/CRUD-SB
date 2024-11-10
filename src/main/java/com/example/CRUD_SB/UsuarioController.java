package com.example.CRUD_SB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Mostrar el formulario de creación
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "create";
    }

    // Manejar el envío del formulario
    @PostMapping("/create")
    public String createUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
        return "redirect:/usuarios";
    }

    // Mostrar todos los usuarios (para confirmar que se guardó)
    @GetMapping
    public String listUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "list"; // Página que muestra la lista de usuarios (la crearemos en el siguiente paso)
    }
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado: " + id));
        model.addAttribute("usuario", usuario);
        return "edit";
    }
    @PostMapping("/update")
    public String updateUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
        return "redirect:/usuarios";
    }
    @GetMapping("/delete/{id}")
    public String deleteUsuario(@PathVariable("id") Long id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado: " + id));
        usuarioRepository.delete(usuario);
        return "redirect:/usuarios";
    }
}