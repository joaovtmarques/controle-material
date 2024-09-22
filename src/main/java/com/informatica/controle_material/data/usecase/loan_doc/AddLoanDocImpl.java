package com.informatica.controle_material.data.usecase.loan_doc;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.informatica.controle_material.domain.model.Loan;
import com.informatica.controle_material.domain.usecases.loan_doc.AddLoanDocUseCase;

@Service
public class AddLoanDocImpl implements AddLoanDocUseCase {

  @Override
  public void execute(Loan loan) {
    Document document = new Document("src/main/resources/loan-form.docx");

    document.replace("{receiver}", loan.getReceiver().getName(), false, true);
    document.replace("{cpf}", loan.getReceiver().getCpf(), false, true);
    document.replace("{date}", loan.getDate(), false, true);
    document.replace("{rank}", loan.getReceiver().getRank(), false, true);
    document.replace("{warName}", loan.getReceiver().getWarName(), false, true);
    document.replace("{lenderRank}", loan.getLender().getRank(), false, true);
    document.replace("{lender}", loan.getLender().getWarName(), false, true);

    if(loan.getDevolutionDate() != null) {
      document.replace("{devolutionDate}", loan.getDevolutionDate(), false, true);
    } else {
      document.replace("{devolutionDate}", "", false, true);
    }

    if(loan.getEquipment() != null) {
      document.replace("{name}", loan.getEquipment().getName(), false, true);
      if(loan.getEquipment().getSerialNumber() != null) {
        document.replace("{serial_number}", loan.getEquipment().getSerialNumber(), false, true);
      }
      document.replace("{serialNumber}", "Sem número de série", false, true);
      document.replace("{category}", loan.getEquipment().getCategory().getName(), false, true);
      document.replace("{condition}", loan.getEquipment().getCondition(), false, true);
      document.replace("{amount}", loan.getAmount().toString(), false, true);
      document.replace("{observation}", loan.getObservation(), false, true);
      document.replace("{price}", loan.getEquipment().getPrice().toString(), false, true);
    }

    if(loan.getItem() != null) { 
      document.replace("{name}", loan.getItem().getName(), false, true);
      if(loan.getItem().getSerialNumber() != null) {
        document.replace("{serial_number}", loan.getItem().getSerialNumber(), false, true);
      }
      document.replace("{serialNumber}", "Sem número de série", false, true);
      document.replace("{category}", loan.getItem().getCategory().getName(), false, true);
      document.replace("{condition}", loan.getItem().getCondition(), false, true);
      document.replace("{amount}", loan.getAmount().toString(), false, true);
      document.replace("{observation}", loan.getObservation(), false, true);
      document.replace("{price}", loan.getItem().getPrice().toString(), false, true);
    }

    String filePath = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    
    File dirPelcom = new File("src/main/resources/tmp/pelcom/"+filePath);
    File dirInformatica = new File("src/main/resources/tmp/informatica/"+filePath);
    if (!dirInformatica.exists()) dirInformatica.mkdirs();
    if (!dirPelcom.exists()) dirPelcom.mkdirs();

    if(loan.getType().equals("PELCOM")) {
      document.saveToFile(dirPelcom+"/"+loan.getReceiver().getRank()+loan.getReceiver().getWarName() + ".docx", FileFormat.Docx);
    } 
    if(loan.getType().equals("INFORMATICA")) {
      document.saveToFile(dirInformatica+"/"+loan.getReceiver().getRank()+loan.getReceiver().getWarName() + ".docx", FileFormat.Docx);
    }
  }

}
