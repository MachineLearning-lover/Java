import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class MainTest {

    private String currentPath = currentPath =  new File( "." ).getCanonicalPath() + "\\" + "src\\test\\resources";

    public MainTest() throws IOException {
    }

    @Test
    public void test_create_map_one_file() throws Exception {
        String[] args = {currentPath + "\\" + "fileForGeneratingMap.txt", "-createMap"};
        Main.main(args);
        Path file = Paths.get(currentPath + "\\" + "fileForGeneratingMap_MapGenerated.txt");

        assertTrue(Files.exists(file));
    }

    @Test
    public void test_create_map_multiple_files() throws Exception {
        String[] args = {currentPath + "\\" + "fileForGeneratingMap_part1.txt",
                currentPath + "\\" + "fileForGeneratingMap_part2.txt","-createMap"};
        Main.main(args);
        Path file1 = Paths.get(currentPath + "\\" + "fileForGeneratingMap_part1_MapGenerated.txt");
        Path file2 = Paths.get(currentPath + "\\" + "fileForGeneratingMap_part2_MapGenerated.txt");

        assertTrue(Files.exists(file1));
        assertTrue(Files.exists(file2));
    }

    @Test
    public void test_interprate_map_one_file() throws Exception {
        String[] args = {currentPath + "\\" + "map.txt", currentPath + "\\" + "logs1.txt", "-solve"};
        Main.main(args);
        Path file = Paths.get(currentPath + "\\" + "logs1_solution.txt");

        assertTrue(Files.exists(file));
    }

    @Test
    public void test_interprate_map_multiple_files() throws Exception {
        String[] args = {currentPath + "\\" + "map.txt", currentPath + "\\" + "logs1.txt", currentPath + "\\" + "logs2.txt", "-solve"};
        Main.main(args);
        Path file1 = Paths.get(currentPath + "\\" + "logs1_solution.txt");
        Path file2 = Paths.get(currentPath + "\\" + "logs2_solution.txt");

        assertTrue(Files.exists(file1));
        assertTrue(Files.exists(file2));
    }

    @Test
    public void test_printing_documentation() throws Exception {
        String[] args ={};
        Main.main(args);
    }

}