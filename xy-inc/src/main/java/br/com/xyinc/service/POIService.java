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

        List<POIModel> lista = new ArrayList<>();

        lista = (List<POIModel>) new POIDao().listarTodos();
        for(POIModel obj : lista){
            System.out.println(new Gson().toJson(obj));
        }
        String json = new Gson().toJson(lista);
        return json;
    }

    @POST
    @Path("/add")
    @Produces({MediaType.APPLICATION_JSON})
    public Response add(String jsonSrt) {
        POIModel objPOIModel = new POIModel();
        try {
            Gson gson = new Gson();
            JsonElement elementoJson = gson.fromJson(jsonSrt, JsonElement.class);
            JsonObject objJson = elementoJson.getAsJsonObject();

            String poi = objJson.get("poi").getAsString();
            int coordenada_x = objJson.get("coordenada_x").getAsInt();
            int coordenada_y = objJson.get("coordenada_y").getAsInt();
            if (coordenada_x < 0 || coordenada_y < 0) {
                return Response.status(406).entity("Coordenada X e Coordenada Y não podem conter valores negativos").build();
            }
            if(poi.equals("") || poi==null){
                return Response.status(406).entity("Necessário POI").build();
            }
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
