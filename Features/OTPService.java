package com.emailsender;

import com.vonage.client.VonageClient;
import com.vonage.client.sms.MessageStatus;
import com.vonage.client.sms.SmsSubmissionResponse;
import com.vonage.client.sms.messages.TextMessage;

import java.util.Random;

public class OTPService {

    // Nexmo Credentials
    public static final String API_KEY = "857e174c";
    public static final String API_SECRET = "zcC4kHO5eDv9uiE4";
    public static final String VONAGE_PHONE_NUMBER = "916382862001";

    public static boolean sendOTP(String toPhoneNumber,String otp) {
        try {
            // Initialize Nexmo client
            VonageClient client = VonageClient.builder()
                .apiKey(API_KEY)
                .apiSecret(API_SECRET)
                .build();

            // Generate OTP
            
            System.out.println("Generated OTP: " + otp);

            // Create a text message
            TextMessage message = new TextMessage(VONAGE_PHONE_NUMBER, toPhoneNumber, "Your OTP is: " + otp);

            // Send the message
            SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

            // Check the response status
            if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
                System.out.println("OTP sent successfully!");
                return true;
            } else {
                System.out.println("Failed to send OTP: " + response.getMessages().get(0).getErrorText());
                return false;
            }
        } catch (Exception e) {
            System.err.println("Error while sending OTP: " + e.getMessage());
            return false;
        }
    }

    public static String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

}
