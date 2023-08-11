package com.manerajona.configuration;

import com.manerajona.common.grpc.loans.LoansServiceGrpc;
import com.manerajona.common.grpc.loans.LoansServiceGrpc.LoansServiceBlockingStub;
import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class GrpcClientConfiguration {

    @Bean
    LoansServiceBlockingStub loansServiceStub() {
        Channel channel = ManagedChannelBuilder.forAddress("localhost", 6565)
            .usePlaintext()
            .build();

        return LoansServiceGrpc.newBlockingStub(channel);
    }

}
