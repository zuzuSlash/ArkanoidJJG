package ies.pedro.utils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import javafx.geometry.Point2D;

import java.io.IOException;

public class AdapterJson extends TypeAdapter<Point2D> {
    @Override
    public void write(JsonWriter jsonWriter, Point2D point2D) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("x").value(point2D.getX());
        jsonWriter.name("y").value(point2D.getY());
        jsonWriter.endObject();
    }

    @Override
    public Point2D read(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        double x = 0;
        double y = 0;

        while (jsonReader.hasNext()) {
            String name = jsonReader.nextName();
            switch (name) {
                case "x":
                    x = jsonReader.nextDouble();
                    break;
                case "y":
                    y = jsonReader.nextDouble();
                    break;
            }
        }
        jsonReader.endObject();
        return new Point2D(x, y);
    }

}
