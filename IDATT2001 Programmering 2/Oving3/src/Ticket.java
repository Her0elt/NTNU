abstract class Ticket {
    private final String tribuneName;
    private final int price;
    /**
     * Konstruktør:
     * Tribunenavn må oppgis. Ingen krav til pris.
     */
    public Ticket(String tribuneName, int price) {
        if (tribuneName == null || tribuneName.trim().equals("")) {
            throw new IllegalArgumentException("Tribunenavn må oppgis.");
        }
        this.tribuneName = tribuneName.trim();
        this.price = price;
    }
    public String getTribune() {
        return tribuneName;
    }
    public int getPris() {
        return price;
    }
    public String toString() {
        return "Tribune: "+tribuneName + " Pris: "+price;
    }
}
/**
 * STandTicket.
 */
class StandingTicket extends Ticket {
    public StandingTicket(String tribuneName, int price) {
        super(tribuneName, price);
    }
}
