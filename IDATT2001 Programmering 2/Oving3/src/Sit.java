import java.util.Arrays;

public final class Sit extends  Tribune {

    private int[] noBusy;
    private final int MAX_ROWLENGTH;

    public Sit(String tribuneName, int capacity, int price, int maxRowLength) {
        super(tribuneName, capacity, price);
        this.MAX_ROWLENGTH = maxRowLength;
        this.noBusy = new int[capacity/maxRowLength];

    }

    @Override
    public int findNumberofTickets() {
        return Arrays.stream(noBusy).sum();
    }

    private int availableRow(int nrOfTickets) {
        for (int i = 0; i < noBusy.length; i++) {
            if (MAX_ROWLENGTH-noBusy[i] >= nrOfTickets) {
                return i;
            }
        }
        assert false; return -1;
    }

    @Override
    public Ticket[] buyTickets (int nrOfSeats){
        if(findNumberofTickets() != getCapacity()) {
             int avRow= availableRow(nrOfSeats);
            Ticket tickets[] = new Ticket[nrOfSeats];
            Ticket aTicket;
            for (int i = 0; i < nrOfSeats; i++) {
                noBusy[avRow]++;
                aTicket = new SittingTicket(getTribuneName(), getPrice(), avRow+1,(noBusy[avRow]));
                tickets[i] = aTicket;
            }
            return tickets;
        }
        return null;
    }

    @Override
    public Ticket[] buyTickets (String[] nameList){
        if(findNumberofTickets() != getCapacity()) {
            int nrOfSeats = nameList.length;
            int avRow= availableRow(nrOfSeats);
            Ticket tickets[] = new Ticket[nrOfSeats];
            Ticket aTicket;
            for (int i = 0; i < nrOfSeats; i++) {
                noBusy[avRow]++;
                aTicket = new SittingTicket(getTribuneName(), getPrice(), avRow+1,(noBusy[avRow]));
                tickets[i] = aTicket;
            }
            return tickets;
        }
        return null;

    }
}



