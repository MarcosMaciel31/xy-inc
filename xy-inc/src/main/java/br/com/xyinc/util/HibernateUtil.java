/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.xyinc.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author Marcos
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    static {
        try {
            criarSessao();
        } catch (Exception e) {
        }
    }

    private static void criarSessao() {
        try {
            //Cria uma sessão a partir do arquivo de configurações hibernate.cfg.xml
            Configuration configuration = new Configuration();
            configuration.configure();//Configura as definições do hibernate
            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (HibernateException e) {
            System.out.println("****** ERRO AO CRIAR SESSÃO DO HIBERNATE*****");
            e.getMessage();
            e.getStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }

    public static void Reconectar() {
        try {
            sessionFactory.close();
        } catch (HibernateException e) {
        }
        try {
            criarSessao();
        } catch (Exception e) {
        }
    }

    /* GET E SET */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void setSessionFactory(SessionFactory sessionFactory) {
        HibernateUtil.sessionFactory = sessionFactory;
    }

    public static ServiceRegistry getServiceRegistry() {
        return serviceRegistry;
    }

    public static void setServiceRegistry(ServiceRegistry serviceRegistry) {
        HibernateUtil.serviceRegistry = serviceRegistry;
    }

}
