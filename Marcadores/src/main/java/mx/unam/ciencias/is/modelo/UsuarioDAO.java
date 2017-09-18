/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.ciencias.is.modelo;

import java.util.List;
import mx.unam.ciencias.is.mapeobd.Usuario;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author alan
 */
public class UsuarioDAO {
    
    /** Sesión para contactarnos con la base de datos. */
    private SessionFactory sessionFactory;

    /**
     * Método setSession.
     * Método para asginar una sesión de conexión de la base de datos.
     * @param session La sesión con la base de datos.
     */
    public void setSession(SessionFactory session) {
        this.sessionFactory = session;
    }
    
    /**
     * Método guardar.
     * Método que guarda el usuario en la base de datos.
     * @param user El usuario a guardar.
     */
    public void guardar(Usuario user){
        Session session = sessionFactory.openSession();//Abrimos la sessión
        Transaction tx = null; //Creamos la transacción
        try{
            tx = session.beginTransaction(); //Empezamos la transacción
            session.persist(user);//Le decimos que guarde al objeto
            tx.commit();//Y luego hacemos el commit
        }catch(Exception e){
            if (tx!=null){ //Si la transacción es nula
                tx.rollback(); //Regresamos a un estádo anterior
            }
            e.printStackTrace(); //Pintamos en consola el error
        }finally{
            session.close(); //Finalmente cerramos la sesión actual
        }
    }
    
    /**
     * Regresa una lista con todos los usarios en la base de datos.
     * @return Una lista con todos los usuarios en la base de datos.
     */
    public List<Usuario> getUsuarios(){
        List<Usuario> resultado = null;
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql = "FROM Usuario";
            Query query = session.createQuery(hql);
            resultado = (List<Usuario>)query.list();
            tx.commit();
        }catch(Exception e){
            if (tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }finally{
            session.close();
        }
        return resultado;
    }
    
    /**
     * Método getUsuarioCorreo.
     * Método que regresa el Usuario con el correo seleccionado.
     * @param correo El correo del usuario.
     */
    public Usuario getUsuarioCorreo(String correo){
        Session session = sessionFactory.openSession();
        Usuario salida = null;
        Transaction tx =null;
        try{
            tx = session.beginTransaction();
            String hql = "FROM Usuario where correo =:c";
            Query query = session.createQuery(hql);
            query.setParameter("c", correo);
            salida = (Usuario) query.uniqueResult();
            tx.commit();
        }catch(Exception e){
            if (tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }finally{
            session.close();
        }
        return salida;
    }
    
    /**
     * Elimina un usuario de la base de datos.
     * @param u El usuario a eliminar.
     */
    public void eliminar(Usuario u){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.delete(u);
            tx.commit();
        }catch(Exception e){
            if (tx!=null){ 
                tx.rollback();
            }
            e.printStackTrace(); 
        }finally{
            session.close();
        }
    }
    
}
