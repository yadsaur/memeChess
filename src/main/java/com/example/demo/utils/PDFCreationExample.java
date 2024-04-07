package com.example.demo.utils;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.itextpdf.layout.renderer.DocumentRenderer;
import com.itextpdf.layout.renderer.DrawContext;
import com.itextpdf.layout.renderer.IRenderer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PDFCreationExample {
    public static void main(String[] args) throws IOException {
    	
//        try {
//            // Step 1: Create a new PdfWriter
//            PdfWriter writer = new PdfWriter("C:\\Users\\yadsa\\OneDrive\\Documents\\Python Scripts/sample.pdf");
//
//            // Step 2: Create a new PdfDocument
//            PdfDocument pdf = new PdfDocument(writer);
//            PdfFontFactory.setFontProvider(new PdfFontFactory());
//
//            // Step 3: Create a new Document
//            Document document = new Document(pdf);
//
//            // Step 4: Set border with microprinted "ECI"
//            document.setRenderer(new CustomBorderRenderer(document));
//
//            // Step 5: Add title on top
//            Paragraph title = new Paragraph("Download Report")
//                    .setFont(StandardFonts.HELVETICA_BOLD)
//                    .setFontSize(18)
//                    .setTextAlignment(TextAlignment.CENTER);
//            document.add(title);
//
//            // Step 6: Create a pie chart with two fields
//            DefaultPieDataset dataset = new DefaultPieDataset();
//            dataset.setValue("Downloaded", 75);
//            dataset.setValue("Pending", 25);
//
//            JFreeChart chart = ChartFactory.createPieChart("", dataset, false, false, false);
//            chart.setBackgroundPaint(new Color(0, 0, 0, 0));
//            PiePlot plot = (PiePlot) chart.getPlot();
//            plot.setSectionPaint("Downloaded", new Color(0, 176, 80));
//            plot.setSectionPaint("Pending", new Color(255, 0, 0));
//
//            // Convert JFreeChart to AWT Image
//            int width = 300;
//            int height = 200;
//            java.awt.Image awtImage = chart.createBufferedImage(width, height);
//
//            // Convert AWT Image to iText Image
//            com.itextpdf.layout.element.Image image = new com.itextpdf.layout.element.Image(ImageDataFactory.create(awtImage, null));
//
//            // Step 7: Add the pie chart to the document
//            document.add(image);
//
//            // Step 8: Close the document
//            document.close();
//
//            System.out.println("PDF created successfully.");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

//    static class CustomBorderRenderer extends DocumentRenderer {
//        public CustomBorderRenderer(Document document) {
//            super(document);
//        }
//
//        @Override
//		public void drawBorder(DrawContext drawContext) {
//            Rectangle pageSize = drawContext.getDocument().getPage(1).getPageSize();
//            PdfCanvas canvas = drawContext.getCanvas();
//            canvas.saveState();
//            canvas.setLineWidth(1f);
//            canvas.setLineDash(3f, 1f);
//            canvas.roundRectangle(pageSize.getLeft() + 10, pageSize.getBottom() + 10,
//                    pageSize.getWidth() - 20, pageSize.getHeight() - 20, 10);
//            canvas.stroke();
//            canvas.restoreState();
//
//            Paragraph microprintedText = new Paragraph("ECI")
//                    .setFontColor(ColorConstants.LIGHT_GRAY)
//                    .setFontSize(5)
//                    .setTextAlignment(TextAlignment.RIGHT)
//                    .setRotationAngle(Math.toRadians(45));
//
//            canvas.saveState();
//            try {
//				canvas.beginText().setFontAndSize(PdfFontFactory.createFont(), 5)
//				        .moveText(pageSize.getLeft() + 15, pageSize.getBottom() + 15)
//				        .showText("ECI")
//				        .endText();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//            canvas.restoreState();
//
//            canvas.saveState();
//            canvas.setLineDash(1f, 1f);
//            canvas.setStrokeColor(ColorConstants.LIGHT_GRAY);
//            for (int i = 20; i <= pageSize.getWidth() - 20; i += 20) {
//                canvas.moveTo(i, pageSize.getBottom() + 10);
//                canvas.lineTo(i, pageSize.getTop() - 10);
//            }
//            for (int i = 20; i <= pageSize.getHeight() - 20; i += 20) {
//                canvas.moveTo(pageSize.getLeft() + 10, i);
//                canvas.lineTo(pageSize.getRight() - 10, i);
//            }
//            canvas.stroke();
//            canvas.restoreState();
//        }
    }

}
