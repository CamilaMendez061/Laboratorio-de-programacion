<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" indent="yes" encoding="UTF-8" />
    <xsl:template match="/">
        <alumnosAprobados>
            <xsl:for-each select="//alumno[nota &gt; 70]">
                <alumno>
                    <nombre>
                        <xsl:value-of select="nombre" />
                    </nombre>
                    <materia>
                        <xsl:value-of select="materia" />
                    </materia>
                    <carrera>
                        <xsl:value-of select="carrera" />
                    </carrera>
                    <nota>
                        <xsl:value-of select="nota" />
                    </nota>
                </alumno>
            </xsl:for-each>
        </alumnosAprobados>
    </xsl:template>
</xsl:stylesheet>