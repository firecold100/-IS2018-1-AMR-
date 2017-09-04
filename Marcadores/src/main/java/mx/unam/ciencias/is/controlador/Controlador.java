/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.ciencias.is.controlador;

import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import mx.unam.ciencias.is.mapeobd.Marcador;
import mx.unam.ciencias.is.modelo.MarcadorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jonathan
 */
@Controller 
public class Controlador {
    /*Injectamos el modelo del marcador */
    @Autowired
    MarcadorDAO marcador_db;
    
    /**
     * Regresa la pagina principal con los marcadores de la base de datos
     * @param model 
     * @return regresa el modelo 
     */
    @RequestMapping(value="/", method = RequestMethod.GET)
    public ModelAndView marcadores(ModelMap model){
        List<Marcador> mar = marcador_db.getMarcadores();
        
        model.addAttribute("marcadores", mar);
        
        return new ModelAndView("inicio",model);
    
    }
    
   
    /**
     * Regresa el nombre de el jsp agrega marcador
     * @return 
     */
    @RequestMapping(value="/agregaMarcador")
    public String agregaMarcador(){
        return "agregaMarcador";
    }
    
    /**
     * Guarda un nuevo marcador 
     * @param request los atributos del marcador.
     * @return 
     */
    @RequestMapping(value="/guardaMarcador", method = RequestMethod.GET)
    public String guardaMarcador(HttpServletRequest request){
        Double latitud = Double.parseDouble(request.getParameter("latitud"));
        Double longitud = Double.parseDouble(request.getParameter("longitud"));
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        Marcador ma = marcador_db.getMarcador(latitud, longitud);
        if(ma==null){
            Marcador m  = new Marcador();
            m.setLatitud(latitud);
            m.setLongitud(longitud);
            m.setNombre_m(nombre);
            m.setDescripcion(descripcion);
            marcador_db.guardar(m);
        
        }
        return "redirect:/";
    }
    
    
    /**
     * Actualiza el marcador
     * @param model
     * @param request
     * @return 
     */ 
    @RequestMapping(value="/actualizaM", method = RequestMethod.GET)
    public ModelAndView actualizaM(ModelMap model,HttpServletRequest request){
        //Aqui va tu codigo
        Double lat = Double.parseDouble(request.getParameter("latitud"));
        Double lon = Double.parseDouble(request.getParameter("longitud"));
        Marcador mar = marcador_db.getMarcador(lat,lon);
        model.addAttribute("actualizaM", mar);
        return new ModelAndView("actualizaM",model);
    }
    
    
    @RequestMapping(value="/eliminaMarcador", method = RequestMethod.GET)
    public String eliminaMarcador(HttpServletRequest request){
        //Aqui va tu codigo
        Double lat = Double.parseDouble(request.getParameter("latitud"));
        Double lon = Double.parseDouble(request.getParameter("longitud"));
        Marcador m = marcador_db.getMarcador(lat, lon);
        marcador_db.eliminar(m);
        return "redirect:/";
    }
    
    @RequestMapping(value= "/actualizar", method = RequestMethod.POST)
    public String actualizar(HttpServletRequest request){
        //Aqui va tu codigo
        int id = Integer.parseInt(request.getParameter("id"));
        String lat = request.getParameter("latitud");
        double latitud = 0;
        if(lat!=null && !lat.equals(""))
            latitud = Double.parseDouble(lat);
        String lon = request.getParameter("longitud");
        double longitud = 0;
        if(lon!= null && !lon.equals(""))
            longitud = Double.parseDouble(lon);
        String n = request.getParameter("nombre");
        String nombre = "NaN";
        if(n!=null && !n.equals(""))
            nombre = n;
        String d = request.getParameter("descripcion");
        String descripcion = "NaN";
        if(d!= null && !d.equals(""))
            descripcion = d;
        Marcador ma = marcador_db.getMarcadorId(id);
        if(ma != null){
            ma.setLatitud(latitud);
            ma.setLongitud(longitud);
            ma.setNombre_m(nombre);
            ma.setDescripcion(descripcion);
            marcador_db.guardar(ma);
        }
        return "redirect:/";
    }
}
