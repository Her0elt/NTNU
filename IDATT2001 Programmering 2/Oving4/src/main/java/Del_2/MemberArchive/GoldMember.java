package Del_2.MemberArchive;

import java.time.LocalDate;

public class GoldMember extends  BonusMember {
    public GoldMember(int memberNO, Personals personals, LocalDate enrollDAte, int points) {
        super(memberNO, personals, enrollDAte,points);
    }
    @Override
    public void registerPoints(double points){
        if(points> 1){
            throw new IllegalArgumentException("Du har skrevet inn noe feil");
        }
        super.registerPoints((int)(points*FACTOR_GOLD));
    }

    @Override
    public String toString() {
        return "Status: GoldMember \n"+super.toString();
    }
}
