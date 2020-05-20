package com.certant.app.service;

import com.certant.app.exceptions.PokedexException;
import com.certant.app.manager.SessionManager;
import com.certant.app.model.Ability;
import com.certant.app.model.Evolution;
import com.certant.app.model.Pokemon;
import com.certant.app.model.Type;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ServicePokemon {
    private final ServiceType st = new ServiceType();

    public void addPokemon(Pokemon pn) throws PokedexException {
        Session s = SessionManager.getSession();
        Transaction t = s.beginTransaction();
        
        /*
        Se recorre el Set de Tipos, Habilidades y Evoluciones 
        recuperados del Pokemon para persistir en cada uno de los Tipos,
        Habilidades y Evoluciones, el Pokemon, ya que estan relacionados
        */        
        try {
            for (Type tmpTp : pn.getTypes()) {
                tmpTp.getPokemons().add(pn);
                s.update(tmpTp);
            }

            for (Ability tmpAy : pn.getAbilities()) {
                tmpAy.getPokemons().add(pn);
                s.update(tmpAy);
            }

            for (Evolution tmpEv : pn.getEvolutions()) {
                tmpEv.setPokemon(pn);
                s.update(tmpEv);
            }        

            s.save(pn);            
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
            pe.setBusinessError("Imposible agregar el Pokemon");
            throw pe;            
        } 
        finally {
            t.commit();
            s.close();
        }
    }

    public Pokemon getPokemon(String name) throws PokedexException {
        Session s = SessionManager.getSession();
        Transaction t = s.beginTransaction();
        
        Pokemon pokemon;
        
        try {
            Query query = s.createQuery("from Pokemon p where p.name = :name");
            query.setString("name", name);

            pokemon = (Pokemon) query.list().get(0);             
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
            pe.setBusinessError("Imposible obtener el Pokemon por nombre");
            throw pe;                     
        } 
        finally {
            t.commit();
            s.close();            
        }
        
        return pokemon;
    }

    public Pokemon getPokemon(Long pokemonId) throws PokedexException {
        Session s = SessionManager.getSession();
        Transaction t = s.beginTransaction();
        
        Pokemon pokemon;
        
        try {
            Query query = s.createQuery("from Pokemon p where p.id = :pokemonId");
            query.setLong("pokemonId", pokemonId);

            pokemon = (Pokemon) query.list().get(0);           
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
            pe.setBusinessError("Imposible obtener el Pokemon por id");
            throw pe;                     
        } 
        finally {
            t.commit();
            s.close();            
        }
        
        return pokemon;
    }    

    
    public Set<Pokemon> getPokemons() throws PokedexException {
        Session s = SessionManager.getSession();
        Transaction t = s.beginTransaction();
        
        Set<Pokemon> pokemons;
        
        try {
            Query query = s.createQuery("from Pokemon");
            pokemons = new HashSet<>(query.list());       
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
            pe.setBusinessError("Imposible obtener los Pokemons");
            throw pe;                     
        } 
        finally {
            t.commit();
            s.close();            
        }
        
        return pokemons;
    }    
    
    public void modifyPokemonName(Long pokemonId, String nameExpected) throws PokedexException {
        Pokemon pokemon = getPokemon(pokemonId);
        
        Session s = SessionManager.getSession();
        Transaction t = s.beginTransaction();
           
        try {
            pokemon.setName(nameExpected);
            s.update(pokemon);   
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
            pe.setBusinessError("Imposible modificar el nombre del Pokemon");
            throw pe;                     
        } 
        finally {
            t.commit();
            s.close();            
        }
    }
    
    public void modifyPokemonLevel(Long pokemonId, Integer lvlExpected) throws PokedexException {
        Pokemon pokemon = getPokemon(pokemonId);
        
        Session s = SessionManager.getSession();
        Transaction t = s.beginTransaction();
           
        try {
            pokemon.setLevelIsFound(lvlExpected);
            s.update(pokemon);   
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
            pe.setBusinessError("Imposible modificar el nivel del Pokemon");
            throw pe;                     
        } 
        finally {
            t.commit();
            s.close();            
        }
    }    
    
    public void addPokemonType(Long pokemonId, Long typeId) throws PokedexException {
        Pokemon pokemon = getPokemon(pokemonId);
        Type type = st.getType(typeId);
        
        Session s = SessionManager.getSession();
        Transaction t = s.beginTransaction();
        
        /*
        Se agregan las relaciones tanto desde el punto de vista del Pokemon
        para con los Tipos y para el Tipo para con los Pokemons
        */
        try {
            pokemon.getTypes().add(type);
            s.update(pokemon);

            type.getPokemons().add(pokemon);
            s.update(type);
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
            pe.setBusinessError("Imposible agregar el tipo al Pokemon");
            throw pe;                     
        } 
        finally {
            t.commit();
            s.close();            
        }
    }
    
    public void removePokemonType(Long pokemonId, Long typeId) throws PokedexException {
        Pokemon pokemon = getPokemon(pokemonId);
        Type type = st.getType(typeId); 
        
        Session s = SessionManager.getSession();
        Transaction t = s.beginTransaction();
    
        /*
        Se eliminan las relaciones tanto desde el punto de vista del Pokemon
        para con los Tipos y para el Tipo para con los Pokemons
        */        
        try {
            pokemon.getTypes().remove(type);
            s.update(pokemon);            

            type.getPokemons().remove(pokemon);
            s.update(type);            
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
            pe.setBusinessError("Imposible quitar el tipo al Pokemon");
            throw pe;                     
        } 
        finally {
            t.commit();
            s.close();            
        }
    }
    
    public void addPokemonEvolution(Long pokemonId, Evolution ev) throws PokedexException {
        Pokemon pokemon = getPokemon(pokemonId);
        
        Session s = SessionManager.getSession();
        Transaction t = s.beginTransaction(); 
        
        /*
        Se agregan las relaciones tanto desde el punto de vista del Pokemon
        para con la Evolucion y para la Evolucion con el Pokemon
        */        
        try {
            pokemon.getEvolutions().add(ev);
            s.update(pokemon);

            ev.setPokemon(pokemon);
            s.update(ev);    
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
            pe.setBusinessError("Imposible agregar la evolucion al Pokemon");
            throw pe;                     
        } 
        finally {
            t.commit();
            s.close();            
        }
    }
}