package com.martin.enjoypadelapi.controller;

import com.martin.enjoypadelapi.domain.Center;
import com.martin.enjoypadelapi.exception.CenterNotFoundException;
import com.martin.enjoypadelapi.exception.ErrorResponse;
import com.martin.enjoypadelapi.service.CenterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CenterController {

    private final Logger logger = LoggerFactory.getLogger(PlayerController.class);

    @Autowired
    private CenterService centerService;

    @GetMapping("/centers")
    public List<Center> findAll() {
        logger.info("Inicio findAll(center)");
        List<Center> centers = centerService.findAll();
        logger.info("Final findAll(center)");
        return centers;
    }

    @GetMapping("/center/{id}")
    public Center findById(@PathVariable long id) throws CenterNotFoundException {
        logger.info("Inicio findById(center)");
        Center center = centerService.findById(id);
        logger.info("Final findById(center)");
        return center;
    }

    @PostMapping("/centers")
    public void addCenter (@RequestBody Center center) {
        logger.info("Inicio addCenter");
        centerService.addCenter(center);
        logger.info("Final addCenter");
    }

    @PutMapping("/center/{id}")
    public Center modifyCenter (@PathVariable long id, @RequestBody Center center) throws CenterNotFoundException {
        logger.info("Inicio modifyCenter");
        Center newCenter = centerService.modifyCenter(id, center);
        logger.info("Final modifyCenter");
        return newCenter;
    }

    @DeleteMapping("/center/{id}")
    public Center deleteCenter (@PathVariable long id) throws CenterNotFoundException {
        logger.info("Inicio deleteCenter");
        Center center = centerService.deleteCenter(id);
        logger.info("Final deleteCenter");
        return center;
    }


    @ExceptionHandler(CenterNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCenterNotFoundException(CenterNotFoundException cenfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", cenfe.getMessage());
        logger.error(cenfe.getMessage(), cenfe);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}