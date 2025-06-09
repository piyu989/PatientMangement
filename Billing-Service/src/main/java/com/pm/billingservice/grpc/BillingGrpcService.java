package com.pm.billingservice.grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class BillingGrpcService extends BillingServiceGrpc.BillingServiceImplBase {

    @Override
    public void createBillingAccount(BillingRequest request, StreamObserver<BillingResponse> responseObserver) {

        // Simulate some logic
        String accountId = "ACC-" + request.getPatientId(); // or call DB, etc.

        BillingResponse response = BillingResponse.newBuilder()
                .setAccountId(accountId)
                .setStatus("SUCCESS")
                .build();

        // Send response
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
