/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.ciencias.is.controlador;

import javax.servlet.http.HttpServletRequest;
import mx.unam.ciencias.is.mapeobd.Usuario;
import mx.unam.ciencias.is.modelo.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author alan
 */
@Controller
public class ControladorUsuario {
    @Autowired
    private UsuarioDAO usuario_bd;
    
    @RequestMapping(value="/guardarUsuario",method = RequestMethod.POST)
    public String guardaUsuario(HttpServletRequest request){
        String nickname = request.getParameter("nickname");
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");
        Usuario u = usuario_bd.getUsuarioCorreo(correo);
        if(u== null){
            Usuario us = new Usuario();
            us.setNickname(nickname);
            us.setCorreo(correo);
            us.setContrasena(contrasena);
            usuario_bd.guardar(us);
        }
        return "redirect:/";
    }
}
