package models;

public class CardColourSwap extends CardClassState{

    public CardColourSwap(CardClass card) {
        super(card);
    }

    @Override
    protected CardClass doAction(Deck deck, Game game) {
        boolean color_correcto = false;
        while(!color_correcto)
        {
            System.out.println("A qué color quieres cambiar [azul, rojo, verde, amarillo]: ");
            // Falta hacer el in
            String input = game.getMyScanner().nextLine();


            switch (input){
                case("azul"): deck.setCardPlayed(new CardClass((Integer) null, input)); color_correcto = true;  return new CardClass((Integer) null, Colours.BLUE) ;
                case("rojo"):  deck.setCardPlayed(new CardClass((Integer) null, input));color_correcto = true; return new CardClass((Integer) null, Colours.RED);
                case("verde"):  deck.setCardPlayed(new CardClass((Integer) null, input)); color_correcto = true ;return new CardClass((Integer) null, Colours.GREEN);
                case("amarillo"):  deck.setCardPlayed(new CardClass((Integer) null, input)); color_correcto = true ;return new CardClass((Integer) null, Colours.YELLOW);
                default: System.out.println("Color no correcto! Introduce otro de nuevo");
            }
        }
        return null;
    }
}
