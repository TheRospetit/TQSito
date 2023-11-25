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
}
