/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineracao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import model.clustering.DataPoint;

/**
 *
 * @author LUIS
 */
public class SecCluster implements Serializable{
    
    //classe que armazeno o resultado do clustering por agente
    
    private Map<Integer, java.util.List<DataPoint>> secClusterMap = null;
    private Integer points = 0;

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Map<Integer, List<DataPoint>> getSecClusterMap() {
        return secClusterMap;
    }

    public void setSecClusterMap(Map<Integer, List<DataPoint>> secClusterMap) {
        this.secClusterMap = secClusterMap;
    }
    
}
