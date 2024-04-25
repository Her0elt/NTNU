package Del_2.MemberArchive;

import java.time.*;

public class BonusMember {
    protected static final double FACTOR_SILVER = 1.2;
    protected static final double FACTOR_GOLD = 1.5;
    private final int memberNO;
    private final Personals personals;
    private final LocalDate enrollDate;
    private int points;

    public BonusMember(int memberNO, Personals personals, LocalDate enrollDate){
        if(memberNO == 0 || personals == null || enrollDate == null){
            throw new IllegalArgumentException("Du har skrevet inn noe feil");
        }
        this.memberNO = memberNO;
        this.personals = personals;
        this.enrollDate = enrollDate;
        this.points = 0;
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


    public int findQualificationPoints(LocalDate dato) {
        if(dato == null){
            throw new IllegalArgumentException("Du har skrevet inn noe feil");
        }
        int daysBetween = Period.between(enrollDate, dato).getYears();
        System.out.println(daysBetween);
        if(daysBetween == 0) {
            return points;
        }
        return -1;
    }
    public boolean okPassord(String password){
        if (password == null) throw new IllegalArgumentException("Du har skrevet inn noe feil");
        return personals.okPassword(password);
    }

    public void registerPoints(double points){
        if(points< 1){
            throw new IllegalArgumentException("Du har skrevet inn noe feil");
        }
        this.points += points;
    }

    @Override
    public String toString() {
        return "Name: "+ this.personals.getSurname()+ " , " + this.personals.getFirstname()+"\n"+
                "Email: "+this.personals.getEPostadr()+ "\n"+
                "Member nummber: "+ this.memberNO +"\n"+
                "Points: "+this.points+"\n";
    }
}



