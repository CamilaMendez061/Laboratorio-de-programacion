const formulario = document.getElementById('formularioExpediente');
const inputs = document.querySelectorAll('#formularioExpediente input');
const inputNumExpediente = document.querySelector('#numExpediente');
const selectTramite = document.querySelector('#tipoTramite');

const campos = {
    numExpediente: false,
    diasTramite: false,
    responsable: false,
    cantExpedientes: false,
    horasTrabajadas: false
}

const expresiones = {
    numExpediente: /^\d{4}$/, // 4 números
    responsable: /^[A-ZÀ-ÿ\s]{1,50}$/ // Patrón de solo letras y espacios.
}

const validarFormulario = (e) => {
    const grupo = e.target.closest('.formulario_grupo');
    const mensajeRequired = grupo.querySelector('.formulario_required');

    if (mensajeRequired && e.target.value.trim() !== '') {
        mensajeRequired.classList.remove('formulario_required-activo');
    }

    let valor = e.target.value;
    switch (e.target.name) {
        case "numExpediente":
            validarCampo(!expresiones.numExpediente.test(valor), 'numExpediente');
            break;

        case "diasTramite":
            validarCampo((valor < 1 || isNaN(valor)), 'diasTramite');
            break;

        case "responsable":
            valor = valor.replace(/\d/g, ''); // no permite el ingreso de números
            valor = valor.toUpperCase(); // convierte texto a mayúsculas automáticamente
            e.target.value = valor;

            validarCampo(!expresiones.responsable.test(valor), 'responsable');
            break;

        case "cantExpedientes":
            validarCampo((valor < 0 || isNaN(valor)), 'cantExpedientes');
            break;

        case "horasTrabajadas":
            validarCampo((valor < 0 || valor > 12 || isNaN(valor)), 'horasTrabajadas');
            break;

        default:
            break;
    }
}

