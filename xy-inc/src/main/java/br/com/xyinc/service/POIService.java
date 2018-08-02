package br.com.xyinc.service;

import br.com.xyinc.model.POIModel;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
    public String listPoi(){
        POIModel poiModel = new POIModel();
        poiModel.setPoi("Lanchonete");
        poiModel.setCoordenada_x(23);
        poiModel.setCoordenada_y(30);
        List<POIModel> lista = new ArrayList<>();
        lista.add(poiModel);
         poiModel = new POIModel();
        poiModel.setPoi("Lanchonete");
        poiModel.setCoordenada_x(23);
        poiModel.setCoordenada_y(30);
        
        lista.add(poiModel);
         poiModel = new POIModel();
        poiModel.setPoi("Lanchonete");
        poiModel.setCoordenada_x(23);
        poiModel.setCoordenada_y(30);
        
        lista.add(poiModel);
        String json = new Gson().toJson(lista);
        return json;
    }
}
