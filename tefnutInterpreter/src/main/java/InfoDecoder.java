import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class InfoDecoder {

    private MutableValueGraph<String, String > map = ValueGraphBuilder.directed().build();

    public List<String> interpret(String filePath){
        Path file = Paths.get(filePath);
        List<String> results = new ArrayList<>();
        try{
            List<String> contents = Files.readAllLines(file);
            String currentState;
            String previousState;
            int lengthContents = contents.size()-2;
            for(int i=0; i<= lengthContents; i+=2){
                //TODO
//                previousState = getState(contents.get(i));
//                currentState = getState(contents.get(i+1));
//
//                if (!currentState.equalsIgnoreCase(previousState)){
//                    results.add(" problema e in starea: " + currentState + " din cauza " +
//                            map.edgeValue(currentState, String.valueOf(Arrays.asList(currentState).get(0))));
//                    break;
//                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    public void setMap(String mapPath){
        Path file = Paths.get(mapPath);
        try{
            List<String> contents = Files.readAllLines(file);
            String[] splittedValue;
            for(String value : contents){
                splittedValue = value.split(",");
                map.putEdgeValue(splittedValue[0].trim(), splittedValue[1].trim(), splittedValue[2].trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MutableValueGraph<String, String> getMap() {
        return map;
    }

    private String getState(String content) throws Exception {
        String[] splited;
        splited = content.split(" ");
        String decoded = InfoPoint.decode(splited[1]);
        splited = decoded.split(",");
        return splited[0];
    }

}
