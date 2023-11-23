package models;

import org.junit.jupiter.api.Test;

public class TestColours {

    @Test
    public void testConstants() {
        Colours testedColours = new Colours();
        assert("red" == testedColours.RED ):"Colours.RED has not the correct value";
        assert("yellow" == testedColours.YELLOW ):"Colours.YELLOW has not the correct value";
        assert("blue" == testedColours.BLUE ):"Colours.BLUE has not the correct value";
        assert("green" == testedColours.GREEN ):"Colours.GREEN has not the correct value";

    }
}
