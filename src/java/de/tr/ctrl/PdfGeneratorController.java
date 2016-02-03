package de.tr.ctrl;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import de.tr.dao.RentalDao;
import de.tr.dao.TrailerDao;
import de.tr.model.Rental;
import de.tr.model.Trailer;
/**
 * The PdfGeneratorPdfGenerator Controller class. Its tasks:
 * <ul>
 * <li>Generate pdf report for the system</li>
 * @author
 */

@Controller
public class PdfGeneratorController {

	private static Logger log = Logger.getLogger(HomeController.class);
	private TrailerDao trailerDao = null;
	private RentalDao rentalDao = null;

	public TrailerDao getTrailerDao() {
		return trailerDao;
	}

	public void setTrailerDao(TrailerDao trailerDao) {
		this.trailerDao = trailerDao;
	}
	

	public RentalDao getRentalDao() {
		return rentalDao;
	}

	public void setRentalDao(RentalDao rentalDao) {
		this.rentalDao = rentalDao;
	}

	/**
	 * Handler for the trailer report in pdf format. Note that this handler relies on
	 * the request URL: "/trailer-pdf.html"
	 * @param              response send for HttpServleRrespone 
	 * @return null
	 */
	@RequestMapping("/trailer-pdf.html")
	public void trailerPDF(HttpServletResponse response)
			throws Exception {
		log.info("creating trailer report pdf ");
		try {
			List<Trailer> trailerList = trailerDao.findAll();      //get all trailers
			
			Document document =  PdfGeneratorController.pdfBuilder(response);
			PdfGeneratorController.headerTextPDF(document , "Trailer Information Report",response);
			
			for(Trailer trailer:trailerList){
			document.add(new Paragraph("Trailer ID  : " + trailer.getTrailerId()));
			document.add(new Paragraph("Trailer Title : " + trailer.getTitle()));
			document.add(new Paragraph("Carrying Capacity ( In Kg) : " + trailer.getCarryingCapacity()));
			document.add(new Paragraph("Rental Price ( Per Day) : " + trailer.getDailyFee()));
			document.add(new Paragraph("--------------------------------------"));
			}
			document.close();
		} catch (Exception de) {
			throw new IOException(de.getMessage());
		}
	}
	
	/**
	 * Handler for the pdf-invoice report in pdf format. Note that this handler relies on
	 * the request URL: "/pdf-invoice.html"
	 * @param              response send for HttpServleRrespone 
	 * @return null
	 */
	@RequestMapping("/pdf-invoice.html")
	public void pdfInvoice(HttpServletResponse response,int rentalId)
			throws Exception {
		log.info("creating invoice");
		try{
			Document document =  PdfGeneratorController.pdfBuilder(response);
			PdfGeneratorController.headerTextPDF(document , "Invoice For Rental",response);
			
			Rental rental = rentalDao.findById(rentalId);					//find the rental object
			Date rentDate = rental.getDateOfRent();			
			Date returnDate = rental.getDateOfReturn();
			int diffInDays = (int)( (returnDate.getTime() - rentDate.getTime()) 	//find days difference between rent and return the trailer
	                 / (1000 * 60 * 60 * 24) );
			if(diffInDays == 0){
				diffInDays = 1;								//if rent and return is on the same day, default set one day difference
			}
			
			float dailyFee = Float.parseFloat(rental.getTrailer().getDailyFee());			//getting the daily fee of the trailer
			double priceWithoutVat = dailyFee * diffInDays;									//calculating price without vat
			double priceIncludingVat = (double) ((Double)priceWithoutVat + (priceWithoutVat * 0.19));	//calculating price with vat
			
			
			DecimalFormat df = new DecimalFormat("###.##");		//formats a decimal number 
			
			document.add(Chunk.NEWLINE );						//add new line the pdf
			document.add(Chunk.NEWLINE );
			document.add(new Paragraph("1.Customer Name --------------------------------" +
					"--------------------------------  " + rental.getCustomer().getFirstName() +" "+ rental.getCustomer().getLastName()));
			document.add(new Paragraph("2.Company Name --------------------------------" +
					"--------------------------------  " + "XYZ"));
			document.add(new Paragraph("3.Rented Trailer Name --------------------------" +
					"--------------------------------  " + rental.getTrailer().getTitle()));
			document.add(new Paragraph("4.Date of Rent -------------------------------------" +
					"--------------------------------  " + rental.getDateOfRent()));
			document.add(new Paragraph("5.Date of Return ----------------------------------" +
					"--------------------------------  " + rental.getDateOfReturn()));
			document.add(new Paragraph("6.Price per day -----------------------------------" +
					"--------------------------------  " + rental.getTrailer().getDailyFee()+" /-"));
			document.add(new Paragraph("7.Amount -------------------------------------------" +
					"--------------------------------  " + df.format(priceWithoutVat) +" /-"));
			document.add(new Paragraph("8.Total Amount(Including 19% Vat) ---------" +
					"--------------------------------  " + df.format(priceIncludingVat)+" /-"));
			document.close();
		}catch (Exception de) {
			throw new IOException(de.getMessage());
		}
	}
	
	

	/**
	 * Handler for the header text for any pdf report. 
	 * @param  doc    pass iText document for rendering header text 
	 * @param  reportName
	 * @param  response     response send for HttpServleRrespone 
	 * @return null
	 */
	public static void headerTextPDF(Document doc, String reportName,HttpServletResponse response) throws DocumentException, IOException{
		response.setContentType("application/pdf");
		Paragraph projectName = new Paragraph("Trailer Rental Shop", new Font(FontFamily.TIMES_ROMAN, 22));
		projectName.setAlignment(Element.ALIGN_CENTER);
        Paragraph reporName = new Paragraph(reportName, new Font(FontFamily.TIMES_ROMAN, 12));
        reporName.setAlignment(Element.ALIGN_CENTER);
        doc.add(projectName);
        doc.add(reporName);
	}
	/**
	 * Handler for the pdf report document. 
	 * @param  reponse     response send for HttpServleRrespone 
	 * @return document    return document for rendering pdf
	 */
	public static Document pdfBuilder(HttpServletResponse response) throws DocumentException, IOException{
		Document document = new Document();
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();
		return document;
	}
}