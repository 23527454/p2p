package com.demo.p2p;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;


public class TesMP {
    private static String author = "gzd";//作者名称
    private static String outputDir = "E:\\作业\\Y2\\SpringBoot\\p2p\\src\\main\\java";//生成的位置
    private static String driver = "com.mysql.cj.jdbc.Driver";//驱动，注意版本
    //连接路径,注意修改数据库名称
    private static String url = "jdbc:mysql://localhost:3306/credit?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false";
    private static String username = "root";//数据库用户名
    private static String password = "root";//数据库密码
    //private static String tablePrefix ="sys";//数据库表的前缀，如tbl_user
    private static String[] tables = {"certification"};    //生成的表
    private static String parentPackage = "com.demo.p2p.ht";//顶级包结构
    private static String dao = "mapper";//数据访问层包名称
    private static String service = "service";//业务逻辑层包名称
    private static String entity = "entity";//实体层包名称
    private static String controller = "controller";//控制器层包名称
    private static String mapperxml = "mapper";//mapper映射文件包名称


    /**
     * 代码生成    示例代码
     */
    @Test
    public void testGenerator() {
        //1. 全局配置
        GlobalConfig config = new GlobalConfig();
        config.setAuthor(author) // 作者
                .setOutputDir(outputDir) // 生成路径
                .setFileOverride(true)  // 文件覆盖
                .setIdType(IdType.AUTO) // 主键策略
                .setServiceName("%sService")  // 设置生成的service接口的名字的首字母是否为I，加%s则不生成I
                .setBaseResultMap(true)    //映射文件中是否生成ResultMap配置
                .setBaseColumnList(true);    //生成通用sql字段

        //2. 数据源配置
        DataSourceConfig dsConfig = new DataSourceConfig();
        dsConfig.setDbType(DbType.MYSQL)  // 设置数据库类型
                .setDriverName(driver)    //设置驱动
                .setUrl(url)            //设置连接路径
                .setUsername(username)    //设置用户名
                .setPassword(password);    //设置密码

        //3. 策略配置
        StrategyConfig stConfig = new StrategyConfig();
        stConfig.setCapitalMode(true) //全局大写命名
                .setNaming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略
                //.setTablePrefix(tablePrefix) //表前缀
                .setInclude(tables);  // 生成的表

        //4. 包名策略配置
        PackageConfig pkConfig = new PackageConfig();
        pkConfig.setParent(parentPackage)//顶级包结构
                .setMapper(dao)    //数据访问层
                .setService(service)    //业务逻辑层
                .setController(controller)    //控制器
                .setEntity(entity)    //实体类
                .setXml(mapperxml);    //mapper映射文件

        //5. 整合配置
        AutoGenerator ag = new AutoGenerator();
        ag.setGlobalConfig(config)
                .setDataSource(dsConfig)
                .setStrategy(stConfig)
                .setPackageInfo(pkConfig);
        //6. 执行
        ag.execute();
    }
}

