package com.patika.bloghubservice.service;

import com.patika.bloghubservice.client.email.dto.request.EmailCreateRequest;
import com.patika.bloghubservice.client.email.dto.request.enums.EmailTemplate;
import com.patika.bloghubservice.client.email.service.EmailClientService;
import com.patika.bloghubservice.client.payment.service.PaymentClientService;
import com.patika.bloghubservice.client.payment.service.dto.request.PaymentRequest;
import com.patika.bloghubservice.converter.BlogTagConverter;
import com.patika.bloghubservice.converter.UserConverter;
import com.patika.bloghubservice.dto.request.BlogTagRequest;
import com.patika.bloghubservice.dto.request.PasswordUpdate;
import com.patika.bloghubservice.dto.request.UserSaveRequest;
import com.patika.bloghubservice.dto.response.UserResponse;
import com.patika.bloghubservice.exception.UserNotFoundException;
import com.patika.bloghubservice.model.User;
import com.patika.bloghubservice.model.enums.StatusType;
import com.patika.bloghubservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final EmailClientService emailClientService;
    private final PaymentClientService paymentClientService;

    public UserResponse saveUser(UserSaveRequest request) {

        User savedUser = new User(request.getEmail(), request.getPassword()); // ödev password' hash'le
        userRepository.save(savedUser);
        emailClientService.sendEmail(new EmailCreateRequest(request.getEmail(), EmailTemplate.CREATE_USER_TEMPLATE));

        return UserConverter.toResponse(savedUser);

    }

    public UserResponse getUserByEmail(String email) {

        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
        return UserConverter.toResponse(user);
    }

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return UserConverter.toResponse(users);
    }

    public void changeStatus(String email, StatusType statusType) {
        Optional<User> foundUser = userRepository.findByEmail(email);

        foundUser.get().setStatusType(statusType);

        paymentClientService.createPayment(new PaymentRequest(new BigDecimal("9.99"),email));

        //userRepository.changeStatus(email, foundUser.get());
    }

    public void changeStatusBulk(List<String> emailList, StatusType statusType) {
        emailList.forEach(email -> changeStatus(email, statusType));
    }

    public Map<String, User> getAllUsersMap() {
        return userRepository.findAll()
                .stream()
                .collect(Collectors.toMap(User::getEmail, Function.identity()));
    }

    public void updatePassword(String email,PasswordUpdate passwordUpdate) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
        user.setPassword(hashPassword(passwordUpdate.password()));
        userRepository.save(user);
    }

    private static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedhash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void addBlogTag(String email, BlogTagRequest tag) {

        User foundUser=userRepository.findByEmail(email).orElseThrow(()->new UserNotFoundException(email));

        foundUser.getFollowedTagList().add(BlogTagConverter.toEntity(tag));

        userRepository.save(foundUser);
    }
}
