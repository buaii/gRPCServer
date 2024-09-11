import com.grpc.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class Client {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        UserServiceGrpc.UserServiceBlockingStub stub = UserServiceGrpc.newBlockingStub(channel);

        UserGetRequest getRequest = UserGetRequest.newBuilder()
                .setId(2)
                .build();

        UserGetResponse getResponse = stub.getUser(getRequest);

        System.out.println(getResponse.getName());

        channel.shutdown();
    }
}
