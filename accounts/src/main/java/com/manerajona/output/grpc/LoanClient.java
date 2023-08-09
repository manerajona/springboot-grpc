package com.manerajona.output.grpc;

import com.manerajona.loans.common.grpc.Loan;
import com.manerajona.loans.common.grpc.LoanId;
import com.manerajona.loans.common.grpc.LoanServiceGrpc;
import com.manerajona.loans.common.grpc.LoanServiceGrpc.LoanServiceBlockingStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class LoanClient {

    private static final ManagedChannel channel =
        ManagedChannelBuilder.forAddress("localhost", 9090)
            .build();

    private static final LoanServiceBlockingStub blockingStub =
        LoanServiceGrpc.newBlockingStub(channel);

    public LoanId createLoan(Loan loan) {
        String guid = blockingStub.createLoan(loan).getGuid();
        return LoanId.newBuilder().setGuid(guid).build();
    }

    public Loan getLoan(LoanId id) {
        return blockingStub.readLoan(id);
    }

}