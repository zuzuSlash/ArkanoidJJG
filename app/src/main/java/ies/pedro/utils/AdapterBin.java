package ies.pedro.utils;

import javafx.geometry.Point2D;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class AdapterBin implements Serializable {
    public static void writePoint2D(ObjectOutputStream oos, Point2D point) throws IOException {
        oos.writeDouble(point.getX());
        oos.writeDouble(point.getY());
    }

    public static Point2D readPoint2D(ObjectInputStream ois) throws IOException {
        double x = ois.readDouble();
        double y = ois.readDouble();
        return new Point2D(x, y);
    }
}
