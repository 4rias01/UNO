package com.example.myuno.model.card.types;
import com.example.myuno.model.card.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NumberCardTest {

    private NumberCard blueNine ;
    private NumberCard yellowZero ;
    private NumberCard yellowNine;
    private NumberCard greenTwo;

    @BeforeEach
    public void setUp(){
        blueNine = new NumberCard(9 ,Card.Color.BLUE);
        yellowZero = new NumberCard(0, Card.Color.YELLOW);
        yellowNine = new NumberCard(9, Card.Color.YELLOW);
        greenTwo = new NumberCard(2, Card.Color.GREEN);

    }

    @Test
    public void GetColorBlue(){
    assertEquals(Card.Color.BLUE, blueNine.getColor());
    }

    @Test
    public void GetNumberNine(){
    assertNotEquals(10,blueNine.getNumber());
    }

    @Test
    public void GetPatchImageNotNull(){
        assertNotNull(yellowZero.getPathImage());
    }

    @Test
    public void CanBePlayerOverSameColorYellow(){
        assertTrue(yellowZero.canBePlayedOver(yellowNine));
    }

    @Test
    public void CanBePlayerOverSameNumberNine(){
        assertTrue(blueNine.canBePlayedOver(yellowNine));
    }

    @Test
    public void CanBePlayerOverDiferentNumberAndColor(){
        assertFalse(blueNine.canBePlayedOver(yellowZero));
    }


}