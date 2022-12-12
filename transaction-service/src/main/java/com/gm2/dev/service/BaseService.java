package com.gm2.dev.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class BaseService {

    protected final ModelMapper mapper;

    public BaseService() {this.mapper = new ModelMapper();}
}
