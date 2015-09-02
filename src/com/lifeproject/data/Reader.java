package com.lifeproject.data;

/**
 * Created by beaussan on 02/09/15.
 */
public abstract class Reader {

    private final String fileName;

    public Reader(String fileName){
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }


}
