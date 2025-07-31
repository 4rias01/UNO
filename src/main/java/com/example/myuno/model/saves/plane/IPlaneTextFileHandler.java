/**
 * Defines the contract for reading from and writing to a plain text file
 * within the UNO game saving subsystem. Implementations of this interface
 * are responsible for serializing content to storage and retrieving it
 * as an array of strings.
 *
 * @author Santiago Arias, Thomas Herrrera, Isabela Bermudez, Lady Matabanchoy
 * @version 1.0
 */
package com.example.myuno.model.saves.plane;


public interface IPlaneTextFileHandler {

    /**
     * Writes the provided content to a text file identified by the given name.
     * Implementations should handle all I/O considerations and ensure that
     * the content is persisted correctly to the designated storage medium.
     *
     * @param fileName the name (and path, if applicable) of the file to write to
     * @param content  the textual content to be written into the file
     * @version 1.0
     */
    void writeToFile(String fileName, String content);

    /**
     * Reads the entirety of a text file identified by the given name and
     * returns its contents as an array of strings, where each element
     * corresponds to one line of the file.
     *
     * @param fileName the name (and path, if applicable) of the file to read from
     * @return a non-null array of strings, each representing a line in the file
     * @version 1.0
     */
    String[] readFromFile(String fileName);
}
