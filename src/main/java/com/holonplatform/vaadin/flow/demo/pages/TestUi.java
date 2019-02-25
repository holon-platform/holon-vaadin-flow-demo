package com.holonplatform.vaadin.flow.demo.pages;

import com.holonplatform.vaadin.flow.demo.root.Menu;
import com.vaadin.flow.component.board.Board;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "testui", layout = Menu.class)
public class TestUi extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	public TestUi() {
		getStyle().set("background-color", "lime");
		getStyle().set("overflow", "auto");

		Board board = new Board();
		board.addRow(new H1("test 1111111111111111111111111111111111111111111111111111111111111111111"),
				new H1("test 1111111111111111"), new H1("test 1111111111111111"), new H1("test 1111111111111111"));
//		board.addRow(new H1("test 1111111111111111"), new H1("test 1111111111111111"), new H1("test 1111111111111111"),
//				new H1("test 1111111111111111"));
//		board.addRow(new H1("test 1111111111111111"), new H1("test 1111111111111111"), new H1("test 1111111111111111"),
//				new H1("test 1111111111111111"));
//		board.addRow(new H1("test 1111111111111111"), new H1("test 1111111111111111"), new H1("test 1111111111111111"),
//				new H1("test 1111111111111111"));
//		board.addRow(new H1("test 1111111111111111"), new H1("test 1111111111111111"), new H1("test 1111111111111111"),
//				new H1("test 1111111111111111"));
//		board.addRow(new H1("test 1111111111111111"), new H1("test 1111111111111111"), new H1("test 1111111111111111"),
//				new H1("test 1111111111111111"));
//		board.addRow(new H1("test 1111111111111111"), new H1("test 1111111111111111"), new H1("test 1111111111111111"),
//				new H1("test 1111111111111111"));
//		board.addRow(new H1("test 1111111111111111"), new H1("test 1111111111111111"), new H1("test 1111111111111111"),
//				new H1("test 1111111111111111"));
//		board.addRow(new H1("test 1111111111111111"), new H1("test 1111111111111111"), new H1("test 1111111111111111"),
//				new H1("test 1111111111111111"));
		add(board);

	}

}
