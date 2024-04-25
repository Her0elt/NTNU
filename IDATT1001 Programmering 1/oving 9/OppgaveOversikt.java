package øving9;

import java.util.ArrayList;

public class OppgaveOversikt {
    private Student[] studenter;
    private int antStud=0;

    public OppgaveOversikt(){

        studenter=new Student[1];
    }

    public int getAntStud() {

        return antStud;
    }

    public int getAntallOgg(String navn){
        int antallOppg=0;
        for(int i=0;i<studenter.length;i++){
            if(studenter[i].getNavn().equals(navn)){
                antallOppg=studenter[i].getAntOppg();


            }
        }
        return antallOppg;
    }


    public void endreArray(){
        Student[] nyArray=new Student[studenter.length+1];
        for (int i=0;i<studenter.length;i++){
            nyArray[i]=studenter[i];
        }
        studenter=nyArray;
    }
    public void leggTilStudent(int antOppger, String navn) {
        if(studenter.length==antStud){
        endreArray();
        }
        studenter[antStud]=new Student(navn, antOppger);
        antStud++;
    }

    public void okAntallOppg(int okning, String navn){
        for(int i=0;i<studenter.length;i++){
            if(studenter[i].getNavn().equals(navn)){
                studenter[i].okAntOppg(okning);
            }
        }
    }
}
