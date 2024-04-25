public final class Stand extends Tribune{

    private int noSoldTrickets;

    public Stand(String tribuneName, int capacity, int price) {
        super(tribuneName, capacity, price);
        this.noSoldTrickets = 0;
    }

    @Override
    public int findNumberofTickets() {
        return noSoldTrickets;
    }

    @Override
    public Ticket[] buyTickets(int nrOfSeats) {
        if(findNumberofTickets() != getCapacity()) {
            Ticket tickets[] = new Ticket[nrOfSeats];
            Ticket ticket = new StandingTicket(getTribuneName(), getPrice());
            for (int i = 0; i < nrOfSeats; i++) {
                tickets[i] = ticket;
                noSoldTrickets++;
            }
            return tickets;
        }
        return null;
    }

    @Override
    public Ticket[] buyTickets(String[] nameList) {
        if(findNumberofTickets() != getCapacity()) {
            Ticket ticket[] = new Ticket[nameList.length];
            Ticket aTicket = new StandingTicket(getTribuneName(), getPrice());
            for (int i = 0; i < ticket.length; i++) {
                ticket[i] = aTicket;
                noSoldTrickets++;
            }
            return ticket;
        }
        return null;
    }
}

