/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ies.pedro.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.google.gson.annotations.JsonAdapter;

import ies.pedro.utils.AdapterJson;
import ies.pedro.utils.AdapterXml;
import ies.pedro.utils.Size;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javafx.geometry.Point2D;


import java.io.*;


import java.util.Arrays;



@XmlRootElement(name = "level")
public class Level {
private Size size;
    private Block[][] blocks;
    private double time;
    private String sound;
    private String name;
    @JsonAdapter(AdapterJson.class)
    private Point2D backgroundPosition;
    private static Size s= new Size(224 / 16, 240 / 8);
    public Level(String name) {
        this.name = name;
        this.setSize(s);
        this.init();
    }


    public Level() {
        this.setSize(s);
        this.init();
        this.name="";
    }

    public void init() {
        this.setBlocks(new Block[this.getSize().getHeight()][this.getSize().getWidth()]);
        for (int i = 0; i < this.getBlocks().length; i++) {
            for (int j = 0; j < this.getBlocks()[i].length; j++) {
                if (this.getBlocks()[i][j] == null)
                    this.getBlocks()[i][j] = new Block();

            }
        }
    }

    public void reset() {
        for (int i = 0; i < this.getBlocks().length; i++) {
            Arrays.fill(this.getBlocks()[i], null);
        }
        this.backgroundPosition = null;
        this.sound = null;
        this.init();

    }

    public void setBlockValue(String value, int row, int col) {

        this.getBlocks()[row][col].setValue(value);
    }

    public String getBlockValue(int row, int col) {
        if (this.getBlocks()[row][col] == null || this.getBlocks()[row][col].getValue() == null)
            return null;
        else
            return this.getBlocks()[row][col].getValue();
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public Block[][] getBlocks() {
        return blocks;
    }

    public void setBlocks(Block[][] blocks) {
        this.blocks = blocks;
    }
    @XmlJavaTypeAdapter(AdapterXml.class)
    public Point2D getBackgroundPosition() {
        return backgroundPosition;
    }

    public void setBackgroundPosition(Point2D backgroundPosition) {
        this.backgroundPosition = backgroundPosition;
    }

    @Override
    public String toString() {
        StringBuilder cadena = new StringBuilder();
        String tempo;
        cadena.append("Nivel ").append(this.getName()).append("\n");
        cadena.append("Backgroud:").append(this.backgroundPosition).append("\n");
        for (int i = 0; i < this.getBlocks().length; i++) {
            for (int j = 0; j < this.getBlocks()[i].length; j++) {
                //cadena.append("["+i+","+j+"]"+ this.getBlocks()[i][j]!=null?this.getBlocks()[i][j].toString():""+" "+"");
                tempo = this.getBlocks()[i][j] != null ? this.getBlocks()[i][j].toString() : "";
                cadena.append("[").append(i).append(",").append(j).append("]").append(tempo).append(" ");
            }
            cadena.append("\n");
        }
        return cadena.toString();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Estos metodos son exactamente los utlizados en serializacion
    // cactus, solo he tenido que modificar el nombre de la  clase que es Level
    public void jsonSave(File file) {
        try (FileWriter writer = new FileWriter(file)) {
            Gson gson = new GsonBuilder().registerTypeAdapter(Point2D.class, new AdapterJson()).create();
            String json = gson.toJson(this);
            writer.write(json);
            System.out.println("JSON guardado correctamente en: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo JSON: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void xmlSave(File file) {
        try {
            JAXBContext contexto = JAXBContext.newInstance(Level.class);
            Marshaller marshaller = contexto.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(this, file);
            System.out.println("XML guardado correctamente en: " + file.getAbsolutePath());
        } catch (JAXBException e) {
            System.err.println("Error al guardar el archivo XML: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Level jsonLoad(File file) {
        try (FileReader reader = new FileReader(file)) {
            Gson gson = new GsonBuilder().registerTypeAdapter(Point2D.class, new AdapterJson()).setPrettyPrinting().create();
            Level level = gson.fromJson(reader, Level.class);
            return level;
        } catch (IOException e) {
            System.err.println("Error al cargar el archivo: " + e.getMessage());
            return null;
        }
    }

    public static Level xmlLoad(File file) {
        try {
            JAXBContext contexto = JAXBContext.newInstance(Level.class);
            System.out.println("Cargado XML: " + contexto);
            return (Level) contexto.createUnmarshaller().unmarshal(file);
        } catch (JAXBException e) {
            System.err.println("Error al cargar el archivo: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

}


