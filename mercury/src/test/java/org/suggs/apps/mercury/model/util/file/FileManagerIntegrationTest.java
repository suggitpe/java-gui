/*
 * TestMercuryFileManager.java created on 6 Oct 2008 06:42:58 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.util.file;

import org.suggs.apps.mercury.model.util.file.impl.FileManager;

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * This integration test tests that the file manager will work against
 * a given file system. That it correctly finds files that are
 * incorrectly created for use with Mercury.
 * 
 * @author suggitpe
 * @version 1.0 6 Oct 2008
 */
public class FileManagerIntegrationTest
{

    private static final Logger LOG = LoggerFactory.getLogger( FileManagerIntegrationTest.class );
    private IFileManager mFileManager_;
    private static final String TEST_ROOT = "/tmp";
    private static final String TEST_DIR = TEST_ROOT + "/test/filetest";
    private static final String TEST_FILE = "dummyFile.txt";
    private static final String DUMMY_CLOB = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";

    /**
     * This is run before all of the tests so that they are correctly
     * set up
     * 
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception
    {
        LOG.debug( "------------------- TestFileManager" );
        LOG.debug( "Should be using: " + System.getProperty( "user.home" ) );
        mFileManager_ = new FileManager();
    }

    /**
     * This is run after each test so that we do not polute the local
     * drives with our crap.
     * 
     * @throws Exception
     */
    @After
    public void teardown() throws Exception
    {
        LOG.debug( "Deleting file [" + TEST_DIR + "/" + TEST_FILE + "]" );
        File file = new File( TEST_DIR + "/" + TEST_FILE );
        file.delete();

        LOG.debug( "Deleting directory [" + TEST_DIR + "]" );
        File dir = new File( TEST_DIR );
        if ( dir.isDirectory() )
        {
            dir.delete();
        }
        else
        {
            dir.deleteOnExit();
        }
        LOG.debug( "------------------- end of TearDown" );
    }

    /**
     * Test that we can correctly create a file in a directory and
     * that we can then later read it
     * 
     * @throws IOException
     *             if there is any issue in the persistence of the
     *             file.
     */
    @Test
    public void testCorrectlyWriteFile() throws IOException
    {
        mFileManager_.persistClobToFile( DUMMY_CLOB, new File( TEST_DIR + "/" + TEST_FILE ) );
        File file = new File( TEST_DIR + "/" + TEST_FILE );
        Assert.assertTrue( file.exists() );
    }

    /**
     * Test that we cannot create a file too close to the underlying
     * file system
     * 
     * @throws IOException
     *             if there is any issue in the persistence of the
     *             file.
     */
    @Ignore
    @Test(expected = IOException.class)
    public void testFileTooCloseToRoot() throws IOException
    {
        String err = "";
        mFileManager_.persistClobToFile( DUMMY_CLOB, new File( "/tmp/" + TEST_FILE ) );
        LOG.error( err );
        Assert.fail( err );
    }

    /**
     * Test that if the file already exits and the file is not
     * writable then an exception is thrown.
     * 
     * @throws IOException
     */
    @Test(expected = IOException.class)
    public void testNotWritableFile() throws IOException
    {
        LOG.debug( "Creating read-only directly to test with" );
        File file = new File( TEST_DIR + "/" + TEST_FILE );

        String err0 = "Failed to create the read only file and dir for the test";
        try
        {
            file.getParentFile().mkdirs();
            if ( !file.createNewFile() )
            {
                Assert.fail( err0 );
            }
        }
        catch ( IOException ioe )
        {
            Assert.fail( err0 );

        }

        if ( !file.setReadOnly() )
        {
            Assert.fail( "Failed to set the file to read only" );
        }

        mFileManager_.persistClobToFile( DUMMY_CLOB, new File( TEST_DIR + "/" + TEST_FILE ) );
        String err1 = "No IOException was thrown from persistClob call";
        LOG.error( err1 );
        Assert.fail( err1 );
    }

    /**
     * This test will create a new file and then will retrieve the
     * data from that file and compare that the returned data is the
     * same as the data that was persisted.
     * 
     * @throws IOException
     *             if there are any issues in persisting the data
     */
    @Test
    public void testWriteAndReadClob() throws IOException
    {
        LOG.debug( "Testing the write and then read of a file to ensure they are the same" );
        String file = TEST_DIR + "/" + TEST_FILE;

        String data = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<test></test>";

        mFileManager_.persistClobToFile( data, new File( file ) );
        String compareWith = mFileManager_.retrieveClobFromFile( new File( file ) );

        Assert.assertEquals( data, compareWith );
    }

    /**
     * This test will create a new file and then will retrieve the
     * data from that file and compare that the returned data is the
     * same as the data that was persisted.
     * 
     * @throws IOException
     *             if there are any issues in persisting the data
     */
    @Test
    public void testWriteAndReadBytes() throws IOException
    {
        LOG.debug( "Testing the write and then read of a file to ensure they are the same" );
        String file = TEST_DIR + "/" + TEST_FILE;

        String data = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<test></test>";

        mFileManager_.persistClobToFile( data, new File( file ) );
        byte[] readFile = mFileManager_.retrieveBytesFromFile( new File( file ) );

        Assert.assertEquals( readFile.length, 52 );

    }
}
