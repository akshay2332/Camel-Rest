package user.repositories;

import edu.stevens.constants.ApplicationConstants;
import edu.stevens.customexceptions.EmailIdAlreadyExistsException;
import edu.stevens.customexceptions.UserIdAlreadyExistsException;
import edu.stevens.mobile.session.user.UserInfo;
import org.apache.camel.Exchange;
import org.apache.camel.util.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.stevens.registration.UserReg;
import user.entities.Authorities;
import user.entities.Users;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.List;


@Repository
public class UserRepository {
    private final Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);

    @PersistenceContext(unitName = "postgresPU")
    private EntityManager entityManager;

    @Transactional
    public void verifyUserIdEmailIdRegistration(Exchange exchange) throws UserIdAlreadyExistsException {
        UserReg userReg = exchange.getIn().getBody(UserReg.class);

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Users> usersCriteriaQuery = criteriaBuilder.createQuery(Users.class);
        Root<Users> usersRoot = usersCriteriaQuery.from(Users.class);
        ParameterExpression<String> userIdParameter = criteriaBuilder.parameter(String.class);
        ParameterExpression<String> emailIdParameter = criteriaBuilder.parameter(String.class);

        usersCriteriaQuery.select(usersRoot).where(criteriaBuilder.or(criteriaBuilder.equal(
                usersRoot.get("userId"), userIdParameter
        ), criteriaBuilder.equal(
                usersRoot.get("email"), emailIdParameter
        )));

        List<Users> usersExist = entityManager.createQuery(usersCriteriaQuery).setParameter(userIdParameter,
                userReg.getUserId()).setParameter(emailIdParameter, userReg.getEmailId()).getResultList();

        if (usersExist.size() != 0) {
            if (userReg.getUserId().equalsIgnoreCase(usersExist.get(0).getUserId())) {
                throw new UserIdAlreadyExistsException(String.format(ApplicationConstants.USER_ID_EXISTS_ERROR_MESSAGE, userReg.getUserId()));
            } else {
                throw new EmailIdAlreadyExistsException(String.format(ApplicationConstants.EMAIL_ID_EXISTS_ERROR_MESSAGE, userReg.getEmailId()));
            }
        }

        exchange.getIn().setBody(userReg, UserReg.class);
    }

    @Transactional
    public void createUserRegistration(Exchange exchange) {
        UserReg userReg = exchange.getIn().getBody(UserReg.class);
        Authorities userAuthorities = exchange.getProperty(ApplicationConstants.AUTHORITIES_OBJECT, Authorities.class);

        Users user = new Users();
        user.setFirstName(userReg.getFirstName());
        user.setLastName(userReg.getLastName());
        user.setNumber(userReg.getMobileNo());
        user.setPassword(userReg.getPassword());
        user.setUserId(userReg.getUserId());
        user.setEmail(userReg.getEmailId());
        user.setAuthorities(userAuthorities);

        entityManager.persist(user);
        exchange.setProperty(ApplicationConstants.EMAIL_OBJECT, userReg.getEmailId());
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("message", "Registration Successful");
        exchange.getIn().setBody(jsonObject);
    }

    @Transactional
    public void checkUserDbAuthentication(Exchange exchange) {

        UserReg userReg = exchange.getIn().getBody(UserReg.class);

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Users> usersCriteriaQuery = criteriaBuilder.createQuery(Users.class);
        Root<Users> usersRoot = usersCriteriaQuery.from(Users.class);
        ParameterExpression<String> userIdParameter = criteriaBuilder.parameter(String.class);
        ParameterExpression<String> passwordParameter = criteriaBuilder.parameter(String.class);

        usersCriteriaQuery.select(usersRoot).where(
                criteriaBuilder.and(
                        criteriaBuilder.equal(
                                usersRoot.get("userId"), userIdParameter
                        ),
                        criteriaBuilder.isTrue(usersRoot.get("status"))
                )
        );

        Users userExist = entityManager.createQuery(usersCriteriaQuery).setParameter(userIdParameter, userReg.getUserId()).getSingleResult();

        UserInfo userSessionInfo = new UserInfo();
        userSessionInfo.setId(userExist.getUserId());
        userSessionInfo.setEmailId(userExist.getEmail());
        userSessionInfo.setFirstName(userExist.getFirstName());
        userSessionInfo.setLastName(userExist.getLastName());
        userSessionInfo.setMobileNo(userExist.getNumber());
        userSessionInfo.setPassword(userExist.getPassword());
        userSessionInfo.setGrantedAuthorityList(Arrays.asList(new SimpleGrantedAuthority(userExist.getAuthorities().getRole())));
        LOGGER.debug("User info | {}", userSessionInfo.getAuthorities().toString());
        exchange.getIn().setBody(userSessionInfo);
    }

    @Transactional
    public void updateUserStatus(Exchange exchange) {
        String emailId = exchange.getIn().getBody(String.class);

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Users> usersCriteriaQuery = criteriaBuilder.createQuery(Users.class);
        Root<Users> usersRoot = usersCriteriaQuery.from(Users.class);
        ParameterExpression<String> emailIdParameter = criteriaBuilder.parameter(String.class);

        usersCriteriaQuery.select(usersRoot).where(
                criteriaBuilder.and(
                        criteriaBuilder.equal(
                                usersRoot.get("email"), emailIdParameter),
                        criteriaBuilder.isFalse(usersRoot.get("status"))
                )
        );

        Users userExist = entityManager.createQuery(usersCriteriaQuery).setParameter(emailIdParameter, emailId).getSingleResult();

        userExist.setStatus(true);
        entityManager.merge(userExist);

        JsonObject jsonObject = new JsonObject();
        jsonObject.put("message", "Registration Updated");
        exchange.getIn().setBody(jsonObject);

    }
}
