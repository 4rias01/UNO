/**
 * Defines the contract for serializing and deserializing objects
 * to and from persistent storage using Java's built-in serialization mechanism.
 * Implementations of this interface are responsible for converting objects
 * into a sequence of bytes and reconstructing them from the stored state.
 *
 * @author Santiago Arias, Thomas Herrrera, Isabela Bermudez, Lady Matabanchoy
 * @version 1.0
 */

package com.example.myuno.model.saves.serializable;

import java.io.Serializable;

public interface ISerializableFileHandler {
    /**
     * Serializes the provided object to a file with the specified name.
     * @param filename the path and name of the file to which the object will be serialized
     * @param element  the object to be serialized; must implement {@link Serializable}
     * @version 1.0
     */
    void serialize(String filename, Object element);
    /**
     * Deserializes an object from the file with the specified name.
     * @param filename the path and name of the file from which the object will be deserialized
     * @return the deserialized {@link Object}, reconstructed from the file's contents
     * @version 1.0
     */
    Object deserialize(String filename);
}
