import java.util.Scanner;

 class Oving1Oppg1 {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    System.out.println("Skriv inn lengden i tommer");

    double tommer= in.nextDouble();
    double centimeter=tommer*2.54;
    System.out.println("Lengden i Centimeter er: "+centimeter);

  }
}