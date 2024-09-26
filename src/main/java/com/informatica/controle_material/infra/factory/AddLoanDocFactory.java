package com.informatica.controle_material.infra.factory;

import org.springframework.stereotype.Component;

import com.informatica.controle_material.data.exception.BadRequestException;
import com.informatica.controle_material.data.usecase.loan_doc.AddEquipmentLoanDocImpl;
import com.informatica.controle_material.data.usecase.loan_doc.AddItemLoanDocImpl;
import com.informatica.controle_material.domain.model.Loan;
import com.informatica.controle_material.domain.usecases.loan_doc.AddLoanDocUseCase;

@Component
public class AddLoanDocFactory {
  
  private final AddEquipmentLoanDocImpl addEquipmentLoanDocImpl;
  private final AddItemLoanDocImpl addItemLoanDocImpl;

  public AddLoanDocFactory(AddEquipmentLoanDocImpl addEquipmentLoanDocImpl, AddItemLoanDocImpl addItemLoanDocImpl) {
    this.addEquipmentLoanDocImpl = addEquipmentLoanDocImpl;
    this.addItemLoanDocImpl = addItemLoanDocImpl;
  }

  public AddLoanDocUseCase getAddLoanDoc(Loan loan) {
    if (loan.getEquipments() != null) {
      return addEquipmentLoanDocImpl;
    } else if (loan.getItems() != null) {
      return addItemLoanDocImpl;
    } else {
      throw new BadRequestException("Nenhum equipamento ou item encontrado para o empréstimo.");
    }
  }

}
