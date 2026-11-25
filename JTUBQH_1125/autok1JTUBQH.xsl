<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="text" encoding="UTF-8"/>

    <xsl:template match="/">
        <xsl:for-each select="autok/auto">
            <xsl:sort select="number(ar)" data-type="number" order="ascending"/>
            <xsl:value-of select="@rsz"/>
            <xsl:text> - </xsl:text>
            <xsl:value-of select="normalize-space(ar)"/>
            <xsl:text>&#10;</xsl:text>
        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>