<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" />
    <xsl:template match="/">

        <html>
            <body>

                <h2>
                    <xsl:value-of select="calificaciones/@fecha" />
                </h2>

                <table border="1">
                    <tr>
                        <th>Nombre</th>
                        <th>Materia</th>
                        <th>Nota</th>
                    </tr>

                    <xsl:for-each select="calificaciones/alumno">
                        <xsl:sort select="nota" />
                            <tr>
                            <td>
                                <xsl:value-of select="nombre" />
                            </td>
                            <td>
                                <xsl:value-of select="carrera" />
                            </td>
                            <td>
                                <xsl:attribute name="style">
                                    <xsl:choose>
                                        <xsl:when test="nota &gt; 70"> color: #09C81C; </xsl:when>
                                        <xsl:when test="nota &gt; 40"> color: #F4F00B; </xsl:when>
                                        <xsl:otherwise> color: #F54927; </xsl:otherwise>
                                    </xsl:choose>
                                </xsl:attribute>
                                <xsl:value-of select="nota" />
                            </td>
                            <!-- Muestra * si el alumno es recursante-->
                            <xsl:if test="@tipo = 'recursante'">*</xsl:if>
                        </tr>
                    </xsl:for-each>
                </table>

                <p>(*) Son alumnos recursantes.</p>

                <ul>
                    <li>Total alumnos: <xsl:value-of select="count(//alumno)" /></li>
                    <li>Total aprobados: <xsl:value-of select="count(//alumno[nota &gt; 70])" /></li>
                    <li>Total desaprobados: <xsl:value-of select="count(//alumno[nota &lt;= 70])" /></li>
                </ul>
            </body>
        </html>

    </xsl:template>

</xsl:stylesheet>