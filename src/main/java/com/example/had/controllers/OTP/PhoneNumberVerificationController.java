package com.example.had.controllers.OTP;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

//import static com.twilio.example.ValidationExample.ACCOUNT_SID;
//import static com.twilio.example.ValidationExample.AUTH_TOKEN;

@RestController
@RequestMapping(path = "api/phoneNumber")
@Slf4j
@CrossOrigin(origins = "*")
public class PhoneNumberVerificationController {
    @GetMapping(value = "/generateOTP/{phoneNo}")
    public ResponseEntity<String> generateOTP(@PathVariable String phoneNo){

        Twilio.init("AC6cab6beb85193a397979b614b2824ddb", "a6cac67315b0e621760d70df5d193a7e");

        Verification verification = Verification.creator(
                        "VAce50c9a76e09d71859b78544bb1fcc4f", // this is your verification sid
                        phoneNo, //this is your Twilio verified recipient phone number
                        "sms") // this is your channel type
                .create();

        System.out.println(verification.getStatus());

        log.info("OTP has been successfully generated, and awaits your verification {}", LocalDateTime.now());

        return new ResponseEntity<>("Your OTP has been sent to your verified phone number", HttpStatus.OK);
    }

    @GetMapping("/verifyOTP/{otp}/{phoneNo}")
    public ResponseEntity<?> verifyUserOTP(@PathVariable String otp, @PathVariable String phoneNo) throws Exception {
        Twilio.init("AC6cab6beb85193a397979b614b2824ddb", "a6cac67315b0e621760d70df5d193a7e");
        try {

            VerificationCheck verificationCheck = VerificationCheck.creator(
                            "VAce50c9a76e09d71859b78544bb1fcc4f")
                    .setTo(phoneNo)
                    .setCode(otp)
                    .create();

            System.out.println(verificationCheck.getStatus());

            if(verificationCheck.getStatus().equals("approved")){
                return new ResponseEntity<>("This user's verification has been completed successfully", HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("Verification failed.", HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            return new ResponseEntity<>("Verification failed.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/sendSMS/{phoneNo}/verificationNo/{verificationNo}")
    public ResponseEntity<String> sendSMS(@PathVariable String phoneNo ,@PathVariable String verificationNo) {

        Twilio.init("AC8a446114f4673e85a4d0969bef749872", "26fec6cfd6dafcc9c1ba6cf218a32631");
//        PhoneNumber to = new PhoneNumber(phoneNo);
//        PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNumber());

        String otpMessage = "Dear Patient , Your OTP is " + verificationNo + ".";
        Message message = Message.creator(new PhoneNumber(phoneNo), new PhoneNumber("+14345954775"),
                otpMessage).create();

        return new ResponseEntity<String>("Message sent successfully", HttpStatus.OK);
    }

}