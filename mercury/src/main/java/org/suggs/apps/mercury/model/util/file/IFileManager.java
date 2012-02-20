/*
 * IMercuryXmlFilePersister.java created on 3 Oct 2008 18:48:46 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.util.file;

import java.io.File;
import java.io.IOException;

/**
 * This interface manages the persistence and retrieval of all files to the underlying operating system. The
 * interfaces here are at the file level and have no concept of the use of these files.
 * 
 * @author suggitpe
 * @version 1.0 3 Oct 2008
 */
public interface IFileManager {

    /**
     * This method will allow any string to be persisted to a given file location. It will also verify that
     * the persistence layer is correctly set up
     * 
     * @param aClob
     *            the string to create the file with
     * @param aFile
     *            the file that you want to use to persist the file
     * @throws IOException
     *             if there are any issues in the persistence layer
     */
    void persistClobToFile( String aClob, File aFile ) throws IOException;

    /**
     * This method will retrieve a file from the persistence layer and return the data in the form of a
     * String.
     * 
     * @param aFile
     *            the file from which to read the data
     * @return the String representation of the data in the file
     * @throws IOException
     *             if there are any issues in reading the file data
     */
    String retrieveClobFromFile( File aFile ) throws IOException;

    /**
     * This method will retrieve a file from a persistence layer on the classpath
     * 
     * @param aResource
     *            the resource to retrieve
     * @return the String representation of the resource
     * @throws IOException
     *             if there are any issues in reading the file
     */
    String retrieveClobFromResource( String aResource ) throws IOException;

    byte[] retrieveBytesFromFile( File aFile ) throws IOException;

}
