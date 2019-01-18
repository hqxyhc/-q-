package com.util;

import java.io.File;
import java.io.IOException;

public class FileUtil {
    public void CreateFile(String name){

        String strPath = "src/"+name+".xml";
        File file = new File(strPath);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


