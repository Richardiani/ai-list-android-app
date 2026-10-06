# ToDo-RicH

A production-ready Android To-Do application built with Kotlin, Jetpack Compose, and Material 3.

## Features

### Core Functionality
- Create, edit, and delete tasks
- Mark tasks as completed
- Set task title, description, priority, category, due date, and due time
- Create and manage task categories
- Search tasks by title or description
- Filter tasks (All, Active, Completed, Overdue, Due Today)
- Filter by category and priority
- Sort tasks (Created date, Due date, Priority, Alphabetical)
- View tasks in different views (Active, Completed, Overdue, Due Today, By Category)
- Persistent local storage with Room database
- Works completely offline

### UI/UX
- Modern Material 3 design
- Light and dark theme support
- Clean, professional interface
- Intuitive navigation with Navigation Compose
- Empty states and error handling
- Accessibility features

## Technology Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose with Material 3
- **Database**: Room (SQLite)
- **Architecture**: MVVM with Repository pattern
- **State Management**: StateFlow and ViewModel
- **Navigation**: Navigation Compose
- **Build System**: Gradle 8.7
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 34 (Android 14)

## Project Structure

```
app/src/main/
├── java/com/todorich/app/
│   ├── data/
│   │   ├── database/        # Room database setup
│   │   ├── dao/             # Data access objects
│   │   ├── entity/          # Room entities
│   │   └── repository/      # Repository pattern implementation
│   ├── domain/
│   │   └── model/           # Domain models
│   ├── ui/
│   │   ├── screens/         # Composable screens
│   │   ├── components/      # Reusable composables
│   │   ├── viewmodel/       # ViewModels
│   │   └── theme/           # Theme configuration
│   ├── navigation/          # Navigation setup
│   └── MainActivity.kt      # Main activity
└── res/
    ├── values/
    ├── values-night/
    └── xml/
```

## Building the Application

### Prerequisites
- Android Studio (latest stable version)
- JDK 17 or higher
- Android SDK (API level 34)

### Build Debug APK
```bash
./gradlew assembleDebug
```

Debug APK location: `app/build/outputs/apk/debug/app-debug.apk`

### Build Release APK
```bash
./gradlew assembleRelease
```

Release APK location: `app/build/outputs/apk/release/app-release-unsigned.apk`

### Build & Install on Connected Device
```bash
./gradlew installDebug
```

### Run Tests
```bash
./gradlew test
```

## APK Generation

After a successful build, the APK files are located in:
- Debug: `app/build/outputs/apk/debug/`
- Release: `app/build/outputs/apk/release/`

For release builds, the APK can be signed with a keystore for production deployment.

## Getting Started

1. Clone the repository
2. Open in Android Studio
3. Allow Gradle to sync
4. Run on an emulator or connected device
5. Start creating tasks!

## Architecture

ToDo-RicH follows clean architecture principles:

**Presentation Layer** (UI)
- Jetpack Compose screens and components
- Material 3 components
- ViewModel for state management

**Domain Layer** (Business Logic)
- Use cases (though simple for this app)
- Domain models

**Data Layer**
- Repository pattern for data access
- Room database for persistence
- DAOs for database operations

All layers communicate through StateFlow and Coroutines for reactive, efficient data flow.

## Database Schema

### Task Table
- id (Primary Key)
- title (String, required)
- description (String)
- isCompleted (Boolean)
- priority (String: LOW, MEDIUM, HIGH)
- categoryId (Foreign Key)
- dueDate (String, ISO format)
- dueTime (String, HH:mm format)
- createdAt (Long)
- updatedAt (Long)

### Category Table
- id (Primary Key)
- name (String, required, unique)
- createdAt (Long)

## Key Features Implementation

### Search
- Real-time search across task titles and descriptions
- Case-insensitive matching

### Filters
- Active/Completed toggle
- Overdue detection
- Due Today detection
- Category filtering
- Priority filtering

### Sorting
- By creation date (newest/oldest)
- By due date (nearest/furthest)
- By priority (high to low)
- Alphabetically (A-Z)

### Category Management
- Create new categories
- Rename categories
- Delete categories (with safety checks)
- Reassign tasks when category is deleted

## Accessibility

- Content descriptions for all icons
- Appropriate touch target sizes
- Readable typography with sufficient contrast
- Semantic labels for composables
- Support for screen readers

## Theme Support

- Material 3 color scheme
- Automatic light/dark theme switching
- Proper color contrast in both themes
- Cohesive visual design

## License

This project is open source.

## Support

For issues or feature requests, please open an issue in the GitHub repository.
