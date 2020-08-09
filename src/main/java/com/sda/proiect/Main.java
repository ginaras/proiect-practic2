package com.sda.proiect;

import com.sda.proiect.dataBase.configuration.HibernateConfiguration;
import com.sda.proiect.services.OrchestratorServices;
import org.hibernate.SessionFactory;

public class Main {
    public static void main ( String[] args ) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory(); //refactorizare- o deschid pt o refolosi pe prcursul rularii

        // OrchestratorServices orchestratorServices= new OrchestratorServices(); //refactorizare
        OrchestratorServices orchestratorServices = new OrchestratorServices( sessionFactory );//refactorizare
        orchestratorServices.runApplication();

        sessionFactory.close();
    }

}
