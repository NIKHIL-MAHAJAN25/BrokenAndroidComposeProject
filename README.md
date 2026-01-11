# Candidate Instructions & Known Bugs (do not change this file name)

This project is intentionally flawed. Your tasks:

1. Fix crashes and freezes -- 
2. Implement navigation to the detail screen
3. Make network calls off the main thread --
4. Replace GlobalScope usage with proper ViewModelScope or lifecycle-aware scope --
5. Implement local persistence (Room) and use it as cache
6. Correct JSON parsing / DTO mapping -- 
7. Add loading/error/empty states properly -- 
8. Remove memory leak (do not use static Activity references) --
9. Implement updateArticle to persist edits
10. Improve project structure where obvious (move business logic out of composables) --


Deliverables:
- Working app (no crashes) that loads articles (from the fake API or a real one)
- README describing changes and tradeoffs
- Optional: unit tests and small demo video


Notes:
- Some code intentionally causes the app to freeze or crash. Find and fix them.
- We expect you to explain each bug and your fix in the README.