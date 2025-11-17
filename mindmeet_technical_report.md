**Plan de Contingencia:**
- Hotfix inmediato si se detecta vulnerabilidad
- Rollback a versión anterior si es necesario
- Comunicación transparente con stakeholders
- Auditoría externa de seguridad

---

### 8.3 Monitoreo de Riesgos

Los riesgos se revisan en cada Sprint Review con el siguiente formato:

| Riesgo | Estado | Tendencia | Acciones |
|--------|--------|-----------|----------|
| R01 | 🟢 Verde | → Estable | Continuar monitoreando |
| R02 | 🟡 Amarillo | ↑ Aumentando | Activar plan de mitigación |
| R04 | 🟢 Verde | ↓ Disminuyendo | Mantener controles |

---

## 9. RECURSOS DEL PROYECTO

### 9.1 Equipo de Desarrollo

| Rol | Nombre | Dedicación | Costo/Mes | Costo Total |
|-----|--------|------------|-----------|-------------|
| **Tech Lead / Scrum Master** | María Jiménez | 100% | $8,000 | $24,000 |
| **Senior Backend Developer** | Brayan Barón | 100% | $7,000 | $21,000 |
| **Frontend Developer** | José Egurrola | 100% | $6,000 | $18,000 |
| **Product Owner** | Cliente Deloitte | 25% | $2,000 | $6,000 |
| **QA Engineer** | Equipo rotativo | 50% | $3,000 | $9,000 |
| **DevOps Engineer** | Consultor externo | 25% | $2,000 | $6,000 |
| **Total Personal** | | | **$28,000** | **$84,000** |

### 9.2 Infraestructura y Herramientas

| Categoría | Recurso | Costo Mensual | 3 Meses | Anual |
|-----------|---------|---------------|---------|-------|
| **Cloud (AWS)** | EC2 + RDS + S3 | $150 | $450 | $1,800 |
| **IA (OpenAI)** | Whisper + GPT | $200 | $600 | $2,400 |
| **Herramientas** | GitHub, Figma, etc | $100 | $300 | $1,200 |
| **Dominio/SSL** | Hosting | $15 | $45 | $180 |
| **Monitoreo** | Datadog/New Relic | $50 | $150 | $600 |
| **Total Infraestructura** | | **$515** | **$1,545** | **$6,180** |

### 9.3 Otros Costos

| Concepto | Costo |
|----------|-------|
| Licencias de software | $2,000 |
| Capacitación del equipo | $1,500 |
| Contingencia (10%) | $8,500 |
| **Total Otros** | **$12,000** |

### 9.4 Infraestructura Física

| Recurso | Descripción | Disponibilidad |
|---------|-------------|----------------|
| **Oficina** | Espacio de trabajo en Deloitte | ✅ Proporcionado |
| **Hardware** | Laptops desarrollo (3) | ✅ Proporcionado |
| **Internet** | Conexión de alta velocidad | ✅ Proporcionado |
| **Servidores** | Ambiente de pruebas | ✅ Proporcionado |

---

## 10. ESTIMACIÓN DE COSTOS

### 10.1 Resumen de Costos del Proyecto

| Categoría | Subtotal |
|-----------|----------|
| **Personal (3 meses)** | $84,000 |
| **Infraestructura (3 meses)** | $1,545 |
| **Infraestructura (Anual)** | $6,180 |
| **Otros costos** | $12,000 |
| **Total Proyecto (MVP)** | **$97,545** |
| **Total Primer Año** | **$103,725** |

### 10.2 Desglose por Fase

| Fase | % Esfuerzo | Costo |
|------|------------|-------|
| Análisis y Diseño | 15% | $12,600 |
| Configuración | 5% | $4,200 |
| Backend | 35% | $29,400 |
| Frontend | 20% | $16,800 |
| Integración y Pruebas | 10% | $8,400 |
| Despliegue | 8% | $6,720 |
| Capacitación y Entrega | 7% | $5,880 |
| **Total** | **100%** | **$84,000** |

### 10.3 Análisis Costo-Beneficio

#### Costos

**Inversión Inicial (Año 1):**
- Desarrollo del proyecto: $97,545
- Infraestructura anual: $6,180
- **Total Año 1:** $103,725

**Costos Operativos Anuales (Años 2+):**
- Infraestructura: $6,180
- Mantenimiento (1 FTE): $72,000
- **Total Anual:** $78,180

#### Beneficios

**Ahorros Anuales Proyectados:**

| Concepto | Cálculo | Ahorro Anual |
|----------|---------|--------------|
| Tiempo en documentación | 500 empleados × 2 reuniones/semana × 28 min ahorrados × $50/hora | $120,000 |
| Mejora en seguimiento | 30% más proyectos completados × $200K valor promedio | $60,000 |
| Reducción de reuniones | 15% menos reuniones necesarias × $100K costo | $15,000 |
| Productividad general | 5% mejora × 500 empleados × $100K salario promedio | $45,000 |
| **Total Beneficios Anuales** | | **$240,000** |

#### ROI Proyectado

```
ROI Año 1 = (Beneficios - Costos) / Costos × 100
ROI Año 1 = ($240,000 - $103,725) / $103,725 × 100
ROI Año 1 = 131%

ROI Año 2 = ($240,000 - $78,180) / $78,180 × 100
ROI Año 2 = 207%

ROI Acumulado 3 Años = 360%
```

**Payback Period:** 5.2 meses

---

## 11. CRITERIOS DE ACEPTACIÓN

### 11.1 Criterios Funcionales

#### 11.1.1 Módulo de Autenticación

- ✅ Usuario puede registrarse con email y contraseña
- ✅ Sistema valida email único
- ✅ Contraseña debe tener mínimo 8 caracteres
- ✅ Usuario puede iniciar sesión y recibir token JWT
- ✅ Token expira en 24 horas
- ✅ Usuario puede recuperar contraseña por email
- ✅ Usuario puede hacer login con Google
- ✅ Usuario puede hacer login con GitHub

#### 11.1.2 Módulo de Reuniones

- ✅ Usuario puede crear reunión con título y descripción
- ✅ Usuario puede listar sus reuniones con paginación
- ✅ Usuario puede ver detalles de reunión específica
- ✅ Usuario puede editar reunión (solo organizador)
- ✅ Usuario puede eliminar reunión (solo organizador)
- ✅ Usuario puede agregar participantes por email
- ✅ Usuario puede iniciar reunión (cambia estado)
- ✅ Usuario puede finalizar reunión (calcula duración)
- ✅ Usuario puede subir grabación (audio/video)
- ✅ Sistema valida formatos permitidos

#### 11.1.3 Módulo de Inteligencia Artificial

- ✅ Sistema transcribe audio automáticamente
- ✅ Precisión de transcripción > 90%
- ✅ Sistema identifica diferentes hablantes
- ✅ Sistema genera resumen ejecutivo
- ✅ Sistema extrae acciones y compromisos
- ✅ Procesamiento completo en < 5 min por hora de audio

#### 11.1.4 Módulo de Documentos

- ✅ Sistema genera acta automáticamente
- ✅ Usuario puede exportar a PDF
- ✅ Usuario puede exportar a Word
- ✅ Usuario puede buscar en documentos
- ✅ Usuario puede compartir documento por email
- ✅ Sistema mantiene versionamiento

#### 11.1.5 Dashboard

- ✅ Usuario ve total de reuniones del mes
- ✅ Usuario ve actas generadas
- ✅ Usuario ve precisión promedio de transcripción
- ✅ Usuario ve tiempo ahorrado
- ✅ Gráficos visualizan tendencias

### 11.2 Criterios No Funcionales

#### 11.2.1 Rendimiento

| Métrica | Objetivo | Medición |
|---------|----------|----------|
| Tiempo de respuesta API | < 200ms | Percentil 95 |
| Tiempo de carga página | < 2s | Primera carga |
| Procesamiento transcripción | < 5 min/hora audio | Promedio |
| Usuarios concurrentes | > 1000 | Sin degradación |

#### 11.2.2 Seguridad

