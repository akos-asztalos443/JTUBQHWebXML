<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="text" encoding="UTF-8"/>

    <xsl:template match="/">
        <xsl:value-of select="count(autok/auto[number(ar) &gt; 30000])"/>
        <xsl:text>&#10;</xsl:text>
    </xsl:template>
</xsl:stylesheet>