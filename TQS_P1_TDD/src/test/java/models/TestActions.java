package models;

import org.junit.jupiter.api.Test;

public class TestActions {

    @Test
    public void testConstants() {
        Actions testedActions = new Actions();
        assert("block" == testedActions.BLOCK ):"Action.BLOCK has not the correct value";
        assert("reverse" == testedActions.REVERSE ):"Action.REVERSE has not the correct value";
        assert("plus_two" == testedActions.PLUS_TWO ):"Action.PLUS_TWO has not the correct value";
        assert("plus_four" == testedActions.PLUS_FOUR ):"Action.PLUS_FOUR has not the correct value";
        assert("color_swap" == testedActions.COLOR_SWAP):"Action.COLOR_SWAP has not the correct value";

    }
}
