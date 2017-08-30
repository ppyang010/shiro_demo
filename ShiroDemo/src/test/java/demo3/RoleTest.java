package demo3;

import org.apache.shiro.authz.UnauthorizedException;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by s on 2017/8/30.
 * 基于角色的访问控制
 */
public class RoleTest extends BaseTest{
    @Test
    public void testHasRole(){
        login("classpath:demo3/shiro-role.ini", "zhang", "123");
        //判断是否拥有角色：role1;
        Assert.assertTrue(subject().hasRole("role1"));
        //判断拥有角色：role1 and role2 需要全部拥有
        Assert.assertTrue(subject().hasAllRoles(Arrays.asList("role1", "role2")));
        //判断拥有角色：role1 and role2 and !role3
        //返回boolean 类型数组 返回集合中的每个角色的匹配结果
        boolean[] result = subject().hasRoles(Arrays.asList("role1", "role2", "role3"));
        Assert.assertEquals(true, result[0]);
        Assert.assertEquals(true, result[1]);
        Assert.assertEquals(false, result[2]);
    }
    @Test(expected = UnauthorizedException.class)
    public void testCheckRole() {
        login("classpath:demo3/shiro-role.ini", "zhang", "123");
        //断言拥有角色：role1
        //同上述代码的区别为hasrole返回true和false  而checkrole 如果不匹配会抛出异常
        subject().checkRole("role1");
        //断言拥有角色：role1 and role3 失败抛出异常
        subject().checkRoles("role1", "role3");
    }

}
