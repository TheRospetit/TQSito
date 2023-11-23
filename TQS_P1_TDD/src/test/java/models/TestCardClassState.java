package models;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCardClassState {
    @Test
    public void TestConstructorDefault() {
        CardClass auxCard = new CardClass(Actions.BLOCK, Colours.RED);
        CardClassState testedCCS = auxCard.getState();
        assert(testedCCS != null) : "CardClassState has not been added properly";
    }

    @Test
    public void TestAttributes() {
        // Testing number of attributes
        CardClass auxCard = new CardClass(Actions.BLOCK, Colours.RED);
        CardClassState testedCCS  = auxCard.getState();
        List<Field> allFields = Arrays.asList(CardClassState.class.getDeclaredFields());
        assert(allFields.size() == 2) : "CardClassState does not have 2 attributes";

        //Testing if attributes are found
        Field card = allFields.stream().filter(field -> field.getName().equals("card")).findFirst().orElseThrow(()
                -> new RuntimeException("Field not found"));
        assertEquals(CardClass.class, card.getType());

        Field state = allFields.stream().filter(field -> field.getName().equals("state")).findFirst().orElseThrow(()
                -> new RuntimeException("Field not found"));
        assertEquals(String.class, state.getType());
    }

    @Test
    public void TestGetters() {
        CardClass auxCard = new CardClass(Actions.BLOCK, Colours.RED);
        CardClassState testedCCS = auxCard.getState();
        String testedName = testedCCS.getState();

        assert(testedName != null) : "CardClassState name has not been added properly";
        assert(testedName == Actions.BLOCK) : "CardClassState name is not set as Block properly";

    }


}
