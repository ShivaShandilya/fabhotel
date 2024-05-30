package com.fabhotel.fabhotel.Service;

import com.fabhotel.fabhotel.payload.BookingDto;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.util.stream.Stream;

@Service
public class PdfService {
public boolean createpdf(BookingDto bookingDto,String filename) {

    try {
        Document document = new Document();

        PdfWriter.getInstance(document, new FileOutputStream(filename));

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Chunk chunk = new Chunk("Sakshi", font);

        document.add(chunk);
        document.add(new Paragraph("\n"));
        PdfPTable table = new PdfPTable(4);
        addTableHeader(table);
        addRows(table, bookingDto);


        document.add(table);

        document.close();
        return true;
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}

        private void addTableHeader(PdfPTable table) {
        Stream.of("Guest name", "Booking id", "propertyname", "total price")
        .forEach(columnTitle -> {
        PdfPCell header = new PdfPCell();
        header.setBackgroundColor(BaseColor.BLUE);
        header.setBorderWidth(2);
        header.setPhrase(new Phrase(columnTitle));
        table.addCell(header);
        });
        }

        private void addRows(PdfPTable table, BookingDto bookingDto) {
        table.addCell(bookingDto.getGuest());
        table.addCell(bookingDto.getId());
        table.addCell(bookingDto.getPropertyName());
        table.addCell(String.valueOf(bookingDto.getTotalprice()));
        }
        }