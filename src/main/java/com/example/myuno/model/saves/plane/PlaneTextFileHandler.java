/**

 * responsible for reading from and writing to plain text files using standard Java I/O.
 * <p>
 * This class manages file streams and ensures that content is correctly persisted
 * and retrieved as specified by the interface contract.
 * </p>
 *
 * @author Santiago Arias, Thomas Herrrera, Isabela Bermudez, Lady Matabanchoy
 * @version 1.0
 */

package com.example.myuno.model.saves.plane;

import java.io.*;

public class PlaneTextFileHandler implements IPlaneTextFileHandler{

    /**
     * Writes the specified content to a text file with the given name.
     * <p>

     * to efficiently write the content and flush the stream upon completion.

     * @param fileName the path and name of the file to which content will be written
     * @param content  the text that is to be saved into the file
     * @throws RuntimeException if an I/O error occurs during writing
     * @version 1.0
     */

    @Override
    public void writeToFile(String fileName, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
            writer.flush();
        } catch (IOException e) {
            System.out.println("socio, la cago escribiendo el archivo plano");
            throw new RuntimeException(e);
        }
    }
    /**
     * Reads the contents of the specified text file and returns each line
     * trimmed and concatenated into a single array of strings.
     * <p>
     * Uses a {@link BufferedReader} with a {@link FileReader} to read lines until EOF,
     * trimming whitespace and delimiting entries by commas. Any I/O errors are printed
     * to the console.
     * </p>
     *
     * @param fileName the path and name of the file from which content will be read
     * @return an array of strings representing individual entries read from the file

     * @version 1.0
     */
    @Override
    public String[] readFromFile(String fileName) {
        StringBuilder content = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line;
            while ((line = reader.readLine()) != null){
                content.append(line.trim()).append(",");
            }
        } catch (IOException e){
            System.out.println("socio, la cago leyendo el archivo plano");
            e.printStackTrace();
        }
        return content.toString().split(",");
    }
}
