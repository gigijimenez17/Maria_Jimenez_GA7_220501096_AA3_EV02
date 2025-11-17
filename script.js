/*
===============================================
MINDMEET - SISTEMA DE REUNIONES CON IA
JavaScript principal completo
===============================================
*/

// ====== VARIABLES GLOBALES ======
let currentAuthView = 'login';
let isLoading = false;
let recordingTimer = null;
let recordingTime = 0;
let isRecording = false;
let currentMeetingData = {};

// ====== INICIALIZACIÓN ======
document.addEventListener('DOMContentLoaded', function() {
    console.log('MindMeet inicializado');
    initializeApplication();
});

/**
 * Inicializa la aplicación
 */
function initializeApplication() {
    // Configurar event listeners para navegación
    setupNavigationListeners();
    
    // Configurar event listeners para formularios
    setupFormListeners();
    
    // Configurar búsqueda en documentos
    setupDocumentSearch();
    
    // Inicializar datos mock
    initializeMockData();
    
    console.log('Aplicación inicializada correctamente');
}

// ====== FUNCIONES PRINCIPALES DE NAVEGACIÓN ======

/**
 * Configura los listeners de navegación
 */
function setupNavigationListeners() {
    const navTabs = document.querySelectorAll('.nav-tab');
    navTabs.forEach(tab => {
        tab.addEventListener('click', function() {
            const screenName = this.getAttribute('data-screen');
            switchScreen(screenName);
        });
    });
}

/**
 * Cambia entre las diferentes pantallas de la aplicación
 * @param {string} screenName - Nombre de la pantalla a mostrar
 */
function switchScreen(screenName) {
    console.log(`Cambiando a pantalla: ${screenName}`);
    
    // Ocultar todas las pantallas con animación
    const screens = document.querySelectorAll('.screen');
    screens.forEach(screen => {
        screen.classList.remove('active');
    });
    
    // Mostrar la pantalla seleccionada
    const targetScreen = document.getElementById(screenName);
    if (targetScreen) {
        setTimeout(() => {
            targetScreen.classList.add('active');
        }, 100);
        
        // Ejecutar funciones específicas según la pantalla
        handleScreenSwitch(screenName);
    } else {
        console.error(`Pantalla no encontrada: ${screenName}`);
    }
    
    // Actualizar estado de navegación
    updateNavigationState(screenName);
}

/**
 * Maneja acciones específicas al cambiar de pantalla
 * @param {string} screenName - Nombre de la pantalla
 */
function handleScreenSwitch(screenName) {
    switch(screenName) {
        case 'dashboard':
            updateDashboardData();
            break;
        case 'recording':
            initializeRecording();
            break;
        case 'analysis':
            loadAnalysisData();
            break;
        case 'documents':
            loadDocuments();
            break;
    }
}

/**
 * Actualiza el estado visual de la navegación
 * @param {string} activeScreen - Pantalla activa
 */
function updateNavigationState(activeScreen) {
    const tabs = document.querySelectorAll('.nav-tab');
    tabs.forEach(tab => {
        tab.classList.remove('active');
        tab.setAttribute('aria-selected', 'false');
    });
    
    const activeTab = document.querySelector(`[data-screen="${activeScreen}"]`);
    if (activeTab) {
        activeTab.classList.add('active');
        activeTab.setAttribute('aria-selected', 'true');
    }
}

// ====== SISTEMA DE AUTENTICACIÓN ======

/**
 * Configura listeners de formularios
 */
function setupFormListeners() {
    // Listeners para validación en tiempo real
    const inputs = document.querySelectorAll('.form-input');
    inputs.forEach(input => {
        input.addEventListener('blur', validateField);
        input.addEventListener('input', clearFieldError);
    });
}

/**
 * Cambia entre las diferentes vistas de autenticación
 * @param {string} viewName - Nombre de la vista
 */
