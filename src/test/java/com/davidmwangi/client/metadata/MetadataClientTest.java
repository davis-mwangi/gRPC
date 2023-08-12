package com.davidmwangi.client.metadata;

import com.davidmwangi.client.deadline.DeadlineInterceptor;
import com.davidmwangi.client.rpctypes.MoneyStreamingResponse;
import com.davidmwangi.models.Balance;
import com.davidmwangi.models.BalanceCheckRequest;
import com.davidmwangi.models.BankServiceGrpc;
import com.davidmwangi.models.WithdrawRequest;
import com.google.common.util.concurrent.Uninterruptibles;
import io.grpc.Deadline;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.MetadataUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MetadataClientTest {
    private BankServiceGrpc.BankServiceBlockingStub blockingStub;
    private BankServiceGrpc.BankServiceStub bankServiceStub;

    @BeforeAll
    public void setUp() {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 6565)
                .intercept(MetadataUtils.newAttachHeadersInterceptor(ClientConstants.getClientToken()))
                .usePlaintext()
                .build();
        this.blockingStub = BankServiceGrpc.newBlockingStub(managedChannel);
        this.bankServiceStub = BankServiceGrpc.newStub(managedChannel);
    }

    @Test
    public void balanceTest() {
        BalanceCheckRequest balanceCheckRequest = BalanceCheckRequest.newBuilder()
                .setAccountNumber(5)
                .build();
        for (int i = 0; i < 20; i++) {
            try {
                int random = ThreadLocalRandom.current().nextInt(1,4);
                Balance balance = this.blockingStub
//                    .withDeadline(Deadline.after(2, TimeUnit.SECONDS))
                        .withCallCredentials(new UserSessionToken("user-secret-"+random+":standard"))
                        .getBalance(balanceCheckRequest);
                System.out.println("Received:: " + balance.getAmount());

            } catch (StatusRuntimeException e) {
                e.printStackTrace();
                //Go with Default values
                System.out.println( e.getLocalizedMessage());
            }
        }

    }

    @Test
    public void withdrawTest() {
        WithdrawRequest withdrawRequest = WithdrawRequest.newBuilder()
                .setAccountNumber(6)
                .setAmount(50).build();
        this.blockingStub
                .withDeadline(Deadline.after(2, TimeUnit.SECONDS))
                .withdraw(withdrawRequest)
                .forEachRemaining(money -> System.out.println("Received :" + money.getValue()));
    }

    @Test
    public void withdrawAsyncTest() {
        WithdrawRequest withdrawRequest = WithdrawRequest.newBuilder().setAccountNumber(10).setAmount(50).build();
        this.bankServiceStub.withdraw(withdrawRequest, new MoneyStreamingResponse());
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
    }


}
