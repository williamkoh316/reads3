
import java.io.*;
import java.util.List;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.List;
import java.util.stream.Collectors;


@SpringBootApplication
class Application {


    private static  AWSCredentials credentials = new BasicSessionCredentials(
            "awsAccessKey",
            "awsSecretKey",
            "awsSessionToken"
    );


    public static void main(String[] args) throws IOException {

        String bucketName ="cfms-s3";

        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.AP_SOUTHEAST_1)
                .build();

//        List<Bucket> buckets = s3client.listBuckets();
//        for(Bucket bucket : buckets) {
//            System.out.println(bucket.getName());
//        }

        S3Object s3object = s3client.getObject(bucketName, "encry.txt");
        S3ObjectInputStream inputStream = s3object.getObjectContent();


        String result = new BufferedReader(new InputStreamReader(inputStream))
                .lines().collect(Collectors.joining("\n"));

        System.out.println("WHAT IS INSIDE: " + result );
        //Files.copy(inputStream, new File("/Users/littlestar/Desktop/cfms/hw.txt").toPath()); //location to local path


        inputStream.close();
    }
}
