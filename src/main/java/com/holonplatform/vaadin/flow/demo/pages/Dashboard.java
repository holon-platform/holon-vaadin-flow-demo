/*
 * Copyright 2016-2018 Axioma srl.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.holonplatform.vaadin.flow.demo.pages;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.holonplatform.core.query.QueryFilter;
import com.holonplatform.vaadin.flow.components.Components;
import com.holonplatform.vaadin.flow.demo.components.StorefrontListing;
import com.holonplatform.vaadin.flow.demo.models.Order;
import com.holonplatform.vaadin.flow.demo.root.Menu;
import com.holonplatform.vaadin.flow.demo.services.OrderDataService;
import com.vaadin.flow.component.board.Board;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.Configuration;
import com.vaadin.flow.component.charts.model.Cursor;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.charts.model.Labels;
import com.vaadin.flow.component.charts.model.ListSeries;
import com.vaadin.flow.component.charts.model.PlotOptionsArea;
import com.vaadin.flow.component.charts.model.PlotOptionsColumn;
import com.vaadin.flow.component.charts.model.PlotOptionsPie;
import com.vaadin.flow.component.charts.model.Stacking;
import com.vaadin.flow.component.charts.model.TickmarkPlacement;
import com.vaadin.flow.component.charts.model.Tooltip;
import com.vaadin.flow.component.charts.model.XAxis;
import com.vaadin.flow.component.charts.model.YAxis;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "dashboard", layout = Menu.class)
public class Dashboard extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	@Autowired
	private OrderDataService orderDataService;

	private final Chart monthlyProductSplit = new Chart(ChartType.PIE);
	private static final String[] MONTHS = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct",
			"Nov", "Dec" };

	@PostConstruct
	public void init() {

		Board board = new Board();
		board.setSizeFull();
		board.getStyle().set("overflow", "auto");

		String todayDeliveriesOrders = orderDataService.getTodayDeliveries().toString() + "/"
				+ orderDataService.getTodayOrders().toString();
		String todayProblems = orderDataService.getTodayProblems().toString();
		String newOrders = orderDataService.getNewOrders().toString();
		String tomorrowOrders = orderDataService.getTomorrowOrders().toString();

		// Orders count labels
		VerticalLayout remainingVl = Components.vl().fullWidth().alignItems(Alignment.CENTER).build();
		remainingVl.add(Components.span().styleNames("analitycs-label", "analitycs-remains").text(todayDeliveriesOrders)
				.build());
		remainingVl.add(Components.h4().text("Remaining Today").build());

		VerticalLayout notAvailableVl = Components.vl().fullWidth().alignItems(Alignment.CENTER).build();
		notAvailableVl.add(
				Components.span().styleNames("analitycs-label", "analitycs-notavailable").text(todayProblems).build());
		notAvailableVl.add(Components.h4().text("Not Available").build());

		VerticalLayout newVl = Components.vl().fullWidth().alignItems(Alignment.CENTER).build();
		newVl.add(Components.span().styleNames("analitycs-label", "analitycs-new").text(newOrders).build());
		newVl.add(Components.h4().text("New").build());

		VerticalLayout tomorrowVl = Components.vl().fullWidth().alignItems(Alignment.CENTER).build();
		tomorrowVl.add(
				Components.span().styleNames("analitycs-label", "analitycs-tomorrow").text(tomorrowOrders).build());
		tomorrowVl.add(Components.h4().text("Tomorrow").build());

		board.addRow(remainingVl, notAvailableVl, newVl, tomorrowVl).getStyle().set("background-color", "#f3f5f7");

		////////////////
		StorefrontListing sl = new StorefrontListing();
		sl.setSizeFull();

		board.add(sl);
		////////////////

		Configuration conf = monthlyProductSplit.getConfiguration();
		conf.setTitle(("Products delivered in " + LocalDate.now().getMonth().getDisplayName(TextStyle.FULL, Locale.US))
				.toUpperCase());
		DataSeries deliveriesPerProductSeries = new DataSeries("Deliveries");
		conf.addSeries(deliveriesPerProductSeries);
		conf.getyAxis().setTitle("");

		Map<String, Number> deliveredProductsMap = orderDataService.getDeliveredProductsLastMonth();
		deliveredProductsMap.forEach((k, v) -> deliveriesPerProductSeries.add(new DataSeriesItem(k, v)));

		// board.addRow(addMonthDeliveriesChart()/* , addYearDeliveriesChart() */);
		// board.addRow(Components.hl().add(addYearlySalesChart(), addMonthlyProductSplitChart()).build());
		// board.addRow(addYearlySalesChart(), addMonthlyProductSplitChart());

		Components.configure(this).fullSize();
		getStyle().set("overflow", "auto");

		// add(board);
		// .fullWidth()
		// .spacing().add(board);

		// setFlexGrow(1, board);
		// getStyle().set("overflow-y", "auto");

		add(addMonthDeliveriesChart(), addYearDeliveriesChart());
	}

	private static void configureColumnSeries(ListSeries series) {
		PlotOptionsColumn options = new PlotOptionsColumn();
		options.setEdgeWidth(1);
		options.setGroupPadding(0);
		series.setPlotOptions(options);

		YAxis yaxis = series.getConfiguration().getyAxis();
		yaxis.setLabels(new Labels(false));
		yaxis.setTitle("");
	}

	private static QueryFilter buildFilter() {
		return Order.DUE_DATE.goe(LocalDate.now());
	}

	private Chart addMonthDeliveriesChart() {
		Chart chart = new Chart();
		// chart.setSizeFull();
		// chart.setHeight("150px");
		Configuration configuration = chart.getConfiguration();
		configuration.setTitle(("Deliveries in " + LocalDate.now().getMonth().getDisplayName(TextStyle.FULL, Locale.US))
				.toUpperCase());
		chart.getConfiguration().getChart().setType(ChartType.COLUMN);

		Map<String, Number> currentMonthDeliveriesMap = orderDataService.getCurrentMonthDeliveries();

		configuration.getxAxis().setCategories(
				currentMonthDeliveriesMap.keySet().toArray(new String[currentMonthDeliveriesMap.keySet().size()]));

		configuration.getLegend().setEnabled(false);

		ListSeries deliveriesThisMonthSeries = new ListSeries("Deliveries");
		deliveriesThisMonthSeries.setData(
				currentMonthDeliveriesMap.values().toArray(new Number[currentMonthDeliveriesMap.values().size()]));
		configuration.addSeries(deliveriesThisMonthSeries);

		YAxis y = new YAxis();
		y.setMin(0);
		y.setTitle("");
		configuration.addyAxis(y);

		Tooltip tooltip = new Tooltip();
		tooltip.setShared(true);
		configuration.setTooltip(tooltip);

		return chart;
	}

	private Chart addYearDeliveriesChart() {
		Chart chart = new Chart();
		// chart.setHeight("150px");
		Configuration configuration = chart.getConfiguration();
		configuration.setTitle(("Deliveries in " + LocalDate.now().getYear()).toUpperCase());
		chart.getConfiguration().getChart().setType(ChartType.COLUMN);

		configuration.getxAxis().setCategories(MONTHS);
		configuration.getLegend().setEnabled(false);

		ListSeries deliveriesThisYearSeries = new ListSeries("Deliveries");
		deliveriesThisYearSeries.setData(orderDataService.getDeliveriesPerMonth());
		configuration.addSeries(deliveriesThisYearSeries);

		YAxis y = new YAxis();
		y.setMin(0);
		y.setTitle("");
		configuration.addyAxis(y);

		Tooltip tooltip = new Tooltip();
		tooltip.setShared(true);
		configuration.setTooltip(tooltip);

		return chart;
	}

	private Chart addYearlySalesChart() {
		Chart chart = new Chart(ChartType.AREA);
		// chart.setHeight("300px");
		final Configuration configuration = chart.getConfiguration();

		configuration.setTitle("Sales last years".toUpperCase());

		XAxis xAxis = configuration.getxAxis();
		xAxis.setCategories(MONTHS);
		xAxis.setTickmarkPlacement(TickmarkPlacement.ON);

		int year = Year.now().getValue();
		int[] years = new int[3];
		years[0] = year;
		years[1] = year - 1;
		years[2] = year - 2;

		for (int y : years) {
			ListSeries salesPerYear = new ListSeries(Integer.toString(y));
			salesPerYear.setData(orderDataService.getSalesPerYear(y));
			configuration.addSeries(salesPerYear);
		}

		YAxis yAxis = configuration.getyAxis();
		yAxis.setMin(0);
		yAxis.setTitle("");

		PlotOptionsArea plotOptionsArea = new PlotOptionsArea();
		plotOptionsArea.setStacking(Stacking.NORMAL);
		configuration.setPlotOptions(plotOptionsArea);

		Tooltip tooltip = new Tooltip();
		tooltip.setShared(true);
		configuration.setTooltip(tooltip);

		return chart;
	}

	private Chart addMonthlyProductSplitChart() {
		Chart chart = new Chart(ChartType.PIE);
		Configuration conf = chart.getConfiguration();

		conf.setTitle(("Products delivered in " + LocalDate.now().getMonth().getDisplayName(TextStyle.FULL, Locale.US))
				.toUpperCase());
		conf.getLegend().setEnabled(false);

		Tooltip tooltip = new Tooltip();
		tooltip.setValueDecimals(1);
		tooltip.setPointFormat("{series.name}: <b>{point.percentage}%</b>");
		conf.setTooltip(tooltip);

		PlotOptionsPie plotOptions = new PlotOptionsPie();
		plotOptions.setAllowPointSelect(true);
		plotOptions.setCursor(Cursor.POINTER);
		plotOptions.setShowInLegend(true);
		conf.setPlotOptions(plotOptions);

		DataSeries deliveriesPerProductSeries = new DataSeries("Deliveries");
		conf.getyAxis().setTitle("");
		conf.addSeries(deliveriesPerProductSeries);

		Map<String, Number> deliveredProductsMap = orderDataService.getDeliveredProductsLastMonth();
		deliveredProductsMap.forEach((k, v) -> deliveriesPerProductSeries.add(new DataSeriesItem(k, v)));

		chart.setVisibilityTogglingDisabled(true);

		return chart;
	}

}
