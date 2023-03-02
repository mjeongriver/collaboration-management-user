package com.choongang.ScheduleProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/charts")
public class ChartController {

	@GetMapping("/apexcharts")
	public String apexChart() {
		return "/charts/charts-apexcharts";
	}
	@GetMapping("/chartjs")
	public String chartjs() {
		return "/charts/charts-chartjs";
	}
	@GetMapping("/echarts")
	public String eChart() {
		return "/charts/charts-echarts";
	}
	
}
