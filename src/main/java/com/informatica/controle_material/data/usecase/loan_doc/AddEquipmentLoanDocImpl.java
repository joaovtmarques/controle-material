package com.informatica.controle_material.data.usecase.loan_doc;

import java.io.File;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.informatica.controle_material.domain.model.Equipment;
import com.informatica.controle_material.domain.model.Loan;
import com.informatica.controle_material.domain.usecases.loan_doc.AddLoanDocUseCase;
import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.Section;
import com.spire.doc.Table;
import com.spire.doc.TableRow;
import com.spire.doc.documents.BookmarksNavigator;
import com.spire.doc.documents.TableRowHeightType;
import com.spire.doc.documents.VerticalAlignment;
import com.spire.doc.fields.TextRange;

@Service
public class AddEquipmentLoanDocImpl implements AddLoanDocUseCase {

  @Override
  public String execute(Loan loan) {
    Document document = null;

    if (loan.getType().equals("INFORMATICA")) {
      document = new Document("src/main/resources/loan-equipments-form.docx");
    } else {
      document = new Document("src/main/resources/loan-equipments-form-pelcom.docx");
    }

    String[] header = { "Material", "Número de série", "Tipo", "Condição", "Quantidade", "Preço" };

    document.replace("{receiver}", loan.getReceiver().getName(), false, true);
    document.replace("{cpf}", loan.getReceiver().getCpf(), false, true);
    document.replace("{date}", loan.getDate(), false, true);
    document.replace("{rank}", loan.getReceiver().getRank(), false, true);
    document.replace("{warName}", loan.getReceiver().getWarName(), false, true);
    document.replace("{lenderRank}", loan.getLender().getRank(), false, true);
    document.replace("{lender}", loan.getLender().getWarName(), false, true);
    document.replace("{observation}", loan.getObservation(), false, true);
    document.replace("{devolutionDate}", loan.getDevolutionDate(), false, true);

    String equipmentsName = String.join(", ", loan.getEquipments().stream()
        .map(Equipment::getName).toArray(String[]::new));
    document.replace("{name}", equipmentsName, false, true);

    Section section = document.getSections().get(0);

    section.getParagraphs().get(9).appendBookmarkStart("Tabl");
    section.getParagraphs().get(10).appendBookmarkEnd("Tabl");

    BookmarksNavigator bn = new BookmarksNavigator(document);
    bn.moveToBookmark("Tabl", true, true);

    Table table = section.getBody().addTable(true);
    table.resetCells(loan.getEquipments().size() + 1, 6);
    bn.insertTable(table);

    TableRow rowHeader = table.getRows().get(0);
    rowHeader.isHeader(true);
    for (int i = 0; i < header.length; i++) {
      rowHeader.getCells().get(i).getCellFormat().setVerticalAlignment(VerticalAlignment.Middle);
      TextRange txtRange = rowHeader.getCells().get(i).addParagraph().appendText(header[i]);
      txtRange.getCharacterFormat().setBold(true);
      txtRange.getCharacterFormat().setFontName("Arial");
      txtRange.getCharacterFormat().setFontSize(10);
    }

    for (int i = 0; i < loan.getEquipments().size(); i++) {
      Equipment equipment = loan.getEquipments().get(i);
      TableRow tb = table.getRows().get(i + 1);

      TextRange text0 = tb.getCells().get(0).addParagraph().appendText(equipment.getName());
      text0.getCharacterFormat().setFontName("Arial");
      text0.getCharacterFormat().setFontSize(10);

      TextRange text1 = tb.getCells().get(1).addParagraph().appendText(
          equipment.getSerialNumber() != null ? equipment.getSerialNumber() : "");
      text1.getCharacterFormat().setFontName("Arial");
      text1.getCharacterFormat().setFontSize(10);

      TextRange text2 = tb.getCells().get(2).addParagraph().appendText(equipment.getCategory().getName());
      text2.getCharacterFormat().setFontName("Arial");
      text2.getCharacterFormat().setFontSize(10);

      TextRange text3 = tb.getCells().get(3).addParagraph().appendText(equipment.getCondition());
      text3.getCharacterFormat().setFontName("Arial");
      text3.getCharacterFormat().setFontSize(10);

      TextRange text4 = tb.getCells().get(4).addParagraph().appendText(loan.getAmount().toString());
      text4.getCharacterFormat().setFontName("Arial");
      text4.getCharacterFormat().setFontSize(10);

      TextRange text5 = tb.getCells().get(5).addParagraph().appendText(equipment.getPrice().toString());
      text5.getCharacterFormat().setFontName("Arial");
      text5.getCharacterFormat().setFontSize(10);
    }

    for (int i = 0; i < table.getRows().getCount(); i++) {
      TableRow row = table.getRows().get(i);
      row.setHeight(15f);
      row.setHeightType(TableRowHeightType.Exactly);
    }

    BigDecimal totalPrice = loan.getEquipments().stream()
        .map(Equipment::getPrice)
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    TableRow lastRow = table.addRow();
    table.applyHorizontalMerge(lastRow.getRowIndex(), 0, 4);
    TextRange totalText = lastRow.getCells().get(0).addParagraph().appendText("Preço Total");
    totalText.getCharacterFormat().setBold(true);
    totalText.getCharacterFormat().setFontName("Arial");
    totalText.getCharacterFormat().setFontSize(10);

    TextRange priceText = lastRow.getCells().get(5).addParagraph()
        .appendText(NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(totalPrice));
    priceText.getCharacterFormat().setFontName("Arial");
    priceText.getCharacterFormat().setFontSize(10);

    String finalFilePath = "";
    String filePath = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    File dir = new File("tmp/" + loan.getType().toLowerCase() + "/" + filePath);
    if (!dir.exists())
      dir.mkdirs();

    finalFilePath = dir + "/" + loan.getReceiver().getRank() + loan.getReceiver().getWarName() + loan.getId() + ".pdf";
    document.saveToFile(finalFilePath, FileFormat.PDF);

    document.dispose();

    return finalFilePath;
  }
}
