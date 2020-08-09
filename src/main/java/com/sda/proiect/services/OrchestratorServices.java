package com.sda.proiect.services;

import com.sda.proiect.dataBase.dao.InvoiceDAO;
import com.sda.proiect.models.Invoice;
import com.sda.proiect.models.InvoiceImput;
import com.sda.proiect.utils.mapers.InputToDatabaseObject;
import org.hibernate.SessionFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class OrchestratorServices {

    private final FileParserService fileParserService;
    private final InvoiceDAO dao;


    public OrchestratorServices ( SessionFactory session ) { //refactorizare Session sesion
        this.fileParserService = new FileParserService();
        this.dao = new InvoiceDAO( session ); //refactorizare session
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
                    writeContentToDatabase( path );
                    printmenu();
                    // System.out.println( "You wrote " + path );
                    break;
                case 2:
                    printAllInvoices();
                    printmenu();
                    break;
                case 3:
                    printTodayEntries();
                    printmenu();

                    break;
                case 4:
                    System.out.println( "in progres" );
                    break;
                default:
                    printmenu();
            }
        }

    }

    private void printTodayEntries () {
        List<Invoice> invoices = dao.getAllInvoices();
        //todo extragem data de azi din care pastaram data calendaristica y-m-d
        String today = extractToday();
        //todo filtram lista pe baza datei
//        List<Invoice> collect = invoices.stream()
//                .filter( invoice -> invoice.getCreated()!= null )
//                .filter(invoice -> today.equals( invoice.getCreated().toString()))
//                .collect( Collectors.toList() );

//      Este acelasi lucu ce e sus cu ce e jos

        Predicate<Invoice> isNotNullDate = invoice -> invoice.getCreated() != null;
        Predicate<Invoice> isCreatedToday = invoice -> today.equals( invoice.getCreated().toString() );
        List<Invoice> collect = invoices.stream()
                .filter( isNotNullDate )
                .filter( isCreatedToday )
                .collect( Collectors.toList() );

        //todo printam
        printInvoices( collect );

    }

    private String extractToday () {
        SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd" );

        return formatter.format( new Date() );
    }

    private void printAllInvoices () {
        List<Invoice> invoices = dao.getAllInvoices();

        printInvoices( invoices );
//
//        for (Invoice temp: invoices){
//            System.out.println(temp);
//        }
        System.out.println( "\n" );

    }

    private void printInvoices ( List<Invoice> invoices ) {
        System.out.println( "U have retrived " + invoices.size() + " entries:" );
        //todo aliniati coloanele la nr
        for (int i = 0; i < invoices.size(); i++) {
            String line = String.format( "%s. %s", (i + 1), invoices.get( i ) );
            System.out.println( line );
        }
    }

    private void writeContentToDatabase ( String path ) {
        List<InvoiceImput> invoiceImputs = fileParserService.readUserFile( path );//parcurgerea fisierului

        System.out.println( "Successfully read " + invoiceImputs.size() + "items from path" );

        for (InvoiceImput inputElement : invoiceImputs) {
            //map element from InvoiceImput Tipe to Invoice type            //so we can save to database
            Invoice mappedImput = InputToDatabaseObject.map( inputElement );
            boolean isSaved = dao.createInvoice( mappedImput );
            if (!isSaved) {
                System.out.println( "could not save to to database the following item: " + mappedImput.toString() );//in viata reala se salveaza intr-un logFile

            } else {
                System.out.println( "suscces saved  " + mappedImput.toString() );
            }
        }
    }

    public void printmenu () {
        System.out.println( "Please choose an option" );
        System.out.println( "0- Exit" );
        System.out.println( "1- Imput file" );
        System.out.println( "2- Get all entries" );
        System.out.println( "3- Get today entrys" );
        System.out.println( "4- Generate Z report" );
        System.out.println( "5- Show menu" );
        System.out.println( "\n" );
    }

}