inputs.forEach((input) => { // inputs: numExpediente, diasTramite, responsable, cantExpedientes, horasTrabajadas.
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

// Formatear el número de expediente
let valorCantExpedientes = "";

inputNumExpediente.addEventListener('focus', (e) => {
    e.target.value = valorCantExpedientes;
})

inputNumExpediente.addEventListener('blur', (e) => {
    valorCantExpedientes = e.target.value.replace(/\D/g, '');
    if (valorCantExpedientes !== "") {
        e.target.value = `EXP-${valorCantExpedientes}/25`;
    }
})

inputNumExpediente.addEventListener('input', (e) => {
    e.target.value = e.target.value.replace(/\D/g, '');
    valorCantExpedientes = e.target.value;
})


// Mensaje según tipo de trámite
selectTramite.addEventListener('change', (e) => {
    switch (e.target.value) {
        case 'urgente':
            alert("Resolución dentro de las 24 horas");
            break;

        case 'normal':
            alert("Resolución dentro de las 48 horas");
            break;

        case 'bajo':
            alert("Resolución dentro de las 96 horas");
            break;

        default:
            break;
    }
})


formulario.addEventListener('submit', (e) => {

    e.preventDefault();

    if (formularioValidado()) { // formulario válido
        document.querySelector('.formulario_btn-exitoso').classList.add('formulario_btn-exitoso-activo');
        document.querySelector('.formulario_btn-noExitoso').classList.remove('formulario_btn-noExitoso-activo');
    } else { // formulario no válido
        document.querySelector('.formulario_btn-exitoso').classList.remove('formulario_btn-exitoso-activo');
        document.querySelector('.formulario_btn-noExitoso').classList.add('formulario_btn-noExitoso-activo');
    }
})


// Devuelve true si es válido, y false si no es válido
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

    // Comprueba si en 'estado' no ha sido seleccionada ninguna opción
    const radioSeleccionado = document.querySelector('input[name="estado"]:checked');
    if (!radioSeleccionado) {
        document.querySelector('#grupo_estado .formulario_required').classList.add('formulario_required-activo');
        hayErrores = true;
    } else {
        document.querySelector('#grupo_estado .formulario_required').classList.remove('formulario_required-activo');
    }
    if (!campos.numExpediente || !campos.diasTramite || !campos.responsable || !campos.cantExpedientes || !campos.horasTrabajadas) {
        hayErrores = true;
    }

    return !hayErrores;
}

formulario.addEventListener('reset', (e) => {
    document.querySelector('.formulario_btn-exitoso').classList.remove('formulario_btn-exitoso-activo');
    document.querySelector('.formulario_btn-noExitoso').classList.remove('formulario_btn-noExitoso-activo');

    document.querySelector('#grupo_estado .formulario_required').classList.remove('formulario_required-activo');
    document.querySelectorAll('[required]').forEach(campo => {
        const grupo = campo.closest('.formulario_grupo');
        const mensaje = grupo.querySelector('.formulario_required');

        if (campo.value.trim() === '') {
            mensaje.classList.remove('formulario_required-activo');
        }
    })

    document.querySelector('.formulario_btn-estadisticaBaja').classList.remove('formulario_btn-estadisticaBaja-activo');
    document.querySelector('.formulario_btn-estadisticaMedia').classList.remove('formulario_btn-estadisticaMedia-activo');
    document.querySelector('.formulario_btn-estadisticaAlta').classList.remove('formulario_btn-estadisticaAlta-activo');
    document.querySelector('.formulario_btn-estadisticaResultado').textContent = "";
    document.getElementById('jsonGenerado').textContent = "";
    document.querySelector('.textoJson').classList.remove('textoJson-activo');

})


// Calcular estadística - productividad
const boton = document.getElementById('btn_calcularEstadistica');

boton.addEventListener('click', () => {
    const cantExp = parseInt(document.getElementById('cantExpedientes').value);
    const horas = parseInt(document.getElementById('horasTrabajadas').value);
    const productividad = cantExp / horas;

    if (isNaN(productividad)) {

        // no hace nada si no hay datos para calcular la estadística. Borra los mensajes si ya estaban en pantalla
        document.querySelector('.formulario_btn-estadisticaBaja').classList.remove('formulario_btn-estadisticaBaja-activo');
        document.querySelector('.formulario_btn-estadisticaMedia').classList.remove('formulario_btn-estadisticaMedia-activo');
        document.querySelector('.formulario_btn-estadisticaAlta').classList.remove('formulario_btn-estadisticaAlta-activo');
        document.querySelector('.formulario_btn-estadisticaResultado').textContent = "";

    } else {

        const resultado = `Productividad: ${productividad}`
        const contenedor = document.querySelector('.formulario_btn-estadisticaResultado');
        contenedor.textContent = resultado;

        if (productividad < 2) {
            document.querySelector('.formulario_btn-estadisticaBaja').classList.add('formulario_btn-estadisticaBaja-activo');
            document.querySelector('.formulario_btn-estadisticaMedia').classList.remove('formulario_btn-estadisticaMedia-activo');
            document.querySelector('.formulario_btn-estadisticaAlta').classList.remove('formulario_btn-estadisticaAlta-activo');
        } else if (productividad <= 5) {
            document.querySelector('.formulario_btn-estadisticaMedia').classList.add('formulario_btn-estadisticaMedia-activo');
            document.querySelector('.formulario_btn-estadisticaBaja').classList.remove('formulario_btn-estadisticaBaja-activo');
            document.querySelector('.formulario_btn-estadisticaAlta').classList.remove('formulario_btn-estadisticaAlta-activo');
        } else {
            document.querySelector('.formulario_btn-estadisticaAlta').classList.add('formulario_btn-estadisticaAlta-activo');
            document.querySelector('.formulario_btn-estadisticaBaja').classList.remove('formulario_btn-estadisticaBaja-activo');
            document.querySelector('.formulario_btn-estadisticaMedia').classList.remove('formulario_btn-estadisticaMedia-activo');
            alert('Buen rendimiento! ;D');
        }
    }
})


// Generar JSON
function crearJSON() {

    if (!formularioValidado()) {
        alert("Se deben corregir los errores del formulario antes de generar el JSON.");
        return; // No genera el JSON si no está validado
    }

    console.log(campos);
    // Si está correctamente validado, continúa con la creación del JSON

    const numExpediente = inputNumExpediente.value;
    const area = document.getElementById('area').value;
    const tipoTramite = document.getElementById('tipoTramite').value;
    const diasTramite = parseInt(document.getElementById('diasTramite').value);
    const estado = document.querySelector('input[name="estado"]:checked').value;
    const responsable = document.getElementById('responsable').value;
    const cantExpedientes = parseInt(document.getElementById('cantExpedientes').value);
    const horasTrabajadas = parseInt(document.getElementById('horasTrabajadas').value);

    const datosFormulario = {
        numExpediente: numExpediente,
        area: area,
        tipoTramite: tipoTramite,
        diasTramite: diasTramite,
        estado: estado,
        responsable: responsable,
        cantExpedientes: cantExpedientes,
        horasTrabajadas: horasTrabajadas,
    }

    const jsonTexto = JSON.stringify(datosFormulario, null, 2);

    document.getElementById('jsonGenerado').textContent = jsonTexto;
    document.querySelector('.textoJson').classList.add('textoJson-activo');

    const blob = new Blob([jsonTexto], { type: 'application/json' });

    const enlaceDescarga = document.createElement('a');
    enlaceDescarga.href = URL.createObjectURL(blob);
    enlaceDescarga.download = "datos_formulario.json";

    document.body.appendChild(enlaceDescarga);
    enlaceDescarga.click();
    document.body.removeChild(enlaceDescarga);
}