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
     * 单个资源多个权限
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

    /**
     * 单个资源全部权限
     *   #对资源system:user拥有create、update、delete、view权限
         role51="system:user:create,update,delete,view"
         #对资源system:user拥有所有权限
         role52=system:user:*
         #对资源system:user拥有所有权限
         role53=system:user
     */
    @Test
    public void testWildcardPermission2() {
        login("classpath:demo3/shiro-permission.ini", "test5", "123");
        //role51 只有判断1通过
        //role52 全部判断通过
        //role53 全部判断通过
        /*
            结论：
            非简写的权限不能用简写的权限表达式校验
            简写的权限可以用非简写的权限表达式校验
         */
        subject().checkPermissions("system:user:create,delete,update,view");
        subject().checkPermissions("system:user:*");
        subject().checkPermissions("system:user");
    }

    /**
     * 所有资源全部权限
     *   #对资源拥有所有权限（如匹配user:view）
         role61=*:view
         #对资源拥有所有权限（如匹配system:user:view，即和之上的不等价）
         role62=*:*:view
        结论如“*:view”不能匹配“system:user:view”，需要使用“*:*:view”，即后缀匹配必须指定前缀（多个冒号就需要多个*来匹配）
     */
    @Test
    public void testWildcardPermission3() {
        login("classpath:demo3/shiro-permission.ini", "test6", "123");
        // role61 判断1 通过
//        subject().checkPermissions("user:view");
        // role62 判断2 通过
        subject().checkPermissions("system:user:view");
    }

    /**
     * 单个实例单个权限
     * #对资源user的1实例拥有view权限
     role71=user:view:1
     #对资源user的1实例拥有update、delete权限
     role72="user:update,delete:1"
     #对资源user的1实例拥有所有权限
     role73=user:*:1
     #对资源user的所有实例拥有auth权限
     role74=user:auth:*
     #对资源user的所有实例拥有所有权限
     role75=user:*:*
     */
    @Test
    public void testWildcardPermission4() {
        login("classpath:demo3/shiro-permission.ini", "test7", "123");
        //role71
//        subject().checkPermissions("user:view:1");
        //role72
        //role73
//        subject().checkPermissions("user:delete,update:1");
//        subject().checkPermissions("user:update:1", "user:delete:1");
//        //role73
//        subject().checkPermissions("user:update:1", "user:delete:1", "user:view:1");
        //role74
        subject().checkPermissions("user:auth:1", "user:auth:2");
        //role75  所有实例所有权限
        subject().checkPermissions("user:view:1", "user:auth:2");

    }

    /**
     * role91=*:*:*
         role92=*:*
         role93=*

     */
    @Test
    public void testWildcardPermission9() {
        login("classpath:demo3/shiro-permission.ini", "test9", "123");
        subject().checkPermissions("user:view:1");
        subject().checkPermissions("user:delete,update:1");
        subject().checkPermissions("user:update:1", "user:delete:1");
        subject().checkPermissions("user:update:1", "user:delete:1", "user:view:1");
        subject().checkPermissions("user:auth:1", "user:auth:2");
        subject().checkPermissions("user:view:1", "user:auth:2");

    }
}
