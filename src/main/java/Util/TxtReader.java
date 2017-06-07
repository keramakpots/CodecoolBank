package Util;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TxtReader {
    public String[] reader(String filePath) throws FileNotFoundException {
        String[] strArr = null;
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader(filePath));
            while ((sCurrentLine = br.readLine()) != null) {
                sb.append(sCurrentLine);
            }
            strArr = sb.toString().split(";");
        } catch (IOException e) {
            throw new FileNotFoundException();

        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return strArr;
    }
}