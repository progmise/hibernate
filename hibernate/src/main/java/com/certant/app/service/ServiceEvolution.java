package com.certant.app.service;

import com.certant.app.exceptions.PokedexException;
import com.certant.app.manager.SessionManager;
import com.certant.app.model.Evolution;
import com.certant.app.model.Type;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ServiceEvolution {
    public Evolution getEvolution(Long evolutionId) throws PokedexException {
        Session s = SessionManager.getSession();
        Transaction t = s.beginTransaction();
        
        Evolution evolution;
        
        try {
            Query query = s.createQuery("from Evolution e where e.id = :evolutionId");
            query.setLong("evolutionId", evolutionId);

            evolution = (Evolution) query.list().get(0);           
        } 
        catch (Exception ex) {
            try {
                //Vuelve atras con los cambios realizados
                t.rollback();
            } 
            catch (HibernateException he) { }
            
            //Captura la excepcion original, y lanza una excepcion de negocios
            PokedexException pe = new PokedexException(ex);
            pe.setTechnicalError(ex.getMessage());
            pe.setBusinessError("Imposible obtener la Evolucion por id");
            throw pe;            
        } 
        finally {
            t.commit();
            s.close();
        }

        return evolution;
    }    
    
    public void addEvolution(Evolution ev) throws PokedexException {
        Session s = SessionManager.getSession();
        Transaction t = s.beginTransaction();

        /*
        Se recorre el Set de Tipos de la Evolucion recuperada
        para persistir en cada uno de los Tipos, la evolucion, ya que
        estan relacionados
        */
        try {
            for (Type tmpTp : ev.getTypes()) {
               tmpTp.getEvolutions().add(ev);
               s.update(tmpTp);
            }       

           s.save(ev);       
        } 
        catch (Exception ex) {
            try {
                //Vuelve atras con los cambios realizados
                t.rollback();
            } 
            catch (HibernateException he) { }
            
            //Captura la excepcion original, y lanza una excepcion de negocios
            PokedexException pe = new PokedexException(ex);
            pe.setTechnicalError(ex.getMessage());
            pe.setBusinessError("Imposible agregar la Evolucion");
            throw pe;            
        } 
        finally {
            t.commit();
            s.close();
        }        
    }   
}