## Тестовое	задание	"Получить	курс	иностранной валюты	к	рублю"
### Запуск:
Запустить класс cherkasova.test.main.Application. Pom.xml уже содержит spring-boot-starter-web, который запустит приложение. 
### Запросы 
GET	http://localhost:8080/api/rate/USD 
GET	http://localhost:8080/api/rate/USD/2020-12-31
### Пример ответа
{
"code": "AZN",
"rate": "43,8302",
"date": "2021-10-07"
}