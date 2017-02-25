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

import xzvf.model.Donation;

public class DonationsReportBuilder extends AbstractReportView {

	private PdfPCell defaultCell = new PdfPCell();
	
	@SuppressWarnings("unchecked")
	@Override
	protected void buildDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Donation> donations = (List<Donation>) model.get("donations");
		
		document.add(new Paragraph("Donations List"));
		document.add(new Paragraph("Printed date: " + dateTimeFormat.format(new Date()), courierFont));
		document.add(new Paragraph("Printed by: " + model.get("printedBy"), courierFont));
		
		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100.0f);
		table.setWidths(new float[] { 2f, 2f, 2f, 2f, 2f, 2f });
		table.setSpacingBefore(20);
		
		defaultCell = new PdfPCell();
		defaultCell.setPadding(5);
		defaultCell.setBorderColor(BaseColor.LIGHT_GRAY);
		
		table = addHeaderCell(table, "Donor");
		table = addHeaderCell(table, "Patient");
		table = addHeaderCell(table, "Title");
		table = addHeaderCell(table, "Entry Date");
		table = addHeaderCell(table, "Expiry Date");
		table = addHeaderCell(table, "Status");
		
		for (Donation donation : donations) {
			table = addCell(table, donation.getDonorDisplayString());
			table = addCell(table, donation.getPatientDisplayString());
			table = addCell(table, donation.getTitle());
			table = addCell(table, donation.getEntryDate());
			table = addCell(table, donation.getExpiryDate());
			table = addCell(table, donation.getStatus());
		}
		document.add(table);
	}
	
	private PdfPTable addCell(PdfPTable table, Object data){
		if (data != null && data instanceof Date) {
			data = dateFormat.format(data);
		}
		
		defaultCell.setPhrase(new Phrase(String.valueOf(data), getDefaultFont()));
		table.addCell(defaultCell);
		return table;
	}
	
	private PdfPTable addHeaderCell(PdfPTable table, Object data){
		defaultCell.setPhrase(new Phrase(String.valueOf(data)));
		table.addCell(defaultCell);
		return table;
	}

}
