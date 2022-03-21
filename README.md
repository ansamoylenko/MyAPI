# MyAPI

РеализованAPI, со следующими методами:

### Добавление пользователя

API принимает в себя данные логин, пароль, имя, фамилию, записывает это в базу данных

### Проверка наличия пользователя

API принимает в себя имя пользователя и отдает ответ, содержащий информацию есть пользователь, или нет в базе

### Удаление пользователя

API принимает в себя имя пользователя (или его id, если соискатель добавил эту информацию) и удаляет из базы данных данные

### Поиск по пользователям

API принимает в себя часть имени пользователя и выводит всех пользователей, которые могут подходить

## Информация по реализации

-Рабочая версия API размещена на хостинге

-Документацию по работе с API можно посмотреть в Swagger

http://80.249.145.210:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/user-controller/showAll
