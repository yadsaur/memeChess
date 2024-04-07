package com.example.demo.serviceImpl;

import org.springframework.stereotype.Service;

import com.example.demo.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService{

	@Override
	public String genPdf() {
		// TODO Auto-generated method stub
		String greet = "Hello";
		return greet;
	}

}
