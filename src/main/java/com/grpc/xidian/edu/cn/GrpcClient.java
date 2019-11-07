package com.grpc.xidian.edu.cn;

import com.proto.xidian.edu.cn.MyRequest;
import com.proto.xidian.edu.cn.MyResponse;
import com.proto.xidian.edu.cn.StudentServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 5532)
                .usePlaintext(true).build();

        StudentServiceGrpc.StudentServiceBlockingStub blockingStub = StudentServiceGrpc.newBlockingStub(channel);
        MyResponse response = blockingStub.getRealNameByUsername(MyRequest.newBuilder().setUsername("zhangsan").build());
        System.out.println(response.getRealname());
    }
}
