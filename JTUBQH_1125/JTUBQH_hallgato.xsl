<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>

    <xsl:template match="/">
        <html>
            <head>
                <meta charset="UTF-8"/>
                <title>Hallgatók adatai - JTUBQH</title>
                <style>
                    body { font-family: Arial, sans-serif; margin: 24px; }
                    table { border-collapse: collapse; width: 60%; }
                    th, td { border: 1px solid #444; padding: 8px 12px; text-align: left; }
                    th { background-color: #2b4c7e; color: #fff; }
                    tr:nth-child(even) { background-color: #f4f6f9; }
                </style>
            </head>
            <body>
                <h2>Hallgatok adatai - for-each, value-of</h2>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Vezeteknev</th>
                            <th>Keresztnev</th>
                            <th>Becenev</th>
                            <th>Kor</th>
                        </tr>
                    </thead>
                    <tbody>
                        <xsl:for-each select="class/student">
                            <tr>
                                <td><xsl:value-of select="@id"/></td>
                                <td><xsl:value-of select="vezeteknev"/></td>
                                <td><xsl:value-of select="keresztnev"/></td>
                                <td><xsl:value-of select="becenev"/></td>
                                <td><xsl:value-of select="kor"/></td>
                            </tr>
                        </xsl:for-each>
                    </tbody>
                </table>
            </body>
        </html>
    </xsl:template>



</xsl:stylesheet>