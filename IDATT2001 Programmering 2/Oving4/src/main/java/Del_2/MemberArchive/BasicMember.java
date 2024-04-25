package Del_2.MemberArchive;

import java.time.LocalDate;

public class BasicMember extends BonusMember {
    public BasicMember(int memberNO, Personals personals, LocalDate enrollDAte) {
        super(memberNO, personals, enrollDAte);
    }
    @Override
    public String toString() {
        return "Status: BasicMember \n "+super.toString();
    }
}
