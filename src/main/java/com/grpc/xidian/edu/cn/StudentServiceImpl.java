package com.grpc.xidian.edu.cn;

import com.proto.xidian.edu.cn.MyRequest;
import com.proto.xidian.edu.cn.MyResponse;
import com.proto.xidian.edu.cn.StudentServiceGrpc;
import io.grpc.stub.StreamObserver;

public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {

    @Override
    public void getRealNameByUsername(MyRequest request, StreamObserver<MyResponse> responseObserver) {
        System.out.println("receive client info:" + request.getUsername());

        responseObserver.onNext(MyResponse.newBuilder().setRealname("zhangsan").build());
        responseObserver.onCompleted();
    }
}
