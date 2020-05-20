package com.certant.app.manager;

import com.certant.app.model.Ability;
import com.certant.app.model.Evolution;
import com.certant.app.model.Pokemon;
import com.certant.app.model.Type;

import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionManager {
    private static SessionFactory sessionFactory;
    
    public static Session getSession() throws HibernateException {
        //Si la fabrica de sesiones ya esta creada, entonces retorna una sesion 
        if (sessionFactory != null) {
            return sessionFactory.openSession();
        }
        //Si la fabrica de sesiones no esta creada, entonces crea una y retorna una sesion
        else {
            //Instancia un objeto del tipo Configuration
            Configuration config = new Configuration();

            //Registra los mappers en la configuracion
            registerMappers(config);

            //Establece las propiedades de configuracion
            config.setProperties(getHibernateProperties());

            //Guarda la fabrica de sesiones
            sessionFactory = config.buildSessionFactory();

            //Retorna una sesion de trabajo abierta
            return sessionFactory.openSession();
        }
    }
    
    //Retorna la sesion de trabajo abierta, para finalizarla
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    private static Properties getHibernateProperties() {
        //Instancia un objeto del tipo Properties
        Properties props = new Properties();

        //Establece el driver de conexion dependiente del RDBMS
        props.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");

        //Establece la url de conexion dependiente del RDBMS
        props.put("hibernate.connection.url", "jdbc:mysql://localhost/db_pokemons");

        //Establece el usuario
        props.put("hibernate.connection.username", "root");

        //Establece la clave
        props.put("hibernate.connection.password", "");

        //Establece el dialecto a utilizar
        props.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

        //Establece el uso de logging, debera existir el archivo log4j.properties
        //props.put("hibernate.show_sql", "true");
        
        props.put("hibernate.hbm2ddl.auto", "update");

        //Retorna las propiedades
        return props;
    }

    private static void registerMappers(Configuration config) throws MappingException {
        config.addAnnotatedClass(Ability.class);
        config.addAnnotatedClass(Evolution.class);
        config.addAnnotatedClass(Pokemon.class);
        config.addAnnotatedClass(Type.class);
    }    
}