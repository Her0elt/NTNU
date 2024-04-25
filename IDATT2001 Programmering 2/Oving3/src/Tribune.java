public abstract class Tribune {

    private final String tribuneName;
    private final int capacity;
    private  final int price;

    public Tribune(String tribuneName, int capacity, int price) {
        this.tribuneName = tribuneName;
        this.capacity = capacity;
        this.price = price;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getPrice() {
        return price;
    }

    public String getTribuneName() {
        return tribuneName;
    }

    public abstract int findNumberofTickets();

    public int findIncome(){
        return getPrice()*findNumberofTickets();
    }

    public  abstract Ticket[] buyTickets(int nrOfTickets);

    public  abstract Ticket[] buyTickets(String[] navnListe);




}
