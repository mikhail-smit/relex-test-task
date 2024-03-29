# Тестовое задание Java

Необходимо реализовать RESTfull API Service позволяющий работникам на ферме сохранять данные о собранных товарах в
электронном виде. Заказчик должен иметь возможность получать выборки о собранных товарах по времени и по конкретному
работнику.

### Зависимости

- Spring Boot 3
- Spring Secutiry;
- JWT;
- Spring Data;
- Hibernate;
- PostgreSQL;
- Docker;
- JSON;
- Lombok - чтобы избавиться от шаблонного кода
- ModelMapper - ради более удобной конвертации сущностей в DTO и обратно
- Validation - для проверки корректности данных

### Сущности
- User - сотрудник фермы
- Product - товар для учёта
- HarvestResult - запись, объединяющая сотрудника, количество и тип собранного (сделанного, полученного и т.п.) им товара в некоторый момент времени


### Примеры запросов

- Аутентификация

```
POST \v1\auth 
Body:
    {"email" : "...", "password": "..."}
Response:
    {"jwt" : "..."}
```

- Создание работником записи о собранном урожае

```
POST \v1\harvests 
Body: 
    {
        "userId": 2,                                // кто собрал
        "productId": 2,                             // какой продукт
        "count": 100,                               // в каком количестве
        "atMoment": "2022-09-20T07:40:12.000+00:00" // когда
    }
```

- Получение списка 'записей о сборе урожая' по сотруднику

```
GET \v1\users\{id}\harvests
```

- Получение списка 'записей о сборе урожая' по сотруднику во временном отрезке

```
GET /v1/users/1/harvests/between 
Body:
    {
        "from": "2021-09-19T07:42:33",
        "to": "2022-09-20T11:22:33"   
    }
```

- Получение списка 'записей о сборе урожая' во временном отрезке, независимо от собравшего урожай сотрудника

```
GET /v1/harvests/between
Body:
    {
        "from": "2018-05-10T11:22:33",
        "to": "2023-05-10T11:22:33"
    }
```

Также реализованы контроллеры для Товаров (ProductController), Сотрудников (UserController) и 'Записей о сборе урожая' (HarvestResultController), чтобы не документировать все запросы вручную добавил Springdoc. Получить общее представление можно ниже:
![first page](https://github.com/mikhail-smit/relex-test-task/blob/main/Swagger%20UI/Swagger%20UI-1.png?raw=true "Title")
![second page](https://github.com/mikhail-smit/relex-test-task/blob/main/Swagger%20UI/Swagger%20UI-2.png?raw=true "Title")
![third page](https://github.com/mikhail-smit/relex-test-task/blob/main/Swagger%20UI/Swagger%20UI-3.png?raw=true "Title")

## Автор
Медведев Михаил 

mikhail.medvedew@mail.ru