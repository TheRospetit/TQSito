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

    @Test
    public void testGetActionsStringName() {
            assert (Actions.getActionsStringName(Actions.REVERSE).equals(String.valueOf('⭾')))
                    : "Action getActionsStringName does not print correctly";

            assert (Actions.getActionsStringName(Actions.PLUS_FOUR).equals("+4\uD83C\uDFB2"))
                    : "Action getActionsStringName does not print correctly";

            assert (Actions.getActionsStringName(Actions.PLUS_TWO).equals("+2"))
                    : "Action getActionsStringName does not print correctly";

            assert (Actions.getActionsStringName(Actions.COLOR_SWAP).equals("\uD83C\uDFA8\uD83D\uDD04"))
                    : "Action getActionsStringName does not print correctly";

            assert (Actions.getActionsStringName(Actions.BLOCK).equals(String.valueOf('\u26D4')))
                    : "Action getActionsStringName does not print correctly";
    }
}
