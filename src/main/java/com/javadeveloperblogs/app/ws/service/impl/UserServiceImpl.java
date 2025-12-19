package com.javadeveloperblogs.app.ws.service.impl;

import com.javadeveloperblogs.app.ws.exception.UserServiceException;
import com.javadeveloperblogs.app.ws.io.Repository.PasswordResetTokenRepository;
import com.javadeveloperblogs.app.ws.io.Repository.RoleRepository;
import com.javadeveloperblogs.app.ws.io.Repository.UserRepository;
import com.javadeveloperblogs.app.ws.io.entity.PasswordResetTokenEntity;
import com.javadeveloperblogs.app.ws.io.entity.UserEntity;
import com.javadeveloperblogs.app.ws.service.UserService;
import com.javadeveloperblogs.app.ws.shared.Utils;
import com.javadeveloperblogs.app.ws.shared.dto.AddressDTO;
import com.javadeveloperblogs.app.ws.shared.dto.UserDto;
import com.javadeveloperblogs.app.ws.ui.model.request.MailRequest;
import com.javadeveloperblogs.app.ws.ui.model.response.ErrorMessages;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.javadeveloperblogs.app.ws.io.entity.RoleEntity;

import java.io.IOException;
import java.util.*;
import java.util.List;
/**
 * Default implementation of the {@link UserService} interface.
 *
 * <p>This service class provides the concrete implementation for all user-related
 * business operations including user lifecycle management, authentication workflows,
 * email verification processes, and administrative functions.</p>
 *
 * <h2>Implementation Overview:</h2>
 * <p>This class serves as the core business logic layer for user management, coordinating
 * between the presentation layer (controllers) and the data access layer (repositories).
 * It handles:</p>
 * <ul>
 *   <li>Data validation and business rule enforcement</li>
 *   <li>Coordination with authentication and authorization services</li>
 *   <li>Email notification triggers for verification and password resets</li>
 *   <li>Token generation and validation for secure operations</li>
 *   <li>Data transformation between entity models and DTOs</li>
 * </ul>
 *
 * <h2>Key Dependencies:</h2>
 * <ul>
 *   <li><b>UserRepository:</b> Data access for user entities</li>
 *   <li><b>PasswordEncoder:</b> Secure password hashing and validation</li>
 *   <li><b>TokenService:</b> JWT token generation and validation</li>
 *   <li><b>EmailService:</b> Email notifications for verification and password resets</li>
 *   <li><b>ModelMapper:</b> DTO to entity mapping and vice versa</li>
 * </ul>
 *
 * <h2>Transaction Management:</h2>
 * <p>Methods that modify data should be annotated with {@code @Transactional} to ensure
 * data consistency and proper rollback behavior on errors.</p>
 *
 * <h2>Exception Handling:</h2>
 * <p>This implementation should throw appropriate custom exceptions (e.g.,
 * UserNotFoundException, InvalidCredentialsException, EmailAlreadyExistsException)
 * which will be handled by a global exception handler.</p>
 *
 * <h2>Security Considerations:</h2>
 * <ul>
 *   <li>Passwords must be hashed before storage using a strong hashing algorithm</li>
 *   <li>Tokens should be validated and checked for expiration</li>
 *   <li>User permissions should be verified before sensitive operations</li>
 *   <li>Email verification tokens should be single-use and time-limited</li>
 *   <li>Rate limiting should be considered for authentication endpoints</li>
 * </ul>
 *
 * <h2>Performance Considerations:</h2>
 * <ul>
 *   <li>Bulk operations should be optimized using batch processing</li>
 *   <li>Search queries should leverage database indexing</li>
 *   <li>Pagination should be implemented for large result sets</li>
 *   <li>Consider caching for frequently accessed user profiles</li>
 * </ul>
 *
 * @author Nasim Sarwar
 * @version 1.0
 * @since 2025
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    PasswordResetTokenRepository passwordResetTokenRepository;



    @Autowired
    RoleRepository roleRepository;


    @Override
    public UserDto createUser(UserDto user) {

        if (userRepository.findByEmail(user.getEmail()) != null)
            throw new UserServiceException("Record already exists");

        for(int i=0;i<user.getAddresses().size();i++)
        {
            AddressDTO address = user.getAddresses().get(i);
            address.setUserDetails(user);
            address.setAddressId(utils.generateAddressId(30));
            user.getAddresses().set(i, address);
        }

        //BeanUtils.copyProperties(user, userEntity);
        ModelMapper modelMapper = new ModelMapper();
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);

        String publicUserId = utils.generateUserId(30);
        userEntity.setUserId(publicUserId);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userEntity.setEmailVerificationToken(utils.generateEmailVerificationToken(publicUserId));

        // Set roles
        Collection<RoleEntity> roleEntities = new HashSet<>();
        for(String role: user.getRoles()) {
            RoleEntity roleEntity = roleRepository.findByName(role);
            if(roleEntity !=null) {
                roleEntities.add(roleEntity);
            }
        }

        userEntity.setRoles(roleEntities);

        UserEntity storedUserDetails = userRepository.save(userEntity);

        //BeanUtils.copyProperties(storedUserDetails, returnValue);
        UserDto returnValue  = modelMapper.map(storedUserDetails, UserDto.class);

        // Send an email message to user to verify their email address
        //amazonSES.verifyEmail(returnValue);

        return returnValue;
    }

    /**
     * Get user by email
     */
    @Override
    public UserDto getUser(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        return new ModelMapper().map(userEntity, UserDto.class);
    }

    /**
     * Get user by userId
     */
    @Override
    public UserDto getUserByUserId(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        return new ModelMapper().map(userEntity, UserDto.class);
    }


    /**
     * Update existing user's first name and last name
     */
    @Override
    public UserDto updateUser(String userId, UserDto userDto) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null)
            throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        UserEntity storedUserEntity = userRepository.save(userEntity);

        return new ModelMapper().map(storedUserEntity, UserDto.class);
    }


    /**
     * Delete a user by userId
     */
    @Override
    public void deleteUser(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        userRepository.delete(userEntity);
    }

    /**
     * Get paginated list of users
     */
    @Override
    public List<UserDto> getUsers(int page, int limit) {
        List<UserDto> returnValue = new ArrayList<>();
        if (page > 0) page = page - 1;

        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<UserEntity> usersPage = userRepository.findAll(pageableRequest);
        List<UserEntity> users = usersPage.getContent();

        for (UserEntity userEntity : users) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(userEntity, userDto);
            returnValue.add(userDto);
        }
        return returnValue;
    }
    /**
     * Verify email using token
     */
    @Override
    public boolean verifyEmailToken(String token) {
        boolean returnValue = false;

        // Find user by verification token
        UserEntity userEntity = userRepository.findUserByEmailVerificationToken(token);
        if (userEntity != null) {
            boolean tokenExpired = Utils.hasTokenExpired(token);
            if (!tokenExpired) {
                userEntity.setEmailVerificationToken(null);
                userEntity.setEmailVerificationStatus(Boolean.TRUE);
                userRepository.save(userEntity);
                returnValue = true;
            }
        }
        return returnValue;
    }


    /**
     * Request password reset by generating token and sending email
     */
    @Override
    public boolean requestPasswordReset(String email) {
        boolean returnValue = false;

        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) return returnValue;

        // Generate token and save in DB
        String token = utils.generatePasswordResetToken(userEntity.getUserId());
        PasswordResetTokenEntity passwordResetTokenEntity = new PasswordResetTokenEntity();
        passwordResetTokenEntity.setToken(token);
        passwordResetTokenEntity.setUserDetails(userEntity);
        passwordResetTokenRepository.save(passwordResetTokenEntity);

        // Prepare email
        MailRequest request = new MailRequest();
        request.setFirstName(userEntity.getFirstName());
        request.setLastName(userEntity.getLastName());
        request.setTo(userEntity.getEmail());
        request.setFrom("atoztraders39@gmail.com");
        request.setSubject("PASSWORD RESET");

        Map<String, Object> model = new HashMap<>();
        model.put("firstName", userEntity.getFirstName());
        model.put("lastName", userEntity.getLastName());
        model.put("location", "Bangalore,India");
        model.put("TOKEN", token);

        try {
           /* Template t = config.getTemplate("password-reset-template.ftl");
            service.sendEmail(request, model, t);
            Template tem = config.getTemplate("email-template.ftl");
            request.setSubject("Login_System");
            service.sendEmail(request, model, tem);*/
        } catch (Exception e) {
            throw new RuntimeException("Mail Sending failure: " + e.getMessage());
        }

        return returnValue;
    }
    /**
     * Reset user password using token
     */
    @Override
    public boolean resetPassword(String token, String password) {
        boolean returnValue = false;

        if (Utils.hasTokenExpired(token)) return returnValue;

        PasswordResetTokenEntity passwordResetTokenEntity = passwordResetTokenRepository.findByToken(token);
        if (passwordResetTokenEntity == null) return returnValue;

        String encodedPassword = bCryptPasswordEncoder.encode(password);
        UserEntity userEntity = passwordResetTokenEntity.getUserDetails();
        userEntity.setEncryptedPassword(encodedPassword);
        UserEntity savedUserEntity = userRepository.save(userEntity);

        if (savedUserEntity != null && savedUserEntity.getEncryptedPassword().equalsIgnoreCase(encodedPassword)) {
            returnValue = true;
        }

        passwordResetTokenRepository.delete(passwordResetTokenEntity);

        return returnValue;
    }


    /**
     * Load user by username for Spring Security authentication
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(username);
        if (userEntity == null)
            throw new UsernameNotFoundException("User does not exist");
        return new User(username, userEntity.getEncryptedPassword(), new ArrayList<>());
    }
}
