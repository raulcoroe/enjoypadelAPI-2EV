package com.martin.enjoypadelapi.service;

import com.martin.enjoypadelapi.domain.Center;
import com.martin.enjoypadelapi.exception.CenterNotFoundException;

import java.util.List;
import java.util.Map;

public interface CenterService {
    List<Center> findAll();
    Center findById(long id) throws CenterNotFoundException;
    void addCenter(Center center);
    Center deleteCenter(long id) throws CenterNotFoundException;
    Center modifyCenter(long id, Center newCenter) throws CenterNotFoundException;
    Center partialCenterModification(long id, Map<Object, Object> fields) throws CenterNotFoundException;

}
