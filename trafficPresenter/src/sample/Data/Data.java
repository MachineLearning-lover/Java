package sample.Data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Data {
    protected List<String> lines;
    protected String filename;

    public Data() {
        lines = new ArrayList<>();
    }

    public Data(String filename) {
        this.filename = filename;
        lines = new ArrayList<>();
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void readFile() {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(this.filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = "";
        try {
            while( (line = bufferedReader.readLine()) != null ){
                lines.add(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
