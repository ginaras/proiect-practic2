package com.sda.proiect.models;

public class InvoiceImput {


    private Integer quantity;
    private ItemType itemType; // enumul creat-SE FACE CLASA TIP ENUM

    // alt+insert : constructor gol, 3 constructori, get+set, toString


    public InvoiceImput ( String productName, Integer quantity, ItemType itemType ) {
        this.quantity = quantity;
        this.itemType = itemType;
    }



    public Integer getQuantity () {
        return quantity;
    }

    public void setQuantity ( Integer quantity ) {
        this.quantity = quantity;
    }

    public ItemType getItemType () {
        return itemType;
    }

    public void setItemType ( ItemType itemType ) {
        this.itemType = itemType;
    }

    public InvoiceImput () {
    }

    @Override
    public String toString () {
        final StringBuilder sb = new StringBuilder( "InvoiceImput{" );
        sb.append( ", quantity=" ).append( quantity );
        sb.append( ", itemType=" ).append( itemType );
        sb.append( '}' );
        return sb.toString();
    }
}
