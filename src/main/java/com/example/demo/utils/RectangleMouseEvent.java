package com.example.demo.utils;

import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class RectangleMouseEvent extends MouseEvent {
    private final Rectangle rectangle;

    public RectangleMouseEvent(Rectangle rectangle, MouseEvent mouseEvent) {
        super(mouseEvent.getEventType(), mouseEvent.getX(), mouseEvent.getY(),
              mouseEvent.getScreenX(), mouseEvent.getScreenY(),
              mouseEvent.getButton(), mouseEvent.getClickCount(),
              mouseEvent.isShiftDown(), mouseEvent.isControlDown(),
              mouseEvent.isAltDown(), mouseEvent.isMetaDown(),
              mouseEvent.isPrimaryButtonDown(), mouseEvent.isMiddleButtonDown(),
              mouseEvent.isSecondaryButtonDown(), mouseEvent.isSynthesized(),
              mouseEvent.isPopupTrigger(), mouseEvent.isStillSincePress(),
              mouseEvent.getPickResult());
        this.rectangle = rectangle;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}

