package br.com.xyinc.service;

import br.com.xyinc.dao.POIDao;
import br.com.xyinc.model.POIModel;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 *
 * @author Marcos
 */
@Path("/poi")
public class POIService {

    @GET
    @Path("/list")
    @Produces({MediaType.APPLICATION_JSON})
    public String listPoi() {
        //Lista todos os poi registrados no banco de dados 
        List<POIModel> lista = new ArrayList<>();
        lista = (List<POIModel>) new POIDao().listarTodos();
        for (POIModel obj : lista) {
            System.out.println(new Gson().toJson(obj));
        }
        String json = new Gson().toJson(lista);
        return json;
    }

    @POST
    @Path("/consulta")
    @Produces({MediaType.APPLICATION_JSON})
    public Response listPoiProxi(String jsonSrt) {
        Gson gson = new Gson();
        //Converte string recebida para JsonObj
        JsonElement elementoJson = gson.fromJson(jsonSrt, JsonElement.class);
        JsonObject objJson = elementoJson.getAsJsonObject();
        int d_max = 0, coordenada_x = 0, coordenada_y = 0;
        try {
            //Caso algum algum erro na estrutura do arquivo enviado, retorna para o cliente aviso de arquivo fora de padroes
            d_max = objJson.get("d_max").getAsInt();
            coordenada_x = objJson.get("coordenada_x").getAsInt();
            coordenada_y = objJson.get("coordenada_y").getAsInt();
            //Apenas para evitar valores negativos 
            if (d_max < 0 || coordenada_x < 0 || coordenada_y < 0) {
                return Response.status(500).entity("Dados de coordenada e distancia maxima deve ser maiores que 0").build();
            }
        } catch (Exception e) {
            return Response.status(500).entity("Arquivo enviado fora dos padrões").build();
        }

        List<POIModel> listaProximos = new ArrayList<>();
        List<POIModel> lista = new ArrayList<>();
        //Lista todos os poi registrados no banco 
        lista = (List<POIModel>) new POIDao().listarTodos();
        //Percorre cada poi fazendo o calculo entre as distancias x e y
        for (POIModel obj : lista) {
            int v1 = obj.getCoordenada_x() - coordenada_x;
            int v2 = obj.getCoordenada_y() - coordenada_y;
            if (v1 < 0) {
                v1 = v1 * -1;
            }
            if (v2 < 0) {
                v2 = v2 * -1;
            }
            //Adiciona o poi que estiver dentro do requisito da distancia maxima em uma lista
            if ((v1 + v2) <= d_max) {
                listaProximos.add(obj);
            }
        }
        //retorna para o cliente uma lista dos poi encontrados em json
        String json = new Gson().toJson(listaProximos);
        return Response.status(200).entity(json).type(MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Path("/add")
    @Produces({MediaType.APPLICATION_JSON})
    public Response add(String jsonSrt) {
        POIModel objPOIModel = new POIModel();
        try {
            Gson gson = new Gson();
            //Recebe string json e converte para jsonobj
            JsonElement elementoJson = gson.fromJson(jsonSrt, JsonElement.class);
            JsonObject objJson = elementoJson.getAsJsonObject();
            //Le cada campo do json caso aja alguma exessao é devolvida mensagem de erro de estrutura para o cliente
            String poi = objJson.get("poi").getAsString();
            int coordenada_x = objJson.get("coordenada_x").getAsInt();
            int coordenada_y = objJson.get("coordenada_y").getAsInt();
            //evita coordenadas negativas
            if (coordenada_x < 0 || coordenada_y < 0) {
                return Response.status(406).entity("Coordenada X e Coordenada Y não podem conter valores negativos").build();
            }
            //evita que o nome do poi venha nulo ou em branco
            if (poi.equals("") || poi == null) {
                return Response.status(406).entity("Necessário POI").build();
            }
            //Preenche um novo objeto com um novo poi e salva no banco de dados
            objPOIModel.setPoi(poi);
            objPOIModel.setCoordenada_x(coordenada_x);
            objPOIModel.setCoordenada_y(coordenada_y);
            new POIDao().salvar(objPOIModel);
        } catch (Exception e) {
            return Response.status(500).entity("Arquivo enviado fora dos padrões").build();
        }
        return Response.status(200).entity("Cadastro realizado com sucesso!").build();
    }
}
