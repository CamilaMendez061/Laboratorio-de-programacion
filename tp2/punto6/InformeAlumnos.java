package punto6;
import java.util.HashMap;
import java.util.Map;
import java.io.File;

import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

/**
 * @author Camila A. Mendez
 * @version 1.0
 * 24/24/2026
 * La clase InformeAlumnos utiliza un archivo XML externo que contiene las nota de alumnos en distintas materias
 * para mostrar por pantalla el promedio de cada alumno y el promedio total de las notas de todos los alumnos.
 * También genera un nuevo archivo XML 'promedios.xml' que contiene esta información.
 * 
 */
public class InformeAlumnos {
    public static void main(String[] args) {

        try {
            String ruta = "punto5/calificaciones.xml";
            DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = fact.newDocumentBuilder();
            Document doc = db.parse(ruta);
            
            doc.getDocumentElement().normalize();

            NodeList alumnos = doc.getElementsByTagName("alumno");

            /* Salida del programa si el archivo utilizado no contiene alumnos */
            if (alumnos.getLength() == 0) {

                System.out.println("No hay alumnos registrados en el documento.");
                return;

            }


            Map<String, double[]> acumuladorNotas = new HashMap<>();

            for (int i = 0; i < alumnos.getLength(); i++) {
                Element elem = (Element) alumnos.item(i);
                String nombre = elem.getElementsByTagName("nombre").item(0).getTextContent().trim();
                double nota = Double.parseDouble(elem.getElementsByTagName("nota").item(0).getTextContent());

                if (!acumuladorNotas.containsKey(nombre)) {
                    acumuladorNotas.put(nombre, new double[] { nota, 1 });
                } else {
                    double[] datos = acumuladorNotas.get(nombre);
                    datos[0] += nota;
                    datos[1] += 1;
                }
            }

            double sumaNotasAlumnos = 0;

            System.out.println("Promedios por alumno:");
            for (Map.Entry<String, double[]> entrada : acumuladorNotas.entrySet()) {
                String nombre = entrada.getKey();
                double suma = entrada.getValue()[0];
                double cantidad = entrada.getValue()[1];
                double promedio = suma / cantidad;

                System.out.println(nombre + " | " + promedio);

                sumaNotasAlumnos += entrada.getValue()[0];
            }

            System.out.println("Promedio total de alumnos:");
            double promedioTotal = sumaNotasAlumnos / acumuladorNotas.size();
            System.out.println(promedioTotal);


            /* Generación de nuevo XML con la información obtenida */
            Document nuevoDoc = db.newDocument();
            Element raiz = nuevoDoc.createElement("informe_promedios");
            nuevoDoc.appendChild(raiz);

            /* Promedio por alumno */
            for (Map.Entry<String, double[]> entrada : acumuladorNotas.entrySet()) {
                Element elemAlumno = nuevoDoc.createElement("alumno");
                raiz.appendChild(elemAlumno);

                Element elemNombre = nuevoDoc.createElement("nombre");
                elemNombre.setTextContent(entrada.getKey());
                elemAlumno.appendChild(elemNombre);

                Element elemPromedio = nuevoDoc.createElement("promedio");
                double promedio = entrada.getValue()[0] / entrada.getValue()[1];
                elemPromedio.setTextContent(String.format("%.2f", promedio));
                elemAlumno.appendChild(elemPromedio);
            }

            /* Promedio total de alumnos */ 
            Element elemPromedioTotal = nuevoDoc.createElement("promedio_total_alumnos");
            elemPromedioTotal.setTextContent(String.format("%.2f", promedioTotal));
            raiz.appendChild(elemPromedioTotal);


            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.setOutputProperty("{http://apache.org}indent-amount", "4");

            DOMSource source = new DOMSource(nuevoDoc);
            StreamResult result = new StreamResult(new File("punto6/promedios.xml"));

            t.transform(source, result);

            System.out.println("Archivo 'promedios.xml' generado con éxito! :D ");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace(System.out);
        }    
    }
}
