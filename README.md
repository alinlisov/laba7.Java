# Лабораторна робота №7 - Lambda workshop

Готовий навчальний проєкт за п'ятьма пунктами PDF `lambdaworkshop.pdf`: 1.1, 1.2 (обидва способи), 2.1, 2.2, 3.1.

## Командний рядок (без Maven/JUnit)

```bash
mkdir -p out
javac -encoding UTF-8 -d out src/workshop/*.java test/workshop/*.java
java -cp out workshop.Main
java -cp out workshop.WorkshopTests
```

## Обмеження

- Факторіал `long`: 0..20 включно (щоб уникнути переповнення); додатковий `BigInteger` працює для 21 і більше.
- Для `CompletableFuture` використано незалежні операції із затримкою 200 мс
- Завдання 2.2 у PDF містить тільки інструкцію проходити тести по одному та редагувати `src` і `test`; конкретні методи відсутні. Тут додано фільтрацію, підрахунок і групування.
