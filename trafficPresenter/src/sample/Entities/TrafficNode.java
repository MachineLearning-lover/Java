package sample.Entities;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TrafficNode {
    protected String source;
    protected String destination;
    protected List<Date> timestamps;
    protected List<Integer> vehicleNumber;
    protected Map<Date,Integer> vehicleFlow;

    public TrafficNode(TrafficNode trafficNode){
        source = trafficNode.source;
        destination = trafficNode.destination;
        timestamps = trafficNode.timestamps;
        vehicleFlow = trafficNode.vehicleFlow;
        vehicleNumber = trafficNode.vehicleNumber;
    }

    public TrafficNode(String source, String destination, List<Date> timestamps, List<Integer> vehicleNumber) {
        this.source = source;
        this.destination = destination;
        this.timestamps = timestamps;
        this.vehicleNumber = vehicleNumber;
    }

    public TrafficNode() {
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public List<Date> getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(List<Date> timestamps) {
        this.timestamps = timestamps;
    }

    public List<Integer> getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(List<Integer> vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public Map<Date, Integer> getVehicleFlow() {
        return vehicleFlow;
    }

    public void setVehicleFlow() {
        this.vehicleFlow = new TreeMap<>();
        for (int i = 0; i < this.vehicleNumber.size() ; i++) {
            this.vehicleFlow.put(this.timestamps.get(i),this.vehicleNumber.get(i));
        }
    }

    public void setVehicleFlow(Map<Date,Integer> vehicleFlow){
        this.vehicleFlow = vehicleFlow;
    }

    public TrafficNode getTrafficNodeReduceTo(String timeResolution){
        TrafficNode temporaryTrafficNode = new TrafficNode();
        Map<Date,Integer> newVehicleFlow = new TreeMap<>();
        // sampling frequency is 15 minutes by default
        int skipSize = 0;

        // copy the current trafficNode
        temporaryTrafficNode=this;

        // apply changes
       switch (timeResolution){
           case "1h": skipSize = 4;
                        break;
           case "2h": skipSize = 8;
                        break;
           case "4h": skipSize = 16;
                        break;
           case "8h": skipSize = 32;
                        break;
           case "1d": skipSize = 96;
                        break;
           default: skipSize = 0;
                    break;
       }

        temporaryTrafficNode.setVehicleFlow(modifyMap(skipSize,this.getVehicleFlow()));

        return temporaryTrafficNode;
    }

    public TrafficNode getTrafficNodeReduceToWeekDay(Integer weekday){

        TrafficNode temporaryTrafficNode = new TrafficNode();
        Map<Date,Integer> newVehicleFlow = new TreeMap<>();
        // sampling frequency is 15 minutes by default
        int skipSize = 0;

        // copy the current trafficNode
        temporaryTrafficNode=this;

        for(Map.Entry<Date,Integer> entry:temporaryTrafficNode.getVehicleFlow().entrySet()){
            if (entry.getKey().getDay() == weekday)
                newVehicleFlow.put(entry.getKey(),entry.getValue());

        }
        temporaryTrafficNode.setVehicleFlow(newVehicleFlow);

        return temporaryTrafficNode;
    }

    public static Map<Date,Integer> modifyMap(Integer skipSize,Map<Date,Integer> map){
        Map<Date,Integer> newVehicleFlow = new TreeMap<>();
        // sampling frequency is 15 minutes by default
        int index = 0;
        int temporarySum = 0;

        if (skipSize == 0)
            return map;

        // apply changes
        for (Map.Entry<Date,Integer> entry: map.entrySet()){
            if (index % skipSize == 0){
                if (index == 0)
                    newVehicleFlow.put(entry.getKey(),entry.getValue());
                else
                    // adding only the averages
                    newVehicleFlow.put(entry.getKey(),temporarySum/skipSize);

                temporarySum = 0;
            }
            else
            {
                temporarySum += entry.getValue();
            }
            index += 1;
        }

        return newVehicleFlow;
    }
}