package com.manerajona;

import com.manerajona.common.grpc.loans.Borrower;
import com.manerajona.common.grpc.loans.Loan;
import com.manerajona.common.grpc.loans.LoanId;
import com.manerajona.common.grpc.loans.LoansServiceGrpc.LoansServiceBlockingStub;
import java.util.UUID;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AccountsApplicationRunner implements ApplicationRunner {

    private final LoansServiceBlockingStub stub;

    public AccountsApplicationRunner(LoansServiceBlockingStub stub) {
        this.stub = stub;
    }

    @Override
    public void run(ApplicationArguments args) {
        // create
        var borrowerBuilder = Borrower.newBuilder()
            .setName("John Doe")
            .setAge(34)
            .setAnnualIncome(100_000);

        var loanBuilder = Loan.newBuilder()
            .setGuid(UUID.randomUUID().toString())
            .setBorrower(borrowerBuilder.build())
            .setRequestedAmount(1_000_000_000)
            .setTermMonths(12)
            .setAnnualInterest(6.5f);

        LoanId id = stub.createLoan(loanBuilder.build());
        System.out.println("** Loan created with " + id);

        // read
        System.out.println("** Loan read:\n" + stub.readLoan(id));

        // update
        Loan loanUpdated = loanBuilder
            .setTermMonths(24)
            .setAnnualInterest(7.5f)
            .build();

        stub.renegotiateLoan(loanUpdated);
        System.out.println("** Loan updated:\n" + stub.readLoan(id));

        // delete
        stub.deleteLoan(id);
        System.out.println("** Is Loan deleted: " +
            stub.readLoan(id).getGuid().isBlank());
    }
}
