package com.stanislav.danylenko.course.web.controller.location;

import com.stanislav.danylenko.course.db.entity.location.PopulatedPoint;
import com.stanislav.danylenko.course.db.service.location.PopulatedPointService;
import com.stanislav.danylenko.course.exception.DBException;
import com.stanislav.danylenko.course.web.model.PopulatedPointModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/points")
public class PopulatedPointController {
    
    @Autowired
    private PopulatedPointService service;

    @GetMapping
    public @ResponseBody
    ResponseEntity<Iterable<PopulatedPoint>> getCountries() throws DBException {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public @ResponseBody
    ResponseEntity<PopulatedPointModel> getPopulatedPoint(@PathVariable Long id) throws DBException {
        PopulatedPoint populatedPoint = service.find(id);
        PopulatedPointModel model = service.getViewModel(populatedPoint);
        return new ResponseEntity<>(model, HttpStatus.FOUND);
    }

    @PostMapping
    public @ResponseBody
    ResponseEntity<PopulatedPointModel> createPopulatedPoint(@RequestBody PopulatedPointModel populatedPointModel) throws DBException {
        PopulatedPoint populatedPoint = new PopulatedPoint();
        service.updatePopulatedPoint(populatedPoint, populatedPointModel);
        service.save(populatedPoint);
        populatedPointModel = service.getViewModel(populatedPoint);
        return new ResponseEntity<>(populatedPointModel, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public @ResponseBody
    ResponseEntity<PopulatedPointModel> updatePopulatedPoint(@RequestBody PopulatedPointModel newPopulatedPoint, @PathVariable Long id) throws DBException {
        PopulatedPoint populatedPoint = service.find(id);
        service.updatePopulatedPoint(populatedPoint, newPopulatedPoint);
        service.update(populatedPoint);
        newPopulatedPoint = service.getViewModel(populatedPoint);
        return ResponseEntity.ok(newPopulatedPoint);
    }

    @DeleteMapping("/{id}")
    public void deletePopulatedPoint(@PathVariable Long id, HttpServletResponse response) throws DBException {
        service.delete(id);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @GetMapping("/region/{id}")
    public @ResponseBody
    ResponseEntity<Iterable<PopulatedPoint>> getPopulatedPointsInCountry(@PathVariable Long id) throws DBException {
        return ResponseEntity.ok(service.findByRegionId(id));
    }
    
}