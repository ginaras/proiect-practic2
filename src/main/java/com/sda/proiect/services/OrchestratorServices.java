package com.sda.proiect.services;

import com.sda.proiect.models.InvoiceImput;

import java.util.List;
import java.util.Scanner;

public class OrchestratorServices {

    private final FileParserService fileParserService;

    public OrchestratorServices (  ) {
        this.fileParserService = new FileParserService();
    }



    public void runApplication () {
        Scanner scanner = new Scanner( System.in );
        boolean isRuning = true;
        printmenu();
        while (isRuning) {
            int option = scanner.nextInt();
            scanner.nextLine();// known isued of scanner

            switch (option) {
                case 0:
                    System.out.println( "Thank for using application!" );
                    isRuning = false;
                    break;

                case 1:
                    System.out.print( "Please input the path to file: " );
                    String path = scanner.nextLine();
                    writeContentToDatabase(path);
                    System.out.println( "You wrote " + path );
                    break;
                case 2:
                    System.out.println( "in progres" );
                    break;
                case 3:
                    System.out.println( "in progres" );
                    break;
                case 4:
                    System.out.println( "in progres" );
                    break;
                default:
                    printmenu();
            }
        }

    }

    private void writeContentToDatabase ( String path ) {
        List<InvoiceImput> invoiceImputs = fileParserService.readUserFile( path );

        System.out.println("Successfully read "+invoiceImputs.size() +"items from path");
        System.out.println("PLACEHOLDER: Write to db....");
    }

    public void printmenu () {
        System.out.println( "Please choose an option" );
        System.out.println( "0-Exit" );
        System.out.println( "1- Imput file" );
        System.out.println( "2- Get all entries" );
        System.out.println( "3- Get today entrys" );
        System.out.println( "4 - Generate Z report" );
        System.out.println( "5- Show menu" );
        System.out.println( "\n" );
    }

}
