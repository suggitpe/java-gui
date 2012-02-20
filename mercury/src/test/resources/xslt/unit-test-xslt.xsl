<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:tns="http://www.w3.org/1999/xhtml">

	<xsl:output method="xml" indent="yes" encoding="UTF-8" />

	<xsl:template match="/xmlrootnode">
		<newxml>
            <fromxml><xsl:value-of select="/xmlrootnode/layer2/text()" /></fromxml>
		</newxml>
	</xsl:template>

</xsl:stylesheet>
