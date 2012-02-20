<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:tns="http://www.w3.org/1999/xhtml" xmlns:cs="http://www.suggs.org.uk/ConnectionStore"
	xmlns:xalan="http://xml.apache.org/xslt">

	<xsl:output method="xml" indent="yes" xalan:indent-amount="3" encoding="UTF-8" />
	
	<xsl:template match="/cs:connectionStore">
        <connections name="connections">
            <xsl:for-each select="cs:connection[not(@type = preceding-sibling::cs:connection/@type)]">
                <xsl:sort order="ascending" select="@type"/>
                <type>
					<xsl:attribute name="name">
                        <xsl:value-of select="@type" />
                    </xsl:attribute>
                    <xsl:call-template name="buildConns">
                        <xsl:with-param name="type" select="@type"/>
                    </xsl:call-template>
                </type>
            </xsl:for-each>
		</connections>
	</xsl:template>
	
	<xsl:template name="buildConns">
	   <xsl:param name="type"/>
	   
	   <xsl:for-each select="/cs:connectionStore/cs:connection[@type=$type]">
	       <xsl:sort order="ascending" select="@name"/>
	       <conn>
	           <xsl:attribute name="name"><xsl:value-of select="@name"/></xsl:attribute>
	       </conn>
	   </xsl:for-each>
	   
	</xsl:template>

</xsl:stylesheet>