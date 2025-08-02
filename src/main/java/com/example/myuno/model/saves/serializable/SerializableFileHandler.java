/**
 * Concrete implementation of {@link com.example.myuno.model.saves.serializable.ISerializableFileHandler}
 * responsible for serializing and deserializing Java objects to and from files
 * using Java's native serialization mechanism.
 * <p>
 * This class manages object streams and handles I/O operations for persisting
 * and reconstructing objects from the file system.
 * </p>
 *
 * @author Santiago Arias, Thomas Herrera, Isabela Bermudez, Lady Matabanchoy
 * @version 1.0
 */

package com.example.myuno.model.saves.serializable;

import java.io.*;

public class SerializableFileHandler implements ISerializableFileHandler {
    /**
     * Serializes the provided object to a file with the specified name.
     * @param filename the path and name of the file to which the object will be serialized
     * @param element  the object to be serialized; must implement {@link java.io.Serializable}
     * @version 1.0
     */
    @Override
    public void serialize(String filename, Object element) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))){
            oos.writeObject(element);
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
    /**
     * Deserializes an object from the file with the specified name.
     * @param filename the path and name of the file from which the object will be deserialized
     * @return the reconstructed object, or {@code null} if deserialization fails
     * @version 1.0
     */
    @Override
    public Object deserialize(String filename) {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))){
            return ois.readObject();
        }catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