- ✅ Comunicación HTTPS obligatoria
- ✅ Contraseñas encriptadas con BCrypt
- ✅ Tokens JWT firmados y verificados
- ✅ Protección contra SQL Injection
- ✅ Protección contra XSS
- ✅ Protección contra CSRF
- ✅ Rate limiting configurado (100 req/min)
- ✅ Logs de auditoría de accesos

#### 11.2.3 Disponibilidad

- ✅ Uptime > 99.5% (máximo 3.6 horas downtime/mes)
- ✅ Recovery Time Objective (RTO) < 15 minutos
- ✅ Recovery Point Objective (RPO) < 1 hora
- ✅ Backups automáticos diarios
- ✅ Monitoreo 24/7 con alertas

#### 11.2.4 Escalabilidad

- ✅ Arquitectura permite escalamiento horizontal
- ✅ Base de datos soporta 100K+ registros
- ✅ Almacenamiento ilimitado (S3)
- ✅ Load balancer configurado

#### 11.2.5 Usabilidad

- ✅ Interfaz intuitiva (usuario puede completar tarea sin capacitación)
- ✅ Diseño responsive (mobile, tablet, desktop)
- ✅ Accesibilidad WCAG 2.1 Nivel AA
- ✅ Tiempo de aprendizaje < 30 minutos
- ✅ Satisfacción de usuarios > 85%

#### 11.2.6 Mantenibilidad

- ✅ Código documentado (JavaDoc en todas las clases públicas)
- ✅ Cobertura de tests > 80%
- ✅ Complejidad ciclomática < 10
- ✅ Deuda técnica < 5%
- ✅ Documentación técnica completa

### 11.3 Proceso de Validación

**Cada historia de usuario será validada mediante:**

1. **Demo en Sprint Review**
   - Demostración funcional al Product Owner
   - Verificación de criterios de aceptación

2. **Pruebas de Aceptación**
   - Test cases automatizados
   - Pruebas manuales exploratorias
   - Pruebas con usuarios reales

3. **Sign-off Formal**
   - Product Owner aprueba la historia
   - Se documenta en acta de sprint

---

## 12. PLAN DE CALIDAD

### 12.1 Estrategia de Calidad

**Objetivo:** Entregar software de alta calidad que cumpla con estándares empresariales de Deloitte.

**Enfoque:** Integración de calidad en cada fase del desarrollo (Shift-Left Testing).

### 12.2 Niveles de Testing

#### 12.2.1 Tests Unitarios (Unit Tests)

**Responsable:** Cada desarrollador

**Herramientas:**
- JUnit 5
- Mockito
- AssertJ

**Cobertura objetivo:** > 80%

**Ejemplo:**
```java
@Test
void testUserRegistration_Success() {
    // Given
    RegisterRequest request = new RegisterRequest(
        "Test User", "test@example.com", "password123"
    );
    
    // When
    AuthResponse response = authService.registerUser(request);
    
    // Then
    assertThat(response.isSuccess()).isTrue();
    assertThat(response.getToken()).isNotNull();
}
```

#### 12.2.2 Tests de Integración

**Responsable:** Equipo de desarrollo

**Herramientas:**
- Spring Boot Test
- TestContainers (PostgreSQL)
- MockMvc

**Cobertura objetivo:** > 70%

**Alcance:**
- Integración con base de datos
- Integración con APIs externas (mocks)
- Flujos end-to-end

#### 12.2.3 Tests de API (Contract Testing)

**Responsable:** Backend team

**Herramientas:**
- REST Assured
- Postman Collections

**Alcance:**
- Validación de contratos de API
- Validación de esquemas JSON
- Pruebas de seguridad básicas

#### 12.2.4 Tests de UI

**Responsable:** Frontend developer

**Herramientas:**
- Selenium
- Cypress (futuro)

**Alcance:**
- Flujos críticos de usuario
- Validaciones de formularios
- Navegación

#### 12.2.5 Tests de Rendimiento

**Responsable:** Tech Lead

**Herramientas:**
- JMeter
- Gatling

**Métricas:**
- Throughput (requests/segundo)
- Latencia (percentiles 50, 95, 99)
- Uso de recursos (CPU, memoria)

**Escenarios:**
- 100 usuarios concurrentes
- 500 usuarios concurrentes
- 1000 usuarios concurrentes

#### 12.2.6 Tests de Seguridad

**Responsable:** Tech Lead + Consultor externo

**Herramientas:**
- OWASP ZAP
- Burp Suite
- SonarQube (análisis estático)

**Alcance:**
- OWASP Top 10
- Penetration testing
- Vulnerability scanning

### 12.3 Proceso de QA

```
Desarrollo → Code Review → Tests Unitarios → Tests Integración
                ↓               ↓                    ↓
           Build falla    Build falla         Build falla
                ↓               ↓                    ↓
              [FIX]           [FIX]                [FIX]
                ↓               ↓                    ↓
        Tests pasan → Deploy a Dev → QA Manual → Tests Aceptación
                                         ↓               ↓
                                    ¿Aprobado?      ¿Aprobado?
                                         │               │
                                      NO │            NO │
                                         ↓               ↓
                                       [FIX]           [FIX]
                                         │               │
                                      SÍ │            SÍ │
                                         ↓               ↓
                                   Deploy a Staging → UAT
                                                        ↓
                                                   ¿Aprobado?
                                                        │
                                                     SÍ │
                                                        ↓
                                                Deploy Producción
```

### 12.4 Métricas de Calidad

| Métrica | Objetivo | Frecuencia | Responsable |
|---------|----------|------------|-------------|
| **Cobertura de código** | > 80% | Por commit | SonarQube |
| **Bugs críticos** | 0 | Continua | Todo equipo |
| **Deuda técnica** | < 5% | Semanal | Tech Lead |
| **Code smells** | < 10/módulo | Por PR | SonarQube |
| **Duplicación** | < 3% | Por commit | SonarQube |
| **Complejidad** | < 10 | Por clase | SonarQube |
| **Defect Density** | < 1 defecto/100 LOC | Sprint | QA Engineer |
| **Test Pass Rate** | > 95% | Continua | CI/CD |

### 12.5 Gestión de Defectos

**Clasificación de Severidad:**

| Severidad | Descripción | SLA Resolución |
|-----------|-------------|----------------|
| **Crítica** | Sistema caído, pérdida de datos | 4 horas |
| **Alta** | Funcionalidad principal no funciona | 24 horas |
| **Media** | Funcionalidad secundaria afectada | 1 semana |
| **Baja** | Problema cosmético, mejora | Siguiente sprint |

**Proceso de Manejo de Bugs:**

1. **Reporte** → Usuario o QA reporta bug
2. **Triaje** → Tech Lead clasifica severidad
3. **Asignación** → Se asigna a desarrollador
4. **Fix** → Desarrollador corrige
5. **Verificación** → QA verifica fix
6. **Cierre** → Bug cerrado con documentación

### 12.6 Herramientas de Calidad

| Herramienta | Propósito | Integración |
|-------------|-----------|-------------|
| **SonarQube** | Análisis estático de código | CI/CD pipeline |
| **JaCoCo** | Cobertura de código | Maven build |
| **Checkstyle** | Estilo de código | Maven build |
| **SpotBugs** | Detección de bugs | CI/CD |
| **OWASP Dependency Check** | Vulnerabilidades en dependencias | CI/CD |
| **JMeter** | Performance testing | Manual |
| **Selenium** | UI testing | CI/CD |

---

## 13. PLAN DE COMUNICACIÓN

### 13.1 Stakeholders del Proyecto

| Stakeholder | Rol | Interés | Influencia | Estrategia |
|-------------|-----|---------|------------|------------|
| **Directivos Deloitte** | Sponsors | Alto | Alto | Mantener satisfechos |
| **Product Owner** | Cliente interno | Muy Alto | Alto | Gestionar de cerca |
| **Usuarios Finales** | Usuarios | Muy Alto | Medio | Mantener informados |
| **Equipo de TI** | Soporte | Medio | Medio | Mantener informados |
| **Equipo Desarrollo** | Ejecutores | Muy Alto | Alto | Gestionar de cerca |
| **Consultores IA** | Asesores técnicos | Medio | Bajo | Monitorear |

