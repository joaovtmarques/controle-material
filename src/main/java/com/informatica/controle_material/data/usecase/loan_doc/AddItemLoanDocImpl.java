package com.informatica.controle_material.data.usecase.loan_doc;

import java.io.File;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.informatica.controle_material.domain.model.Item;
import com.informatica.controle_material.domain.model.Loan;
import com.informatica.controle_material.domain.usecases.loan_doc.AddLoanDocUseCase;
import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.Section;
import com.spire.doc.Table;
import com.spire.doc.TableRow;
import com.spire.doc.documents.BookmarksNavigator;
import com.spire.doc.documents.HorizontalAlignment;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.documents.VerticalAlignment;
import com.spire.doc.fields.TextRange;

@Service
public class AddItemLoanDocImpl implements AddLoanDocUseCase {

  @Override
  public String execute(Loan loan) {
    Document document = new Document("src/main/resources/loan-items-form.docx");
    String[] header = {"Material", "Número de série", "Tipo", "Condição", "Quantidade", "Preço"};

    document.replace("{receiver}", loan.getReceiver().getName(), false, true);
    document.replace("{cpf}", loan.getReceiver().getCpf(), false, true);
    document.replace("{date}", loan.getDate(), false, true);
    document.replace("{rank}", loan.getReceiver().getRank(), false, true);
    document.replace("{warName}", loan.getReceiver().getWarName(), false, true);
    document.replace("{lenderRank}", loan.getLender().getRank(), false, true);
    document.replace("{lender}", loan.getLender().getWarName(), false, true);
    document.replace("{observation}", loan.getObservation(), false, true);

    Section section = document.getSections().get(0);
    Paragraph paragraphStart = section.getParagraphs().get(9);
    Paragraph paragraphEnd = section.getParagraphs().get(10);
    paragraphStart.appendBookmarkStart("Tabl");
    paragraphEnd.appendBookmarkEnd("Tabl");

    BookmarksNavigator bn = new BookmarksNavigator(document);
    bn.moveToBookmark("Tabl", true, true);
    Table table=section.getBody().addTable(true);
    table.resetCells(loan.getItems().size(), 6);
    bn.insertTable(table);
    TableRow rowHeader = table.getRows().get(0);
    rowHeader.isHeader(true);

    for (int i = 0; i < header.length; i++) {
      rowHeader.getCells().get(i).getCellFormat().setVerticalAlignment(VerticalAlignment.Middle);
      Paragraph p = rowHeader.getCells().get(i).addParagraph();
      p.getFormat().setHorizontalAlignment(HorizontalAlignment.Left);
      TextRange txtRange = p.appendText(header[i]);
      txtRange.getCharacterFormat().setBold(true);
    }

    Double totalPrice = 0D;

    for (Item item : loan.getItems()) {
      TableRow dataRow = table.addRow();
      TableRow tb = table.getRows().get(dataRow.getRowIndex());
      tb.getCells().get(0).addParagraph().appendText(item.getName());
      if(item.getSerialNumber() != null) {
          tb.getCells().get(1).addParagraph().appendText(item.getSerialNumber());
      }
      tb.getCells().get(2).addParagraph().appendText(item.getCategory().getName());
      tb.getCells().get(3).addParagraph().appendText(item.getCondition());
      tb.getCells().get(4).addParagraph().appendText(loan.getAmount().toString());
      tb.getCells().get(5).addParagraph().appendText(item.getPrice().toString());
      totalPrice += Double.parseDouble(item.getPrice());
    }
  
    DecimalFormatSymbols separator = new DecimalFormatSymbols();
    separator.setDecimalSeparator('.');
    DecimalFormat formatter = new DecimalFormat("#00.000", separator);
    table.addRow();
    TableRow lastRow = table.addRow();
    table.applyHorizontalMerge(lastRow.getRowIndex(), 0, 4);
    lastRow.getCells().get(0).addParagraph().appendText("Preço Total").getCharacterFormat().setBold(true);
    lastRow.getCells().get(0).getLastParagraph();
    lastRow.getCells().get(5).addParagraph().appendText("R$ "+formatter.format(totalPrice));

    String finalFilePath = "";
    String filePath = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    
    File dirPelcom = new File("tmp/pelcom/"+filePath);
    File dirInformatica = new File("tmp/informatica/"+filePath);
    if (!dirPelcom.exists()) dirPelcom.mkdirs();
    if (!dirInformatica.exists()) dirInformatica.mkdirs();

    if(loan.getType().equals("PELCOM")) {
      finalFilePath = dirPelcom+"/"+loan.getReceiver().getRank()+loan.getReceiver().getWarName()+loan.getId()+".docx";
      document.saveToFile(dirPelcom+"/"+loan.getReceiver().getRank()+loan.getReceiver().getWarName()+loan.getId()+".docx", FileFormat.Docx);
    }
    if(loan.getType().equals("INFORMATICA")) {
      finalFilePath = dirInformatica+"/"+loan.getReceiver().getRank()+loan.getReceiver().getWarName()+loan.getId()+".docx";
      document.saveToFile(dirInformatica+"/"+loan.getReceiver().getRank()+loan.getReceiver().getWarName()+loan.getId()+".docx", FileFormat.Docx);
    }

    document.dispose();
    
    return finalFilePath;
  }

}
