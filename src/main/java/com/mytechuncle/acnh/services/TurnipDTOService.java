package com.mytechuncle.acnh.services;

import com.mytechuncle.acnh.models.TurnipWeek;
import com.mytechuncle.acnh.models.ui.TurnipWeekLocalStorage;
import com.mytechuncle.acnh.repositories.TurnipWeekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TurnipDTOService {
    @Autowired
    TurnipWeekRepository repository;

    public static final int PRICES_BOUGHT_FOR_INDEX = 0;
    //public static final int PRICES_BOUGHT_FOR_INDEX = 1;
    public static final int PRICES_MON_AM = 2;
    public static final int PRICES_MON_PM = 3;
    public static final int PRICES_TUES_AM = 4;
    public static final int PRICES_TUES_PM = 5;
    public static final int PRICES_WED_AM = 6;
    public static final int PRICES_WED_PM = 7;
    public static final int PRICES_THURS_AM = 8;
    public static final int PRICES_THURS_PM = 9;
    public static final int PRICES_FRI_AM = 10;
    public static final int PRICES_FRI_PM = 11;
    public static final int PRICES_SAT_AM = 12;
    public static final int PRICES_SAT_PM = 13;

    public void updateTurnipWeekWithLocalStorage(TurnipWeek week, TurnipWeekLocalStorage localStorage) {
        if (localStorage.getPrices().length != 14) {
            //TODO handle
        }
        if (week.getId() == null || !repository.existsById(week.getId())) {
            //TODO Handle
        }

        week.setFirstBuy(localStorage.isFirstBuy());
        week.setPreviousPattern(localStorage.getPreviousPattern());

        int[] prices = localStorage.getPrices();
        week.setBoughtFor(prices[PRICES_BOUGHT_FOR_INDEX]);
        week.setMonAM(prices[PRICES_MON_AM]);
        week.setMonPM(prices[PRICES_MON_PM]);
        week.setTueAM(prices[PRICES_TUES_AM]);
        week.setTuePM(prices[PRICES_TUES_PM]);
        week.setWedAM(prices[PRICES_WED_AM]);
        week.setWedPM(prices[PRICES_WED_PM]);
        week.setThuAM(prices[PRICES_THURS_AM]);
        week.setThuPM(prices[PRICES_THURS_PM]);
        week.setFriAM(prices[PRICES_FRI_AM]);
        week.setFriPM(prices[PRICES_FRI_PM]);
        week.setSatAM(prices[PRICES_SAT_AM]);
        week.setSatPM(prices[PRICES_SAT_PM]);

        repository.save(week);
    }
}
