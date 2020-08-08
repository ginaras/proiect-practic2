package com.sda.proiect.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.proiect.models.InvoiceImput;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileParserService {

    private final ObjectMapper objectMapper;

    public FileParserService (  ) {
        this.objectMapper = new ObjectMapper();
    }


    //serviciu care se ocupa cu gest fisierului - JSON-+ citirea din fisier
    public List<InvoiceImput> readUserFile ( String path){
        //verific daca fisierul este JSON
        if (!checkJsonType(path)){
            throw new IllegalArgumentException("The file is not a JSON type");
        }
        try {
            File file = new File( path );
            ObjectMapper objectMapper= new ObjectMapper(); //ObjectMapper=
          //  objectMapper.readValue(file, InvoiceImput.class); se face click dreapta pe readValue
           // objectMapper.readValue( file, InvoiceImput.class ); - pt un obiect - ce urmeaza e pt > obiecte
            return objectMapper.readValue( file, objectMapper.getTypeFactory().constructCollectionType ( List.class,InvoiceImput.class ));

        } catch (IOException e) {// din 3 las doar un catch
            e.printStackTrace();
        }

        return null;
    }

    private boolean checkJsonType ( String path ) {
    return path.toLowerCase().endsWith(  ".json" );
    }
}
