///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.app.controllers;
//
//import com.app.models.MyappUser;
//import com.app.repositories.MyappUserRepository;
//import com.app.solr.repositories.MyappUserSolrRepository;
//import java.util.List;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
///**
// *
// * @author sandeepkumar
// */
//@Controller
//@RequestMapping("/solr")
//public class SolrTasksController {
//    
//    @Autowired
//    MyappUserRepository myappUserRepository;
//    
//    @Autowired
//    MyappUserSolrRepository myappUserSolrRepository;
//    
//    @RequestMapping("")
//    public String solrHome() {
//        return "solr";
//    }
//    
//    @RequestMapping("t1")
//    public ResponseEntity t1(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
//        List<MyappUser> mauList = myappUserRepository.findAll();
//        myappUserSolrRepository.save(mauList);
//        return new ResponseEntity(HttpStatus.OK);
//    }
//
//    @RequestMapping("t2")
//    public ResponseEntity t2(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
//        Iterable<MyappUser> mauList = myappUserSolrRepository.findAll();
//        return new ResponseEntity(HttpStatus.OK);
//    }
//
//    @RequestMapping("t3")
//    public ResponseEntity t3(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
//        return new ResponseEntity(HttpStatus.OK);
//    }
//
//    @RequestMapping("t4")
//    public ResponseEntity t4(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
//        return new ResponseEntity(HttpStatus.OK);
//    }
//
//    @RequestMapping("t5")
//    public ResponseEntity t5(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
//        return new ResponseEntity(HttpStatus.OK);
//    }
//    
//    
//    
//}
