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
    ``` kotlin
     companion object {
        var leakedActivity: MainActivity? = null
        }
    ```

    ``` kotlin
    {
        NewsScreen()                
    }
    ```
    
    * **Fix:** Removed the static companion object reference entirely and passed viewmodel inside parameters of `NewsScreen`, also there was a small bug where padding values were missing for the scafold.
    ``` kotlin
    {
        Scaffold(topBar =
                { TopAppBar(title =
                { Text("Broken News")})})
                { paddingValues ->
                    Box(modifier = Modifier.padding(paddingValues)) {
                        NewsScreen(viewModel = viewModel())
                    }
                }                
    }
    ```

3.  **Incorrect Scope Usage (GlobalScope) and network call on main thread**
    * **Issue:** `GlobalScope` was used for fetching data and network call was made on main thread blocking main ui screen.
    ``` kotlin
    LaunchedEffect(Unit) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val result = BrokenRepository.fetchArticlesBlocking()
                articles = result
                loading = false
            } catch (e: Exception) {
                error = e.message
                loading = false
            }
        }
    }
    ```
    * **Fix:** Replaced with `viewModelScope` in the `ApiViewModel`, which automatically cancels tasks when the ViewModel is cleared.
    ``` kotlin
    class NewsRepository (private val database: AppDatabase)
    {

    val articles = database.DAO().getarticles()
    suspend fun refreshNews() {
        withContext(Dispatchers.IO) {
            val response = ApiClient.api.getArticles(BuildConfig.News)
            if (response.articles.isNotEmpty()) {
                database.DAO().insertarticles(response.articles)
            }
        }
    }
    fun getArticleById(id: Int) = database.DAO().getarticlesbyid(id)
    }
    ```
    ``` kotlin
     private fun observeDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.articles.collect { list ->
                if (list.isNotEmpty()) {
                    _uistate.value = NewsStates.Sucess(list)
                } else {
                    _uistate.value = NewsStates.Loading
                }
            }
        }
    }
    ```

4.  **JSON Parsing Failures**
    * **Issue:** The mock JSON in `BrokenRepository` used keys (`identifier`, `heading`) that did not match the `Article` data class fields (`id`, `title`), it caused a serialization error in logcat.
    ``` kotlin
    object BrokenRepository {
    fun fetchArticlesBlocking(): List<Article> {
        Thread.sleep(2000)
        val fakeJson = "[{\"identifier\":1,\"heading\":\"Hello\",\"writer\":\"Alice\"}]"
        val gson = Gson()
        val articles: Array<Article> = try {
            gson.fromJson(fakeJson, Array<Article>::class.java)
        } catch (e: Exception) {
            emptyArray()
        }
        return articles.toList()
        }             
    }
    ```
    * **Fix:** Implemented proper Retrofit + Gson parsing with a `NewsResponse` DTO to match the actual NewsAPI structure.
    ``` kotlin
    data class Article(
    @PrimaryKey(autoGenerate = true)
    val dbid:Int=0,
    val author:String?,
    val title:String?,
    val content:String?,
    val urlToImage:String?
    )
    data class ArticleResponse(
    val articles:List<Article>,
    val status:String,
    val code:String?,
    val message:String?
    )
    ```

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