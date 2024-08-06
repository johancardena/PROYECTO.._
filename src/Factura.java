
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Factura {
    public static void generarFactura(String idTransaccion, String nombreProducto, int cantidad, double precio, String cajero) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("factura_" + idTransaccion + ".pdf"));
            document.open();
            document.add(new Paragraph("Factura"));
            document.add(new Paragraph("ID de Transacción: " + idTransaccion));
            document.add(new Paragraph("Producto: " + nombreProducto));
            document.add(new Paragraph("Cantidad: " + cantidad));
            document.add(new Paragraph("Precio Unitario: " + precio));
            document.add(new Paragraph("Total: " + (precio * cantidad)));
            document.add(new Paragraph("Cajero: " + cajero));
            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
