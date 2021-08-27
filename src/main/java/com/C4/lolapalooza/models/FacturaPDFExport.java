package com.C4.lolapalooza.models;

import com.C4.lolapalooza.dtos.FacturaDTO;
import com.C4.lolapalooza.dtos.TicketFacturaDTO;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import javax.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.io.IOException;
import java.util.List;

public class FacturaPDFExport {
    private FacturaDTO factura;
    private List<MerchFactura> merchs;
    private List<TicketFacturaDTO> tickets;

    public FacturaPDFExport(List<MerchFactura> merches, FacturaDTO factura, List<TicketFacturaDTO> ticketFacturaDTOS) {
        this.factura = factura;
        this.merchs = merches;
        this.tickets = ticketFacturaDTOS;
    }


    private void writeHeaderFactura(PdfPTable table) {

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.DARK_GRAY);
        cell.setPadding(4);
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(Color.black);
        cell.setPhrase(new Phrase("N° Invoice", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Purchaser", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Date", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Address", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Price Total", font));
        table.addCell(cell);

    }
    private void writeTableFactura(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.GRAY);
        table.addCell(String.valueOf(factura.getId()));
        table.addCell(factura.getClient().getFirstName() + " " + factura.getClient().getLastName());
        table.addCell(String.valueOf(factura.getDate()));
        table.addCell(String.valueOf(factura.getClient().getEmail()));
        table.addCell(String.valueOf(factura.getAmount()));

    }

    private void writeTableHeader(PdfPTable table) {

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.DARK_GRAY);
        cell.setPadding(5);
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.CYAN);
        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Description", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Unit Price", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Quantity", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Price Total", font));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.GRAY);
        for(MerchFactura merchs: merchs){
            table.addCell(String.valueOf(merchs.getName()));
            table.addCell(merchs.getDescription());
            table.addCell(String.valueOf(merchs.getPrecioUnitario()));
            table.addCell(String.valueOf(merchs.getCantidad()));
            table.addCell(String.valueOf(merchs.getPrecioTotal()));
        }
    }

    private void writeTableHeaderT(PdfPTable table) {

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.DARK_GRAY);
        cell.setPadding(3);
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.CYAN);
        cell.setPhrase(new Phrase("N°", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Site", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Price Total", font));
        table.addCell(cell);

    }

    private void writeTableDataT(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.GRAY);
        for(TicketFacturaDTO tickets : tickets){
            table.addCell(String.valueOf(tickets.getId()));
            table.addCell(tickets.getSite());
            table.addCell(String.valueOf(tickets.getPrecioTotal()));

        }
    }

    public void usePDFExport(HttpServletResponse response) throws DocumentException, IOException {
        var doc= new Document(PageSize.A4);
        PdfWriter.getInstance(doc, response.getOutputStream());
        doc.open();
        //doc.addCreationDate();
        //doc.addTitle(String.valueOf(new Paragraph("LollaPalooza")));
        //doc.addHeader(factura.getClient().getFirstName() + factura.getClient().getLastName(), String.valueOf(factura.getId()));

        //FUENTES
        Font font = FontFactory.getFont(FontFactory.COURIER);
        //font.setColor(Color.BLACK);
        Font fontTit = FontFactory.getFont(FontFactory.COURIER);
        fontTit.setSize(25);
        fontTit.setStyle(Font.BOLD);
        font.setStyle(Font.BOLD);
        font.setColor(Color.BLACK);

        //PARRAFOS
        Paragraph p = new Paragraph("Products", font);
        Paragraph tittle = new Paragraph("LollaPalooza", fontTit);
        tittle.setSpacingBefore(15);
        Paragraph tickets = new Paragraph("Tickets", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        tittle.setAlignment(Paragraph.ALIGN_CENTER);
        tickets.setAlignment(Paragraph.ALIGN_CENTER);
        doc.add(tittle);


        //TAMAÑOS CELDAS
        PdfPTable table = new PdfPTable(5);
        PdfPTable tableP = new PdfPTable(5);
        PdfPTable tableT = new PdfPTable(3);
        tableT.setWidthPercentage(100f);
        tableP.setWidths(new float[] {4.0f, 3.0f, 2.0f, 2.0f, 2.0f});
        tableP.setSpacingBefore(10);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.0f, 2.0f, 2.0f,4.0f, 2.0f});
        table.setSpacingBefore(10);
        tableT.setWidths(new float[] {1.0f, 4.0f, 2.0f});
        tableT.setSpacingBefore(10);
        tableP.setWidthPercentage(100f);

        //CREACION TABLA PRODUCTOS Y CABECERA
        writeHeaderFactura(table);
        writeTableFactura(table);
        doc.add(table);
        doc.add(p);
        writeTableHeader(tableP);
        writeTableData(tableP);
        doc.add(tableP);
        doc.add(tickets);
        writeTableHeaderT(tableT);
        writeTableDataT(tableT);
        doc.add(tableT);

        //CIERRE PDF
        doc.close();
    }

}
