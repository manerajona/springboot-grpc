package com.manerajona;

import com.manerajona.ports.input.grpc.LoanServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;

public class LoansApp {

    public static void main(String[] args) throws IOException, InterruptedException {

        Server server = ServerBuilder.forPort(9090)
            .addService(new LoanServiceImpl())
            .build();

        server.start();
        System.out.println("gRPC server started in port " + server.getPort());

        server.awaitTermination();
    }
}
