import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) throws Exception {


        List<String> commands = Arrays.asList("-createMap","-solve");
        Predicate<String> commandIsPresent = (command) -> Arrays.stream(args).anyMatch(el -> el.equalsIgnoreCase(command));
        List<String> filteredArgs = new ArrayList<>(Arrays.asList(args));
        filteredArgs.removeAll(commands);

        if (args.length == 0){
            print_documentation();
        }

        if (commandIsPresent.test(commands.get(0)))
        {
            createMap(filteredArgs);
            System.out.println("Successfully created map");
        }

        if (commandIsPresent.test(commands.get(1))){
            findProblem(filteredArgs);
            System.out.println("Successfully solved problem");

        }
    }

    private static void createMap(List<String> files) throws Exception {

        for (String file : files){
            String newFileName = stripFileName(file) + "_" + "MapGenerated.txt";
            InfoDecoder.generateMap(newFileName,  file);
        }

    }

    private static void findProblem(List<String> files) throws IOException {

        InfoDecoder infoDecoder = new InfoDecoder();
        infoDecoder.setMap(files.get(0));
        List<String> reasonsOfFailures;

        for (int i=1; i<files.size(); i++)
        {
            String newFileName = stripFileName(files.get(i)) + "_" + "solution.txt";
            reasonsOfFailures = infoDecoder.interpret(files.get(i));
            if(reasonsOfFailures.size() == 0){
                System.out.println("File " + files.get(i) + " is all right. ");
            }
            else
            {
                Files.write(Paths.get(newFileName),
                        reasonsOfFailures,
                        StandardCharsets.UTF_8,
                        StandardOpenOption.CREATE,
                        StandardOpenOption.WRITE);
            }
            reasonsOfFailures.clear();
        }

    }

    private static String stripFileName(String fileName){
        String[] splitFileName = fileName.split("\\.");
        return splitFileName[0];
    }

    private static void print_documentation(){
        System.out.println("--------------------------");
        System.out.println("How to generate the map?");
        System.out.println("e.g. java -jar tefnutInterpreter file1.txt file2.txt file3.txt -createMap");
        System.out.println("After creating the map you could augment it and then call for solution");
        System.out.println("How to call for solution?");
        System.out.println("e.g. java -jar tefnutInterpreter map.txt file1.txt file2.txt file3.txt -solve");
        System.out.println("--------------------------");
    }
}
