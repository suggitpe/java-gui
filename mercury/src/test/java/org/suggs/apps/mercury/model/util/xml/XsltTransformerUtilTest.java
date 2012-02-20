/*
 * XsltTransformerUtil.java created on 10 Dec 2008 19:22:37 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.util.xml;

import org.suggs.apps.mercury.model.util.MercuryUtilityException;
import org.suggs.apps.mercury.model.util.xml.impl.XsltTransformerUtil;

import java.io.UnsupportedEncodingException;

import junit.framework.Assert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Test class to test that the xslt process is correctly working.
 * 
 * @author suggitpe
 * @version 1.0 10 Dec 2008
 */
public class XsltTransformerUtilTest
{

    private static final Logger LOG = LoggerFactory.getLogger( XsltTransformerUtilTest.class );
    private IXsltTransformerUtil mXsltUtil_;

    /**
     * This is run before all of the tests so that they are correctly
     * set up
     * 
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception
    {
        LOG.debug( "------------------- XsltTransformerUtilTest" );
        mXsltUtil_ = new XsltTransformerUtil();
    }

    /**
     * This test ensures that we can apply some xslt to a given set of
     * xml and ensure that the outcome matches the expected result.
     * 
     * @throws UnsupportedEncodingException
     * @throws MercuryUtilityException
     */
    @Test
    public void testSimpleTransform() throws UnsupportedEncodingException, MercuryUtilityException
    {
        String xml = createValidTestXml();
        Node node = mXsltUtil_.transformXmlToDom( xml.getBytes( "UTF-8" ),
                                                  "xslt/unit-test-xslt.xsl" );

        // now we validate that the correct data has been set
        Node n = ( (Document) node ).getDocumentElement();
        Assert.assertEquals( n.getNodeName(), "newxml" );
        NodeList list = n.getChildNodes();
        for ( int i = 0; i < list.getLength(); ++i )
        {
            Node child = list.item( i );
            if ( child instanceof Element )
            {
                Assert.assertEquals( child.getNodeName(), "fromxml" );
            }
        }
    }

    /**
     * This test ensures that we can apply some xslt to a given set of
     * xml and ensure that the outcome matches the expected result.
     * 
     * @throws UnsupportedEncodingException
     * @throws MercuryUtilityException
     */
    @Test(expected = MercuryUtilityException.class)
    public void testFailTransformForInvalidXml() throws UnsupportedEncodingException,
                    MercuryUtilityException
    {
        String xml = createInvalidTestXml();
        Node node = mXsltUtil_.transformXmlToDom( xml.getBytes( "UTF-8" ),
                                                  "xslt/unit-test-xslt.xsl" );

        // now we validate that the correct data has been set
        Node n = ( (Document) node ).getDocumentElement();
        Assert.assertEquals( n.getNodeName(), "newxml" );
        NodeList list = n.getChildNodes();
        for ( int i = 0; i < list.getLength(); ++i )
        {
            Node child = list.item( i );
            if ( child instanceof Element )
            {
                Assert.assertEquals( child.getNodeName(), "fromxml" );
            }
        }
    }

    /**
     * In this test we ensure that if we pass in a zero byte xml, then
     * the whole process falls over
     * 
     * @throws MercuryUtilityException
     */
    @Test(expected = MercuryUtilityException.class)
    public void testFailDueToNoXmlBytes() throws MercuryUtilityException
    {
        mXsltUtil_.transformXmlToDom( new byte[0], "xslt/unit-test-xslt.xsl" );
    }

    /**
     * Here we are testing that if we pass in a null xml object then
     * the whole process falls over
     * 
     * @throws MercuryUtilityException
     */
    @Test(expected = MercuryUtilityException.class)
    public void testFailDueToNullXmlBytes() throws MercuryUtilityException
    {
        mXsltUtil_.transformXmlToDom( null, "xslt/unit-test-xslt.xsl" );
    }

    /**
     * Tests that when you pass in some xslt that no one can find then
     * we get the correct exception thrown
     * 
     * @throws MercuryUtilityException
     * @throws UnsupportedEncodingException
     */
    @Test(expected = MercuryUtilityException.class)
    public void testUnfoundXslt() throws MercuryUtilityException, UnsupportedEncodingException
    {
        String xml = createValidTestXml();
        mXsltUtil_.transformXmlToDom( xml.getBytes( "UTF-8" ), "xslt/you-cant-find-me.xsl" );
    }

    /**
     * Tests that when you pass in some xslt that no one can find then
     * we get the correct exception thrown
     * 
     * @throws MercuryUtilityException
     * @throws UnsupportedEncodingException
     */
    @Test(expected = MercuryUtilityException.class)
    public void testNullXslt() throws MercuryUtilityException, UnsupportedEncodingException
    {
        String xml = createValidTestXml();
        mXsltUtil_.transformXmlToDom( xml.getBytes( "UTF-8" ), null );
    }

    /**
     * Creates some valid xml for use in the tests
     * 
     * @return
     */
    private String createValidTestXml()
    {
        StringBuffer buff = new StringBuffer();
        buff.append( "<?xml version=\"1.0\"?>\n<xmlrootnode>\n<layer1>This is some layer1 text</layer1>" );
        buff.append( "\n<layer2>This is some layer2 text</layer2>\n<layer3 name=\"layer3Attribute\"/>\n</xmlrootnode>" );
        return buff.toString();
    }

    /**
     * Creates some invalid xml for use in the tests
     * 
     * @return
     */
    private String createInvalidTestXml()
    {
        StringBuffer buff = new StringBuffer();
        buff.append( "<?xml version=\"1.0\"?>\n<xmlrootnode>\n<layer1>This is some layer1 text</layer1>" );
        buff.append( "\n<layer2>This is some layer2 text</layer2>\n<layer3 name=\"layer3Attribute\"/>" );
        return buff.toString();
    }
}
