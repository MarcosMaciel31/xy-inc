/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.xyinc.dao;

import br.com.xyinc.util.HibernateUtil;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.validation.ConstraintViolationException;
import org.hibernate.Session;
import org.hibernate.Transaction;
//import org.hibernate.usertype.ParameterizedType;

/**
 *
 * @author Marcos
 * 
 * A classe GenericDao pode ser usada para todos os modelos fazendo todas as operações
 * de Salvar, editar, remover e listar caso futuramente existam outras tabelas
 * no banco de dados. Basta apenas extender a classe no dao de cada modelo.
 * A sessão do hibernate aberta ira sozinho cuidar de todas as operações de banco de dados.
 * A sessão do hibernet será finalizada no final da execussão de cada metodo.
 * @param <T>
 * @param <PK>
 */

public class GenericDao<T, PK> {

    public void salvar(T modelo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            t.setTimeout(10);
            session.save(modelo);
            t.commit();
        } catch (ConstraintViolationException esql) {
            if (t != null) {
                t.rollback();
            }
            System.out.println("ERRO TIMEOUT");
        } finally {
            if (session.isOpen()) {
                session.close();
            }
            t = null;
            session = null;
        }
    }

    public void atualizar(T modelo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            t.setTimeout(10);
            session.update(modelo);
            t.commit();
        } catch (ConstraintViolationException esql) {
            if (t != null) {
                t.rollback();
            }
            System.out.println("ERRO TIMEOUT");
        } finally {
            if (session.isOpen()) {
                session.close();
            }
            t = null;
            session = null;
        }
    }

    public void remover(T modelo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            t.setTimeout(10);
            session.delete(modelo);
            t.commit();
        } catch (ConstraintViolationException esql) {
            if (t != null) {
                t.rollback();
            }
            System.out.println("ERRO TIMEOUT");
        } finally {
            if (session.isOpen()) {
                session.close();
            }
            t = null;
            session = null;
        }
    }

    /*               PODE SER FEITO UM FILTRO PASSANDO 
    UM PARAMENTRO E CONCATENANDO COM WHERE APOS O PEGAR O NOME DA CLASSE*/
    public Collection<T> listarTodos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = null;
        List objListaTodos = new ArrayList();
        try {
            t = session.beginTransaction();
            t.setTimeout(10);
            objListaTodos = session.createQuery("From " + getTypeClass().getName()).list();
            t.commit();
        } catch (ConstraintViolationException esql) {
            if (t != null) {
                t.rollback();
            }
            System.out.println("ERRO TIMEOUT");
        } finally {
            if (session.isOpen()) {
                session.close();
            }
            t = null;
            session = null;
        }
        return objListaTodos;
    }

    private Class<T> getTypeClass() {
        return (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
