<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="text" encoding="UTF-8"/>
    <xsl:key name="tipusKulcs" match="auto" use="normalize-space(tipus)"/>

    <xsl:template match="/">
        <xsl:for-each select="autok/auto[generate-id() = generate-id(key('tipusKulcs', normalize-space(tipus))[1])]">
            <xsl:sort select="count(key('tipusKulcs', normalize-space(tipus)))" data-type="number" order="descending"/>
            <xsl:value-of select="normalize-space(tipus)"/>
            <xsl:text>: </xsl:text>
            <xsl:value-of select="count(key('tipusKulcs', normalize-space(tipus)))"/>
            <xsl:text>&#10;</xsl:text>
        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>