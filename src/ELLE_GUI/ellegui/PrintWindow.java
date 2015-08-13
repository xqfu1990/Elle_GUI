package ELLE_GUI.ellegui;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;

import javax.swing.*;

public class PrintWindow implements Printable {

	JFrame frameToPrint;
	CreateTables tableToPrint;

	public PrintWindow(JFrame f) {
		frameToPrint = f;
		tableToPrint = null;
	}

	public PrintWindow(CreateTables t) {
		tableToPrint = t;
		frameToPrint = null;
	}

	public int print(Graphics g, PageFormat pf, int page) {

		if (page > 0) {
			return NO_SUCH_PAGE;
		}

		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(pf.getImageableX(), pf.getImageableY());
		pf.setOrientation(PageFormat.LANDSCAPE);
		Paper paper = pf.getPaper();
		paper.setSize(792, 612);
		paper.setImageableArea(0, 0, paper.getWidth(), paper.getHeight());
		pf.setPaper(paper);

		if (tableToPrint == null) {
			frameToPrint.printAll(g);
		} else {
			tableToPrint.printAll(g);
		}

		return PAGE_EXISTS;
	}

}
