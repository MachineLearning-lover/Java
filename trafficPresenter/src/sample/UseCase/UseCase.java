package sample.UseCase;

import sample.Data.Data;
import sample.Entities.TrafficNode;
import sample.Misc.Constants;
import sample.Misc.StringTrim;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class UseCase {
    public static List<TrafficNode> trafficNodeList = new ArrayList<>();
    public static List<List<String>> listTokenized = new ArrayList<>();
    final public static Integer notAllDaysSelected = 7;

    public static void readDataFromFile(){
        Data date = new Data("date_trafic.txt");
        StringTokenizer stringTokenizer;
        date.readFile();
        for(String line:date.getLines())
        {
            stringTokenizer = new StringTokenizer(line,"\t");
            List<String> list = new ArrayList<>();
            while(stringTokenizer.hasMoreTokens()){
                list.add(stringTokenizer.nextToken());
            }
            listTokenized.add(list.stream().map(e-> StringTrim.stringTrim(e)).collect(Collectors.toList()));
        }
        listTokenized = listTokenized.stream().skip(1).filter(e->e.size()>1).collect(Collectors.toList());
    }

    public static List<String> getCountsTrafficNode(String source,String destination ){
        List<String> countTraffic = new ArrayList<>();
        for (List<String> innerlist: listTokenized)
            if (innerlist.get(Constants.from).equals(source) &&
                    innerlist.get(Constants.to).equals(destination))
                countTraffic.add(innerlist.get(Constants.vehicleflow));
        return countTraffic;
    }

    public static List<Date> getTimestampsTrafficNode(String source, String destination) {
        List<Date> timestamps = new ArrayList<>();
        for(List<String> innerList:listTokenized)
            if(innerList.get(Constants.from).equals(source) &&
                    innerList.get(Constants.to).equals(destination))
                try {
                    timestamps.add(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(innerList.get(Constants.timestamp)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
        return timestamps;
    }

    public static List<List<String>> getAdjacentList(){
        List<List<String>> adjacentList = new ArrayList<>();

        for(List<String> innerList : listTokenized) {
            List<String> node = new ArrayList<>(Arrays.asList(innerList.get(Constants.from), innerList.get(Constants.to)));
            if (adjacentList.contains(node))
                continue;
            else
                adjacentList.add(node);
        }

        return adjacentList;
    }

    public static void createTrafficNode(){
        TrafficNode trafficNode;
        List<List<String>> adjacentList ;
        adjacentList = UseCase.getAdjacentList();

        for(List element: adjacentList)
        {
            trafficNode = new TrafficNode();
            List<Integer> list = UseCase.getCountsTrafficNode(element.get(0).toString(),element.get(1).toString())
                    .stream()
                    .map(e->Integer.parseInt(e))
                    .collect(Collectors.toList());
            List<Date> timestamps = UseCase.getTimestampsTrafficNode(element.get(0).toString(),element.get(1).toString());
            trafficNode.setVehicleNumber(list);
            trafficNode.setSource(element.get(0).toString());
            trafficNode.setTimestamps(timestamps);
            trafficNode.setDestination(element.get(1).toString());
            trafficNodeList.add(trafficNode);
        }
    }

    public static void setTrafficNodeList(){
        for(TrafficNode node:UseCase.trafficNodeList)
            node.setVehicleFlow();
    }

    public static void setupTrafficNodeList(){
        UseCase.readDataFromFile();
        UseCase.createTrafficNode();
        UseCase.setTrafficNodeList();
    }

    public static List<String> getRoadFromTrafficNodeList(){
        List<String> roads = new ArrayList<>();
        for (int i = 0; i < UseCase.trafficNodeList.size(); i++) {
            roads.add(""+i);
        }
        return roads;
    }

    public static TrafficNode getTrafficNodeBy(Integer index){
        int contor=0;
        TrafficNode temporaryStorageNode = new TrafficNode();
        for(TrafficNode node: UseCase.trafficNodeList)
            if(contor == index){
                temporaryStorageNode = node;
                break;
            }
            else
                contor += 1;
        return new TrafficNode(temporaryStorageNode);
    }

    public static List<String> getSourceAndDestinationFromTrafficNodeList(Integer index){
        int contor=0;
        TrafficNode temporaryStorageNode = new TrafficNode();
        for(TrafficNode node: UseCase.trafficNodeList)
            if(contor == index){
                temporaryStorageNode = node;
                break;
            }
            else
                contor += 1;
        return new ArrayList<>(Arrays.asList(temporaryStorageNode.getSource(),temporaryStorageNode.getDestination()));
    }

}
