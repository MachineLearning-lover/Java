import com.google.common.graph.MutableValueGraph;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@RunWith(JUnit4.class)
public class InfoDecoderTest {

    private String currentPath;

    public InfoDecoderTest() throws IOException {
        currentPath =  new File( "." ).getCanonicalPath() + "\\" + "src\\test\\resources";
    }

    @Test
    public void test_setMap_correctly() {
        InfoDecoder infoDecoder = new InfoDecoder();
        infoDecoder.setMap(currentPath+"\\map.txt");
        MutableValueGraph<String, String> map = infoDecoder.getMap();
        assertEquals(false, map.nodes().isEmpty());
        System.out.println(map.toString());
    }

    @Test
    public void test_decode_correctly_erroneous_state(){
        InfoDecoder infoDecoder = new InfoDecoder();
        infoDecoder.setMap(currentPath+"\\map.txt");
        List<String> results = infoDecoder.interpret(currentPath + "\\logs.txt");
        assertEquals(false, results.isEmpty());
        System.out.println(results);

    }

}