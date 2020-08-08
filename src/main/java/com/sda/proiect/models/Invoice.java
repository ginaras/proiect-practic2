package com.sda.proiect.models;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(schema = "Store", name = "invoices") //javax.persistance
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Double totatPrice;

    @Temporal( TemporalType.DATE )
    @Column(name = "created_date")
    private Date created;

    public Invoice () {
    }

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName ( String name ) {
        this.name = name;
    }

    public Integer getQuantity () {
        return quantity;
    }

    public void setQuantity ( Integer quantity ) {
        this.quantity = quantity;
    }

    public Double getTotatPrice () {
        return totatPrice;
    }

    public void setTotatPrice ( Double totatPrice ) {
        this.totatPrice = totatPrice;
    }

    public Date getCreated () {
        return created;
    }

    public void setCreated ( Date created ) {
        this.created = created;
    }

    @Override
    public String toString () {
        final StringBuilder sb = new StringBuilder( "Invoice{" );
        sb.append( "id=" ).append( id );
        sb.append( ", name='" ).append( name ).append( '\'' );
        sb.append( ", quantity=" ).append( quantity );
        sb.append( ", totatPrice=" ).append( totatPrice );
        sb.append( ", created=" ).append( created );
        sb.append( '}' );
        return sb.toString();
    }
}
