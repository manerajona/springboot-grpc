package com.manerajona;

import com.manerajona.loans.common.grpc.Borrower;
import com.manerajona.loans.common.grpc.Loan;
import com.manerajona.loans.common.grpc.LoanId;
import com.manerajona.output.grpc.LoanClient;
import java.util.UUID;

public class AccountsApp {

    public static void main(String[] args) {

        LoanClient client = new LoanClient();

        // create
        var borrowerBuilder = Borrower.newBuilder()
            .setName("John Doe")
            .setAge(34)
            .setAnnualIncome(100_000.);

        var loanBuilder = Loan.newBuilder()
            .setGuid(UUID.randomUUID().toString())
            .setBorrower(borrowerBuilder.build())
            .setAnnualInterest(5)
            .setTermMonths(24)
            .setRequestedAmount(1_000_000_000);

        LoanId id = client.createLoan(
            loanBuilder.build()
        );

        System.out.println("Loan created with guid=" + id.getGuid());

        // read
        Loan loan = client.getLoan(id);

        System.out.println("Loan=" + loan);
    }
}
