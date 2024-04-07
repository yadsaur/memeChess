package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.ReportService;

@RestController
@RequestMapping("/api")
public class ReportController {

	@Autowired
	ReportService reportService;

	@GetMapping("/greeting")
	public String greeting() {
		return reportService.genPdf();
	}
	
}
