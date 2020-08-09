package com.sda.proiect.dataBase.dao;

import com.sda.proiect.models.Invoice;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class InvoiceDAO {

    private final SessionFactory sessionFactory;//refactorizare add + urm 2

    public InvoiceDAO ( SessionFactory sessionFactory ) {
        this.sessionFactory = sessionFactory;
    }


    //create
    public boolean createInvoice ( Invoice invoice ) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) { // try wuth resurces
            //refactorizare
            transaction = session.beginTransaction();
            session.save( invoice );
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }

//    //afisare de aici nu este scris de SERA
//    public Invoice findInvoice (Long id){
//        Transaction transaction= null;
//        Invoice result =null;
//        try {
//            Session session = HibernateConfiguration.getSessionFactory().openSession();
//
//            transaction= session.beginTransaction();
//            result=session.find(Invoice.class, id);
//            transaction.commit();
//        } catch (HibernateException e) {
//            if (transaction!=null){transaction.rollback();}
//            e.printStackTrace();
//        }
//        return result;
//    }
//    public void updateInvoice (Invoice invoice){
//        Transaction transaction = null;
//        try{
//            Session session =HibernateConfiguration.getSessionFactory().openSession();
//            transaction = session.beginTransaction();
//        } catch (HibernateException e) {
//            if (transaction != null){
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }
//    }
//    public void deleteInvoice (Invoice invoice){
//        Transaction transaction = null;
//        try {
//            Session session = HibernateConfiguration.getSessionFactory().openSession();
//            transaction = session.beginTransaction();
//            session.delete( invoice );
//            transaction.commit();
//        } catch (HibernateException e) {
//            if(transaction != null){
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }
//    }

    public List<Invoice> getAllInvoices () {
        try (Session session = sessionFactory.openSession()) { // try wuth resurces
            //refactorizare
            return session.createQuery( "from Invoice", Invoice.class ).list();

        } catch (Exception e) {

            e.printStackTrace();

            return new ArrayList<>();

//        Transaction transaction= null;
//        List<Invoice> invoices=null;
//        try{
//            Session session= HibernateConfiguration.getSessionFactory().openSession();
//            transaction = session.beginTransaction();
//            invoices =session.createQuery("fromInvoice", Invoice.class).list();
//
//        } catch (HibernateException e) {
//            e.printStackTrace();
//        }

        }


    }
}
