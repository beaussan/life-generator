package com.lifeproject.ihm;

import com.lifeproject.data.Humain;
import com.lifeproject.data.ImageDB;

import javax.swing.*;
import java.awt.*;

/**
 * Created by beaussan on 04/09/15.
 */
public class ProfilePicture extends JPanel{
    private Humain hum;
    private ImageIcon img;

    public ProfilePicture(Humain hum){
        setBackground(new Color(40, 40, 40));
        setHum(hum);
    }

    public void setHum(Humain hum){
        this.hum = hum;
        img = ImageDB.getInstance().getImg(hum);
        removeAll();
        JLabel imLab = new JLabel(img);
        setLayout(new BorderLayout());
        add(imLab, BorderLayout.CENTER);
    }
}
