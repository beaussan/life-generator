package com.lifeproject.data;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by beaussan on 02/09/15.
 */
public class SurnameReader extends Reader {
    public static void main(String[] args) {
		SurnameReader s=new SurnameReader("/com/lifeproject/res/humain_prenom");
		System.out.println(s.getMasculin());
		System.out.println(s.getFeminin());
	}
List<String>masculin=new ArrayList<String>();
List<String>feminin=new ArrayList<String>();
	public SurnameReader(String filename){
		super(filename);
		BufferedReader b=null;
		String ligneCourante;
		try{
		b=new BufferedReader(new FileReader(new File(getClass().getResource(filename).getPath())));
		while ((ligneCourante = b.readLine()) != null) {
			if(ligneCourante.contains("*")){
				String motajoutemasc="";
				for(int i =0;i<ligneCourante.length();i++){
					if(ligneCourante.charAt(i)!='*'){
						motajoutemasc+=ligneCourante.charAt(i);
					}
				}
				masculin.add(motajoutemasc);
			}
			else{
				String motajoutefem="";
				for(int i =0;i<ligneCourante.length();i++){
				if(ligneCourante.charAt(i)!='+'){
					motajoutefem+=ligneCourante.charAt(i);
				}

			}
				feminin.add(motajoutefem);
			}
		}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	 finally {
		try {
			if (b != null)b.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	}

    public List<String> getFeminin(){
        return feminin;
    }

    public List<String> getMasculin(){
        return masculin;
    }

    public boolean isFeminin(){
    	return false;
    }

    public boolean isMasculin(){
        return false;
    }
}