function showAuthView(viewName) {
    console.log(`Cambiando a vista de autenticación: ${viewName}`);
    
    const authViews = document.querySelectorAll('.auth-view');
    authViews.forEach(view => {
        view.classList.remove('active');
    });

    const targetView = document.getElementById(viewName + '-view');
    if (targetView) {
        setTimeout(() => {
            targetView.classList.add('active');
        }, 150);
    }

    updateAuthNavigationState(viewName);
    currentAuthView = viewName;
    clearFormErrors();
}

/**
 * Actualiza el estado de los botones de navegación de auth
 */
function updateAuthNavigationState(viewName) {
    const authNavBtns = document.querySelectorAll('.auth-nav-btn');
    authNavBtns.forEach(btn => btn.classList.remove('active'));

    if (viewName === 'login' || viewName === 'register') {
        const activeBtn = document.querySelector(`[onclick="showAuthView('${viewName}')"]`);
        if (activeBtn) activeBtn.classList.add('active');
    }
}

// ====== FUNCIONES DE VALIDACIÓN ======

/**
 * Valida un campo específico
 * @param {Event} event - Evento del campo
 */
function validateField(event) {
    const field = event.target;
    const fieldId = field.id;
    const value = field.value.trim();
    
    switch(fieldId) {
        case 'login-email':
        case 'register-email':
        case 'forgot-email':
            if (!value) {
                showFieldError(fieldId, 'El correo electrónico es requerido');
            } else if (!validateEmail(value)) {
                showFieldError(fieldId, 'Por favor ingresa un correo válido');
            } else {
                showFieldSuccess(fieldId);
            }
            break;
            
        case 'register-name':
            if (!validateName(value)) {
                showFieldError(fieldId, 'El nombre debe tener al menos 2 caracteres');
            } else {
                showFieldSuccess(fieldId);
            }
            break;
            
        case 'login-password':
        case 'register-password':
            if (!value) {
                showFieldError(fieldId, 'La contraseña es requerida');
            } else if (!validatePassword(value)) {
                showFieldError(fieldId, 'La contraseña debe tener al menos 8 caracteres');
            } else {
                showFieldSuccess(fieldId);
            }
            break;
            
        case 'confirm-password':
            const password = document.getElementById('register-password').value;
            if (value !== password) {
                showFieldError(fieldId, 'Las contraseñas no coinciden');
            } else {
                showFieldSuccess(fieldId);
            }
            break;
    }
}

/**
 * Limpia el error de un campo cuando el usuario empieza a escribir
 */
function clearFieldError(event) {
    const field = event.target;
    const errorElement = document.getElementById(field.id + '-error');
    
    if (field.classList.contains('error') && field.value.trim()) {
        field.classList.remove('error');
        if (errorElement) errorElement.style.display = 'none';
    }
}

function validateEmail(email) {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
}

function validatePassword(password) {
    return password.length >= 8;
}

function validateName(name) {
    return name.trim().length >= 2;
}

// ====== FUNCIONES DE MANEJO DE ERRORES ======

function showFieldError(fieldId, message) {
    const field = document.getElementById(fieldId);
    const errorElement = document.getElementById(fieldId + '-error');
    
    if (field && errorElement) {
        field.classList.add('error');
        field.classList.remove('success');
        errorElement.textContent = message;
        errorElement.style.display = 'block';
        field.focus();
    }
}

function showFieldSuccess(fieldId) {
    const field = document.getElementById(fieldId);
    const errorElement = document.getElementById(fieldId + '-error');
    
    if (field && errorElement) {
        field.classList.remove('error');
        field.classList.add('success');
        errorElement.style.display = 'none';
    }
}

function clearFormErrors() {
    const errorMessages = document.querySelectorAll('.error-message');
    const formInputs = document.querySelectorAll('.form-input');
    
    errorMessages.forEach(error => error.style.display = 'none');
    formInputs.forEach(input => input.classList.remove('error', 'success'));
}

// ====== FUNCIONES DE ESTADO DE CARGA ======

function setLoading(buttonId, loading) {
    const button = document.getElementById(buttonId);
    if (button) {
        if (loading) {
            button.classList.add('loading');
            button.disabled = true;
        } else {
            button.classList.remove('loading');
            button.disabled = false;
        }
    }
}

// ====== MANEJADORES DE FORMULARIOS ======

