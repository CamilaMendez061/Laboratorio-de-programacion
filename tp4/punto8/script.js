const formulario = document.getElementById('formulario_gestionAlumnos');
const inputs = document.querySelectorAll('#formulario_gestionAlumnos input');

const expresiones = {
    idCarrera: /^\d{4}$/, // 4 números
    idAlumno: /^\d{5}$/, // 5 números
    nombre: /^[a-zA-ZÀ-ÿ\s]{1,50}$/, // Patrón de solo letras y espacios.
    dni: /^\d{8}$/, // 8 números
    email: /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/, // formato nombre@correo.com
    telefono: /^\d{11}$/
}

const campos = {
    idCarrera: false,
    nombreCarrera: false,
    idAlumno: false,
    nombreAlumno: false,
    dniAlumno: false,
    emailAlumno: true,
    telefonoAlumno: true,
    idCarreraAlumno: false
}

const validarFormulario = (e) => {
    const grupo = e.target.closest('.formulario_grupo');
    const mensajeRequired = grupo.querySelector('.formulario_required');

    if (mensajeRequired && e.target.value.trim() !== '') {
        mensajeRequired.classList.remove('formulario_required-activo');
    }

    let valor = e.target.value;
    switch (e.target.name) {
        case "idCarrera":
            validarCampo(!expresiones.idCarrera.test(valor), 'idCarrera');
            break;

        case "nombreCarrera":
            validarCampo(!expresiones.nombre.test(valor), 'nombreCarrera');
            break;

        case "idAlumno":
            validarCampo(!expresiones.idAlumno.test(valor), 'idAlumno');
            break;

        case "nombreAlumno":
            validarCampo(!expresiones.nombre.test(valor), 'nombreAlumno');
            break;

        case "dniAlumno":
            validarCampo(!expresiones.dni.test(valor), 'dniAlumno');
            break;

        case "emailAlumno":
            if (valor.trim() === '') {
                campos.emailAlumno = true;
                document.querySelectorAll(`#grupo_emailAlumno .formulario_input-error`).classList.remove('formulario_input-error-activo');
            } else {
                validarCampo(!expresiones.email.test(valor), 'emailAlumno');
            }
            break;

        case "telefonoAlumno":
            if (valor.trim() === '') {
                campos.telefonoAlumno = true;
                document.querySelector(`#grupo_telefonoAlumno .formulario_input-error`).classList.remove('formulario_input-error-activo');
            } else {
                validarCampo(!expresiones.telefono.test(valor), 'telefonoAlumno');
            } break;

        case "idCarreraAlumno":
            validarCampo(!expresiones.idCarrera.test(valor), 'idCarreraAlumno');
            break;
        default:
            break;
    }
}

inputs.forEach((input) => {
    input.addEventListener('input', validarFormulario);
})

const validarCampo = (condicion, campo) => { // muestra mensaje de error si no cumple la condición
    if (condicion) {
        document.querySelector(`#grupo_${campo} .formulario_input-error`).classList.add('formulario_input-error-activo');
        campos[campo] = false;
    } else {
        document.querySelector(`#grupo_${campo} .formulario_input-error`).classList.remove('formulario_input-error-activo');
        campos[campo] = true;
    }

}

formulario.addEventListener('submit', (e) => {

    e.preventDefault();

    if (formularioValidado()) { // formulario válido
        alert('Expediente validado correctamente! :D');
        document.querySelector('.formulario_btn-exitoso').classList.add('formulario_btn-exitoso-activo');
        document.querySelector('.formulario_btn-noExitoso').classList.remove('formulario_btn-noExitoso-activo');
    } else { // formulario no válido
        alert('Se encontraron errores en el formulario :c');
        document.querySelector('.formulario_btn-exitoso').classList.remove('formulario_btn-exitoso-activo');
        document.querySelector('.formulario_btn-noExitoso').classList.add('formulario_btn-noExitoso-activo');
    }
})

function formularioValidado() {
    let hayErrores = false;

    // Comprueba si hay campos required vacíos
    document.querySelectorAll('[required]').forEach(campo => {
        const grupo = campo.closest('.formulario_grupo');
        const mensaje = grupo.querySelector('.formulario_required');

        if (campo.value.trim() === '') {
            mensaje.classList.add('formulario_required-activo');
            hayErrores = true;
        } else {
            mensaje.classList.remove('formulario_required-activo');
        }
    });


    if (!campos.idCarrera || !campos.nombreCarrera || !campos.idAlumno || !campos.nombreAlumno || !campos.dniAlumno || !campos.emailAlumno || !campos.telefonoAlumno || !campos.idCarreraAlumno) {
        hayErrores = true;
    }

    return !hayErrores;
}

formulario.addEventListener('reset', () => {
    document.querySelector('.formulario_btn-exitoso').classList.remove('formulario_btn-exitoso-activo');
    document.querySelector('.formulario_btn-noExitoso').classList.remove('formulario_btn-noExitoso-activo');

    document.querySelectorAll('[required]').forEach(campo => {
        const grupo = campo.closest('.formulario_grupo');
        const mensaje = grupo.querySelector('.formulario_required');

        if (campo.value.trim() === '') {
            mensaje.classList.remove('formulario_required-activo');
        }
    })
})