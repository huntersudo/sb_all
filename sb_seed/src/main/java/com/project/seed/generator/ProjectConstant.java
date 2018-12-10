package com.project.seed.generator;

/**
 * 项目常量
 */
public final class ProjectConstant {
    /**
     *
     生成代码所在的基础包名称
     */
    public static final String BASE_PACKAGE = "com.project.seed";

    //生成的Model所在包
    public static final String MODEL_PACKAGE = BASE_PACKAGE + ".model";

    //生成的XML, Model 所在包
    public static final String PERSISTENCE_PACKAGE = BASE_PACKAGE + ".persistence";

    //生成的Service所在包
    public static final String SERVICE_PACKAGE = BASE_PACKAGE + ".service";

    //生成的ServiceImpl所在包
    public static final String SERVICE_IMPL_PACKAGE = SERVICE_PACKAGE + ".impl";

    //生成的Controller所在包
    public static final String CONTROLLER_PACKAGE = BASE_PACKAGE + ".controller";

    //Mapper插件基础接口的完全限定名
    public static final String MAPPER_INTERFACE_REFERENCE = BASE_PACKAGE + ".generator.Mapper";
}