function handleLogin(event) {
    event.preventDefault();
    console.log('Procesando login...');
    
    const email = document.getElementById('login-email').value.trim();
    const password = document.getElementById('login-password').value;
    let isValid = true;

    clearFormErrors();

    if (!email || !validateEmail(email)) {
        showFieldError('login-email', 'Por favor ingresa un correo válido');
        isValid = false;
    }

    if (!password) {
        showFieldError('login-password', 'La contraseña es requerida');
        isValid = false;
    }

    if (isValid) {
        setLoading('login-btn', true);
        
        setTimeout(() => {
            setLoading('login-btn', false);
            console.log('Login exitoso');
            switchScreen('dashboard');
            showNotification('¡Bienvenido de vuelta!', 'success');
        }, 1500);
    }
}

function handleRegister(event) {
    event.preventDefault();
    console.log('Procesando registro...');
    
    const name = document.getElementById('register-name').value.trim();
    const email = document.getElementById('register-email').value.trim();
    const password = document.getElementById('register-password').value;
    const confirmPassword = document.getElementById('confirm-password').value;
    let isValid = true;

    clearFormErrors();

    if (!validateName(name)) {
        showFieldError('register-name', 'El nombre debe tener al menos 2 caracteres');
        isValid = false;
    }

    if (!email || !validateEmail(email)) {
        showFieldError('register-email', 'Por favor ingresa un correo válido');
        isValid = false;
    }

    if (!validatePassword(password)) {
        showFieldError('register-password', 'La contraseña debe tener al menos 8 caracteres');
        isValid = false;
    }

    if (password !== confirmPassword) {
        showFieldError('confirm-password', 'Las contraseñas no coinciden');
        isValid = false;
    }

    if (isValid) {
        setLoading('register-btn', true);
        
        setTimeout(() => {
            setLoading('register-btn', false);
            console.log('Registro exitoso');
            switchScreen('dashboard');
            showNotification('¡Cuenta creada exitosamente!', 'success');
        }, 1500);
    }
}

function handleForgotPassword(event) {
    event.preventDefault();
    
    const email = document.getElementById('forgot-email').value.trim();
    
    if (!email || !validateEmail(email)) {
        showFieldError('forgot-email', 'Por favor ingresa un correo válido');
        return;
    }

    setLoading('forgot-btn', true);
    
    setTimeout(() => {
        setLoading('forgot-btn', false);
        showAuthView('success');
    }, 1500);
}

function handleSocialLogin(provider) {
    console.log(`Login con ${provider}`);
    showNotification(`Redirigiendo a ${provider}...`, 'info');
    
    setTimeout(() => {
        switchScreen('dashboard');
        showNotification('¡Bienvenido!', 'success');
    }, 2000);
}

// ====== FUNCIONES DEL DASHBOARD ======

function updateDashboardData() {
    // Actualizar estadísticas con animación
    animateNumbers();
    
    // Actualizar reuniones recientes
    loadRecentMeetings();
}

function animateNumbers() {
    const statNumbers = document.querySelectorAll('.stat-number');
    const finalValues = [24, 18, 94, 12];
    
    statNumbers.forEach((element, index) => {
        const finalValue = finalValues[index];
        let currentValue = 0;
        const increment = finalValue / 20;
        const suffix = element.textContent.includes('%') ? '%' : 
                      element.textContent.includes('h') ? 'h' : '';
        
        const timer = setInterval(() => {
            currentValue += increment;
            if (currentValue >= finalValue) {
                currentValue = finalValue;
                clearInterval(timer);
            }
            element.textContent = Math.round(currentValue) + suffix;
        }, 50);
    });
}

function loadRecentMeetings() {
    // Los datos ya están en el HTML, pero aquí podrías cargar desde una API
    console.log('Reuniones recientes cargadas');
}

// ====== FUNCIONES DE GRABACIÓN ======

function initializeRecording() {
    if (!isRecording) {
        startRecording();
    }
}

