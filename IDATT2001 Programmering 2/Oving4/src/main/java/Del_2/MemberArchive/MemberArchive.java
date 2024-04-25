package Del_2.MemberArchive;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class MemberArchive {
    static final int SILVER_LIMIT = 25000;
    static final int GOLD_LIMIT = 75000;
    private final int MAKS_TRY = 1000;
    private static final Random NUMBER = new Random();
    private  ArrayList<BonusMember> members;

    public MemberArchive() {
        this.members = new ArrayList<>();
    }

    public ArrayList<BonusMember> getMembers() {
        return members;
    }

    private BonusMember findMember(int mNr){
        for (BonusMember bm: members ) {
            if(bm.getMemberNO() == mNr){
                return bm;
            }
        }
        return null;
    }
    private int findPoints(int mNr, String password){
        BonusMember member = findMember(mNr);
        if(member != null){
            if(member.okPassord(password)) {
                return member.getPoints();
            }
        }
        return -1;
    }
    public boolean registerPoints(int no, int number) {
        BonusMember member = findMember(no);
       if (member instanceof BasicMember) {
            (member).registerPoints(number);
            return true;
        } else if (member instanceof SilverMember) {
            (member).registerPoints(number);
            return true;
        } else if(member instanceof GoldMember){
            ( member).registerPoints(number);
        return true;
        }
        else{
            return false;
        }
    }

    public int newMember(Personals personals, LocalDate enrollDate){
            BasicMember member = new BasicMember(findAvailableNO(), personals, enrollDate);
            members.add(member);
            return member.getMemberNO();

    }
    private int findAvailableNO(){
        boolean alike = true;
        int number= 0;
        int tall;
        while (alike){
            tall = 0;
            number = NUMBER.nextInt(MAKS_TRY)+1;
                for (BonusMember bm : members) {
                    if (bm.getMemberNO() == number) {
                        tall = 1;
                        break;
                    }
                }
                if (tall == 0) alike = false;
            }
        return number;
    }
    private BonusMember checkGoldLimit(int mNr, LocalDate d){
        BonusMember bm = findMember(mNr);
        if(bm != null) {
            if (bm.getPoints() >= GOLD_LIMIT && bm.findQualificationPoints(d) != 0) {
                GoldMember gm = new GoldMember(bm.getMemberNO(), bm.getPersonals(), bm.getEnrolledDate(), bm.getPoints());
                return gm;
            }
        }
        return bm;
    }
    private BonusMember checkSilverLimit(int mNr, LocalDate d){
        BonusMember bm = findMember(mNr);
        if(bm !=null) {
            if (bm.getPoints() >= SILVER_LIMIT && bm.getPoints() < GOLD_LIMIT && bm.findQualificationPoints(d) != 0) {
                SilverMember sm = new SilverMember(bm.getMemberNO(), bm.getPersonals(), bm.getEnrolledDate(), bm.getPoints());
                return sm;
            }
        }
        return bm;
    }
    public void checkMember(LocalDate d ){
        for (int i = 0; i< members.size(); i++ ) {
             if(members.get(i) instanceof BasicMember || members.get(i) instanceof SilverMember){
                    members.set(i, checkGoldLimit(members.get(i).getMemberNO(), d));
            }
             if(members.get(i) instanceof BasicMember) {
                 members.set(i, checkSilverLimit(members.get(i).getMemberNO(), d));
            }
        }
    }

    public  String createOutput(LocalDate p){
       return  "You became a member on " + p.getDayOfMonth() + " , " + p.getMonth() +
                " , "+ p.getYear()+ "\n";


    }

    @Override
    public String toString() {
        String toString ="";
        for (BonusMember bm: members) {
            toString += bm.toString() + createOutput(bm.getEnrolledDate())+"\n";
        }
        return toString;
    }

    public static void main(String[] args) {
        MemberArchive ma = new MemberArchive();
        Personals ole = new Personals("Olsen", "Ole", "ole.olsen@dot.com", "ole");
        Personals tove = new Personals("Hansen", "Tove", "tove.hansen@dot.com", "tove");
        System.out.println(ma.newMember( ole, LocalDate.of(2006, 2, 15)));
        System.out.println(ma.newMember( tove, LocalDate.of(2007, 3, 5)));
        LocalDate testdato = LocalDate.of(2007, 2, 10);
        System.out.println(ma.registerPoints(ma.getMembers().get(0).getMemberNO(), 3000));
        System.out.println(ma.registerPoints(ma.getMembers().get(1).getMemberNO() , 3000));
        System.out.println(ma.findAvailableNO());
        System.out.println(ma.findPoints(ma.getMembers().get(0).getMemberNO(), "ole"));
        System.out.println(ma.findPoints(ma.getMembers().get(1).getMemberNO(),"tove"));
        System.out.println(ma.toString());
        System.out.println(ma.findAvailableNO());
        System.out.println(ma.registerPoints(ma.getMembers().get(0).getMemberNO(), 25000));
        ma.checkMember(testdato);
        System.out.println(ma.toString());
        System.out.println(ma.findAvailableNO());
        System.out.println(ma.registerPoints(ma.getMembers().get(1).getMemberNO(), 750000));
        ma.checkMember(testdato);
        System.out.println(ma.toString());
    }
}


