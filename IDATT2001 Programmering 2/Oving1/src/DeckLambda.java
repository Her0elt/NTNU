import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class DeckLambda {

    private void lagKortStokk(ArrayList<card> rCards){
        for (int i = 1; i <=13 ; i++) {
            rCards.add(new card('S', i));
            rCards.add(new card('H', i));
            rCards.add(new card('C', i));
            rCards.add(new card('D', i));
        }
    }

    private ArrayList<card> pickACard(int antall, ArrayList<card> rCards ){
        ArrayList<card> nCards = new ArrayList<>();
        Random r = new Random();
        int tall;
        for (int i = 0; i <antall ; i++) {
            tall = r.nextInt(rCards.size());
            nCards.add(rCards.get(tall));
            rCards.remove(tall);
        }
        return nCards;
    }
    public static void main(String[] args) {
        DeckLambda d = new DeckLambda();
        ArrayList<card> cards = new ArrayList<>();
        d.lagKortStokk(cards);
        ArrayList<card> rCards = d.pickACard(10, cards);

        System.out.println("Alle spar kort: ");

        IDeck<ArrayList<card>> sparKort = (rs) -> rs.stream().filter(p ->p.getSuit() == 'S').collect(Collectors.toCollection(ArrayList::new));
        sparKort.compute(rCards).forEach(s -> System.out.print(s+" "));

        System.out.println("\nAlle hjerterkort: ");

        IDeck<ArrayList<card>> hjerteKort = (rs) -> rs.stream().filter(p -> p.getSuit() == 'H').collect(Collectors.toCollection(ArrayList::new));
        hjerteKort.compute(rCards).forEach(s -> System.out.print(s+ " "));

        System.out.println("\nKort fargene: ");

        IDeck<ArrayList<String>> kortFarge = (rs) -> rs.stream().map(c -> {
            if(c.getSuit() == 'H' || c.getSuit() == 'D') {return "Rød";} else{ return "Svart";}
        }).collect(Collectors.toCollection(ArrayList::new));
        kortFarge.compute(rCards).forEach(s -> System.out.print(s+ " "));

        System.out.println("\nSummen av alle kortene:  ");

        IDeck<Integer> sumKort = (rs) -> rs.stream().map(card :: getFace).reduce((a,b)->a+b).get();
        System.out.print(sumKort.compute(rCards));

        System.out.println("\nHar kortstokken spar dame:  ");

        IDeck<Boolean> sparDame = (rs) -> rs.stream().anyMatch(p -> p.getFace() == 12) && rs.stream().anyMatch(p -> p.getSuit() == 'S');
        System.out.print(sparDame.compute(rCards));

        System.out.println("\nHar kortstokken en pokerflush: ");

        IDeck<Boolean> pokerFlush = (rs) -> {
            if(rs.size() == 5) {
            if(rs.stream().allMatch(x -> x.getSuit() == 'S')) return true;
            if(rs.stream().allMatch(x -> x.getSuit() == 'C')) return true;
            if(rs.stream().allMatch(x -> x.getSuit() == 'H')) return true;
            if(rs.stream().allMatch(x -> x.getSuit() == 'D')) return true;
        }
            return false;
        };
        System.out.println(pokerFlush.compute(rCards));
    }
}


