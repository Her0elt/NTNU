public final class VIP extends Tribune{

    private String[][]spectator;
    private final int MAX_ROWLENGTH;

    public VIP(String tribuneName, int capacity, int price, int maxRowlength) {
        super(tribuneName, capacity, price);
        this.MAX_ROWLENGTH = maxRowlength;
        this.spectator = new String[capacity/maxRowlength][maxRowlength];
    }

    private int availableRow(int nrOfTickets) {
        for (int i = 0; i < spectator.length; i++) {
            int nrBusy = 0 ;
            for (int j = 0; j <spectator[i].length ; j++) {
                    if (spectator[i][j] != null) {
                        nrBusy++;
                    }
                }

            if(MAX_ROWLENGTH-nrBusy >= nrOfTickets) {
                return i;
            }
        }
         assert false; return -1;
    }

    private int[] findAvailableSeats(int rowNr){
        int []available = new int[spectator[rowNr].length];
        for (int i = 0; i < spectator[rowNr].length; i++) {
            if (spectator[rowNr][i] == (null)) {
                    available[i] = i;

            }
        }
        return available;
    }

    @Override
    public int findNumberofTickets() {
        int nrOfTickets = 0 ;
        for (int i = 0; i < spectator.length; i++) {
            for (int j = 0; j <spectator[i].length ; j++) {
                if(spectator[i][j] !=null){
                    nrOfTickets++;
                }
            }
        }
        return nrOfTickets;
    }

    @Override
    public Ticket[] buyTickets(int nrOfSeats) {
        return null;
    }

    @Override
    public Ticket[] buyTickets(String[] nameList) {
        if(findNumberofTickets() != getCapacity()) {
            int nrOfSeats = nameList.length;
            int avRow= availableRow(nrOfSeats);
            Ticket tickets[] = new Ticket[nrOfSeats];
            Ticket aTicket;
             int [] available = findAvailableSeats(avRow);
            for (int j = 0; j <nrOfSeats ; j++) {
                    aTicket = new SittingTicket(getTribuneName(), getPrice(), avRow+1,available[j]+1);
                    tickets[j] = aTicket;
                    spectator[avRow][available[j]]=nameList[j];
            }
            return tickets;
        }
        return null;
    }

}



