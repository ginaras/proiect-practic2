package com.sda.proiect.utils.mapers;

import com.sda.proiect.models.Invoice;
import com.sda.proiect.models.InvoiceImput;

import java.util.Date;

public class InputToDatabaseObject {

    private InputToDatabaseObject () {
        //nu ma lasa sa creez un obiect nou in clasa
        throw new IllegalAccessError( "Utility class. ;please do not instantiate" );
    }

    public static Invoice map ( InvoiceImput imput ) {
        Invoice result = new Invoice();
        result.setName( imput.getItemType().getPrettyName() );
        result.setQuantity( imput.getQuantity() );
        result.setTotatPrice( computePrice( imput ) );
        result.setCreated( new Date() ); // sa puna si data

        return result;
    }

    private static Double computePrice ( InvoiceImput imput ) {
        return imput.getQuantity() * imput.getItemType().getUnitPrice();
    }
}
