package xzvf.reports;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import xzvf.model.Donor;

public class DonorsReportBuilder extends AbstractReportView {

	private PdfPCell defaultCell = new PdfPCell();

	@SuppressWarnings("unchecked")
	@Override
	protected void buildDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Donor> donors = (List<Donor>) model.get("donors");

		document.add(new Paragraph("Donors List"));
		document.add(new Paragraph("Printed date: " + dateTimeFormat.format(new Date()), courierFont));
		document.add(new Paragraph("Printed by: " + model.get("printedBy"), courierFont));

		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100.0f);
		table.setWidths(new float[] { 2.5f, 0.8f, 2.5f, 2.2f, 1.8f, 0.8f });
		table.setSpacingBefore(20);

		defaultCell = new PdfPCell();
		defaultCell.setPadding(5);
		defaultCell.setBorderColor(BaseColor.LIGHT_GRAY);

		table = addHeaderCell(table, "Name");
		table = addHeaderCell(table, "BT");
		table = addHeaderCell(table, "Address");
		table = addHeaderCell(table, "Email");
		table = addHeaderCell(table, "Contact No");
		table = addHeaderCell(table, "Sex");

		for (Donor donor : donors) {
			table = addCell(table, donor.getDisplayString());
			table = addCell(table, donor.getBloodType());
			table = addCell(table, donor.getUser().getAddress());
			table = addCell(table, donor.getUser().getEmail());
			table = addCell(table, donor.getUser().getContactNo());
			table = addCell(table, donor.getUser().getGender().getCode());
		}
		document.add(table);
	}

	private PdfPTable addCell(PdfPTable table, Object data) {
		if (data instanceof Date) {
			data = dateFormat.format(data);
		}
		
		data = data != null ? String.valueOf(data) : "";
		defaultCell.setPhrase(new Phrase(data.toString(), getDefaultFont()));
		table.addCell(defaultCell);
		return table;
	}

	private PdfPTable addHeaderCell(PdfPTable table, Object data) {
		defaultCell.setPhrase(new Phrase(String.valueOf(data)));
		table.addCell(defaultCell);
		return table;
	}

}
