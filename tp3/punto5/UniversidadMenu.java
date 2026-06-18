package punto5;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONObject;

public class UniversidadMenu {
    public static void main(String[] args) {

        ArchivoUniversidadJSON universidad;
        String nombreArchivo = "punto5/universidad.json";

        try {
            universidad = new ArchivoUniversidadJSON(nombreArchivo);

        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el archivo");
            System.out.println(e.getMessage());
            return;
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error de E/S");
            System.out.println(e.getMessage());
            return;
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error.");
            System.out.println(e.getMessage());
            return;
        }

        Scanner scan = new Scanner(System.in);

        int opcion = 0;
        int opcionAux = 0;
        int aux;

        boolean encontrado;
        String nombreApellido;
        long dni;
        String telefono;
        String email;
        long idCarrera;
        long id;
        String nombreCarrera;
        
        Alumno alum;
        Carrera carre;
        JSONObject jsonObjectAux;

        do {
            System.out.println("Ingrese una opción:");
            System.out.println(
                    "1. Mostrar archivo\n2. Listar alumnos\n3. Listar carreras\n4. Modificar alumnos\n5. Modificar carreras\n6. Realizar consultas\n0. Salir");
            opcion = scan.nextInt();
            scan.nextLine();

            switch (opcion) {
                case 1: // Mostrar archivo
                    System.out.println("Contenido del archivo:");
                    universidad.mostrarInfoUniversidad();
                    break;

                case 2: // Listar alumnos
                    System.out.println("Listado alumnos:");
                    universidad.listarAlumnos();
                    break;

                case 3: // Listar carreras
                    System.out.println("Listado carreras:");
                    universidad.listarCarreras();
                    break;

                case 4: // Modificar alumnos
                    do {
                        System.out.println("Ingrese una opción de alumno:");
                        System.out
                                .println("1. Agregar alumno\n2. Eliminar alumno\n3. Modificar datos alumno\n4. Volver");
                        opcionAux = scan.nextInt();
                        scan.nextLine();

                        switch (opcionAux) {
                            case 1: // Agregar alumno
                                System.out.println("Ingrese nombre y apellido:");
                                nombreApellido = scan.nextLine();
                                System.out.println("Ingrese DNI:");
                                dni = scan.nextLong();
                                scan.nextLine();
                                System.out.println("Ingrese email");
                                email = scan.nextLine();
                                System.out.println("Ingrese teléfono:");
                                telefono = scan.nextLine();
                                System.out.println("Ingrese id carrera:");
                                idCarrera = scan.nextLong();
                                scan.nextLine();
                                id = universidad.cantidadAlumnos() + 1;

                                alum = new Alumno(id, nombreApellido, dni, email, telefono, idCarrera);
                                jsonObjectAux = (JSONObject) alum.getAlumno();
                                universidad.getListaAlumnos().add(jsonObjectAux);

                                GuardarArchivoJSON.guardarCambios(nombreArchivo, universidad.getUniversidad());
                                break;

                            case 2: // Eliminar alumno
                                System.out.println("1. por id\n2. por DNI");
                                opcionAux = scan.nextInt();
                                scan.nextLine();

                                switch (opcionAux) {
                                    case 1: // por id
                                        System.out.println("Ingrese id:");
                                        id = scan.nextLong();
                                        scan.nextLine();

                                        universidad.eliminarAlumnoId(id);
                                        GuardarArchivoJSON.guardarCambios(nombreArchivo, universidad.getUniversidad());
                                        break;

                                    case 2: // por DNI
                                        System.out.println("Ingrese DNI:");
                                        dni = scan.nextLong();
                                        scan.nextLine();

                                        universidad.eliminarAlumnoDNI(dni);
                                        GuardarArchivoJSON.guardarCambios(nombreArchivo, universidad.getUniversidad());
                                        break;

                                    default:
                                        System.out.println("Opción no disponible");
                                        break;
                                }
                                break;

                            case 3: // Modificar datos alumno
                                System.out.println("1. por id\n2. por DNI");
                                opcionAux = scan.nextInt();
                                scan.nextLine();

                                switch (opcionAux) {
                                    case 1: // por id
                                        System.out.println("Ingrese id:");
                                        id = scan.nextLong();
                                        scan.nextLine();
                                        encontrado = universidad.mostrarAlumnoId(id);

                                        if (encontrado) {
                                            System.out.println("Datos a modificar:");
                                            System.out.println("Ingrese nombre y apellido:");
                                            nombreApellido = scan.nextLine();
                                            System.out.println("Ingrese DNI:");
                                            dni = scan.nextLong();
                                            scan.nextLine();
                                            System.out.println("Ingrese email");
                                            email = scan.nextLine();
                                            System.out.println("Ingrese teléfono:");
                                            telefono = scan.nextLine();
                                            System.out.println("Ingrese id carrera:");
                                            idCarrera = scan.nextLong();
                                            scan.nextLine();

                                            universidad.eliminarAlumnoId(id);
                                            alum = new Alumno(id, nombreApellido, dni, email, telefono, idCarrera);
                                            jsonObjectAux = (JSONObject) alum.getAlumno();
                                            universidad.getListaAlumnos().add(jsonObjectAux);
                                            GuardarArchivoJSON.guardarCambios(nombreArchivo,
                                                    universidad.getUniversidad());
                                        }
                                        break;

                                    case 2: // por DNI
                                        System.out.println("Ingrese DNI:");
                                        long dniAux = scan.nextLong();
                                        scan.nextLine();
                                        encontrado = universidad.mostrarAlumnoDNI(dniAux);

                                        if (encontrado) {
                                            System.out.println("Datos a modificar:");
                                            System.out.println("Ingrese nombre y apellido:");
                                            nombreApellido = scan.nextLine();
                                            System.out.println("Ingrese DNI:");
                                            dni = scan.nextLong();
                                            scan.nextLine();
                                            System.out.println("Ingrese email");
                                            email = scan.nextLine();
                                            System.out.println("Ingrese teléfono:");
                                            telefono = scan.nextLine();
                                            System.out.println("Ingrese id carrera:");
                                            idCarrera = scan.nextLong();
                                            scan.nextLine();
                                            id = universidad.getIdAlumno(dniAux);

                                            universidad.eliminarAlumnoDNI(dniAux);
                                            alum = new Alumno(id, nombreApellido, dni, email, telefono, idCarrera);
                                            jsonObjectAux = (JSONObject) alum.getAlumno();
                                            universidad.getListaAlumnos().add(jsonObjectAux);
                                            GuardarArchivoJSON.guardarCambios(nombreArchivo,
                                                    universidad.getUniversidad());
                                        }
                                        break;

                                    default:
                                        System.out.println("Opción no disponible");
                                        break;
                                }
                                break;

                            case 4: // Volver
                                break;

                            default:
                                System.out.println("Opción no disponible");
                                break;
                        }
                    } while (opcionAux != 4);
                    break;

                case 5: // Modificar carreras
                    do {
                        System.out.println("Ingrese una opción de carrera:");
                        System.out.println(
                                "1. Agregar carrera\n2. Eliminar carrera\n3. Modificar datos carrera\n4. Volver");
                        opcionAux = scan.nextInt();
                        scan.nextLine();

                        switch (opcionAux) {
                            case 1: // Agregar carrera
                                System.out.println("Ingresar id de la carrera:");
                                idCarrera = scan.nextLong();
                                scan.nextLine();
                                System.out.println("Ingrese nombre de la carrera:");
                                nombreCarrera = scan.nextLine();

                                carre = new Carrera(idCarrera, nombreCarrera);
                                jsonObjectAux = (JSONObject) carre.getCarrera();
                                universidad.getListaCarreras().add(jsonObjectAux);
                                GuardarArchivoJSON.guardarCambios(nombreArchivo, universidad.getUniversidad());
                                break;

                            case 2: // Eliminar carrera
                                System.out.println("Ingrese id de la carrera:");
                                idCarrera = scan.nextLong();
                                scan.nextLine();

                                universidad.eliminarCarreraId(idCarrera);
                                GuardarArchivoJSON.guardarCambios(nombreArchivo, universidad.getUniversidad());
                                break;

                            case 3: // Modificar datos carrera
                                System.out.println("Ingrese id de la carrera:");
                                idCarrera = scan.nextLong();
                                scan.nextLine();
                                encontrado = universidad.mostrarCarreraId(idCarrera);

                                if (encontrado) {
                                    System.out.println("Datos a modificar:");
                                    System.out.println("Ingrese nuevo nombre de la carrera:");
                                    nombreCarrera = scan.nextLine();

                                    universidad.eliminarCarreraId(idCarrera);
                                    carre = new Carrera(idCarrera, nombreCarrera);
                                    jsonObjectAux = (JSONObject) carre.getCarrera();
                                    universidad.getListaCarreras().add(jsonObjectAux);
                                    GuardarArchivoJSON.guardarCambios(nombreArchivo, universidad.getUniversidad());
                                }
                                break;

                            case 4: // Volver
                                break;

                            default:
                                System.out.println("Opción no disponible");
                                break;
                        }
                    } while (opcionAux != 4);

                    break;

                case 6: // Realizar consultas
                    System.out.println("Ingrese una opción:");
                    System.out.println(
                            "1. Mostrar cantidad de alumnos inscriptos\n2. Mostrar cantidad de carreras\n3. Mostrar cantidad de alumnos por carrera");
                    opcionAux = scan.nextInt();
                    scan.nextLine();

                    switch (opcionAux) {
                        case 1: // Mostrar cantidad de alumnos inscriptos
                            aux = universidad.cantidadAlumnos();
                            System.out.println("La cantidad de alumnos inscriptos es de:" + aux);
                            break;

                        case 2: // Mostrar cantidad de carreras
                            aux = universidad.cantidadCarreras();
                            System.out.println("La cantidad de carreras es de:" + aux);
                            break;

                        case 3: // Mostrar cantidad de alumnos por carrera
                            universidad.cantidadAlumnosPorCarrera();
                            break;

                        default:
                            break;
                    }
                    break;

                case 0: // Salir
                    System.out.println("Saliendo.");
                    break;

                default:
                    System.out.println("Opción no disponible.");
                    break;
            }
        } while (opcion != 0);

    }
}
