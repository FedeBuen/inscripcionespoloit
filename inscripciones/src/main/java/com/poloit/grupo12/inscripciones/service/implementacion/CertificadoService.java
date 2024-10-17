package com.poloit.grupo12.inscripciones.service.implementacion;
import com.itextpdf.text.*;
import java.io.InputStream;
import javax.imageio.ImageIO;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfContentByte;
import com.poloit.grupo12.inscripciones.dto.CursoEstudianteDTO;
import com.poloit.grupo12.inscripciones.service.interfaces.ICertificadoService;
import org.springframework.stereotype.Service;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class CertificadoService implements ICertificadoService {

    public ByteArrayInputStream generarCertificado(CursoEstudianteDTO cursoEstudianteDTO) {
        float width = 2000;  // Ancho en píxeles
        float height = 1414; // Alto en píxeles
        Document document = new Document(new Rectangle(width, height));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, out);
            document.open();
            PdfContentByte canvas = writer.getDirectContentUnder();
            InputStream inputStream = getClass().getResourceAsStream("/static/images/fondo.png");
            Image fondo = Image.getInstance(ImageIO.read(inputStream), null);
            fondo.setAbsolutePosition(0, 0);
            canvas.addImage(fondo);
            Font fontNombre = new Font(Font.FontFamily.TIMES_ROMAN, 90, Font.BOLD);
            Font fontTexto = new Font(Font.FontFamily.TIMES_ROMAN, 48, Font.NORMAL);
            document.add(new Paragraph("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"));
            Paragraph nombreEstudiante = new Paragraph(cursoEstudianteDTO.getNombreEstudiante(), fontNombre);
            nombreEstudiante.setAlignment(Element.ALIGN_CENTER);
            document.add(nombreEstudiante);
            document.add(new Paragraph("\n"));
            Paragraph textoCertificado = new Paragraph(String.format("Aprobó el curso '%s' con una calificación de %.2f.",
                    cursoEstudianteDTO.getTituloCurso(),
                    cursoEstudianteDTO.getCalificacion()), fontTexto);
            textoCertificado.setAlignment(Element.ALIGN_CENTER);
            document.add(textoCertificado);
            document.add(new Paragraph("\n"));
            Paragraph estado = new Paragraph(String.format("Estado del curso: APROBADO"), fontTexto);
            estado.setAlignment(Element.ALIGN_CENTER);
            document.add(estado);
            document.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(out.toByteArray());
    }
}