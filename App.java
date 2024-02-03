import java.util.Scanner;

public class Main extends RealOperation {
    public static void main(String[] args) {
        Scanner scStr = new Scanner(System.in);
        Scanner scInt = new Scanner(System.in);
        Real m, n, result;
        long start, end;
        int choice;

        while (true) {
            System.out.println("Which operation do you want to do with m and n");
            System.out.println("\t1 addition");
            System.out.println("\t2 subtraction");
            System.out.println("\t3 multiplication");
            System.out.println("\t4 division");
            System.out.println("\t5 modulo");
            System.out.println("0 stop the program.");
            System.out.print(": ");
            choice = scInt.nextInt();

            if (choice == 0) {
                break;
            }

            System.out.print("Enter m : ");
            m = new Real(scStr.nextLine());

            System.out.print("Enter n : ");
            n = new Real(scStr.nextLine());

            if (choice == 1) {
                start = System.currentTimeMillis();
                result = RealOperation.add(m,n);
                end = System.currentTimeMillis();
                System.out.println(m.getFullNumber() + " + " + n.getFullNumber());
            }
            else if (choice == 2) {
                start = System.currentTimeMillis();
                result = RealOperation.minus(m,n);
                end = System.currentTimeMillis();
                System.out.println(m.getFullNumber() + " - " + n.getFullNumber());
            }
            else if (choice == 3) {
                start = System.currentTimeMillis();
                result = RealOperation.multiply(m,n);
                end = System.currentTimeMillis();
                System.out.println(m.getFullNumber() + " * " + n.getFullNumber());
            }
            else if (choice == 4) {
                start = System.currentTimeMillis();
                result = RealOperation.divideInteger(m,n);
                end = System.currentTimeMillis();
                System.out.println(m.getFullNumber() + " / " + n.getFullNumber());
            }
            else if (choice == 5) {
                start = System.currentTimeMillis();
                result = RealOperation.divideInteger(m,n);
                end = System.currentTimeMillis();
                System.out.println(m.getFullNumber() + " % " + n.getFullNumber());
            }
            else {
                continue;
            }

            System.out.println("= " + result.getFullNumber());
            System.out.println("Estimate time : "+(end - start)+" ms");
            System.out.println("--------------------------------------------");
        }
    }
}