### 13.2 Canales de Comunicación

| Canal | Frecuencia | Audiencia | Propósito |
|-------|------------|-----------|-----------|
| **Daily Standup** | Diario | Equipo desarrollo | Sincronización |
| **Sprint Review** | Cada 2 semanas | Todo el equipo + PO | Demo de incremento |
| **Sprint Planning** | Cada 2 semanas | Equipo desarrollo + PO | Planificar sprint |
| **Retrospectiva** | Cada 2 semanas | Equipo desarrollo | Mejora continua |
| **Status Report** | Semanal | Directivos | Reporte de avance |
| **Slack Channel** | Continuo | Todo el equipo | Comunicación informal |
| **Email Updates** | Quincenal | Todos stakeholders | Actualizaciones |
| **Demo Ejecutiva** | Mensual | Directivos | Avance genera# 📋 INFORME TÉCNICO DE PLAN DE TRABAJO
## Sistema de Reuniones con Inteligencia Artificial - MindMeet

---

**Cliente:** Deloitte Colombia  
**Proyecto:** MindMeet - Sistema de Reuniones con IA  
**Versión del Documento:** 1.0  
**Fecha:** 15 de Marzo, 2025  
**Estado:** Aprobado  

---

## ÍNDICE

1. [Resumen Ejecutivo](#1-resumen-ejecutivo)
2. [Alcance del Proyecto](#2-alcance-del-proyecto)
3. [Objetivos del Proyecto](#3-objetivos-del-proyecto)
4. [Análisis de Tecnologías Seleccionadas](#4-análisis-de-tecnologías-seleccionadas)
5. [Arquitectura del Sistema](#5-arquitectura-del-sistema)
6. [Metodología de Desarrollo](#6-metodología-de-desarrollo)
7. [Plan de Trabajo Detallado](#7-plan-de-trabajo-detallado)
8. [Análisis de Riesgos](#8-análisis-de-riesgos)
9. [Recursos del Proyecto](#9-recursos-del-proyecto)
10. [Estimación de Costos](#10-estimación-de-costos)
11. [Criterios de Aceptación](#11-criterios-de-aceptación)
12. [Plan de Calidad](#12-plan-de-calidad)
13. [Plan de Comunicación](#13-plan-de-comunicación)
14. [Entregables del Proyecto](#14-entregables-del-proyecto)
15. [Conclusiones y Recomendaciones](#15-conclusiones-y-recomendaciones)

---

## 1. RESUMEN EJECUTIVO

### 1.1 Contexto del Proyecto

**MindMeet** es una solución tecnológica innovadora diseñada para revolucionar la gestión de reuniones corporativas en Deloitte Colombia mediante el uso de Inteligencia Artificial. El sistema aborda un problema crítico identificado en organizaciones modernas: la pérdida de información valiosa durante reuniones y el tiempo significativo invertido en documentación manual.

### 1.2 Problema Identificado

Según estudios internos de Deloitte:

- **40%** del contenido discutido en reuniones se pierde o no se documenta
- **30 minutos** promedio se invierte manualmente en crear actas de reunión
- **15%** de las decisiones tomadas no se ejecutan por falta de seguimiento
- **$50,000 USD** anuales de pérdida de productividad por reunión ineficaz

### 1.3 Solución Propuesta

MindMeet integra tecnologías de vanguardia:

- **Transcripción automática** con OpenAI Whisper (94% precisión)
- **Generación de actas** mediante procesamiento de lenguaje natural
- **Mapas mentales automáticos** para visualización de temas clave
- **Análisis inteligente** de compromisos y acciones
- **Integración empresarial** con herramientas existentes

### 1.4 Beneficios Esperados

| Métrica | Antes | Después | Mejora |
|---------|-------|---------|--------|
| Tiempo en documentación | 30 min | 2 min | **93%** ↓ |
| Precisión de transcripción | 60% | 94% | **57%** ↑ |
| Seguimiento de compromisos | 40% | 95% | **137%** ↑ |
| Satisfacción de usuarios | 65% | 90% | **38%** ↑ |

### 1.5 Inversión y Retorno

- **Inversión total estimada:** $85,000 USD
- **ROI proyectado:** 280% en primer año
- **Payback period:** 4.3 meses
- **Ahorro anual proyectado:** $240,000 USD

---

## 2. ALCANCE DEL PROYECTO

### 2.1 Alcance Incluido

#### 2.1.1 Módulos Funcionales

**Módulo de Autenticación y Usuarios**
- Sistema de registro con validaciones robustas
- Login tradicional (email/contraseña)
- Autenticación social (Google, GitHub)
- Recuperación de contraseña
- Gestión de sesiones con JWT
- Roles y permisos (Admin, Usuario, Invitado)

**Módulo de Gestión de Reuniones**
- Creación y programación de reuniones
- Invitación de participantes
- Grabación de audio/video
- Estados de reunión (Programada, En Progreso, Completada)
- Gestión de participantes en tiempo real
- Historial completo de reuniones

**Módulo de Inteligencia Artificial**
- Transcripción automática de audio
- Identificación de hablantes
- Generación de resúmenes ejecutivos
- Extracción de acciones y compromisos
- Creación automática de mapas mentales
- Análisis de sentimientos (Fase 2)

**Módulo de Documentos**
- Generación automática de actas
- Exportación múltiple (PDF, Word, JSON)
- Búsqueda avanzada en documentos
- Versionamiento de documentos
- Compartir y colaboración

**Módulo de Dashboard y Estadísticas**
- Métricas de reuniones por periodo
- Indicadores de productividad
- Precisión de transcripciones
- Tiempo ahorrado calculado
- Gráficos y visualizaciones

#### 2.1.2 Integraciones

- ✅ API REST completa y documentada
- ✅ Integración con Gmail (SMTP)
- ✅ Almacenamiento en la nube (AWS S3)
- ✅ OpenAI API (Whisper, GPT)
- 🔄 Google Calendar (Fase 2)
- 🔄 Microsoft Teams (Fase 2)

#### 2.1.3 Aspectos No Funcionales

- **Seguridad:** Encriptación end-to-end, JWT, HTTPS
- **Rendimiento:** Respuesta API < 200ms
- **Escalabilidad:** Soporte para 1000+ usuarios concurrentes
- **Disponibilidad:** 99.5% uptime
- **Usabilidad:** Interfaz intuitiva, responsive
- **Mantenibilidad:** Código documentado, modular

### 2.2 Alcance Excluido

- ❌ Aplicaciones móviles nativas (planificado para Q3 2025)
- ❌ Videollamadas integradas (usar plataformas existentes)
- ❌ Traducción en tiempo real (planificado para Q4 2025)
- ❌ Integración con Zoom/Webex (planificado para Q3 2025)
- ❌ Reconocimiento facial de participantes
- ❌ Realidad aumentada para presentaciones

### 2.3 Supuestos del Proyecto

1. Deloitte proporcionará infraestructura de AWS
2. Acceso a OpenAI API con créditos suficientes
3. Usuarios finales tienen acceso a navegadores modernos
4. Internet estable (mínimo 5 Mbps)
5. Disponibilidad del equipo de desarrollo completo
6. Acceso a stakeholders para feedback continuo

### 2.4 Restricciones

- **Presupuesto:** Máximo $100,000 USD
- **Tiempo:** 12 semanas para MVP
- **Tecnología:** Debe usar stack Java/Spring Boot
- **Normativas:** Cumplimiento GDPR y normativas colombianas
- **Recursos:** Equipo de 3 desarrolladores
- **Calidad:** Cobertura de tests mínima 80%

---

## 3. OBJETIVOS DEL PROYECTO

### 3.1 Objetivo General

Desarrollar e implementar un sistema integral de gestión de reuniones con Inteligencia Artificial que automatice la transcripción, documentación y seguimiento de reuniones corporativas en Deloitte Colombia, logrando reducir en 90% el tiempo invertido en documentación manual y aumentando la precisión de captura de información a 94%.

### 3.2 Objetivos Específicos

#### 3.2.1 Objetivos Técnicos

1. **Implementar sistema de autenticación seguro**
   - JWT con expiración de 24 horas
   - Encriptación BCrypt para contraseñas
   - OAuth 2.0 para proveedores externos
   - **KPI:** 0 brechas de seguridad

2. **Desarrollar API REST completa**
   - 20+ endpoints documentados
   - Tiempo de respuesta < 200ms
   - Rate limiting configurado
   - **KPI:** 100% endpoints documentados en Swagger

3. **Integrar transcripción IA**
   - Precisión mínima 90%
   - Soporte español latinoamericano
   - Procesamiento < 5 minutos por hora de audio
   - **KPI:** 94% precisión de transcripción

4. **Implementar generación automática de documentos**
   - Actas estructuradas
   - Mapas mentales interactivos
   - Exportación múltiples formatos
   - **KPI:** 95% satisfacción con calidad

#### 3.2.2 Objetivos de Negocio

1. **Reducir tiempo de documentación**
   - De 30 min a 3 min por reunión
   - **KPI:** 90% reducción de tiempo

2. **Aumentar seguimiento de compromisos**
   - De 40% a 95% de seguimiento
   - **KPI:** 95% trazabilidad

3. **Mejorar satisfacción de usuarios**
   - De 65% a 90% satisfacción
   - **KPI:** NPS > 50

4. **Generar ROI positivo**
   - ROI > 200% primer año
   - **KPI:** $240K ahorro anual

#### 3.2.3 Objetivos de Calidad

1. **Cobertura de pruebas**
   - Tests unitarios > 80%
   - Tests integración > 70%
   - **KPI:** 85% cobertura total

2. **Disponibilidad del sistema**
   - Uptime > 99.5%
   - Recovery time < 15 min
   - **KPI:** 99.8% disponibilidad

3. **Deuda técnica**
   - Mantener deuda técnica < 5%
   - Code smells < 10 por módulo
   - **KPI:** Calidad A en SonarQube

### 3.3 Indicadores de Éxito (KPIs)

| KPI | Métrica | Meta | Frecuencia |
|-----|---------|------|------------|
| **Adopción de usuarios** | Usuarios activos mensuales | 500+ | Mensual |
| **Tiempo de documentación** | Minutos/reunión | < 3 min | Semanal |
| **Precisión IA** | % transcripción correcta | > 94% | Diaria |
| **Satisfacción** | NPS Score | > 50 | Trimestral |
| **Disponibilidad** | % Uptime | > 99.5% | Continua |
| **Performance** | Tiempo respuesta API | < 200ms | Continua |
| **ROI** | Retorno inversión | > 200% | Anual |

---

## 4. ANÁLISIS DE TECNOLOGÍAS SELECCIONADAS

### 4.1 Criterios de Selección

Para la selección de tecnologías se consideraron los siguientes criterios:

1. **Madurez tecnológica** (peso 25%)
2. **Escalabilidad** (peso 20%)
3. **Capa de Datos**
   - Encriptación de passwords (BCrypt)
   - Cifrado de datos sensibles
   - Respaldos automáticos
   - Control de acceso por roles

4. **Capa de Infraestructura**
   - VPC privada en AWS
   - Security Groups configurados
   - Logs de auditoría
   - Monitoreo de intrusiones

---

## 6. METODOLOGÍA DE DESARROLLO

### 6.1 Marco de Trabajo: Scrum

**Justificación de Scrum:**

```
✅ Entregas incrementales cada 2 semanas
✅ Adaptabilidad a cambios de requisitos
✅ Feedback continuo de stakeholders
✅ Transparencia en el progreso
✅ Equipos auto-organizados
✅ Mejora continua
```

### 6.2 Ceremonias Scrum

#### Sprint Planning (Planificación)
- **Frecuencia:** Inicio de cada sprint (cada 2 semanas)
- **Duración:** 4 horas
- **Participantes:** Todo el equipo + Product Owner
- **Objetivo:** Definir el trabajo del sprint

#### Daily Standup (Reunión Diaria)
- **Frecuencia:** Diaria
- **Duración:** 15 minutos
- **Formato:** 
  - ¿Qué hice ayer?
  - ¿Qué haré hoy?
  - ¿Tengo algún impedimento?

#### Sprint Review (Revisión)
- **Frecuencia:** Fin de sprint
- **Duración:** 2 horas
- **Participantes:** Equipo + Stakeholders
- **Objetivo:** Demostrar incremento del producto

#### Sprint Retrospective (Retrospectiva)
- **Frecuencia:** Después de Sprint Review
- **Duración:** 1.5 horas
- **Participantes:** Equipo de desarrollo
- **Objetivo:** Identificar mejoras

### 6.3 Roles del Equipo

| Rol | Responsable | Responsabilidades |
|-----|-------------|-------------------|
| **Product Owner** | Deloitte Lead | Definir visión, priorizar backlog |
| **Scrum Master** | María Jiménez | Facilitar ceremonias, remover impedimentos |
| **Tech Lead** | María Jiménez | Decisiones técnicas, arquitectura |
| **Backend Developer** | Brayan Barón | Desarrollo backend, integración IA |
| **Frontend Developer** | José Egurrola | Desarrollo frontend, UX/UI |
| **QA Engineer** | Equipo rotativo | Pruebas, calidad |

### 6.4 Definición de "Done"

Una historia de usuario se considera completada cuando:

- ✅ Código implementado y funcional
- ✅ Tests unitarios escritos (cobertura > 80%)
- ✅ Tests de integración pasando
- ✅ Código revisado (Code Review)
- ✅ Documentación actualizada
- ✅ Sin deuda técnica crítica
- ✅ Desplegado en ambiente de desarrollo
- ✅ Aceptado por Product Owner

### 6.5 Gestión de Código

#### Estrategia de Branching (Git Flow)

```
main (producción)
  │
  ├── develop (desarrollo)
  │     │
  │     ├── feature/auth-jwt
  │     ├── feature/meeting-crud
  │     ├── feature/ai-transcription
  │     │
  │     └── hotfix/security-patch
  │
  └── release/v1.0.0
```

#### Reglas de Branches

1. **main**: Solo código en producción
2. **develop**: Integración continua de features
3. **feature/***: Nuevas funcionalidades
4. **hotfix/***: Correcciones urgentes
5. **release/***: Preparación de versiones

#### Proceso de Merge

```bash
# 1. Crear feature branch
git checkout -b feature/nueva-funcionalidad develop

# 2. Desarrollar y commit
git commit -m "feat: Agregar nueva funcionalidad"

# 3. Abrir Pull Request
# 4. Code Review (mínimo 1 aprobación)
# 5. Pasar CI/CD pipeline
# 6. Merge a develop
```

### 6.6 Estándares de Código

#### Convenciones de Nombrado

**Java:**
```java
// Clases: PascalCase
public class UserService { }

// Métodos: camelCase
public void createUser() { }

// Constantes: UPPER_SNAKE_CASE
public static final String API_VERSION = "1.0.0";

// Variables: camelCase
private String userName;
```

#### Comentarios y Documentación

```java
/**
 * Crea un nuevo usuario en el sistema
 * 
 * @param request Datos del usuario a crear
 * @return Usuario creado con ID asignado
 * @throws IllegalArgumentException si el email ya existe
 */
public User createUser(RegisterRequest request) {
    // Validar email único
    if (userRepository.existsByEmail(request.getEmail())) {
        throw new IllegalArgumentException("Email ya registrado");
    }
    
    // Crear y guardar usuario
    User user = new User();
    user.setEmail(request.getEmail());
    // ... resto del código
    
    return userRepository.save(user);
}
```

### 6.7 Proceso de Code Review

**Checklist de Revisión:**

- [ ] ¿El código cumple con los estándares?
- [ ] ¿Hay comentarios suficientes?
- [ ] ¿Los tests están presentes?
- [ ] ¿Hay manejo de errores adecuado?
- [ ] ¿Se validaron las entradas?
- [ ] ¿Hay problemas de seguridad?
- [ ] ¿El código es eficiente?
- [ ] ¿Se actualizó la documentación?

---

## 7. PLAN DE TRABAJO DETALLADO

### 7.1 Cronograma General

| Fase | Duración | Inicio | Fin | Estado |
|------|----------|--------|-----|--------|
| **Fase 1: Análisis y Diseño** | 2 semanas | Sem 1 | Sem 2 | ✅ Completado |
| **Fase 2: Configuración** | 1 semana | Sem 3 | Sem 3 | ✅ Completado |
| **Fase 3: Backend** | 4 semanas | Sem 4 | Sem 7 | 🔄 En progreso |
| **Fase 4: Frontend** | 2 semanas | Sem 8 | Sem 9 | 📅 Planificado |
| **Fase 5: Integración** | 1 semana | Sem 10 | Sem 10 | 📅 Planificado |
| **Fase 6: Despliegue** | 1 semana | Sem 11 | Sem 11 | 📅 Planificado |
| **Fase 7: Entrega** | 1 semana | Sem 12 | Sem 12 | 📅 Planificado |

### 7.2 Desglose por Fases

#### FASE 1: ANÁLISIS Y DISEÑO (Semanas 1-2) ✅

**Sprint 0: Iniciación del Proyecto**

**Semana 1: Levantamiento de Requisitos**

| Actividad | Responsable | Esfuerzo | Entregable |
|-----------|-------------|----------|------------|
| Reunión inicial con stakeholders | Product Owner | 4h | Acta de reunión |
| Definición de alcance | Todo el equipo | 8h | Documento de alcance |
| Identificación de usuarios | Product Owner | 4h | Perfiles de usuario |
| Definición de requisitos funcionales | Todo el equipo | 12h | Documento de requisitos |
| Definición de requisitos no funcionales | Tech Lead | 8h | Documento técnico |
| Priorización de features (MoSCoW) | Product Owner | 4h | Backlog priorizado |

**Semana 2: Diseño del Sistema**

| Actividad | Responsable | Esfuerzo | Entregable |
|-----------|-------------|----------|------------|
| Diseño de arquitectura | Tech Lead | 12h | Diagrama de arquitectura |
| Diseño de base de datos | Tech Lead | 8h | Modelo ER |
| Casos de uso | Todo el equipo | 8h | Diagramas UML |
| Historias de usuario | Product Owner | 12h | User Stories |
| Diseño de API REST | Backend Dev | 8h | Especificación OpenAPI |
| Mockups y prototipos | Frontend Dev | 16h | Prototipos Figma |
| Selección de tecnologías | Tech Lead | 4h | Stack tecnológico |
| Plan de proyecto | Scrum Master | 8h | Cronograma detallado |

**Entregables de la Fase:**
- ✅ Documento de requisitos (50 páginas)
- ✅ Diagramas UML (casos de uso, clases, secuencia)
- ✅ Modelo de base de datos
- ✅ Prototipos visuales (15 pantallas)
- ✅ Especificación de API REST
- ✅ Plan de proyecto detallado

---

#### FASE 2: CONFIGURACIÓN DEL ENTORNO (Semana 3) ✅

**Sprint 1: Setup Inicial**

| Actividad | Responsable | Esfuerzo | Entregable |
|-----------|-------------|----------|------------|
| Configurar repositorio Git | Tech Lead | 2h | Repo configurado |
| Crear proyecto Spring Boot | Backend Dev | 4h | Proyecto base |
| Configurar base de datos PostgreSQL | Backend Dev | 4h | BD operativa |
| Configurar dependencias Maven | Backend Dev | 4h | pom.xml |
| Configurar perfiles (dev/prod) | Tech Lead | 4h | application.yml |
| Setup CI/CD pipeline | Tech Lead | 8h | GitHub Actions |
| Configurar entorno Docker | Backend Dev | 4h | Dockerfile |
| Documentación de setup | Todo el equipo | 4h | README.md |

**Entregables de la Fase:**
- ✅ Proyecto base configurado y funcional
- ✅ Base de datos PostgreSQL operativa
- ✅ Pipeline CI/CD configurado
- ✅ Documentación de configuración
- ✅ Entorno Docker listo

---

#### FASE 3: DESARROLLO BACKEND (Semanas 4-7) 🔄

**Sprint 2: Autenticación y Usuarios (Semana 4)**

| Historia de Usuario | Estimación | Responsable | Tareas |
|---------------------|------------|-------------|--------|
| Como usuario quiero registrarme | 13 pts | Brayan | • Modelo User<br>• UserRepository<br>• AuthService.register()<br>• AuthController.register()<br>• Tests |
| Como usuario quiero iniciar sesión | 8 pts | Brayan | • JWT configuration<br>• AuthService.login()<br>• AuthController.login()<br>• Tests |
| Como usuario quiero recuperar contraseña | 5 pts | Brayan | • EmailService<br>• AuthService.forgotPassword()<br>• Tests |
| Como admin quiero gestionar roles | 5 pts | María | • Modelo Role<br>• RoleRepository<br>• Tests |

**Criterios de Aceptación Sprint 2:**
- ✅ Usuario puede registrarse con email/password
- ✅ Usuario puede iniciar sesión y recibir JWT
- ✅ Usuario puede recuperar contraseña por email
- ✅ Sistema de roles funcional
- ✅ Cobertura de tests > 80%

---

**Sprint 3: Gestión de Reuniones Parte 1 (Semana 5)**

| Historia de Usuario | Estimación | Responsable | Tareas |
|---------------------|------------|-------------|--------|
| Como usuario quiero crear reunión | 8 pts | Brayan | • Modelo Meeting<br>• MeetingRepository<br>• MeetingService.create()<br>• MeetingController.create()<br>• Tests |
| Como usuario quiero listar mis reuniones | 5 pts | Brayan | • MeetingService.list()<br>• Paginación<br>• Filtros<br>• Tests |
| Como usuario quiero ver detalles de reunión | 3 pts | Brayan | • MeetingService.getById()<br>• MeetingController.get()<br>• Tests |
| Como usuario quiero editar reunión | 5 pts | María | • MeetingService.update()<br>• Validaciones<br>• Tests |
| Como usuario quiero eliminar reunión | 3 pts | María | • MeetingService.delete()<br>• Soft delete<br>• Tests |

**Criterios de Aceptación Sprint 3:**
- ✅ CRUD completo de reuniones funcional
- ✅ Paginación implementada
- ✅ Validaciones de negocio
- ✅ Autorización por roles
- ✅ Tests > 80%

---

**Sprint 4: Gestión de Reuniones Parte 2 (Semana 6)**

| Historia de Usuario | Estimación | Responsable | Tareas |
|---------------------|------------|-------------|--------|
| Como usuario quiero agregar participantes | 5 pts | Brayan | • Modelo Participant<br>• MeetingService.addParticipant()<br>• Tests |
| Como usuario quiero iniciar reunión | 5 pts | Brayan | • Estado management<br>• MeetingService.start()<br>• Tests |
| Como usuario quiero finalizar reunión | 5 pts | María | • Cálculo duración<br>• MeetingService.finish()<br>• Tests |
| Como usuario quiero subir grabación | 8 pts | María | • FileStorageService<br>• S3 integration<br>• Upload endpoint<br>• Tests |

**Criterios de Aceptación Sprint 4:**
- ✅ Gestión de participantes funcional
- ✅ Estados de reunión correctos
- ✅ Subida de archivos a S3
- ✅ Validaciones de tamaño/formato
- ✅ Tests > 80%

---

**Sprint 5: Integración IA (Semana 7)**

| Historia de Usuario | Estimación | Responsable | Tareas |
|---------------------|------------|-------------|--------|
| Como sistema quiero transcribir audio | 13 pts | Brayan | • TranscriptionService<br>• OpenAI Whisper integration<br>• Audio processing<br>• Tests |
| Como sistema quiero generar resumen | 8 pts | Brayan | • AIProcessingService<br>• GPT integration<br>• Prompt engineering<br>• Tests |
| Como usuario quiero ver transcripción | 5 pts | María | • Endpoint de transcripción<br>• Formato de respuesta<br>• Tests |
| Como sistema quiero notificar usuarios | 5 pts | María | • NotificationService<br>• Email templates<br>• Tests |

**Criterios de Aceptación Sprint 5:**
- ✅ Transcripción automática funcional
- ✅ Precisión > 90%
- ✅ Generación de resúmenes
- ✅ Notificaciones por email
- ✅ Tests > 75%

---

#### FASE 4: DESARROLLO FRONTEND (Semanas 8-9) 📅

**Sprint 6: Frontend Base (Semana 8)**

| Tarea | Estimación | Responsable | Descripción |
|-------|------------|-------------|-------------|
| Estructura HTML base | 8 pts | José | Layouts, navegación, componentes |
| Sistema de autenticación UI | 8 pts | José | Login, registro, recuperar password |
| Dashboard principal | 5 pts | José | Estadísticas, gráficos, widgets |
| Responsive design | 5 pts | José | Media queries, mobile-first |
| Integración con API | 8 pts | José | Fetch API, manejo de errores |

**Sprint 7: Frontend Avanzado (Semana 9)**

| Tarea | Estimación | Responsable | Descripción |
|-------|------------|-------------|-------------|
| UI de gestión de reuniones | 8 pts | José | Crear, listar, editar, eliminar |
| UI de grabación | 5 pts | José | Timer, participantes, controles |
| UI de análisis | 8 pts | José | Transcripción, mapa mental |
| UI de documentos | 5 pts | José | Lista, búsqueda, descarga |
| Validaciones y UX | 5 pts | José | Forms, feedback, animaciones |

**Criterios de Aceptación Fase 4:**
- ✅ Interfaz completamente funcional
- ✅ Diseño responsive (mobile, tablet, desktop)
- ✅ Integración completa con backend
- ✅ Validaciones en cliente
- ✅ Experiencia de usuario fluida

---

#### FASE 5: INTEGRACIÓN Y PRUEBAS (Semana 10) 📅

**Sprint 8: Testing y Optimización**

| Actividad | Responsable | Esfuerzo | Descripción |
|-----------|-------------|----------|-------------|
| Pruebas de integración | Todo el equipo | 16h | Frontend ↔ Backend |
| Pruebas de rendimiento | Tech Lead | 8h | Load testing, optimización |
| Pruebas de seguridad | Tech Lead | 8h | Penetration testing |
| Pruebas de usabilidad | José | 8h | UX testing con usuarios |
| Corrección de bugs | Todo el equipo | 16h | Bug fixing sprint |
| Optimización de código | Todo el equipo | 8h | Refactoring, mejoras |
| Revisión de documentación | Todo el equipo | 4h | Actualizar docs |

**Entregables de la Fase:**
- ✅ Sistema integrado funcional
- ✅ Reporte de pruebas (100+ casos)
- ✅ Reporte de rendimiento
- ✅ Reporte de seguridad
- ✅ Lista de bugs corregidos
- ✅ Documentación actualizada

---

#### FASE 6: DESPLIEGUE (Semana 11) 📅

**Sprint 9: Preparación para Producción**

| Actividad | Responsable | Esfuerzo | Descripción |
|-----------|-------------|----------|-------------|
| Configurar servidor AWS | Tech Lead | 8h | EC2, RDS, S3, etc. |
| Configurar dominio y SSL | Tech Lead | 4h | Route 53, Certificate Manager |
| Migración de base de datos | Brayan | 4h | Scripts de migración |
| Despliegue de aplicación | Tech Lead | 8h | Deploy pipeline |
| Configurar monitoreo | María | 4h | Prometheus, logs |
| Pruebas en producción | Todo el equipo | 8h | Smoke tests |
| Configurar backups | Tech Lead | 4h | Automated backups |
| Documentación de operaciones | Tech Lead | 4h | Runbooks |

**Entregables de la Fase:**
- ✅ Sistema desplegado en producción
- ✅ Dominio configurado con HTTPS
- ✅ Monitoreo activo
- ✅ Backups automatizados
- ✅ Documentación de operaciones
- ✅ Plan de rollback

---

#### FASE 7: CAPACITACIÓN Y ENTREGA (Semana 12) 📅

**Sprint 10: Cierre del Proyecto**

| Actividad | Responsable | Esfuerzo | Descripción |
|-----------|-------------|----------|-------------|
| Capacitación usuarios finales | Todo el equipo | 8h | Sesiones de training |
| Capacitación equipo soporte | Tech Lead | 4h | Transferencia técnica |
| Documentación de usuario | José | 8h | Manual de usuario |
| Videos tutoriales | José | 8h | Screencast tutorials |
| Presentación ejecutiva | Product Owner | 4h | Demo a stakeholders |
| Entrega formal | Product Owner | 2h | Sign-off del proyecto |
| Retrospectiva final | Todo el equipo | 2h | Lecciones aprendidas |
| Celebración | Todo el equipo | 4h | 🎉 Team building |

**Entregables de la Fase:**
- ✅ Manual de usuario completo
- ✅ Videos tutoriales (5+ videos)
- ✅ Documentación técnica
- ✅ Acta de entrega firmada
- ✅ Plan de soporte
- ✅ Reporte de lecciones aprendidas

---

### 7.3 Diagrama de Gantt

```
Semanas │ 1  │ 2  │ 3  │ 4  │ 5  │ 6  │ 7  │ 8  │ 9  │ 10 │ 11 │ 12 │
────────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┤
Análisis│████│████│    │    │    │    │    │    │    │    │    │    │
Diseño  │████│████│    │    │    │    │    │    │    │    │    │    │
Config  │    │    │████│    │    │    │    │    │    │    │    │    │
Auth    │    │    │    │████│    │    │    │    │    │    │    │    │
Meet 1  │    │    │    │    │████│    │    │    │    │    │    │    │
Meet 2  │    │    │    │    │    │████│    │    │    │    │    │    │
AI Int  │    │    │    │    │    │    │████│    │    │    │    │    │
Front 1 │    │    │    │    │    │    │    │████│    │    │    │    │
Front 2 │    │    │    │    │    │    │    │    │████│    │    │    │
Testing │    │    │    │    │    │    │    │    │    │████│    │    │
Deploy  │    │    │    │    │    │    │    │    │    │    │████│    │
Entrega │    │    │    │    │    │    │    │    │    │    │    │████│
────────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┘
```

### 7.4 Hitos del Proyecto

| # | Hito | Fecha | Criterio de Éxito |
|---|------|-------|-------------------|
| 1 | Diseño aprobado | Semana 2 | Sign-off de stakeholders |
| 2 | Entorno configurado | Semana 3 | Pipeline CI/CD funcionando |
| 3 | Autenticación completa | Semana 4 | Login/registro funcional |
| 4 | CRUD reuniones | Semana 6 | Gestión completa |
| 5 | IA integrada | Semana 7 | Transcripción > 90% |
| 6 | Frontend completo | Semana 9 | UI funcional y responsive |
| 7 | Sistema integrado | Semana 10 | Todas las pruebas pasando |
| 8 | Go-live | Semana 11 | Sistema en producción |
| 9 | Entrega final | Semana 12 | Acta firmada |

---

## 8. ANÁLISIS DE RIESGOS

### 8.1 Matriz de Riesgos

| ID | Riesgo | Probabilidad | Impacto | Severidad | Estrategia |
|----|--------|--------------|---------|-----------|------------|
| R01 | Precisión de IA < 90% | Media | Alto | **Alta** | Mitigar |
| R02 | Retrasos en desarrollo | Media | Medio | Media | Mitigar |
| R03 | Costos de OpenAI altos | Baja | Medio | Media | Transferir |
| R04 | Problemas de rendimiento | Media | Alto | **Alta** | Mitigar |
| R05 | Cambios en requisitos | Alta | Medio | **Alta** | Aceptar |
| R06 | Falta de recursos | Baja | Alto | Media | Evitar |
| R07 | Brechas de seguridad | Baja | Muy Alto | **Alta** | Mitigar |
| R08 | Problemas de integración | Media | Medio | Media | Mitigar |

### 8.2 Plan de Mitigación Detallado

#### R01: Precisión de IA < 90%

**Descripción:** La transcripción de OpenAI Whisper no alcanza la precisión esperada.

**Estrategia de Mitigación:**
- Realizar pruebas exhaustivas con diferentes acentos
- Implementar post-procesamiento de transcripciones
- Permitir corrección manual de transcripciones
- Evaluar modelos alternativos como backup

**Plan de Contingencia:**
- Usar Google Speech-to-Text como alternativa
- Implementar corrección colaborativa
- Reducir expectativa a 85% con disclaimer

---

#### R02: Retrasos en Desarrollo

**Descripción:** El equipo no cumple con los plazos establecidos.

**Estrategia de Mitigación:**
- Daily standups rigurosos
- Tracking de velocity por sprint
- Identificación temprana de blockers
- Buffer de 10% en estimaciones

**Plan de Contingencia:**
- Priorizar features críticos (MoSCoW)
- Extender timeline 1-2 semanas
- Agregar recurso temporal

---

#### R04: Problemas de Rendimiento

**Descripción:** El sistema no maneja la carga esperada.

**Estrategia de Mitigación:**
- Load testing desde Sprint 5
- Optimización de queries de BD
- Implementar caching (Redis)
- CDN para contenido estático

**Plan de Contingencia:**
- Escalamiento vertical (upgrade de servidor)
- Escalamiento horizontal (más instancias)
- Optimización agresiva de código

---

#### R07: Brechas de Seguridad

**Descripción:** Vulnerabilidades en el sistema.

**Estrategia de Mitigación:**
- Security review en cada sprint
- Penetration testing en Semana 10
- Mantener dependencias actualizadas
- Seguir OWASP Top 10

**Plan de Contingencia:**
- Hotfix inmediatoomunidad y soporte** (peso 15%)
4. **Curva de aprendizaje** (peso 10%)
5. **Costo** (peso 10%)
6. **Seguridad** (peso 10%)
7. **Rendimiento** (peso 10%)

### 4.2 Stack Tecnológico Seleccionado

#### 4.2.1 Backend

**Java 17 (LTS)**

```
Justificación:
✅ Versión LTS con soporte hasta 2029
✅ Mejoras en rendimiento vs Java 11 (15-20%)
✅ Records para DTOs más limpios
✅ Pattern matching mejorado
✅ Amplia adopción empresarial
✅ Ecosistema maduro

Alternativas consideradas:
❌ Java 11: Funciona pero sin features recientes
❌ Java 21: Muy reciente, poca adopción
❌ Kotlin: Curva de aprendizaje mayor
```

**Spring Boot 3.2.0**

```
Justificación:
✅ Framework empresarial líder
✅ Ecosistema completo (Security, Data, etc.)
✅ Configuración por convención
✅ Excelente documentación
✅ Gran comunidad
✅ Integración nativa con herramientas

Alternativas consideradas:
❌ Quarkus: Menos maduro para aplicaciones complejas
❌ Micronaut: Comunidad más pequeña
❌ Jakarta EE: Más verboso, menos ágil
```

**PostgreSQL 15**

```
Justificación:
✅ Base de datos relacional robusta
✅ Soporte para JSON (flexibilidad)
✅ Rendimiento excelente
✅ ACID compliant
✅ Código abierto
✅ Extensibilidad

Alternativas consideradas:
❌ MySQL: Menos features avanzados
❌ MongoDB: No óptimo para datos relacionales
❌ Oracle: Costo prohibitivo
```

#### 4.2.2 Seguridad

**Spring Security + JWT**

```
Justificación:
✅ Estándar de facto en Spring
✅ Implementación de JWT madura
✅ Soporte para OAuth 2.0
✅ Protección contra ataques comunes
✅ Altamente configurable
✅ Excelente documentación

Componentes:
- JWT (JJWT 0.12.3)
- BCrypt para passwords
- OAuth 2.0 para login social
```

#### 4.2.3 Inteligencia Artificial

**OpenAI Whisper + GPT-4**

```
Justificación Whisper:
✅ Precisión líder en transcripción (94%+)
✅ Soporte multiidioma
✅ Modelo optimizado para español
✅ API simple de integrar
✅ Costo razonable

Justificación GPT-4:
✅ Mejor modelo para resúmenes
✅ Comprensión contextual superior
✅ Generación de texto natural
✅ API estable y confiable

Alternativas consideradas:
❌ Google Speech-to-Text: Menor precisión en español
❌ AWS Transcribe: Más complejo de integrar
❌ Modelos propios: Requiere infraestructura especializada
```

#### 4.2.4 Frontend

**HTML5 + CSS3 + Vanilla JavaScript**

```
Justificación:
✅ Sin dependencias de frameworks pesados
✅ Rendimiento óptimo
✅ Fácil mantenimiento
✅ Compatible con todos los navegadores
✅ Menor curva de aprendizaje

Para versiones futuras (Q3 2025):
- React para aplicación web avanzada
- React Native para móviles
```

#### 4.2.5 Infraestructura

**AWS (Amazon Web Services)**

```
Componentes:
✅ EC2: Servidor de aplicación
✅ RDS PostgreSQL: Base de datos
✅ S3: Almacenamiento de archivos
✅ CloudFront: CDN
✅ Route 53: DNS
✅ Certificate Manager: SSL/TLS

Justificación:
✅ Líder en cloud computing
✅ Amplia gama de servicios
✅ Alta disponibilidad (99.99%)
✅ Escalabilidad automática
✅ Excelente soporte

Alternativas consideradas:
❌ Azure: Preferencia por AWS en Deloitte
❌ GCP: Menor adopción en la organización
```

### 4.3 Herramientas de Desarrollo

| Categoría | Herramienta | Versión | Propósito |
|-----------|-------------|---------|-----------|
| **IDE** | IntelliJ IDEA | 2024.1 | Desarrollo Java |
| **Control de versiones** | Git | 2.40+ | Gestión de código |
| **Repositorio** | GitHub | - | Hosting de código |
| **Build** | Maven | 3.9+ | Gestión de dependencias |
| **CI/CD** | GitHub Actions | - | Integración continua |
| **Documentación API** | Swagger/OpenAPI | 3.0 | Documentación interactiva |
| **Testing** | JUnit 5 + Mockito | 5.10 / 5.5 | Pruebas unitarias |
| **Cobertura** | JaCoCo | 0.8.11 | Cobertura de código |
| **Análisis estático** | SonarQube | 10.0 | Calidad de código |
| **Contenedores** | Docker | 24.0+ | Contenedorización |
| **Orquestación** | Docker Compose | 2.20+ | Gestión de contenedores |
| **Monitoreo** | Prometheus | 2.45+ | Métricas y alertas |
| **Logs** | ELK Stack | 8.10+ | Agregación de logs |

### 4.4 Dependencias Principales (Maven)

```xml
<!-- Framework -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <version>3.2.0</version>
</dependency>

<!-- Seguridad -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
    <version>3.2.0</version>
</dependency>

<!-- JWT -->
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.12.3</version>
</dependency>

<!-- Base de datos -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
    <version>3.2.0</version>
</dependency>

<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>

<!-- IA -->
<dependency>
    <groupId>com.theokanning.openai-gpt3-java</groupId>
    <artifactId>service</artifactId>
    <version>0.17.0</version>
</dependency>

<!-- Utilidades -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.30</version>
</dependency>

<!-- Documentación -->
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.3.0</version>
</dependency>
```

### 4.5 Justificación de Costos Tecnológicos

| Tecnología | Costo Mensual | Costo Anual | Justificación |
|------------|---------------|-------------|---------------|
| **AWS EC2 (t3.medium)** | $40 | $480 | Servidor aplicación |
| **AWS RDS PostgreSQL** | $50 | $600 | Base de datos |
| **AWS S3** | $20 | $240 | Almacenamiento |
| **OpenAI API** | $200 | $2,400 | Transcripción y GPT |
| **Dominio + SSL** | $15 | $180 | Seguridad |
| **Monitoreo (Datadog)** | $30 | $360 | Observabilidad |
| **Total** | **$355** | **$4,260** | |

---

## 5. ARQUITECTURA DEL SISTEMA

### 5.1 Arquitectura de Alto Nivel

```
┌──────────────────────────────────────────────────────────────┐
│                        CAPA DE CLIENTE                        │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐       │
│  │  Navegador   │  │   Móvil      │  │    API       │       │
│  │    Web       │  │  (Futuro)    │  │   Externa    │       │
│  └──────────────┘  └──────────────┘  └──────────────┘       │
└──────────────────────────────────────────────────────────────┘
                            ↓ HTTPS
┌──────────────────────────────────────────────────────────────┐
│                    CAPA DE APLICACIÓN                         │
│  ┌───────────────────────────────────────────────────────┐   │
│  │               Spring Boot Application                  │   │
│  │                                                         │   │
│  │  ┌────────────┐  ┌─────────────┐  ┌───────────────┐  │   │
│  │  │Controllers │  │   Services  │  │  Repositories │  │   │
│  │  └────────────┘  └─────────────┘  └───────────────┘  │   │
│  │                                                         │   │
│  │  ┌────────────┐  ┌─────────────┐  ┌───────────────┐  │   │
│  │  │  Security  │  │     DTOs    │  │   Utilities   │  │   │
│  │  └────────────┘  └─────────────┘  └───────────────┘  │   │
│  └───────────────────────────────────────────────────────┘   │
└──────────────────────────────────────────────────────────────┘
                            ↓
┌──────────────────────────────────────────────────────────────┐
│                    CAPA DE PERSISTENCIA                       │
│  ┌───────────────────────────────────────────────────────┐   │
│  │             PostgreSQL Database                        │   │
│  │  ┌─────────┐  ┌─────────┐  ┌─────────┐  ┌─────────┐  │   │
│  │  │  Users  │  │Meetings │  │Documents│  │  Roles  │  │   │
│  │  └─────────┘  └─────────┘  └─────────┘  └─────────┘  │   │
│  └───────────────────────────────────────────────────────┘   │
└──────────────────────────────────────────────────────────────┘
                            ↓
┌──────────────────────────────────────────────────────────────┐
│                  SERVICIOS EXTERNOS                           │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐       │
│  │   OpenAI     │  │    AWS S3    │  │    Gmail     │       │
│  │   Whisper    │  │   Storage    │  │    SMTP      │       │
│  └──────────────┘  └──────────────┘  └──────────────┘       │
└──────────────────────────────────────────────────────────────┘
```

### 5.2 Patrón de Arquitectura

**Arquitectura en Capas (Layered Architecture)**

```
Presentation Layer (Controllers)
        ↓
Business Logic Layer (Services)
        ↓
Data Access Layer (Repositories)
        ↓
Database Layer (PostgreSQL)
```

**Ventajas:**
- Separación clara de responsabilidades
- Fácil mantenimiento y testing
- Escalabilidad horizontal
- Reusabilidad de componentes

### 5.3 Diagrama de Componentes

```
┌─────────────────────────────────────────────────────────┐
│                   MINDMEET SYSTEM                       │
├─────────────────────────────────────────────────────────┤
│                                                         │
│  ┌──────────────────┐         ┌──────────────────┐    │
│  │  Auth Module     │◄────────┤  Security Filter │    │
│  │  - JWT Provider  │         │  - JWT Auth      │    │
│  │  - User Service  │         └──────────────────┘    │
│  └──────────────────┘                                  │
│           │                                             │
│           ▼                                             │
│  ┌──────────────────┐         ┌──────────────────┐    │
│  │ Meeting Module   │◄────────┤  File Storage    │    │
│  │  - CRUD Ops      │         │  - S3 Integration│    │
│  │  - Participants  │         └──────────────────┘    │
│  └──────────────────┘                                  │
│           │                                             │
│           ▼                                             │
│  ┌──────────────────┐         ┌──────────────────┐    │
│  │ AI Processing    │◄────────┤  OpenAI Client   │    │
│  │  - Transcription │         │  - Whisper API   │    │
│  │  - Summarization │         │  - GPT API       │    │
│  └──────────────────┘         └──────────────────┘    │
│           │                                             │
│           ▼                                             │
│  ┌──────────────────┐         ┌──────────────────┐    │
│  │ Document Module  │◄────────┤  Template Engine │    │
│  │  - Generation    │         │  - PDF Export    │    │
│  │  - Versioning    │         └──────────────────┘    │
│  └──────────────────┘                                  │
│           │                                             │
│           ▼                                             │
│  ┌──────────────────┐         ┌──────────────────┐    │
│  │Notification Mod  │◄────────┤  Email Service   │    │
│  │  - Alerts        │         │  - SMTP Client   │    │
│  │  - Reminders     │         └──────────────────┘    │
│  └──────────────────┘                                  │
│                                                         │
└─────────────────────────────────────────────────────────┘
```

### 5.4 Modelo de Datos

#### 5.4.1 Diagrama Entidad-Relación

```
┌──────────────┐       ┌──────────────┐       ┌──────────────┐
│    User      │       │   Meeting    │       │  Document    │
├──────────────┤       ├──────────────┤       ├──────────────┤
│ id (PK)      │───────│ organizer_id │       │ id (PK)      │
│ full_name    │   1   │ (FK)         │   1   │ meeting_id   │
│ email        │       │ title        │───────│ (FK)         │
│ password     │       │ description  │       │ title        │
│ active       │       │ start_time   │       │ type         │
│ provider     │       │ end_time     │       │ content      │
│ created_at   │       │ status       │       │ created_at   │
└──────────────┘       │ recording_url│       └──────────────┘
      │                │ transcript   │
      │ N              │ summary      │
      │                └──────────────┘
      │                      │ N
      │                      │
      │                ┌──────────────┐
      └────────────────│ Participant  │
             N         ├──────────────┤
                       │ meeting_id   │
                       │ user_id      │
                       │ role         │
                       └──────────────┘
```

#### 5.4.2 Descripción de Entidades Principales

**User (Usuario)**
- Almacena información de usuarios del sistema
- Relación 1:N con Meetings (como organizador)
- Relación N:M con Meetings (como participante)

**Meeting (Reunión)**
- Entidad central del sistema
- Contiene información de la reunión y estados
- Relacionada con Users, Documents, Transcripts

**Document (Documento)**
- Almacena actas y documentos generados
- Pertenece a una Meeting específica
- Soporta versionamiento

**Role (Rol)**
- Define permisos de usuarios
- Relación N:M con User

### 5.5 Flujos de Proceso Principales

#### 5.5.1 Flujo de Autenticación

```
Usuario → Login Form → AuthController → AuthService
                                             ↓
                                    Validate Credentials
                                             ↓
                                    UserRepository
                                             ↓
                                    Generate JWT Token
                                             ↓
                                    Return Token → Client
```

#### 5.5.2 Flujo de Transcripción

```
Upload Recording → MeetingController → FileStorageService
                                              ↓
                                     Store in S3
                                              ↓
                                   TranscriptionService
                                              ↓
                                   OpenAI Whisper API
                                              ↓
                                   Process Response
                                              ↓
                                   Save Transcript
                                              ↓
                                   Generate Summary (GPT)
                                              ↓
                                   Notify User
```

### 5.6 Seguridad de la Arquitectura

#### Capas de Seguridad

1. **Capa de Red**
   - HTTPS obligatorio
   - Firewall configurado
   - Rate limiting

2. **Capa de Aplicación**
   - JWT tokens
   - CSRF protection
   - XSS prevention
   - SQL injection protection

3. **C