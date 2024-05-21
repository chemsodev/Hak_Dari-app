package com.example.demo;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class PDFGenerator {

    public void createRealEstateContract(String src, String dest, Map<String, String> data) throws IOException {
        // Load existing PDF document from resources
        InputStream templateStream = getClass().getResourceAsStream(src);
        if (templateStream == null) {
            throw new IOException("Template file not found");
        }
        PDDocument document = PDDocument.load(templateStream);

        // Load the font from resources
        InputStream fontStream = getClass().getResourceAsStream("/com/example/demo/fonts/Sans.ttf");
        if (fontStream == null) {
            throw new IOException("Font file not found");
        }
        PDType0Font font = PDType0Font.load(document, fontStream);

        // Construct the complete text in the desired order
        StringBuilder completeText = new StringBuilder();
        completeText.append("Real Estate Contract\n")
                .append("Buyyer Informations:\n")
                .append("Name: ").append(data.get("buyerName")).append("\n")
                .append("Phone: ").append(data.get("buyerPhone")).append("\n")
                .append("Seller Informations:\n")
                .append("Name: ").append(data.get("sellerName")).append("\n")
                .append("Phone: ").append(data.get("sellerPhone")).append("\n")
                .append("Property Information:\n")
                .append("Address: ").append(data.get("propertyAddress")).append("\n")
                .append("Sale Price: ").append(data.get("salePrice")).append("\n")
                .append("Terms and Conditions:\n")
                .append("The buyer agrees to purchase the property located at ").append(data.get("propertyAddress1")).append(" for\n")
                .append(data.get("salePrice1")).append(".\n")
                .append("The seller agrees to transfer the ownership of the property to the buyer upon\n")
                .append("receipt of the payment.\n")
                .append("Both parties agree to the terms and conditions stated in this contract.\n")
                .append("Signatures:\n")
                .append("Buyer: _________________________\n")
                .append("Date: _______________\n")
                .append("Seller: _________________________\n")
                .append("Date: _______________\n");

        // Iterate over pages and replace the content with the ordered text
        for (PDPage page : document.getPages()) {
            // Clear the existing content of the page
            PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.OVERWRITE, true, true);
            contentStream.setFont(font, 12);
            contentStream.setLeading(14.5f);
            contentStream.beginText();
            contentStream.newLineAtOffset(25, 750);

            // Write the ordered text line by line
            String[] lines = completeText.toString().split("\n");
            for (String line : lines) {
                contentStream.showText(line);
                contentStream.newLine();
            }

            contentStream.endText();
            contentStream.close();
        }

        // Save the filled document
        document.save(dest);
        document.close();
    }
}
