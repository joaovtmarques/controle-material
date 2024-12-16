package com.informatica.controle_material.data.usecase.loan_doc;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.informatica.controle_material.data.dto.equipment.EquipmentDTO;
import com.informatica.controle_material.data.dto.loan.LoanEquipmentDTO;
import com.informatica.controle_material.domain.model.Equipment;
import com.informatica.controle_material.domain.usecases.loan_doc.CreateLoanReadyDocUseCase;
import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.Section;
import com.spire.doc.Table;
import com.spire.doc.TableRow;
import com.spire.doc.documents.BookmarksNavigator;
import com.spire.doc.documents.VerticalAlignment;
import com.spire.doc.fields.TextRange;

@Service
public class CreateLoanReadyDocImpl implements CreateLoanReadyDocUseCase {

  @Override
  public String execute(List<LoanEquipmentDTO> loans, List<Equipment> equipments) {
    Document document = new Document("src/main/resources/loan-ready.docx");

    String[] header = { "Equipamento",
        "Número de série",
        "Quantidade em estoque",
        "Quantidade em cautela",
        "Equipamento temporário?",
        "Cliente",
        "Destino",
        "Data de cautela",
    };

    String[] header2 = { "Equipamento",
        "Número de série",
        "Quantidade em estoque",
        "Quantidade em cautela",
        "Situação",
        "Condição",
        "Observação",
        "Preço",
    };

    document.replace("{date}", new SimpleDateFormat("dd 'de' MMM 'de' yyyy").format(new Date()), false, true);

    Section section = document.getSections().get(0);

    // Primeira Tabela
    section.getParagraphs().get(2).appendBookmarkStart("Tabl");
    section.getParagraphs().get(3).appendBookmarkEnd("Tabl");

    BookmarksNavigator bn = new BookmarksNavigator(document);
    bn.moveToBookmark("Tabl", true, true);

    Table table = section.getBody().addTable(true);

    int totalRows = loans.stream()
        .mapToInt(loan -> loan.equipments().size())
        .sum() + 1;

    table.resetCells(totalRows, 8);

    bn.insertTable(table);

    TableRow rowHeader = table.getRows().get(0);
    rowHeader.isHeader(true);
    for (int i = 0; i < header.length; i++) {
      rowHeader.getCells().get(i).getCellFormat().setVerticalAlignment(VerticalAlignment.Middle);
      rowHeader.getCells().get(i).getCellFormat().setBackColor(new java.awt.Color(224, 244, 255));
      TextRange txtRange = rowHeader.getCells().get(i).addParagraph().appendText(header[i]);
      txtRange.getCharacterFormat().setBold(true);
      txtRange.getCharacterFormat().setFontName("Arial");
      txtRange.getCharacterFormat().setFontSize(8);
    }

    for (int i = 0, rowIndex = 1; i < loans.size(); i++) {
      LoanEquipmentDTO loan = loans.get(i);

      for (int j = 0; j < loan.equipments().size(); j++, rowIndex++) {
        EquipmentDTO equipment = loan.equipments().get(j);
        TableRow tb = table.getRows().get(rowIndex);

        if (rowIndex % 2 == 0) {
          for (int k = 0; k < 8; k++) {
            tb.getCells().get(k).getCellFormat().setBackColor(new java.awt.Color(224, 244, 255)); // Azul claro
          }
        }

        TextRange text0 = tb.getCells().get(0).addParagraph().appendText(equipment.name());
        text0.getCharacterFormat().setFontName("Arial");
        text0.getCharacterFormat().setFontSize(8);

        TextRange text1 = tb.getCells().get(1).addParagraph().appendText(
            equipment.serialNumber() != null ? equipment.serialNumber() : "");
        text1.getCharacterFormat().setFontName("Arial");
        text1.getCharacterFormat().setFontSize(8);

        TextRange text2 = tb.getCells().get(2).addParagraph().appendText(equipment.amount().toString());
        text2.getCharacterFormat().setFontName("Arial");
        text2.getCharacterFormat().setFontSize(8);

        TextRange text3 = tb.getCells().get(3).addParagraph().appendText(loan.loanAmount().toString());
        text3.getCharacterFormat().setFontName("Arial");
        text3.getCharacterFormat().setFontSize(8);

        TextRange text4 = tb.getCells().get(4).addParagraph().appendText(equipment.isTemporary() ? "Sim" : "Não");
        text4.getCharacterFormat().setFontName("Arial");
        text4.getCharacterFormat().setFontSize(8);

        TextRange text5 = tb.getCells().get(5).addParagraph().appendText(loan.receiverName());
        text5.getCharacterFormat().setFontName("Arial");
        text5.getCharacterFormat().setFontSize(8);

        TextRange text6 = tb.getCells().get(6).addParagraph().appendText(loan.mission());
        text6.getCharacterFormat().setFontName("Arial");
        text6.getCharacterFormat().setFontSize(8);

        TextRange text7 = tb.getCells().get(7).addParagraph().appendText(loan.loanDate());
        text7.getCharacterFormat().setFontName("Arial");
        text7.getCharacterFormat().setFontSize(8);
      }
    }

    // Segunda Tabela
    section.getParagraphs().get(3).appendBookmarkStart("Tabl1");
    section.getParagraphs().get(4).appendBookmarkEnd("Tabl1");

    bn.moveToBookmark("Tabl1", true, true);

    Table table1 = section.getBody().addTable(true);

    int totalRows1 = equipments.size() + 1; // Cabeçalho + linhas para equipamentos
    table1.resetCells(totalRows1, header2.length);

    // Cabeçalho da segunda tabela
    TableRow rowHeader2 = table1.getRows().get(0);
    rowHeader2.isHeader(true);

    for (int i = 0; i < header2.length; i++) {
      rowHeader2.getCells().get(i).getCellFormat().setVerticalAlignment(VerticalAlignment.Middle);
      rowHeader2.getCells().get(i).getCellFormat().setBackColor(new java.awt.Color(224, 244, 255));
      TextRange txtRange1 = rowHeader2.getCells().get(i).addParagraph().appendText(header2[i]);
      txtRange1.getCharacterFormat().setBold(true);
      txtRange1.getCharacterFormat().setFontName("Arial");
      txtRange1.getCharacterFormat().setFontSize(8);
    }

    // Dados da segunda tabela
    for (int i = 0; i < equipments.size(); i++) {
      Equipment equipment = equipments.get(i);
      TableRow dataRow = table1.getRows().get(i + 1);

      if ((i + 1) % 2 == 0) {
        for (int k = 0; k < header2.length; k++) {
          dataRow.getCells().get(k).getCellFormat().setBackColor(new java.awt.Color(224, 244, 255)); // Azul claro
        }
      }

      TextRange text0 = dataRow.getCells().get(0).addParagraph().appendText(equipment.getName());
      text0.getCharacterFormat().setFontName("Arial");
      text0.getCharacterFormat().setFontSize(8);

      TextRange text1 = dataRow.getCells().get(1).addParagraph().appendText(
          equipment.getSerialNumber() != null ? equipment.getSerialNumber() : "");
      text1.getCharacterFormat().setFontName("Arial");
      text1.getCharacterFormat().setFontSize(8);

      TextRange text2 = dataRow.getCells().get(2).addParagraph().appendText(equipment.getAmount().toString());
      text2.getCharacterFormat().setFontName("Arial");
      text2.getCharacterFormat().setFontSize(8);

      TextRange text3 = dataRow.getCells().get(3).addParagraph().appendText(
          equipment.getAmountOut() != null ? equipment.getAmountOut().toString() : "0");
      text3.getCharacterFormat().setFontName("Arial");
      text3.getCharacterFormat().setFontSize(8);

      TextRange text4 = dataRow.getCells().get(4).addParagraph().appendText(
          equipment.getState() != null ? equipment.getState() : "Indefinido");
      text4.getCharacterFormat().setFontName("Arial");
      text4.getCharacterFormat().setFontSize(8);

      TextRange text5 = dataRow.getCells().get(5).addParagraph().appendText(
          equipment.getCondition() != null ? equipment.getCondition() : "Indefinida");
      text5.getCharacterFormat().setFontName("Arial");
      text5.getCharacterFormat().setFontSize(8);

      TextRange text6 = dataRow.getCells().get(6).addParagraph().appendText(
          equipment.getObservation() != null ? equipment.getObservation() : "");
      text6.getCharacterFormat().setFontName("Arial");
      text6.getCharacterFormat().setFontSize(8);

      TextRange text7 = dataRow.getCells().get(7).addParagraph().appendText(
          equipment.getPrice() != null ? equipment.getPrice().toString() : "0");
      text7.getCharacterFormat().setFontName("Arial");
      text7.getCharacterFormat().setFontSize(8);
    }

    String filePath = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    File dir = new File("tmp/prontos/" + filePath);
    if (!dir.exists())
      dir.mkdirs();

    String finalFilePath = dir + "/" + filePath + ".pdf";
    document.saveToFile(finalFilePath, FileFormat.PDF);

    document.dispose();

    return finalFilePath;
  }
}
