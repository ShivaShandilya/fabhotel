package com.fabhotel.fabhotel.Controller;

import com.fabhotel.fabhotel.Entity.Booking;
import com.fabhotel.fabhotel.Entity.PropertyUser;
import com.fabhotel.fabhotel.Service.*;
import com.fabhotel.fabhotel.payload.BookingDto;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/Booking")
public class BookingController {
    private BookingService bookingService;
    private TwilioService twilioService;
    private EmailService emailService;
    private PdfService pdfService;
    private BucketService bucketService;

    public BookingController(BookingService bookingService, TwilioService twilioService, EmailService emailService, PdfService pdfService, BucketService bucketService) {
        this.bookingService = bookingService;
        this.twilioService = twilioService;


        this.emailService = emailService;
        this.pdfService = pdfService;
        this.bucketService = bucketService;
    }


    @PostMapping("/booking/{Propertyid}")
    public ResponseEntity<BookingDto> hotelbooking(@RequestBody Booking booking, @PathVariable Long Propertyid,
                                                   @AuthenticationPrincipal PropertyUser user) throws Exception {
        BookingDto dto = bookingService.hotelbooking(booking, Propertyid, user);
        twilioService.sendSms("+917974424673", "Success Booking");
        emailService.sendEmail("shakshiashti2@gmail.com", "booking", "success");
        return new ResponseEntity<>(dto, HttpStatus.CREATED);

    }

    @GetMapping("/creatpdf")
    public ResponseEntity<String> createpdf(@RequestBody BookingDto bookingDto) throws IOException {

        boolean b = pdfService.createpdf(bookingDto,"C://oct/" + "iTextHelloWorld.pdf");
        if(b==true){
            String filename = "C://oct/" + "iTextHelloWorld.pdf";
            MultipartFile file =BookingController.convert(filename);
            System.out.println(file.getOriginalFilename());
            String uploadedFile = bucketService.uploadFile(file, "fabhotel4");
            System.out.println(uploadedFile);
        }


        return new ResponseEntity<>("Create", HttpStatus.OK);


    }
    public static MultipartFile convert (String filename) throws IOException {
        File file = new File(filename);
        byte[] fileContent = Files.readAllBytes(file.toPath());
        Resource resource = new ByteArrayResource(fileContent);
        MultipartFile multipartFile = new MultipartFile(){
            @Override
            public String getName(){
                return file.getName();
            }
            @Override
            public String getOriginalFilename(){
                return file.getName();
            }
            @Override
            public  String getContentType(){
                return null;
            }
            @Override
            public boolean isEmpty(){
                return fileContent.length==0;
            }

            @Override
            public long getSize() {
                return fileContent.length;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return fileContent;
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return resource.getInputStream();
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {
                Files.write(dest.toPath(),fileContent);
            }
        };
        return multipartFile;


    }



}
