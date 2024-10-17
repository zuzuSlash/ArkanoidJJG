<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>

    <xsl:template match="/">
        <html>
            <head>
                <title>Niveles de Arkanoid</title>
                <style>
                    body { font-family: Arial, sans-serif; }
                    h1 { color: #4CAF50; }
                    th, td { border: 1px solid #dddddd; }
                    th { background-color: #f2f2f2; }
                    .blocks-table td {
                    width: 50px;
                    height: 50px;
                </style>
            </head>
            <body>
                <h1>Niveles de Arkanoid</h1>
                <table>
                    <tr>
                        <th>Nombre</th>
                        <th>Tiempo</th>
                        <th>Sonido</th>
                        <th>Fondo</th>
                    </tr>
                    <xsl:for-each select="levels/level">
                        <tr>
                            <td><xsl:value-of select="name"/></td>
                            <td><xsl:value-of select="time"/></td>
                            <td><xsl:value-of select="sound"/></td>
                            <td><xsl:value-of select="backgroundPosition"/></td>
                        </tr>
                    </xsl:for-each>
                </table>

                <h2>Bloques</h2>

                <xsl:for-each select="levels/level/blocks">
                    <table class="blocks-table">
                        <tr>
                            <xsl:for-each select="item">
                                <td>
                                    <xsl:choose>
                                        <xsl:when test="value">
                                            <xsl:attribute name="style">
                                                <xsl:text>background-color: </xsl:text>
                                                <xsl:value-of select="value"/>
                                                <xsl:text>;</xsl:text>
                                            </xsl:attribute>
                                        </xsl:when>
                                    </xsl:choose>
                                </td>
                            </xsl:for-each>
                        </tr>
                    </table>
                </xsl:for-each>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>