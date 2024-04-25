import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Deck {

    private void lagKortStokk(ArrayList<card> rCards){
        for (int i = 1; i <=13 ; i++) {
            rCards.add(new card('S', i));
            rCards.add(new card('H', i));
            rCards.add(new card('C', i));
            rCards.add(new card('D', i));
        }
    }

    private ArrayList<card> assignCards(int antall, ArrayList<card> rCards ){
        ArrayList<card> nCards = new ArrayList<>();
        int tall;
        for (int i = 0; i <antall ; i++) {
            tall = new Random().nextInt(52);
            if(!nCards.contains(rCards.get(tall))){
                nCards.add(rCards.get(tall));
            }
        }
        return nCards;
    }

    private void sparKort (ArrayList<card> rCards){
        rCards.stream().filter(p ->p.getSuit() == 'S').forEach(s -> System.out.println(s));
    }

    private ArrayList<card> hjerteKort(ArrayList<card> rCards){
        return  rCards.stream().filter(p -> p.getSuit() == 'H').collect(Collectors.toCollection(ArrayList::new));
    }

    private  ArrayList<String> kortFarge (ArrayList<card> rCards){
        ArrayList<String>  farge;
        farge = rCards.stream().map(c -> {
            if(c.getSuit() == 'H' || c.getSuit() == 'D') return "Rød"; else return "Svart";
        }).collect(Collectors.toCollection(ArrayList::new));
        return farge;
    }

    private int sumKort (ArrayList <card> rCards){
        return rCards.stream().map(card :: getFace).reduce((a,b)->a+b).get();
    }

    private boolean sparDame(ArrayList<card> rCards){
       return rCards.stream().anyMatch(p -> p.getFace() == 12) && rCards.stream().anyMatch(p -> p.getSuit() == 'S');
    }

    public boolean pokerFlush(List<card> rCards){
        if(rCards.size() == 5 ) {
            if(rCards.stream().allMatch(x -> x.getSuit() == 'S')) return true;
            if(rCards.stream().allMatch(x -> x.getSuit() == 'C')) return true;
            if(rCards.stream().allMatch(x -> x.getSuit() == 'H')) return true;
            if(rCards.stream().allMatch(x -> x.getSuit() == 'D')) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Deck d = new Deck();
        ArrayList<card> cards = new ArrayList<>();
        d.lagKortStokk(cards);
        ArrayList <card> pokerFlush = new ArrayList<>();
        for (int i = 1; i <=5 ; i++) {
            pokerFlush.add(new card('S',i));
        }
        ArrayList<card> trekkteKort;
              trekkteKort  = d.assignCards(10,cards);
        System.out.println(d.sumKort(trekkteKort));
        System.out.println(d.hjerteKort(trekkteKort));
        System.out.println(d.kortFarge(trekkteKort));
        d.sparKort(trekkteKort);
        System.out.println(d.sparDame(trekkteKort));
        System.out.println(d.pokerFlush(trekkteKort));
        System.out.println(d.pokerFlush(pokerFlush));

    }
}


