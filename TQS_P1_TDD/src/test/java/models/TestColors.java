package models;

import org.junit.jupiter.api.Test;

public class TestColors {

    @Test
    public void testConstants() {
        Colors testedColors = new Colors();
        assert("red" == testedColors.RED ):"Colours.RED has not the correct value";
        assert("yellow" == testedColors.YELLOW ):"Colours.YELLOW has not the correct value";
        assert("blue" == testedColors.BLUE ):"Colours.BLUE has not the correct value";
        assert("green" == testedColors.GREEN ):"Colours.GREEN has not the correct value";

    }

    @Test
    public void testgetColorsStringName() {
        assert (Colors.getColorsStringName("red").equals("\uD83D\uDD34") ) : "Actions getActionsStringName with red is not printing correctly";
        assert (Colors.getColorsStringName("green").equals("\uD83D\uDFE2") ) : "Actions getActionsStringName with green is not printing correctly";
        assert (Colors.getColorsStringName("blue").equals("\uD83D\uDD35") ) : "Actions getActionsStringName with blue is not printing correctly";
        assert (Colors.getColorsStringName("yellow").equals("\uD83D\uDFE1") ) : "Actions getActionsStringName with yellow is not printing correctly";
    }
}
