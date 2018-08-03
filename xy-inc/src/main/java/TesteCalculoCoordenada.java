
import br.com.xyinc.dao.POIDao;
import br.com.xyinc.model.POIModel;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Marcos
 */
public class TesteCalculoCoordenada {

    public static void main(String[] args) {
        List<POIModel> lista = new ArrayList<>();
        int coordenadaA, coordenadaB, d_max;
        coordenadaA = 20;
        coordenadaB = 10;
        d_max = 10;
        lista = (List<POIModel>) new POIDao().listarTodos();
        for (POIModel obj : lista) {

            int v1 = obj.getCoordenada_x() - coordenadaA;
            int v2 = obj.getCoordenada_y() - coordenadaB;
            if(v1 < 0){
                v1 = v1*-1;
            }
            if(v2 < 0){
                v2 = v2*-1;
            }
            if(d_max <= (v1+v2)){
                System.out.println(obj.getPoi());
            }
        }
    }
}
