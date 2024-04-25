import java.util.Arrays;
import java.util.Comparator;

public class Test {
    public static void main(String[] args) {
        Stand s1 = new Stand("East Side", 20, 110);
        Stand s2 = new Stand("West Side", 15, 100);
        Sit sit = new Sit("North Side", 60, 120, 20);
        VIP vip = new VIP("South Side",50, 130, 10);
        Tribune [] tribune = {s1,sit,s2,vip};
        /*Tribune [] tribune = {
                new Stand("East Side", 20, 110),
                new Sit("North Side", 60, 120, 20),
                new Stand("West Side", 15, 100),
                new VIP("South Side",50, 130, 10)
        };*/
        String [] nameList = {"fddf","dsfdd","2dfsfsd","dffsfs"};
        Ticket [] ticketss1 = tribune[0].buyTickets(4);
        Ticket [] ticketssit = tribune[1].buyTickets(4);
        Ticket [] ticketssit2 = tribune[1].buyTickets(11);
        Ticket [] ticketssit3 = tribune[1].buyTickets(9);
        Ticket [] ticketss2 = tribune[2].buyTickets(4);
        Ticket [] ticketsvip = tribune[3].buyTickets(nameList);

        Arrays.stream(ticketss1).forEach(s-> System.out.println(s));
        System.out.println();
        Arrays.stream(ticketss2).forEach(s-> System.out.println(s));
        System.out.println();
        Arrays.stream(ticketssit).forEach(s-> System.out.println(s));
        System.out.println();
        Arrays.stream(ticketsvip).forEach(s-> System.out.println(s));
        System.out.println();

       Arrays.stream(ticketssit2).forEach(s-> System.out.println(s));
        System.out.println();
        Arrays.stream(ticketssit3).forEach(s-> System.out.println(s));


        System.out.println("");

        //Arrays.sort(tribune, (a,b) -> a.findIncome() - b.findIncome());
        Arrays.sort(tribune, Comparator.comparingInt(Tribune::findIncome));

        for (int i = 0; i <tribune.length ; i++) {
            System.out.println(tribune[i].findIncome()+ " , " +tribune[i].getTribuneName());
        }


    }
}
