package com.poloit.grupo12.inscripciones.service.implementacion;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.poloit.grupo12.inscripciones.dto.CursoEstudianteDTO;
import com.poloit.grupo12.inscripciones.service.interfaces.ICertificadoService;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
public class CertificadoService implements ICertificadoService {

    public ByteArrayInputStream generarCertificado(CursoEstudianteDTO cursoEstudianteDTO) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            // Crear el escritor de PDF y asociarlo al documento
            PdfWriter.getInstance(document, out);

            // Abrir el documento
            document.open();

            // Fuente para el título
            Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD);

            // Crear el título del certificado
            Paragraph titulo = new Paragraph("Certificado de Finalización", fontTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);

            // Saltar una línea
            document.add(new Paragraph("\n"));

            // Texto del certificado
            String textoCertificado = String.format(
                    "Certificamos que %s ha completado con éxito el curso '%s' con una calificación de %.2f.\n\n" +
                            "Estado del curso: %s\nFecha de inscripción: %s",
                    cursoEstudianteDTO.getNombreEstudiante(),
                    cursoEstudianteDTO.getTituloCurso(),
                    cursoEstudianteDTO.getCalificacion(),
                    cursoEstudianteDTO.getEstado(),
                    cursoEstudianteDTO.getFechaInscripcion()
            );

            Paragraph texto = new Paragraph(textoCertificado);
            texto.setAlignment(Element.ALIGN_CENTER);
            document.add(texto);

            // Cerrar el documento
            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}