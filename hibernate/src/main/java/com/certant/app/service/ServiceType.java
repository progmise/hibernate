package com.certant.app.service;

import com.certant.app.exceptions.PokedexException;
import com.certant.app.manager.SessionManager;
import com.certant.app.model.Type;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ServiceType {
    public Type getType(Long typeId) throws PokedexException {
        Session s = SessionManager.getSession();
        Transaction t = s.beginTransaction();
        
        Type type;

        try {
            Query query = s.createQuery("from Type t where t.id = :typeId");
            query.setLong("typeId", typeId);

            type = (Type) query.list().get(0);            
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
            pe.setBusinessError("Imposible obtener el Tipo por id");
            throw pe;            
        } 
        finally {
            t.commit();
            s.close();
        }
        
        return type;
    }
    
    public Set<Type> getTypes() throws PokedexException {
        Session s = SessionManager.getSession();
        Transaction t = s.beginTransaction();
        
        Set<Type> types;
        
        try {
            Query query = s.createQuery("from Type");
            types = new HashSet<>(query.list());            
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
            pe.setBusinessError("Imposible obtener los Tipos");
            throw pe;            
        } 
        finally {
            t.commit();
            s.close();
        }
        
        return types;
    } 
}