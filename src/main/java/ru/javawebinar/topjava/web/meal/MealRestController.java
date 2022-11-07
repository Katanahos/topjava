package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;
@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(MealRestController.class);
    private final MealService service;
    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public Meal get(int id){
        int userId = SecurityUtil.authUserId();
        log.info("get meals{}, from user {}",id,userId);
        return service.get(id,userId);
    }

    public void delete(int id){
        int userId = SecurityUtil.authUserId();
        log.info("delete meals{}, from user {}",id,userId);
        service.delete(id,userId);
    }

    public Meal create(Meal meal){
        int userId = SecurityUtil.authUserId();
        checkNew(meal);
        log.info("create {} for user {}",meal,userId);
        return service.create(meal,userId);
    }

    public Meal update(Meal meal, int id){
        int userId = SecurityUtil.authUserId();
        assureIdConsistent(meal,id);
        log.info("update {} for user {}",meal,userId);
        return service.create(meal,userId);
    }

    public List<MealTo> getAll(){
        int userId = SecurityUtil.authUserId();
        log.info("getAll for user {}",userId);
        return MealsUtil.getWithExcess(service.getAll(userId),SecurityUtil.authUserCaloriesPerDay());
    }

    public List<MealTo> getBetweenHalfOpen(@Nullable LocalDate startDate, @Nullable LocalTime startTime,
                                           @Nullable LocalDate endDate, @Nullable LocalTime endTime){
        int userId = SecurityUtil.authUserId();
        log.info("getBetween Dates ({} - {}), time ({} - {}) for user {}",startDate,endDate,startTime,endTime,userId);
        List<Meal> filteredMeals = service.getBetweenHalfOpen(startDate,endDate,userId);
        return MealsUtil.getWithExcess(filteredMeals,SecurityUtil.authUserCaloriesPerDay());
    }


}