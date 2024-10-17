package ies.pedro.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ies.pedro.utils.AdapterJson;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import javafx.geometry.Point2D;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "levels")
public class Levels  {
    @XmlElement(name = "level")
    private List<Level> levels;

    public Levels() {
        this.levels = new ArrayList<>();
    }

    public List<Level> getLevels() {
        return levels;
    }

    public void addLevel(Level level) {
        levels.add(level);
    }


    public void removeLevel(Level level) {
        this.levels.remove(level);
    }

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
            JAXBContext contexto = JAXBContext.newInstance(Levels.class);
            Marshaller marshaller = contexto.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(this, file);
            System.out.println("XML guardado correctamente en: " + file.getAbsolutePath());
        } catch (JAXBException e) {
            System.err.println("Error al guardar el archivo XML: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Levels jsonLoad(File file) {
        try (FileReader reader = new FileReader(file)) {
            Gson gson = new GsonBuilder().registerTypeAdapter(Point2D.class, new AdapterJson()).setPrettyPrinting().create();
            Levels levels = gson.fromJson(reader, Levels.class);
            System.out.println("Cargado JSON: " + levels);
            return levels;
        } catch (IOException e) {
            System.err.println("Error al cargar el archivo: " + e.getMessage());
            return null;
        }
    }


    public static Levels xmlLoad(File file) {
        Levels levels = null;
        try {
            JAXBContext contexto = JAXBContext.newInstance(Levels.class);
            System.out.println("Cargado XML: " + contexto);
            Unmarshaller unmarshaller = contexto.createUnmarshaller();
            levels=(Levels) unmarshaller.unmarshal(file);
            return levels;
        } catch (JAXBException e) {
            System.err.println("Error al cargar el archivo: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
