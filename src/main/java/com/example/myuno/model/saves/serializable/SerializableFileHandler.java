package com.example.myuno.model.saves.serializable;

import java.io.*;

public class SerializableFileHandler implements ISerializableFileHandler {
    @Override
    public void serialize(String filename, Object element) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))){
            oos.writeObject(element);
        } catch ( IOException e ) {
            System.out.println("algo fallo en escribiendo el objeto manin");
            e.printStackTrace();
        }
    }

    @Override
    public Object deserialize(String filename) {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))){
            return ois.readObject();
        }catch (IOException | ClassNotFoundException e) {
            System.out.println("algo fallo recibiendo el objeto manin");
            e.printStackTrace();
        }
        return null;
    }
}
