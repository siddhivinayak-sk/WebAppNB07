/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controllers;

import com.app.dynamic.datasource.RoutingDataSourceContextHolder;
import com.app.dynamic.datasource.RoutingDataSourceType;
import com.app.models.MyappUser;
import com.app.models.VinInterfaceTab;
import com.app.services.MyappUserService;
import com.app.services.VinInterfaceService;

import flexjson.JSONDeserializer;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sandeepkumar
 */
@RestController
@RequestMapping("/appuser")
public class MyappUserController {
    
    @Autowired
    MyappUserService myappUserService;

    @Autowired
    private VinInterfaceService vis;
    
    
    @RequestMapping("/all")
    public ResponseEntity<List<MyappUser>> all() {
        RoutingDataSourceContextHolder.setDB(RoutingDataSourceType.DB01);
        List<MyappUser> al = null;
        try {
            al = myappUserService.getAllMyappUser();
        }
        catch(Exception e) {
        }
        return new ResponseEntity<>(al, HttpStatus.OK);
    }

    @RequestMapping("/{loginId}")
    public ResponseEntity<MyappUser> getMyappuserByLoginId(@PathVariable("loginId")String loginId) {
        MyappUser al = null;
        try {
            al = myappUserService.getMyappUserByLoginId(loginId);
        }
        catch(Exception e) {
        }
        return new ResponseEntity<>(al, HttpStatus.OK);
    }


    @RequestMapping("/evict")
    public ResponseEntity evict() {
        myappUserService.evict();
        return new ResponseEntity(HttpStatus.OK);
    }

    
    @RequestMapping("/getallvins")
    public List<VinInterfaceTab> getallvins() {
    	List<VinInterfaceTab> list = vis.getAllVinInterfaceTab();
    	return list;
    }
    
    @RequestMapping("/savevin")
    public VinInterfaceTab savevin(@RequestParam(value = "id", required = false) String id) {
    	
    	VinInterfaceTab temp = new VinInterfaceTab();
    	//temp.setId(id);
    	Emp e = new Emp();
    	e.setRoll(1);
    	e.setName("Abc");
    	temp.setClassName("Emp");
    	temp.setObject(e);
    	vis.save(temp);
    	return temp;
    }

	public static class Emp implements java.io.Serializable {
		final private static long serialVersionUID = 1L;
		private int roll;
		private String name;
		public int getRoll() {
			return roll;
		}
		public void setRoll(int roll) {
			this.roll = roll;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	};
    
}
