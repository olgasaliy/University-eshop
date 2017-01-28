package com.jee.controller;

import org.apache.log4j.Logger;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 * Created by olgasaliy on 14.11.16.
 */
@ManagedBean(name = "personManage", eager = true)
@ApplicationScoped
public class PersonController {

    Logger logger = Logger.getLogger(getClass().getName());


}
