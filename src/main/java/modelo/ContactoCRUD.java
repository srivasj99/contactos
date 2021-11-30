/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author DAW-A
 */
public class ContactoCRUD {
    
    public static List<Contacto> getContactos() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("contactos");
        EntityManager manager = factory.createEntityManager();
        String sql = "SELECT * FROM contacto";
        Query q = manager.createNativeQuery(sql,Contacto.class); //método para consultas en SQL
        List<Contacto> contactoBD =  q.getResultList();

        return contactoBD;        
        }
    
    public static Contacto getContacto(int id) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("contactos");
        EntityManager manager = factory.createEntityManager();
        String sql = "SELECT c FROM Contacto c WHERE c.id=" + id; //consulta en JPQL 
        Query q = manager.createQuery(sql,Contacto.class); //método para consultas en JPQL
        Contacto contactoBD =  (Contacto) q.getSingleResult();
        return contactoBD;
    }
    
    public static int destroyContacto(int id) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("contactos");
        EntityManager manager = factory.createEntityManager();
        String sql = "DELETE from Contacto c WHERE c.id = " + id;
        Query q = manager.createQuery(sql);
        manager.getTransaction().begin();
        int filasAfectadas = q.executeUpdate(); //para las consultas de modif. datos se usa el método executeUpdate
        manager.getTransaction().commit();
        return filasAfectadas;  
    }
    
    public static List<Contacto> getContactosPaginado(int offset, int lineas_pagina){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("contactos");
        EntityManager manager = factory.createEntityManager();
        String sql = "SELECT * FROM contacto";
        Query q = manager.createNativeQuery(sql,Contacto.class);
        q.setMaxResults(lineas_pagina);
        q.setFirstResult(offset);
        List<Contacto> contactoDB = q.getResultList();
        return contactoDB;
    }
    
    public static int actualizaContacto(Contacto miContacto) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("contactos");
        EntityManager manager = factory.createEntityManager();
        String sql = "UPDATE Contacto c SET c.nombre = :nombre, c.telefono = :telefono WHERE c.id = :id";
        Query q = manager.createQuery(sql,Contacto.class);
        q.setParameter("id", miContacto.getId());
        q.setParameter("nombre", miContacto.getNombre());
        q.setParameter("telefono", miContacto.getTelefono());
        manager.getTransaction().begin();
        int filasAfectadas = q.executeUpdate();
        manager.getTransaction().commit();
        //manager.close();
        return filasAfectadas;      
    }
    
    public static void insertaContacto(Contacto miContacto) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("contactos");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(miContacto);
        manager.getTransaction().commit();
    }
}
