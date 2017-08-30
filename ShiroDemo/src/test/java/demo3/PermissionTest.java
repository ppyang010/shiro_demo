package demo3;

import org.apache.shiro.authz.UnauthorizedException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by s on 2017/8/30.
 * 基于角色的访问控制/权限校验
 */
public class PermissionTest extends BaseTest {
    @Test
    public void testIsPermitted() {
        login("classpath:demo3/shiro-permission.ini", "zhang", "123");
        //判断拥有权限：user:create
        Assert.assertTrue(subject().isPermitted("user:create"));
        //判断拥有权限：user:update and user:delete 全部拥有
        Assert.assertTrue(subject().isPermittedAll("user:update", "user:delete"));
        //判断没有权限：user:view
        Assert.assertFalse(subject().isPermitted("user:view"));
    }
    //上面的isPermitted返回布尔类型
    //checkPermission 不匹配会抛出异常
    @Test(expected = UnauthorizedException.class)
    public void testCheckPermission() {
        login("classpath:demo3/shiro-permission.ini", "zhang", "123");
        //断言拥有权限：user:create
        subject().checkPermission("user:create");
        //断言拥有权限：user:delete and user:update
        subject().checkPermissions("user:delete", "user:update");
        //断言拥有权限：user:view 失败抛出异常
        subject().checkPermissions("user:view");
    }

    /**
     * #对资源user拥有update、delete权限
        role41=system:user:update,system:user:delete
        #对资源user拥有update、delete权限（上述权限简写，但不等价）
        role42="system:user:update,delete"
     */
    @Test
    public void testWildcardPermission1() {
        login("classpath:demo3/shiro-permission.ini", "hello", "123");
        //role41 语句1通过 语句2 不通过
        //role42 两种检测均通过
        subject().checkPermissions("system:user:update", "system:user:delete");
        subject().checkPermissions("system:user:update,delete");
    }
}
