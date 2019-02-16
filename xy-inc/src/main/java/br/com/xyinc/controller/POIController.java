/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.xyinc.controller;

/**
 *
 * @author Marcos Maciel
 */
import br.com.xyinc.dao.POIDao;
import br.com.xyinc.model.POIModel;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean//ANOTACAO DE BEAN TODO BEAN PODE SER USADO NA TELA, CADA MODEL DEVER TER O SEU BEAN BEM COMO SEU DAO
@ViewScoped//TIPO DO BEAN, VIEW - SCOPO DE VISUALIZACAO A CADA REQUISICAO URL ELE SERA ATUALIZADO
public final class POIController {

    private List<POIModel> objLista = new ArrayList<>();
    private POIModel objSelecionado = new POIModel();
    private POIDao DAO_POI = new POIDao();

    public POIController() {
        //CONSTRUTOR A CADA REFRESH DA PAGINA O QUE ESTIVER AQUI DENTRO SERA EXECUTADO
        listar();
    }

    public void listar() {
        try {
            objLista = (List<POIModel>) DAO_POI.listarTodos();
        } catch (Exception e) {
            System.out.println("Erro de consulta ao banco de dados: " + e.getMessage());
        }
    }

    public void salvar() {
        try {
            DAO_POI.salvar(objSelecionado);
            objSelecionado = new POIModel();
            listar();
        } catch (Exception e) {
            System.out.println("Erro ao salvar cadastro: " + e.getMessage());
        }
    }

    public void alterar() {
        try {
            DAO_POI.atualizar(objSelecionado);
            objSelecionado = new POIModel();
            listar();
        } catch (Exception e) {
            System.out.println("Erro ao atualizar cadastro: " + e.getMessage());
        }
    }

    public void deletar(POIModel obj) {
        try {
            DAO_POI.remover(obj);
            listar();
        } catch (Exception e) {
            System.out.println("Erro ao deletar cadastro: " + e.getMessage());
        }
    }

    //Pego o objeto selecionado da tela e carrego ele para visualizacao dos dados e para alteracoes
    public void selecionaObjeto(POIModel obj) {
        objSelecionado = new POIModel();
        objSelecionado = obj;
    }

    //GET SET PARA PEGAR OBJ NA TELA
    public List<POIModel> getObjLista() {
        return objLista;
    }

    public void setObjLista(List<POIModel> objLista) {
        this.objLista = objLista;
    }

    public POIModel getObjSelecionado() {
        return objSelecionado;
    }

    public void setObjSelecionado(POIModel objSelecionado) {
        this.objSelecionado = objSelecionado;
    }
}
