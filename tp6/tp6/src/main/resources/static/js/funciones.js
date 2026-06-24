const formulario = document.getElementById('formularioAlumno');
const inputs = document.querySelectorAll('#formularioAlumno input');

const campos = {
    apellidoNombre: false,
    dni: false,
    email: false,
    telefono: false
}

const expresiones = {
    apellidoNombre: /^[A-Za-zÁÉÍÓÚáéíóúÑñÜü\s]{1,30}$/, // Letras y espacios, pueden llevar acentos.
    dni: /^\d{8}$/, // 8 dígitos.
    email: /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/, // Formato de correo electrónico válido.
    telefono: /^\d{1,15}$/ // Hasta 15 dígitos.
}

const verificarCampo = (nombreCampo, valor) => {
    switch (nombreCampo) {
        case "apellidoNombre":
            valor = valor.replace(/\d/g, '').replace(/[^A-Za-zÁÉÍÓÚáéíóúÑñÜü\s]/g, ''); // Elimina números y símbolos
            const inputAn = document.getElementById('apellidoNombre');
            if (inputAn) {
                inputAn.value = valor;
            }
            return expresiones.apellidoNombre.test(valor);

        case "dni":
            return expresiones.dni.test(valor);

        case "email":
            return expresiones.email.test(valor) && valor.length <= 30;

        case "telefono":
            valor = valor.replace(/\D/g, ''); // elimina todo lo que no sea un dígito
            const inputTel = document.getElementById('telefono');
            if (inputTel) {
                inputTel.value = valor;
            }
            return expresiones.telefono.test(valor);

        default:
            return true;
    }
}

const validarFormulario = (e) => {
    const grupo = e.target.closest('.formulario_grupo');
    const mensajeRequired = grupo.querySelector('.formulario_required');

    if (mensajeRequired && e.target.value.trim() !== '') {
        mensajeRequired.classList.remove('formulario_required-activo');
    }

    const esValido = verificarCampo(e.target.name, e.target.value);
    validarCampo(esValido, e.target.name);
}

inputs.forEach((input) => {
    input.addEventListener('input', validarFormulario);
    input.addEventListener('blur', validarFormulario);
});

const validarCampo = (esValido, campo) => {
    const grupo = document.getElementById(`grupo_${campo}`);
    const mensajeError = grupo.querySelector('.formulario_input-error');

    if (!mensajeError) return;

    if (!esValido) {
        mensajeError.classList.add('formulario_input-error-activo');
        campos[campo] = false;
    } else {
        mensajeError.classList.remove('formulario_input-error-activo');
        campos[campo] = true;
    }
}

formulario.addEventListener('submit', (e) => {
    let hayErrores = false;

    document.querySelectorAll('[required]').forEach(campo => {
        const grupo = campo.closest('.formulario_grupo');
        const mensaje = grupo.querySelector('.formulario_required');

        if (campo.value.trim() === '') {
            if (mensaje) {
                mensaje.classList.add('formulario_required-activo');
                hayErrores = true;
            } else {
                if (mensaje) {
                    mensaje.classList.remove('formulario_required-activo');
                }
            }
        }
    });

    inputs.forEach((input) => {
        if (campos.hasOwnProperty(input.name)) {
            const esValido = verificarCampo(input.name, input.value);
            validarCampo(esValido, input.name);
        }
    });

    if (!campos.apellidoNombre || !campos.dni || !campos.email || !campos.telefono) {
        hayErrores = true;
    }

    if (hayErrores) {
        e.preventDefault();
        alert('Por favor, complete correctamente todos los campos requeridos antes de enviar el formulario.');
    }
});