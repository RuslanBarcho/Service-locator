# Service-locator
Тестовое задание для прохождения отбора на позицию Android стажера в Avito 

При разработке использовались:
* Kotlin
* Архитектурный паттерн MVVM
* Koin для внедрения зависимостей
* Google Maps API
* Google Maps Utility для работы с кластеризацией
* GSON для десериализации

В приложении два основных экрана: карта с метками-сервисами и фильтр для выбора отображаемых меток

# MapActivty - экран с картой
Экран с картой поддерживает кластеризацию меток для лучшей производительности и удобства использования

<span>
<img src="https://github.com/RuslanBarcho/Service-locator/blob/master/screenshots/map.png" width="300"/>
</span> 

# FilterActivity - фильтрация
Фильтрация осуществляется по всем доступным сервисам и применяется по нажатию соответствующей кнопки

<span>
<img src="https://github.com/RuslanBarcho/Service-locator/blob/master/screenshots/filter.png" width="300"/>
<img src="https://github.com/RuslanBarcho/Service-locator/blob/master/screenshots/filtered-map.png" width="300"/>
</span> 

# ServiceInfoDialog - обработка нажатий
Также каждый маркер и кластер кликабелен - при нажатии на кластер карта увеличит зум, а при нажатии на маркер появится небольшое окно, информирующее пользователя, на какой маркер он нажал

<span>
<img src="https://github.com/RuslanBarcho/Service-locator/blob/master/screenshots/dialog.png" width="300"/>
</span> 
