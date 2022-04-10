package com.martin.enjoypadelapi.service;

import com.martin.enjoypadelapi.domain.Center;
import com.martin.enjoypadelapi.domain.Match;
import com.martin.enjoypadelapi.exception.CenterNotFoundException;
import com.martin.enjoypadelapi.repository.CenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void addCenter (Center center) {
        centerRepository.save(center);
    }

    @Override
    public Center deleteCenter (long id) throws CenterNotFoundException {
        Center center = centerRepository.findById(id)
                .orElseThrow(CenterNotFoundException::new);
        for (Match match : center.getMatches()) {
            match.setCenter(null);
        }
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
