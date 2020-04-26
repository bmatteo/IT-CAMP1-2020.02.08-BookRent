package pl.camp.it.services.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.camp.it.config.AppConfigurationTest;
import pl.camp.it.dao.IRentDAO;
import pl.camp.it.dao.IUserDAO;
import pl.camp.it.model.User;
import pl.camp.it.model.UserRole;
import pl.camp.it.services.IUserService;

import static org.mockito.ArgumentMatchers.anyString;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfigurationTest.class})
public class UserServiceImplTest {

    @MockBean
    IRentDAO rentDAO;

    @Autowired
    IUserService userService;

    @Autowired
    IUserDAO userDAO;

    @Test
    public void authenticateTest() {
        String login = "jan";
        String pass = "jan666";
        UserRole expectedUserRole = UserRole.USER;
        int expectedUserId = 1;

        Mockito.when(this.userDAO.getUserByLogin(anyString())).thenReturn(generateUserForTests());

        User userToCheck = this.userService.authenticate(login, pass);

        Assert.assertEquals(login, userToCheck.getLogin());
        Assert.assertEquals(DigestUtils.md5Hex(pass), userToCheck.getPass());
        Assert.assertEquals(expectedUserRole, userToCheck.getRole());
        Assert.assertEquals(expectedUserId, userToCheck.getId());
    }

    private User generateUserForTests() {
        User user = new User();
        user.setId(1);
        user.setRole(UserRole.USER);
        user.setLogin("jan");
        user.setPass("25c58a8593e155d25726d87dbde04106");

        return user;
    }
}
