# Introduction
This was a broken project given by GreedyGame as an assignment. The major task was to identify the bugs and improve the code structure

## Major Bugs Identified

1.  **AGP version mismatch and Compile SDK syntax**
    * **Issue:** There was a library version mismatch while importing the project from github and a gradle sync failure caused due to incorrect syntax.
    ```kotlin
    compileSdk {
        version = release(36)
    }
    ```
    * **Fix:** Fixed the syntax and matched the agp version in library.
     ```kotlin
     compileSdk = 36
    ```


2.  **Memory Leak and inocrrect UI screen call in MainActivity**
    * **Issue:** A static reference `leakedActivity` was holding onto the `Activity` instance preventing garbage collection and also `NewsScreen` function was called without parameters.
    ```kotlin
     companion object {
        var leakedActivity: MainActivity? = null
        }
    ```

    ```kotlin
    {
        NewsScreen()                
    }
    ```
    
    * **Fix:** Removed the static companion object reference entirely.

3.  **Incorrect Scope Usage (GlobalScope)**
    * **Issue:** `GlobalScope` was used for fetching data.
    * **Impact:** Network requests continued executing even after the screen was destroyed, leading to potential crashes and resource waste.
    * **Fix:** Replaced with `viewModelScope` in the `ApiViewModel`, which automatically cancels tasks when the ViewModel is cleared.

4.  **JSON Parsing Failures**
    * **Issue:** The mock JSON in `BrokenRepository` used keys (`identifier`, `heading`) that did not match the `Article` data class fields (`id`, `title`).
    * **Fix:** Implemented proper Retrofit + Gson parsing with a `NewsResponse` DTO to match the actual NewsAPI structure.

5.  **Missing Database Implementation**
    * **Issue:** `AppDatabase` was an empty class, and `Article` entities were missing Room annotations.
    * **Fix:** Added `@Entity`, `@PrimaryKey`, and implemented the `ArticleDao`. Configured Room correctly in `build.gradle`.

## 🛠️ Fixes & Improvements Implemented

* **MVVM Architecture:** Moved all data fetching logic out of the UI (`NewsScreen`) and into `ApiViewModel`. The UI now observes a `StateFlow` for updates.
* **Offline-First Support:** Implemented a "Single Source of Truth" pattern. The app now saves downloaded news to the local Room database and displays data from there.
* [cite_start]**Navigation:** Implemented navigation between the News List and a Detail Screen[cite: 56, 82].
* **Error Handling:** Added try-catch blocks in the Repository and exposed proper `Loading`, `Success`, and `Error` states to the UI.
* **Code Cleanliness:** Removed hardcoded API keys and dead code.

## ⏱️ Time Taken
Approx. 4-5 hours

## 📦 Dependencies Added
* `androidx.lifecycle:lifecycle-viewmodel-compose` (For ViewModel integration)
* `androidx.room:room-compiler` (For Database generation)
* `androidx.room:room-ktx` (For Coroutines support in Room)