function startRecording() {
    console.log('Iniciando grabación...');
    isRecording = true;
    recordingTime = 0;
    
    updateRecordingTimer();
    recordingTimer = setInterval(updateRecordingTimer, 1000);
    
    // Simular actividad de participantes
    simulateParticipantActivity();
}

function updateRecordingTimer() {
    recordingTime++;
    const minutes = Math.floor(recordingTime / 60);
    const seconds = recordingTime % 60;
    const timeDisplay = `${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}`;
    
    const timerElement = document.querySelector('.timer');
    if (timerElement) {
        timerElement.textContent = timeDisplay;
    }
}

function pauseRecording() {
    if (recordingTimer) {
        clearInterval(recordingTimer);
        recordingTimer = null;
    }
    isRecording = false;
    console.log('Grabación pausada');
    
    // Cambiar texto del botón
    const pauseBtn = document.querySelector('.recording-controls .btn-secondary');
    if (pauseBtn) {
        pauseBtn.textContent = isRecording ? 'Pausar' : 'Reanudar';
        pauseBtn.onclick = isRecording ? pauseRecording : resumeRecording;
    }
}

function resumeRecording() {
    if (!recordingTimer) {
        recordingTimer = setInterval(updateRecordingTimer, 1000);
    }
    isRecording = true;
    console.log('Grabación reanudada');
    
    const pauseBtn = document.querySelector('.recording-controls .btn-secondary');
    if (pauseBtn) {
        pauseBtn.textContent = 'Pausar';
        pauseBtn.onclick = pauseRecording;
    }
}

function finishRecording() {
    if (recordingTimer) {
        clearInterval(recordingTimer);
        recordingTimer = null;
    }
    isRecording = false;
    
    // Guardar datos de la reunión
    currentMeetingData = {
        duration: recordingTime,
        participants: ['María Jiménez', 'Brayan Barón', 'José Egurrola'],
        timestamp: new Date()
    };
    
    console.log('Grabación finalizada', currentMeetingData);
    switchScreen('analysis');
}

function simulateParticipantActivity() {
    const participants = document.querySelectorAll('.participant-avatar');
    
    setInterval(() => {
        participants.forEach(participant => {
            if (Math.random() > 0.7) {
                participant.style.boxShadow = '0 0 20px rgba(99, 102, 241, 0.5)';
                setTimeout(() => {
                    participant.style.boxShadow = '';
                }, 1000);
            }
        });
    }, 3000);
}

// ====== FUNCIONES DE ANÁLISIS ======

function loadAnalysisData() {
    console.log('Cargando datos de análisis...');
    
    // Simular carga de transcripción
    loadTranscript();
    
    // Animar nodos del mapa mental
    animateMindMapNodes();
}

function loadTranscript() {
    // La transcripción ya está en el HTML, pero aquí se podría cargar dinámicamente
    console.log('Transcripción cargada');
}

function animateMindMapNodes() {
    const nodes = document.querySelectorAll('.mindmap-node');
    
    nodes.forEach((node, index) => {
        setTimeout(() => {
            node.style.opacity = '0';
            node.style.transform = 'scale(0.8)';
            
            setTimeout(() => {
                node.style.transition = 'all 0.5s ease';
                node.style.opacity = '1';
                node.style.transform = 'scale(1)';
            }, 100);
        }, index * 200);
    });
}

function editMindMap() {
    showNotification('Función de edición en desarrollo', 'info');
}

function exportMindMapPDF() {
    showNotification('Exportando mapa mental a PDF...', 'info');
    
    setTimeout(() => {
        showNotification('Mapa mental exportado exitosamente', 'success');
    }, 2000);
}

function generateFinalDocument() {
    showNotification('Generando acta final...', 'info');
    
    setTimeout(() => {
        switchScreen('documents');
        showNotification('Acta generada exitosamente', 'success');
    }, 2000);
}

// ====== FUNCIONES DE DOCUMENTOS ======

function setupDocumentSearch() {
    const searchInput = document.querySelector('.search-input');
    if (searchInput) {
        searchInput.addEventListener('input', handleDocumentSearch);
    }
}

