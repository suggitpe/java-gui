/*
 * DomParserTest.java created on 9 Dec 2008 19:35:38 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.util.xml;

import org.suggs.apps.mercury.model.util.MercuryUtilityException;
import org.suggs.apps.mercury.model.util.file.impl.FileManager;
import org.suggs.apps.mercury.model.util.xml.impl.DomParserUtil;

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * This test will test the DomParser utility.
 * 
 * @author suggitpe
 * @version 1.0 9 Dec 2008
 */
public class DomParserTest
{

    private static final Logger LOG = LoggerFactory.getLogger( DomParserTest.class );
    private static final String TEST_ROOT = "/tmp";
    private static final String TEST_DIR = TEST_ROOT + "/test/xmltest";
    private static final String TEST_FILE = TEST_DIR + "/dummyXml.xml";

    private IDomParserUtil mDomParser_;
    private FileManager mFileManager_;

    /**
     * This is run before all of the tests so that they are correctly
     * set up
     * 
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception
    {
        LOG.debug( "------------------- DomParserTest" );
        mDomParser_ = new DomParserUtil();
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
        LOG.debug( "Deleting file [" + TEST_FILE + "]" );
        File file = new File( TEST_FILE );
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
     * This will test the parsing of a file that is valid for the
     * schema.
     * 
     * @throws IOException
     * @throws MercuryUtilityException
     */
    @Test
    public void testParseValidXmlFile() throws IOException, MercuryUtilityException
    {
        // created good xml
        StringBuffer buff = new StringBuffer();
        buff.append( "<?xml version=\"1.0\"?>\n" )
            .append( "<xmlroot xmlns=\"http://www.suggs.org.uk/UnitTestSchema\">\n" );
        buff.append( "<testlayer1/>" );
        buff.append( "<testlayer2/>" );
        buff.append( "<testlayer3/>" );
        buff.append( "</xmlroot>" );

        // persist the data to a file
        LOG.debug( "Creating test file [" + TEST_FILE + "]" );
        mFileManager_.persistClobToFile( buff.toString(), new File( TEST_FILE ) );

        // no we do the actual parse
        LOG.debug( "Parsing file [" + TEST_FILE + "]" );
        Document doc = mDomParser_.createDocFromXmlFile( TEST_FILE, "xml/unit-test-schema.xsd" );

        Node docElem = doc.getDocumentElement();
        Assert.assertEquals( docElem.getNodeName(), "xmlroot" );

        NodeList list = docElem.getChildNodes();
        LOG.debug( "Checking parsed elements are correct from [" + list.getLength()
                   + "] child nodes" );
        for ( int i = 0; i < list.getLength(); ++i )
        {
            if ( list.item( i ) instanceof Element )
            {
                LOG.debug( "Checking [" + list.item( i ).getNodeName() + "]" );
                Assert.assertEquals( list.item( i ).getNodeName().substring( 0, 9 ), "testlayer" );
            }
        }
    }

    /**
     * This will test the parsing of a file that is not valid for the
     * schema.
     * 
     * @throws IOException
     * @throws MercuryUtilityException
     */
    @Test(expected = IllegalStateException.class)
    public void testParseInvalidXmlFile() throws IOException, MercuryUtilityException
    {
        // created good xml
        StringBuffer buff = new StringBuffer();
        buff.append( "<?xml version=\"1.0\"?>\n" )
            .append( "<xmlroot xmlns=\"http://www.suggs.org.uk/UnitTestSchema\">\n" );
        buff.append( "</xmlroot>" );

        // persist the data to a file
        LOG.debug( "Creating test file [" + TEST_FILE + "]" );
        mFileManager_.persistClobToFile( buff.toString(), new File( TEST_FILE ) );

        // no we do the actual parse
        LOG.debug( "Parsing file [" + TEST_FILE + "]" );
        mDomParser_.createDocFromXmlFile( TEST_FILE, "xml/unit-test-schema.xsd" );
    }

    /**
     * This will test the parsing of a file that is not valid for the
     * schema.
     * 
     * @throws IOException
     * @throws MercuryUtilityException
     */
    @Test(expected = IllegalStateException.class)
    public void testParseBadXmlFile() throws IOException, MercuryUtilityException
    {
        // created good xml
        StringBuffer buff = new StringBuffer();
        buff.append( "<?xml version=\"1.0\"?>\n" )
            .append( "<xmlroot xmlns=\"http://www.suggs.org.uk/UnitTestSchema\">\n" );
        buff.append( "" );

        // persist the data to a file
        LOG.debug( "Creating test file [" + TEST_FILE + "]" );
        mFileManager_.persistClobToFile( buff.toString(), new File( TEST_FILE ) );

        // no we do the actual parse
        LOG.debug( "Parsing file [" + TEST_FILE + "]" );
        mDomParser_.createDocFromXmlFile( TEST_FILE, "xml/unit-test-schema.xsd" );
    }

    /**
     * This will try and run the parser but without finding a schema
     * and thus will throw an exception.
     * 
     * @throws MercuryUtilityException
     */
    @Test(expected = MercuryUtilityException.class)
    public void testCantFindXsd() throws MercuryUtilityException
    {
        LOG.debug( "Calling the dom parser with no valid schema" );
        mDomParser_.createDocFromXmlFile( new String(), "you-cant-find-me.xsd" );
    }

}
