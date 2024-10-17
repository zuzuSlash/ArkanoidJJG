package ies.pedro.utils;


import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import javafx.geometry.Point2D;


public class AdapterXml extends XmlAdapter<String, Point2D> {

    @Override
    public Point2D unmarshal(String s) throws Exception {
        String[] coords = s.split(",");
        double x = Double.parseDouble(coords[0]);
        double y = Double.parseDouble(coords[1]);
        return new Point2D(x, y);
    }

    @Override
    public String marshal(Point2D point2D) throws Exception {
         return point2D.getX() + "," + point2D.getY();
    }
}