function handleDocumentSearch(event) {
    const searchTerm = event.target.value.toLowerCase();
    const documentCards = document.querySelectorAll('.document-card');
    
    documentCards.forEach(card => {
        const title = card.querySelector('.document-title').textContent.toLowerCase();
        const type = card.querySelector('.document-type').textContent.toLowerCase();
        
        if (title.includes(searchTerm) || type.includes(searchTerm)) {
            card.style.display = 'block';
        } else {
            card.style.display = searchTerm ? 'none' : 'block';
        }
    });
}

function loadDocuments() {
    console.log('Cargando documentos...');
    // Los documentos ya están en el HTML
}

function downloadDocument(documentId) {
    showNotification('Descargando documento...', 'info');
    setTimeout(() => {
        showNotification('Documento descargado', 'success');
    }, 1000);
}

function shareDocument(documentId) {
    showNotification('Enlace de compartir copiado al portapapeles', 'success');
}

function viewDocument(documentId) {
    showNotification('Abriendo documento...', 'info');
}

function editDocument(documentId) {
    showNotification('Abriendo editor...', 'info');
}

function exportDocument(documentId) {
    showNotification('Exportando documento...', 'info');
    setTimeout(() => {
        showNotification('Documento exportado exitosamente', 'success');
    }, 1500);
}

// ====== SISTEMA DE NOTIFICACIONES ======

function showNotification(message, type = 'info') {
    // Crear elemento de notificación
    const notification = document.createElement('div');
    notification.className = `notification notification-${type}`;
    notification.innerHTML = `
        <div class="notification-content">
            <span class="notification-message">${message}</span>
            <button class="notification-close" onclick="this.parentElement.parentElement.remove()">×</button>
        </div>
    `;
    
    // Agregar estilos si no existen
    if (!document.querySelector('#notification-styles')) {
        const styles = document.createElement('style');
        styles.id = 'notification-styles';
        styles.textContent = `
            .notification {
                position: fixed;
                top: 20px;
                right: 20px;
                background: white;
                border-radius: 8px;
                padding: 15px 20px;
                box-shadow: 0 4px 12px rgba(0,0,0,0.15);
                z-index: 1000;
                max-width: 300px;
                animation: slideIn 0.3s ease;
            }
            .notification-success { border-left: 4px solid #10b981; }
            .notification-error { border-left: 4px solid #ef4444; }
            .notification-info { border-left: 4px solid #3b82f6; }
            .notification-content {
                display: flex;
                justify-content: space-between;
                align-items: center;
                gap: 10px;
            }
            .notification-close {
                background: none;
                border: none;
                font-size: 18px;
                cursor: pointer;
                color: #64748b;
            }
            @keyframes slideIn {
                from { transform: translateX(100%); opacity: 0; }
                to { transform: translateX(0); opacity: 1; }
            }
        `;
        document.head.appendChild(styles);
    }
    
    document.body.appendChild(notification);
    
    // Auto-remover después de 3 segundos
    setTimeout(() => {
        if (notification.parentElement) {
            notification.remove();
        }
    }, 3000);
}

// ====== DATOS MOCK ======

function initializeMockData() {
    // Aquí se podrían cargar datos iniciales desde localStorage o una API
    console.log('Datos mock inicializados');
}

// ====== UTILIDADES ======

/**
 * Formatea tiempo en formato MM:SS
 */
function formatTime(seconds) {
    const minutes = Math.floor(seconds / 60);
    const remainingSeconds = seconds % 60;
    return `${minutes.toString().padStart(2, '0')}:${remainingSeconds.toString().padStart(2, '0')}`;
}

/**
 * Genera ID único
 */
function generateId() {
    return Date.now().toString(36) + Math.random().toString(36).substr(2);
}

// ====== MANEJADORES GLOBALES ======

// Manejar errores globales
window.addEventListener('error', function(event) {
    console.error('Error global:', event.error);
    showNotification('Ha ocurrido un error inesperado', 'error');
});

// Manejar cambios de tamaño de ventana
window.addEventListener('resize', function() {
    // Ajustes responsivos adicionales si son necesarios
    console.log('Ventana redimensionada');
});

console.log('MindMeet JavaScript cargado completamente');