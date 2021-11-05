package se.iths.java21.philippe.laboration3;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


    class ModelTest {
        Model model;

        @BeforeEach
        void setup() {
            model = new Model();
        }
        @Test
        void whenTryingToAddNewSquareANewSquareShouldBePutInTheList() {
            model.shapes.add(ShapeBuilder.squareOf(1,2,5,Color.BLACK));
            assertThat(model.shapes.size()).isEqualTo(1);
        }

        @Test
        void whenTryingToAddNewCircleANewCircleShouldBePutInTheList() {
            model.shapes.add(ShapeBuilder.circleOf(1,1,5,Color.BLACK));
            assertThat(model.shapes.size()).isEqualTo(1);
        }

}