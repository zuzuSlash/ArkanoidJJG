package ies.pedro.components;


import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;


public class DialogTime extends Dialog<Double> {

    public DialogTime() {
        super();
    }

    public void init() {
        this.setTitle("Tiempo nivel");
        ButtonType acceptButtonType = new ButtonType("Aceptar", ButtonData.OK_DONE);
        this.getDialogPane().getButtonTypes().addAll(acceptButtonType, ButtonType.CANCEL);
        /*GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));*/

        Slider tim = new Slider();
        tim.setMin(1);
        tim.setMax(500);
        tim.setBlockIncrement(1);
        tim.setShowTickLabels(true);
        tim.setShowTickMarks(true);

        this.getDialogPane().setContent(tim);
        this.setResultConverter(dialogButton -> {
            if (dialogButton == acceptButtonType) {

                return  tim.getValue();
            }
            return null;
        });
    }
}
