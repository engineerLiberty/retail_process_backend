package com.grpcretails.grpcretailprocess.UserService;

import com.grpcretails.grpcretailprocess.LoginAndRegistrationServiceGrpc;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginAndRegistrationService extends LoginAndRegistrationServiceGrpc.LoginAndRegistrationServiceImplBase {
}
