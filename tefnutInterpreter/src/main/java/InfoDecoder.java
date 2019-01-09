import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;


public class InfoDecoder {

    private MutableValueGraph<String, String > map = ValueGraphBuilder.directed().build();
    private Stack<String> successorNode = new Stack<>();
    private List<String> contents = new ArrayList<>();
    private List<String> reasonsForFailure = new ArrayList<>();
    private Logger logger = Logger.getLogger(InfoDecoder.class.getName());

    public List<String> interpret(String filePath){
        Path file = Paths.get(filePath);
        try{
            contents = Files.readAllLines(file);
            int indexOfFailure = -1;
            int lengthContents = contents.size();
            while (! (indexOfFailure >= lengthContents) )
            {
                indexOfFailure = addReasonOfFailure(indexOfFailure+1);
                successorNode.clear();
            }

        } catch (FileNotFoundException e) {
            logger.log(Level.ALL, e.fillInStackTrace().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reasonsForFailure;
    }

    private int addReasonOfFailure(int startIndex) {
        int lengthContents = contents.size();
        try {
            int indexOfFirstStateFound = getFirstState(contents, startIndex);
            String currentState;
            successorNode.addAll(map.successors(getState(contents.get(indexOfFirstStateFound))));
            for (int i = indexOfFirstStateFound + 1; i <= lengthContents - 1; i++) {
                if (valid(contents.get(i))) {
                    currentState = getState(contents.get(i));
                    successorNode.remove(currentState);
                    successorNode.addAll(map.successors(currentState));
                    if (!successorNode.isEmpty()) {
                        reasonsForFailure.add("Error in " + currentState + " -> " + successorNode.get(0) + " check: "
                                + map.edgeValue(currentState, successorNode.get(0)).orElse(""));
                        return i;
                    }
                }
            }
        }
        catch (NextLineNotFoundException e){

        }
         catch (Exception e) {
            e.printStackTrace();
        }
        return lengthContents+1;
    }

    public void setMap(String mapPath){
        Path file = Paths.get(mapPath);
        try{
            List<String> contents = Files.readAllLines(file);
            String[] splitValue;
            for(String value : contents){
                splitValue = value.split(",");
                map.putEdgeValue(splitValue[0].trim(), splitValue[1].trim(), splitValue[2].trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean valid(String line){
        String INFO = "INFO:";
        String DEBUG = "DEBUG:";
        String[] splitLine = line.trim().split(" ");

        if (splitLine.length == 2 && (splitLine[0].equalsIgnoreCase(INFO) || splitLine[0].equalsIgnoreCase(DEBUG))){
            return true;
        }

        return false;
    }

    private int getFirstState(List<String> lines, int startIndex) throws NextLineNotFoundException {
        int i = startIndex;
        while (true){
            if (i >= lines.size()){
                throw new NextLineNotFoundException("the file does not contain encrypted text");
            }
            if(valid(lines.get(i))){
                return i;
            }
            i++;
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
