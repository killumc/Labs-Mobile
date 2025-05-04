

package com.example.lb4_town;

import com.example.lb4_town.Place;
import com.example.lb4_town.R;

import java.util.ArrayList;
import java.util.List;

public class PlaceRepository {
    public List<Place> getPlaces() {
        List<Place> places = new ArrayList<>();
        places.add(new Place("Невский проспект",
                "Самая оживленная и интересная часть — между площадью Восстания у" +
                " Московского вокзала и Дворцовой площадью, на которой стоит императорский Зимний дворец. ",
                R.drawable.image_dost1));
        places.add(new Place("Эрмитаж",
                "Чтобы ознакомиться с основными экспонатами и залами, заранее купите билеты на официальном сайте. " +
                        "Там можно выбрать дату и время визита, а также маршрут.",
                R.drawable.image_dost2));
        places.add(new Place("Летний сад",
                "Дворец построили в 1712 году, а на облагороженной территории вокруг него разместили фонтаны " +
                        "и античные статуи, часть которых царь лично привёз из Италии. ",
                R.drawable.image_dost3));
        return places;
    }
}
