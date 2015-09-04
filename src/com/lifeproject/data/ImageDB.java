package com.lifeproject.data;

import javax.swing.*;

/**
 * Created by beaussan on 04/09/15.
 */
public class ImageDB {

    private static ImageDB instance;

    public static ImageDB getInstance() {
        if (null == instance) {
            instance = new ImageDB();
        }
        return instance;
    }

    private ImageDB(){

    }


    private final String pathStart = "/com/lifeproject/res/images/";
    private final ImageIcon ELF_FEM = new ImageIcon(getClass().getResource(pathStart+"female-elf.png"));

    public static ImageIcon getImg(Humain hum){
        return null;
    }
}
