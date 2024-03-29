import java.util.Scanner;

public class Main extends RealOperation {
    public static void main(String[] args) {
        Scanner scStr = new Scanner(System.in);
        Scanner scInt = new Scanner(System.in);
        Real m, n, result;
        int choice, choiceSame, point;
        boolean ChooseChoice, SameChoice;

        choice = 0;
        ChooseChoice = true;
        SameChoice = false;
        while (true) {
            if (SameChoice) {
                while (true) {
                    System.out.println("Do you want to do same operation???");
                    System.out.println("\t1 yes");
                    System.out.println("\t0 no");
                    System.out.print(": ");
                    choiceSame = scInt.nextInt();
                    if (choiceSame == 0) {
                        System.out.println("--------------------------------------------");
                        break;
                    }
                    if (choiceSame == 1) {
                        break;
                    }
                    System.out.println("--------------------------------------------");
                }
                ChooseChoice = choiceSame == 0;
            }

            if (ChooseChoice) {
                System.out.println("Which operation do you want to do with m and n???");
                System.out.println("\t1 addition");
                System.out.println("\t2 subtraction");
                System.out.println("\t3 multiplication");
                System.out.println("\t4 division (30 places decimal output)");
                System.out.println("\t5 division (customizable places decimal output)");
                System.out.println("\t6 division (integer output)");
                System.out.println("\t7 modulo");
                System.out.println("0 stop the program.");
                System.out.print(": ");
                choice = scInt.nextInt();
            }

            if (choice == 0) {
                break;
            }
            System.out.println("--------------------------------------------");
            SameChoice = choice >= 1 && choice <= 7;
            if (!SameChoice) {
                continue;
            }

            System.out.print("Enter m : ");
            m = new Real(scStr.nextLine());

            System.out.print("Enter n : ");
            n = new Real(scStr.nextLine());

            if (choice == 1) {
                result = RealOperation.add(m,n);
                System.out.println(m.getFullNumber() + " + " + n.getFullNumber());
            }
            else if (choice == 2) {
                result = RealOperation.minus(m,n);
                System.out.println(m.getFullNumber() + " - " + n.getFullNumber());
            }
            else if (choice == 3) {
                result = RealOperation.multiply(m,n);
                System.out.println(m.getFullNumber() + " * " + n.getFullNumber());
            }
            else if (choice == 4) {
                result = RealOperation.divide(m,n);
                System.out.println(m.getFullNumber() + " / " + n.getFullNumber());
            }
            else if (choice == 5) {
                System.out.print("\tEnter a decimal point : ");
                point = scInt.nextInt();
                result = RealOperation.divide(m,n,point);
                System.out.println(m.getFullNumber() + " / " + n.getFullNumber());
            }
            else if (choice == 6) {
                result = RealOperation.divideInteger(m,n);
                System.out.println(m.getFullNumber() + " % " + n.getFullNumber());
            }
            else {
                result = RealOperation.modulo(m,n);
                System.out.println(m.getFullNumber() + " % " + n.getFullNumber());
            }

            System.out.println("= " + result.getFullNumber());
            System.out.println("--------------------------------------------");
        }
        System.out.println(" ---------------------------------------");
        System.out.println("|     E N D   T H E   P R O G R A M     |");
        System.out.println("|               made by \" Vampirality \" |");
        System.out.println(" ---------------------------------------");
    }
}
