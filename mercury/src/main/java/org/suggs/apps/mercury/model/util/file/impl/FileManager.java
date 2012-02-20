/*
 * MercuryFilePersister.java created on 3 Oct 2008 18:49:23 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.util.file.impl;

import org.suggs.apps.mercury.model.util.file.IFileManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This implementation is the basic implementation that uses the NIO channels for persistence and retrieval.
 * 
 * @author suggitpe
 * @version 1.0 3 Oct 2008
 */
public class FileManager implements IFileManager {

    private static final Logger LOG = LoggerFactory.getLogger( FileManager.class );
    private static final String CHARSET = "UTF-8";

    /**
     * @see org.suggs.apps.mercury.model.util.file.IFileManager#retrieveClobFromFile(java.io.File)
     */
    @Override
    public final String retrieveClobFromFile( File file ) throws IOException {
        byte[] data = retrieveBytesFromFile( file );
        return new String( data, CHARSET );
    }

    /**
     * @see org.suggs.apps.mercury.model.util.file.IFileManager#retrieveBytesFromFile(java.io.File)
     */
    @Override
    public final byte[] retrieveBytesFromFile( File file ) throws IOException {
        FileInputStream fis = null;
        FileChannel chan = null;
        try {
            fis = new FileInputStream( file );
            chan = fis.getChannel();
            ByteBuffer buff = ByteBuffer.allocate( (int) chan.size() );
            chan.read( buff );
            return buff.array();
        }
        finally {
            if ( chan != null ) {
                chan.close();
            }
            if ( fis != null ) {
                fis.close();
            }
        }
    }

    /**
     * @see org.suggs.apps.mercury.model.util.file.IFileManager#retrieveClobFromResource(java.lang.String)
     */
    @Override
    public final String retrieveClobFromResource( String resource ) throws IOException {
        InputStream is = null;
        StringWriter w = new StringWriter();
        String ret = null;
        try {
            is = getClass().getClassLoader().getResourceAsStream( resource );
            if ( is == null ) {
                throw new IOException( "Failed to retrieve data from resource [" + resource + "]" );
            }

            for ( int i = is.read(); i != -1; i = is.read() ) {
                w.write( i );
            }
            ret = w.toString();
        }
        finally {
            if ( is != null ) {
                is.close();
            }
            w.close();
        }

        return ret;
    }

    /**
     * Persist CLOB data to the underlying persistence layer
     * 
     * @param aClob
     *            the clob data to persist
     * @param aFile
     *            the file to persist the data
     * @throws IOException
     *             if there are any issues in the persistence
     */
    @Override
    public final void persistClobToFile( String aClob, File aFile ) throws IOException {
        // first ensure that the persistence layer is OK to use
        verifyPersistenceLayer( aFile );

        // now we persist the new values to the persistent file
        FileOutputStream out = null;
        FileChannel chan = null;
        try {
            LOG.debug( "Creating new file at [" + aFile.getCanonicalPath() + "]" );
            out = new FileOutputStream( aFile );
            chan = out.getChannel();
            int xmlSize = aClob.getBytes().length;

            ByteBuffer buff = ByteBuffer.allocate( xmlSize );
            buff.put( aClob.getBytes( CHARSET ) );
            // flip will move the buffer limit to where the buffer
            // pointer now sits
            buff.flip();

            chan.write( buff );
        }
        catch ( IOException ioe ) {
            LOG.warn( "Exception caught when persisting file [" + aFile.getAbsolutePath() + "]: "
                      + ioe.getMessage() + "\n.. rethrowing it" );
            throw ioe;
        }
        finally {
            try {
                if ( chan != null ) {
                    chan.close();
                }

                if ( out != null ) {
                    out.close();
                }
            }
            catch ( IOException ioe ) {
                LOG.warn( "Caught IOException when closing file channel [" + ioe.getMessage() + "]" );
            }
        }
    }

    /**
     * Verifies that the persistence layer is read for the new file to be persisted.
     * 
     * @throws IOException
     *             if there are any issues in the persistence layer
     */
    private static void verifyPersistenceLayer( File aFile ) throws IOException {
        if ( aFile.getParentFile().getParentFile() == null ) {
            throw new IOException( "Trying to create a file too close to the root of the file system is dangerous" );
        }

        // check that the persistence dir exists
        File dir = new File( aFile.getParentFile().getAbsolutePath() );

        if ( !( dir.exists() ) ) {
            LOG.info( "Creating directory [" + dir.getAbsolutePath() + "]" );
            if ( !( dir.mkdirs() ) ) {
                throw new IOException( "Failed to create directory [" + dir.getAbsolutePath() + "]" );
            }
        }
        else {
            if ( !( dir.isDirectory() ) || !( dir.canWrite() ) ) {
                String err = "Cannot write to the directory [" + aFile.getAbsolutePath() + "]";
                LOG.error( err );
                throw new IOException( err );
            }
        }

        // now check that the actual file exists
        if ( !( aFile.exists() ) ) {
            createPersistenceFile( aFile );
        }
        else {
            if ( !( aFile.isFile() ) || !( aFile.canWrite() ) ) {
                String err = "Cannot write to file [" + aFile.getAbsolutePath() + "]";
                LOG.error( err );
                throw new IOException( err );
            }
        }

        LOG.debug( "File correctly set up" );
    }

    /**
     * Creates the underlying file that we will use.
     * 
     * @throws IOException
     *             if there are issues in the creation of the file
     */
    private static void createPersistenceFile( File aFile ) throws IOException {
        LOG.info( "Creating persistence file [" + aFile.getAbsolutePath() + "]" );
        try {
            if ( !( aFile.createNewFile() ) ) {
                throw new IOException( "Failed to create the persistence file [" + aFile.getAbsolutePath()
                                       + "]" );
            }
        }
        catch ( IOException ioe ) {
            LOG.error( "Failed to create persistence file [" + ioe.getMessage() + "]" );
            throw ioe;
        }
    }

}
