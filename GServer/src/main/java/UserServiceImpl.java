
import com.grpc.*;
import io.grpc.stub.StreamObserver;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {

    private final Map<Integer, String> userDatabase = new HashMap<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);

    @Override
    public void saveUser(UserSaveRequest request, StreamObserver<UserSaveResponse> responseObserver) {
        String name = request.getName();
        int id = idCounter.getAndIncrement();
        userDatabase.put(id, name);

        UserSaveResponse response = UserSaveResponse.newBuilder()
                .setSuccess(true)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getUser(UserGetRequest request, StreamObserver<UserGetResponse> responseObserver) {
        int id = request.getId();
        String name = userDatabase.getOrDefault(id, "User not found");

        UserGetResponse response = UserGetResponse.newBuilder()
                .setName(name)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
