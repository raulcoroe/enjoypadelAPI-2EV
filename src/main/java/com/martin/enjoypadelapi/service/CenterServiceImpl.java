package com.martin.enjoypadelapi.service;

import com.martin.enjoypadelapi.domain.Center;
import com.martin.enjoypadelapi.exception.CenterNotFoundException;
import com.martin.enjoypadelapi.repository.CenterRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class CenterServiceImpl implements CenterService {

    @Autowired
    private CenterRepository centerRepository;


    @Override
    public List<Center> findAll() {
        List<Center> centers = centerRepository.findAll();
        return centers;
    }

    @Override
    public Center findById(long id) throws CenterNotFoundException {
        Center center = centerRepository.findById(id)
                .orElseThrow(()-> new CenterNotFoundException());
        return center;
    }

    @Override
    public Center addCenter (Center center) {
        return centerRepository.save(center);
    }

    @Override
    public Center deleteCenter (long id) throws CenterNotFoundException {
        Center center = centerRepository.findById(id)
                .orElseThrow(CenterNotFoundException::new);
        centerRepository.delete(center);
        return center;
    }

    @Override
    public Center modifyCenter(long id, Center center) throws CenterNotFoundException {
        centerRepository.findById(id)
                .orElseThrow(CenterNotFoundException::new);
        center.setId(id);
        centerRepository.save(center);
        return center;
    }
}
