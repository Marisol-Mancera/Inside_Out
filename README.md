# Proyecto Gestor de Momentos

Este proyecto es una aplicación de consola en Java diseñada para registrar, listar, filtrar y eliminar momentos importantes de la vida de un usuario.  
Cada momento se enriquece con datos como título, descripción, emoción, fecha y categoría (positivo/negativo).

## 🚀 Funcionalidades actuales
- Añadir momentos (con DTO `AddMomentDTO`).
- Listar todos los momentos almacenados.
- Filtrar momentos por:
  - Emoción
  - Fecha
  - Categoría (positivo/negativo)
- Eliminar momentos por identificador único.
- Menú interactivo en consola.

## 🛠️ Arquitectura del proyecto
El proyecto sigue el patrón MVC:
- **Model**: Contiene la entidad `Moment` y la enumeración `Emotion`.
- **View**: Clases para la interacción por consola (`MainMenuView`, `AddMomentView`, etc.).
- **Controller**: Maneja la lógica de flujo (`MainController`, `MomentController`).
- **Service**: Procesa la lógica de negocio (`MomentService`).
- **Repository**: Gestiona los datos en memoria (`MomentsRepository`).

## 📚 Tecnologías usadas
- **Java 17**
- **JUnit 5** para pruebas unitarias.

## 🔨 Instalacion
1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/usuario/nombre-del-proyecto.git
   cd nombre-del-proyecto
   ```

2. **Requisitos previos**
   - Tener instalado **Java 17** o superior.
   - Tener instalado **Maven** (para compilación y gestión de dependencias).
   - Un IDE recomendado: **IntelliJ IDEA** o **Eclipse**.

3. **Compilar el proyecto**
   ```bash
   mvn clean install
   ```

4. **Ejecutar las pruebas**
   ```bash
   mvn test
   ```


---


## ⚠️ Estado del proyecto
Este proyecto está **en construcción y mejora continua**.  
Actualmente cumple con la funcionalidad básica, pero se están refinando pruebas, mensajes de usuario y cobertura de test.

## 📰 Diagrama


```mermaid
classDiagram
    class Moment {
        -int id
        -String title
        -String description
        -Emotion emotion
        -LocalDate momentDate
        -LocalDate creationDate
        -LocalDate modificationDate
        -boolean isPositive
        +Moment(int id, String title, String description, Emotion emotion, LocalDate momentDate, boolean isPositive)
        +getId() int
        +getTitle() String
        +getDescription() String
        +getEmotion() Emotion
        +getMomentDate() LocalDate
        +getCreationDate() LocalDate
        +getModificationDate() LocalDate
        +setModificationDate(LocalDate date) void
        +isPositive() boolean
    }

    class Emotion {
        <<enumeration>>
        +HAPPY
        +SAD
        +ANGRY
        +NOSTALGIA
        +etc...
    }

    class AddMomentDTO {
        -String title
        -String description
        -Emotion emotion
        -LocalDate momentDate
        -boolean isPositive
        +AddMomentDTO(String, String, Emotion, LocalDate, boolean)
        +getTitle() String
        +getDescription() String
        +getEmotion() Emotion
        +getMomentDate() LocalDate
        +isPositive() boolean
    }

    class MomentsRepository {
        +save(Moment) void
        +findAll() List~Moment~
        +deleteById(int) boolean
        +filterByEmotion(Emotion) List~Moment~
        +filterByDate(LocalDate) List~Moment~
        +filterByCategory(boolean) List~Moment~
    }

    class MomentService {
        -MomentsRepository repository
        +MomentService(MomentsRepository)
        +addMoment(AddMomentDTO) Moment
        +listMoments() List~Moment~
        +deleteMoment(int) boolean
        +filterByEmotion(Emotion) List~Moment~
        +filterByDate(LocalDate) List~Moment~
        +filterByCategory(boolean) List~Moment~
    }

    class MomentController {
        -AddMomentView addMomentView
        -MomentService momentService
        -DeleteMomentView deleteMomentView
        -FilterMomentListView filterView
        -FilterByEmotionView filterEmotion
        -FilterByDateOfView filterDate
        -FilterByCategoryView filterCategory
        +addMoment() String
        +listMoments() List~Moment~
        +deleteMoment() String
        +filterMoments() List~Moment~
    }

    class MainController {
        -MomentsRepository repository
        -MomentService momentService
        -AddMomentView addMomentView
        -MomentController momentController
        -MessageView messageView
        -MainMenuView mainMenuView
        -DeleteMomentView deleteMomentView
        -FilterMomentListView filterView
        -FilterByEmotionView filterEmotion
        -FilterByDateOfView filterDate
        -FilterByCategoryView filterCategory
        +start() void
    }

    %% Views
    class AddMomentView
    class DeleteMomentView
    class FilterMomentListView
    class FilterByEmotionView
    class FilterByDateOfView
    class FilterByCategoryView
    class ListMomentsView
    class MainMenuView
    class MessageView

    %% Relationships
    MomentService --> MomentsRepository
    MomentController --> MomentService
    MomentController --> AddMomentView
    MomentController --> DeleteMomentView
    MomentController --> FilterMomentListView
    MomentController --> FilterByEmotionView
    MomentController --> FilterByDateOfView
    MomentController --> FilterByCategoryView
    MainController --> MomentController
    MainController --> MomentService
    MainController --> MomentsRepository
    MainController --> MainMenuView
    MainController --> MessageView
    MainController --> AddMomentView
    MainController --> DeleteMomentView
    MainController --> FilterMomentListView
    MainController --> FilterByEmotionView
    MainController --> FilterByDateOfView
    MainController --> FilterByCategoryView
```

## 🚦Covertura
<img width="330" height="666" alt="test-cover" src="https://github.com/user-attachments/assets/81be2d4d-bd8b-4082-95f0-5b1e89568609" />

   


### Próximos pasos / Backlog
- Mejorar cobertura de pruebas unitarias.
- Estandarizar los mensajes en la vista para mayor consistencia.
- Persistencia con base de datos o ficheros.
- Ampliar opciones de filtrado y estadísticas de momentos.

## 👩‍🎓 Nota personal de aprendizaje
Este proyecto ha servido como ejercicio de aprendizaje en:
- Uso del patrón MVC en aplicaciones de consola.
- Manejo de DTOs para desacoplar datos.
- Implementación de servicios y repositorios en memoria.
- Escritura de pruebas unitarias con JUnit.
- Refactorización y mejora iterativa de código.

---
📌 **Estado final de entrega:**  
El proyecto funciona con las características esenciales, pero queda **abierto a mejoras** en la arquitectura, consistencia de mensajes y pruebas.  


