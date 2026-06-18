<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:key name="refCarrera" match="gestionAlumnos/carreras/carrera" use="id"/>    
<xsl:output method="html" />
    <xsl:template match="/">

        <html>
            <body>

                <font face="arial">
                    <!-- Tabla de alumnos-->
                    <font color="blue">
                        <h2>Listado Alumnos</h2>
                    </font>
                    <table border="1">
                        <tr>
                            <th>Apellido y nombre</th>
                            <th>DNI</th>
                            <th>Email</th>
                            <th>Teléfono</th>
                            <th>Carrera</th>
                        </tr>

                        <xsl:for-each select="gestionAlumnos/alumnos/alumno">
                            <tr>
                                <td>
                                    <xsl:value-of select="apellidoNombre" />
                                </td>
                                <td>
                                    <xsl:value-of select="dni" />
                                </td>
                                <td>
                                    <xsl:value-of select="email" />
                                </td>
                                <td>
                                    <xsl:value-of select="telefono" />
                                </td>
                                <td>
                                    <xsl:value-of select="key('refCarrera', carreraInscripto)/nombreCarrera" />
                                </td>
                            </tr>
                        </xsl:for-each>
                    </table>

                    <!-- Tabla de carreras-->
                    <font color="blue">
                        <h2>Listado Carreras</h2>
                    </font>
                    <table border="1">
                        <tr>
                            <th>ID</th>
                            <th>Carrera</th>
                        </tr>

                        <xsl:for-each select="gestionAlumnos/carreras/carrera">
                            <tr>
                                <td>
                                    <xsl:value-of select="id"/>
                                </td>
                                <td>
                                    <xsl:value-of select="nombreCarrera" />
                                </td>
                            </tr>
                        </xsl:for-each>
                    </table>
                </font>

            </body>
        </html>

    </xsl:template>
</xsl:stylesheet>