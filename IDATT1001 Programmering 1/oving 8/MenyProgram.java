package øving8;

import øving8.ArbTaker;

class MenyProgram{
    public static void main(String[]args){
        java.util.Scanner in=new java.util.Scanner(System.in);

        System.out.println("Skriv inn fornavnet til den ansatte");
        String fNavn=in.nextLine();

        System.out.println("Skriv inn etternavnet til den ansatte");
        String eNavn=in.nextLine();

        System.out.println("Skriv inn fødselsåret til den ansatte");
        int fAr=in.nextInt();

        System.out.println("Skriv inn Arbeidstaker nummeret til den ansatte");
        int arbstakernr=in.nextInt();

        System.out.println("Skriv inn Ansettelses År til den ansatte");
        int ansettelsesar=in.nextInt();

        System.out.println("Skriv inn Månedslønn til den ansatte");
        double manedslonn=in.nextDouble();

        System.out.println("Skriv inn Skatteprosent til den ansatte");
        double skatteprosent=in.nextDouble();

        Person person = new Person(fNavn,eNavn,fAr);
        ArbTaker nyArb=new ArbTaker(person, arbstakernr , ansettelsesar, manedslonn,skatteprosent);

        nyArb.getNavn();
        System.out.println("Alder: " +nyArb.getAlder());
        System.out.println("Arbeidstaker nummer: "+nyArb.getArbstakernr());
        System.out.println("Ansettelses år: "+nyArb.getAnsettelsesAr());
        System.out.println("Månedslønn: "+nyArb.getManedslonn());
        System.out.println("Skatteprosent: "+nyArb.getSkatteProsent());

        boolean fortsett=true;
        while(fortsett){
            System.out.println("1: Skattetrekk pr Måned, 2: Bruttolønn, 3: Skattetrekk pr År, 4: Navn 5: Alder 6: Antall år i bedriften 7: Ansatt lengere enn 8: Avslutt");
            int valg=in.nextInt();
            if(valg==1){
                System.out.println("Skattetrekk pr måned: "+nyArb.skatteDrag());
            }
            else if(valg==2){
                System.out.println("Brutto Lønn pr måned: "+nyArb.bruttoLonn());
            }
            else if(valg==3){
                System.out.println("Skattetrekk pr År: "+nyArb.skatteTrekkPrAr());
            }
            else if(valg==4){
                nyArb.getNavn();
            }
            else if(valg==5){
                System.out.println("Alder: "+nyArb.getAlder());
            }
            else if(valg==6){
                System.out.println("Antall år i bedriften: "+nyArb.antallAr());
            }
            else if(valg==7){
                System.out.println("Skriv inn tallet du vil sjekke mot");
                int ar=in.nextInt();
                if(nyArb.gittAr(ar)){
                    System.out.println("Ansatt har vært ansatt mer enn "+ar+"år");
                }
                else{
                    System.out.println("Ansatt har ikke vært ansatt mer enn "+ar+"år");
                }
               
            }
            else{
                fortsett=false;
                break;
            }
            System.out.println("Vil du gjøre endringer 1: ja 2: Nei");
            int endring=in.nextInt();
            if(endring==1){
                System.out.println("Hva vil du endre 1: Navn 2: Alder 3: Arbeidstaker nummer 4: Ansettelses År 5: Månedslønn 6: Skatteprosent" );
                int vEndring=in.nextInt();
                if(vEndring==1){
                }
                else if(vEndring==2){
                    System.out.println("Brutto Lønn pr måned: "+nyArb.bruttoLonn());
                }
                else if(vEndring==3){
                    System.out.println("Skriv inn nytt Arbeidstaker nummer");
                    int nyArbNr=in.nextInt();
                    nyArb.setArbstakernr(nyArbNr);
                }
                else if(vEndring==4){
                    System.out.println("Skriv inn nytt Ansettelses År");
                    int nyAAr=in.nextInt();
                    nyArb.setAnsettelsesAr(nyAAr);
                }
                else if(vEndring==5){
                    System.out.println("Skriv inn ny månedslønn");
                    int nyMLonn=in.nextInt();
                    nyArb.setManedslonn(nyMLonn);
                }
                else if(vEndring==6){
                    System.out.println("Skriv inn ny Skatteprosent");
                    int nySprosent=in.nextInt();
                    nyArb.setSkatteprosent(nySprosent);
                }

            }
            else if(endring==2){

            }

        }


    }


    }


