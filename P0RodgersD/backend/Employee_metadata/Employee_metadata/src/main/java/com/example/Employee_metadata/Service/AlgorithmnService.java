package com.example.Employee_metadata.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Employee_metadata.Repository.AlgorithmnRepository;

@Service
public class AlgorithmnService {

    @Autowired
    private AlgorithmnRepository algorithmnRepository;

}
