package javautils.utils.parsers.ini;

import javautils.structs.Seq;
import java.io.*;
import java.util.HashMap;
import java.util.Objects;

public class INIParser {
    public final File file;

    protected FileReader tempReader;
    protected int tempChar;
    protected String tempKey = "", tempValue = "", tempSymbol = "", temp = "";
    protected int ind;
    protected short mode;

    private Seq<String> data = new Seq<String>();
    private HashMap<String, String> values = new HashMap<String, String>();

    public INIParser(File file){
        this.file = file;
    }

    public void parseContent(){
        read();
        parse();
        close();
    }

    void read(){
        try {
            tempReader = new FileReader(file);
            while((tempChar = tempReader.read()) != -1){
                data.add(String.valueOf((char) tempChar));
            }
            tempReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void parse(){
        mode = 0;
        for(int i = 0;i < data.getSize();i++){
            tempSymbol = data.get(i);
            if(Objects.equals(tempSymbol, "\n")){
                mode = 0;
                values.put(tempKey.strip(), tempValue.strip());
                tempKey = "";
                tempValue = "";
            }else if(Objects.equals(tempSymbol, "=")){
                mode = 1;
            }else if(!Objects.equals(tempSymbol, " ")){
                if(mode == 0){
                    tempKey += tempSymbol;
                }else if(mode == 1){
                    tempValue += tempSymbol;
                }
            }
        }
    }

    void close(){
        try {
            tempReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
