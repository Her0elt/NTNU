import java.time.*;

public class BonusMember {
    protected static final double FACTOR_SILVER = 1.2;
    protected static final double FACTOR_GOLD = 1.5;
    private final int memberNO;
    private final Personals personals;
    private final LocalDate enrollDate;
    private int points = 0;

    public BonusMember(int memberNO, Personals personals, LocalDate enrollDate) {
        this.memberNO = memberNO;
        this.personals = personals;
        this.enrollDate = enrollDate;
    }

    public BonusMember(int memberNO, Personals personals, LocalDate enrollDate,int points) {
        this.memberNO = memberNO;
        this.personals = personals;
        this.enrollDate = enrollDate;
        this.points = points;
    }

    public int getMemberNO() {
        return memberNO;
    }

    public Personals getPersonals() {
        return personals;
    }

    public LocalDate getEnrolledDate() {
        return enrollDate;
    }

    public int getPoints() {
        return points;
    }


    public int findQualificationPoints(LocalDate dato){
        int daysBetween = Period.between(enrollDate, dato).getYears();
        if(daysBetween > 0) {
            return 0;
        }
        return points;
    }
    public boolean okPassord(String password){
        return personals.okPassword(password);
    }

    public void registerPoints(double points){
        this.points += points;
    }

    @Override
    public String toString() {
        return "Name: "+ this.personals.getSurname()+ " , " + this.personals.getFirstname()+"\n"+
                "Email: "+this.personals.getEPostadr()+ "\n"+
                "Member nummber: "+ this.memberNO +"\n";
    }
}



