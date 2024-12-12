import java.util.*;
public class Ranch {
    //Global Variables------------------------------
    public static int gMAX_BARNS = 15;
    public static Scanner gSCANNER = new Scanner(System.in);
    public static final int gMAX_OCCUPANCY = 50;
    public static int gBarnNum = 16;
    public static int[] gBarns = new int[gMAX_BARNS];

    //TO EMPTY THE BARN------------------------------
    public static void emptyBarn() {
        int index = -1;
        //VALIDATING THE INDEX
        while (index >= gBarnNum || index < 0) {
            System.out.print("Enter the index (0 to " + (gBarnNum - 1) + "): ");
            index = gSCANNER.nextInt();
            System.out.println();
            if (index >= gBarnNum || index < 0) {
                System.out.println("ERROR, you need to enter a number between 0 and " + (gBarnNum - 1));
            }
        }
        gBarns[index] = 0;
        System.out.println("Barn at index " + index + " was set to zero.");
    }

    //Add or Remove Cattle------------------------------------
    public static void addRemoveCattle() {
        int index = -1;
        int quantity = -1;
        char choice = 'd';
        //VALIDATING THE INDEX
        while (index >= gBarnNum || index < 0) {
            System.out.print("Enter the index (0 to " + (gBarnNum - 1) + "): ");
            index = gSCANNER.nextInt();
            System.out.println();
            if (index > gBarnNum || index < 0) {
                System.out.println("ERROR, you need to enter a number between 0 and " + (gBarnNum - 1) + "(inclusive)");
            }
        }

        //VALIDATING AND ASKING FOR THE QUANTITY
        while (quantity > gMAX_OCCUPANCY || quantity < 0) {
            System.out.println("The current occupancy of the center at index " + index + " is " + gBarns[index]);
            System.out.print("Enter the number of cattle in this operation (0 - 50): ");
            quantity = gSCANNER.nextInt();
            System.out.println();
            if (quantity > gMAX_OCCUPANCY || quantity < 0) {
                System.out.println("ERROR, you need to enter an number between 0 and 50(inclusive)");
            }
        }

        //VALIDATES THE USERS CHOICE
        while (choice != 'Y' && choice != 'N') {
            System.out.print("Are the cattle added to the barn at index " + index + "? (Y/N) ");
            choice = gSCANNER.next().charAt(0);
            System.out.println();
            if (choice != 'Y' && choice != 'N') {
                System.out.println("ERROR, you need to enter either Y or N.");
            }
        }

        //ADDING OR SUBTRACTING THE CATTLE
        switch (choice) {
            case 'Y':
                gBarns[index] = gBarns[index] + quantity;
                System.out.println(quantity + " cattle were added to barn at index " + index);
                break;
            case 'N':
                if (quantity > gBarns[index]) {
                    System.out.println("ERROR, you can't remove more cattle than the ones at barn at index " + index + ". Try again");
                    System.out.println("The current occupancy at that barn is: " + gBarns[index]);
                } else {
                    gBarns[index] = gBarns[index] - quantity;
                }
                break;
        }
    }

    //LIST BARNS
    public static void listBarns() {
        int i;
        System.out.println("List of Cattle Occupancy per Barn");

        for (i = 0; i < gBarns.length; i++) {
            System.out.println("Barn[" + i + "] : " + gBarns[i] + " cattle");
        }
    }

    //ADDING A NEW BARN
    public static void addNewBarn() {
        //CHECKING IF THERE IS ALREADY MAX BARNS
        if (gBarnNum >= gMAX_BARNS) {
            System.out.println("The database is full, no more barns can be added");
            return;
        }
        int[] newBarns = new int[gBarnNum + 1];

        for (int i = 0; i < gBarnNum; i++) {
            newBarns[i] = gBarns[i];
        }

        gBarns = newBarns;
        gBarns[gBarnNum] = 0;
        gBarnNum++;
    }

    //GATHERS A REPORT FOR THE BARNS
    public static void report() {
        System.out.println("Barn Occupancy Classification Summary");
        System.out.println("Under Capacity:\t\t" + getBarnsByOccupancy(0));
        System.out.println("Exact Capacity:\t\t" + getBarnsByOccupancy(1));
        System.out.println("Over Capacity:\t\t" + getBarnsByOccupancy(2));
    }

    //COUNTS FOR HOWEVER MANY THEY HAVE PER BARN
    public static int getBarnsByOccupancy(int code) {
        int count = 0;
        for (int barn : gBarns) {
            switch (code) {
                case 0:
                    if (barn >= 0 && barn < gMAX_OCCUPANCY) {
                        count++;
                    }
                    break;

                case 1:
                    if (barn == gMAX_OCCUPANCY) {
                        count++;
                    }
                    break;
                case 2:
                    if(barn > gMAX_OCCUPANCY){
                        count++;
                    }
                    break;
            }
        }
        return count;
    }

    //MAIN METHOD----------------------------------------
    public static void main(String[] args) {
        int choice = -1;
        boolean doing = true;
        System.out.println("Fall 2024 - UTSA - CS1083 - Section 005 - Project 2 – Ranch - written by Dominic Gasper");

        //ASKING FOR MAX AMOUNT OF BARNS
        while(gBarnNum > gMAX_BARNS || gBarnNum < 0) {
            System.out.print("Please, enter the intial number of barns in the data base (Max 15): ");
            gBarnNum = gSCANNER.nextInt();
            if(gBarnNum > gMAX_BARNS || gBarnNum < 0) {
                System.out.println("ERROR, you need to enter a number between 0 and 15(inclusive)");
            }
        }
            gBarns = new int[gBarnNum];

        //MAIN MENU
        while(doing) {
            while(choice > 5 || choice < 0) {
                System.out.println();
                System.out.println("Main Menu\n0 - Empty Barn, 1 - Add/Remove cattle, 2 - List barns occupancy, 3 - Add new barn, 4 - Report, 5 - Exit");
                System.out.print("Select an option: ");
                choice = gSCANNER.nextInt();
                if (choice > 5 || choice < 0){
                    System.out.println("ERROR, you need to enter a number between 0 and 5(inclusive)");
                }
            }

            //Choices
            switch(choice) {
                case 0:
                    emptyBarn();
                    choice = -1;
                    break;
                case 1:
                    addRemoveCattle();
                    choice = -1;
                    break;
                case 2:
                    listBarns();
                    choice = -1;
                    break;
                case 3:
                    addNewBarn();
                    choice = -1;
                    break;
                case 4:
                    report();
                    choice = -1;
                    break;
                case 5:
                    choice = -1;
                    doing = false;
            }
        }

        //EXIT MESSAGE
        System.out.println("Thank you for using the program!");
    }
}
