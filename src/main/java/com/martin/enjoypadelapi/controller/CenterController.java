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
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CenterController {

    private final Logger logger = LoggerFactory.getLogger(PlayerController.class);

    @Autowired
    private CenterService centerService;

    @GetMapping("/centers")
    public ResponseEntity<List<Center>> findAll() {
        logger.info("Inicio findAll(center)");
        List<Center> centers = centerService.findAll();
        logger.info("Final findAll(center)");
        return new ResponseEntity<>(centers, HttpStatus.OK);
    }

    @GetMapping("/center/{id}")
    public ResponseEntity<Center> findById(@PathVariable long id) throws CenterNotFoundException {
        logger.info("Inicio findById(center)");
        Center center = centerService.findById(id);
        logger.info("Final findById(center)");
        return new ResponseEntity<>(center, HttpStatus.OK);
    }

    @PostMapping("/centers")
    public ResponseEntity<Center> addCenter (@Valid @RequestBody Center center) {
        logger.info("Inicio addCenter");
        Center newCenter = centerService.addCenter(center);
        logger.info("Final addCenter");
        return new ResponseEntity<>(newCenter, HttpStatus.CREATED);
    }

    @PutMapping("/center/{id}")
    public ResponseEntity<Center> modifyCenter (@PathVariable long id, @Valid @RequestBody Center center) throws CenterNotFoundException {
        logger.info("Inicio modifyCenter");
        Center newCenter = centerService.modifyCenter(id, center);
        logger.info("Final modifyCenter");
        return new ResponseEntity<>(newCenter, HttpStatus.OK);
    }

    @DeleteMapping("/center/{id}")
    public ResponseEntity<Void> deleteCenter (@PathVariable long id) throws CenterNotFoundException {
        logger.info("Inicio deleteCenter");
        centerService.deleteCenter(id);
        logger.info("Final deleteCenter");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/center/{id}")
    public ResponseEntity<Center>  partialCenterModification(@PathVariable long id, @Valid @RequestBody Map<Object, Object> fields) throws CenterNotFoundException {
        logger.info("Inicio partialCenterModification");
        Center center = centerService.partialCenterModification(id, fields);
        logger.info("Final partialCenterModification");
        return new ResponseEntity<>(center, HttpStatus.OK);
    }


    @ExceptionHandler(CenterNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCenterNotFoundException(CenterNotFoundException cenfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", cenfe.getMessage());
        logger.error(cenfe.getMessage(), cenfe);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse("3", "Internal server error");
        logger.error(exception.getMessage(), exception);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}