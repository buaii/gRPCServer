import com.grpc.UserSaveRequest;
import com.grpc.UserSaveResponse;
import com.grpc.UserServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class Client {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        UserServiceGrpc.UserServiceBlockingStub stub = UserServiceGrpc.newBlockingStub(channel);

        UserSaveRequest saveRequest = UserSaveRequest.newBuilder()
                .setName("KIMJEONGHO")
                .build();

        UserSaveResponse saveResponse = stub.saveUser(saveRequest);

        System.out.println("User saved: " + saveResponse.getSuccess());

        channel.shutdown();
    }
}
