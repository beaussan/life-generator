package com.lifeproject.data;

import com.sun.org.apache.bcel.internal.generic.RET;

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
    private final ImageIcon ELF_MEN = new ImageIcon(getClass().getResource(pathStart+"male-elf.png"));

    private final ImageIcon ORC_FEM = new ImageIcon(getClass().getResource(pathStart+"female-orc.jpg"));
    private final ImageIcon ORC_MEN = new ImageIcon(getClass().getResource(pathStart+"male-orc.jpg"));

    private final ImageIcon GNOME_MEN = new ImageIcon(getClass().getResource(pathStart+"gnome.png"));
    private final ImageIcon GNOME_FEM = new ImageIcon(getClass().getResource(pathStart+"gnome-female.png"));

    private final ImageIcon DWARF_MEN = new ImageIcon(getClass().getResource(pathStart+"nain.jpg"));
    private final ImageIcon DWARF_FEM = new ImageIcon(getClass().getResource(pathStart+"dwarf-female.jpg"));

    private final ImageIcon HUMAIN_MEN = new ImageIcon(getClass().getResource(pathStart+"human-male.jpg"));
    private final ImageIcon HUMAIN_FEM = new ImageIcon(getClass().getResource(pathStart+"human-female.png"));

    private final ImageIcon DEFAULT = new ImageIcon(getClass().getResource(pathStart+"default.jpg"));


    public ImageIcon getImg(Humain hum){

        Race ra = hum.getRace();
        boolean isMale = hum.isMasculin();
        if (ra == Race.ELF){
            if (isMale){
                return ELF_MEN;
            } else {
                return ELF_FEM;
            }
        } else if (ra == Race.ORC) {
            if (isMale) {
                return ORC_MEN;
            } else {
                return ORC_FEM;
            }
        } else if (ra == Race.DWARF){
            if (isMale){
                return DWARF_MEN;
            } else {
                return DWARF_FEM;
            }
        } else if (ra == Race.GNOME){
            if (isMale){
                return GNOME_MEN;
            } else {
                return GNOME_FEM;
            }
        } else if (ra == Race.HUMAIN){
            if (isMale){
                return HUMAIN_MEN;
            } else {
                return HUMAIN_FEM;
            }
        }
        return DEFAULT;
    }
}
