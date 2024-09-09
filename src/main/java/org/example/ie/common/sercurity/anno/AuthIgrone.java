package org.example.ie.common.sercurity.anno;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
//自定义注解

/**
 * @Retention(RUNTIME): 这个元注解指定了注解的保留策略。RUNTIME 表示该注解在运行时仍然可用，可以通过反射机制读取。这意味着注解信息会包含在生成的
 * 字节码中， * 并且可以在运行时通过反射API访问。
 * @Target({TYPE, METHOD}):
 * 这个元注解指定了注解可以应用的目标元素。TYPE 表示注解可以应用于类、接口（包括注解类型）和枚举声明。METHOD 表示注解可以应用于方法声明。
 * 因此，AuthIgrone 注解可以应用在类、接口、枚举以及方法上。
 * public @interface AuthIgrone:
 * 这是注解的定义。AuthIgrone 是注解的名称，public 表示这个注解是公共的，可以被其他类访问。
 * 用途
 * AuthIgrone 注解本身没有定义任何属性，因此它是一个标记注解（marker annotation）。标记注解通常用于标记某些元素，以便在代码的其他
 * 部分进行特殊处理。例如，AuthIgrone 可以用于标记那些不需要进行权限验证的方法或类。
 * 实现原理
 * 反射机制：在运行时，通过反射API可以读取注解信息。例如，可以使用 Class.getAnnotation(AuthIgrone.class) 方法来检查某个类是否被
 * AuthIgrone 注解标记。
 * 编译时处理：一些工具和框架（如Lombok、APT）可以在编译时处理注解，生成额外的代码。虽然这段代码没有定义任何属性，但通过编译时处理，
 * 可以生成与注解相关的代码。
 * 注意事项
 * 无属性注解：由于 AuthIgrone 注解没有定义任何属性，因此它只能作为一个标记存在。如果需要传递信息，应该定义相应的属性。
 * 使用场景：标记注解通常用于标记需要特殊处理的代码元素，例如需要跳过某些检查或处理的元素。
 * 反射性能：频繁使用反射API可能会影响性能，特别是在性能敏感的应用中。因此，应该谨慎使用反射，并考虑缓存结果以提高性能。
 * 总结来说，这段代码定义了一个名为 AuthIgrone 的标记注解，可以应用于类、接口和方法上，用于标记不需要进行权限验证的元素。
 */
@Retention(RUNTIME)
@Target({TYPE,METHOD})//注解可以应用到类、接口以及方法上
public @interface AuthIgrone {

}
