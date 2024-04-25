import java.time.LocalDate;

public class SilverMember extends BonusMember {
    public SilverMember(int memberNO, Personals personals, LocalDate enrollDAte, int points) {
        super(memberNO, personals, enrollDAte, points);



    }
    @Override
    public void registerPoints(double points){
        super.registerPoints((int)(points*FACTOR_SILVER));
    }
}
