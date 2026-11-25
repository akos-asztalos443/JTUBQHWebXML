<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>
    
    <xsl:template match="/">
        <html>
            <head>
                <meta charset="UTF-8"/>
                <title>JTUBQH Órarend  2026. I. félév.</title>
                <style>
                    body { font-family: Arial, sans-serif; margin: 24px; }
                    table { width: 100%; border-collapse: collapse; }
                    th, td { border: 1px solid #444; padding: 8px 10px; text-align: left; }
                    th { background-color: #2b4c7e; color: #fff; }
                    tr:nth-child(even) { background-color: #f4f6f9; }
                </style>
            </head>
            <body>
                <h1>JTUBQH_órarend</h1>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Tantárgy</th>
                            <th>Tipus</th>
                            <th>Nap</th>
                            <th>Kezdés</th>
                            <th>Befejezés</th>
                            <th>Helyszín</th>
                            <th>Oktató</th>
                            <th>Szak</th>
                        </tr>
                    </thead>
                    <tbody>
                        <xsl:for-each select="XY_orarend/ora">
                            <tr>
                                <td><xsl:value-of select="@id"/></td>
                                <td><xsl:value-of select="targy"/></td>
                                <td><xsl:value-of select="@tipus"/></td>
                                <td><xsl:value-of select="idopont/nap"/></td>
                                <td><xsl:value-of select="idopont/tol"/></td>
                                <td><xsl:value-of select="idopont/ig"/></td>
                                <td><xsl:value-of select="helyszin"/></td>
                                <td><xsl:value-of select="oktato"/></td>
                                <td><xsl:value-of select="szak"/></td>
                            </tr>
                        </xsl:for-each>
                    </tbody>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>