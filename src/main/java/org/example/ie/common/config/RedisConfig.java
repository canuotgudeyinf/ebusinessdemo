package org.example.ie.common.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;


import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Configuration
@EnableCaching
public class RedisConfig {
	/**
	 这段Java代码定义了一个Spring Bean，名为`keyGenerator`，它返回一个`KeyGenerator`对象。`KeyGenerator`是一个接口，
	 通常用于生成键值，例如在缓存中生成唯一的键。
	 这个自定义的`KeyGenerator`实现通过组合目标类名、
	 方法名和参数来生成键。
	 1. 定义Bean：使用`@Bean`注解将`keyGenerator`方法标记为一个Spring Bean，这样Spring容器在启动时会自动调用这个方法并注册
	 返回的`KeyGenerator`对象。
	 2. 匿名内部类：`keyGenerator`方法返回一个匿名内部类，这个内部类实现了`KeyGenerator`接口。
	 3. 生成键的逻辑：
	 - 使用`StringBuilder`来构建键字符串。
	 - `target.getClass().getName()`获取目标类的全限定名。
	 - `method.getName()`获取方法的名称。
	 - 遍历方法的参数，将每个参数的`toString()`方法结果追加到`StringBuilder`中。
	 4. 返回键：将构建好的键字符串返回。
	 用途
	 这个自定义的`KeyGenerator`可以用于生成缓存键，确保每个缓存条目都有一个唯一的键，即使调用的是同一个方法，只要参数不同，
	 生成的键也会不同。
	 1. 性能：每次调用`generate`方法时都会创建一个新的`StringBuilder`对象，这可能会影响性能。如果性能是一个关键考虑因素，
	 可以考虑使用更高效的数据结构或缓存机制。
	 2. 线程安全：`StringBuilder`不是线程安全的，如果`KeyGenerator`可能会在多线程环境中使用，需要确保线程安全。
	 3. 键的长度：生成的键长度可能会随着参数数量的增加而增加，这可能会影响缓存系统的性能。如果键的长度过长，可能需要考虑优化键的生成逻辑。
	 4. 参数类型：如果参数是复杂对象，直接调用`toString()`方法可能不会得到预期的结果。可能需要自定义参数的字符串表示形式。
	 */
	@Bean
	public KeyGenerator keyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object target, Method method, Object... params) {
				StringBuilder sb = new StringBuilder();
				sb.append(target.getClass().getName());
				sb.append("#" + method.getName());
				for (Object obj : params) {
					sb.append(obj.toString());
				}
				return sb.toString();
			}
		};
	}

	/**
	 在Spring应用中使用Redis作为缓存，以提升性能和响应速度。通过配置不同的缓存名称和过期时间，可以灵活地管理不同类型的数据缓存
 */

	@Bean
	public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		Jackson2JsonRedisSerializer<Object> jacksonSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
		config = config.entryTtl(Duration.ofMinutes(60))
				.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(stringRedisSerializer))
				.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jacksonSerializer))
				.disableCachingNullValues();
		Set<String> cacheNames = new HashSet<>();
		cacheNames.add("timeGroup");
		cacheNames.add("user");
		Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
		configMap.put("timeGroup", config);
		configMap.put("user", config.entryTtl(Duration.ofSeconds(120)));
		RedisCacheManager cacheManager = RedisCacheManager.builder(redisConnectionFactory)
				.cacheDefaults(
						RedisCacheConfiguration.defaultCacheConfig(Thread.currentThread().getContextClassLoader()))
				.initialCacheNames(cacheNames).withInitialCacheConfigurations(configMap).build();
		return cacheManager;
	}

	/**
	 这段Java代码定义了一个Spring Bean，用于配置一个`RedisTemplate`实例，该实例用于与Redis数据库进行交互。`RedisTemplate`是
	 Spring Data Redis
	 提供的一个高级抽象，用于简化与Redis的交互操作
	 1. 定义Bean：使用`@Bean`注解，表明该方法返回的对象是一个Spring Bean，Spring容器会管理这个Bean的生命周期。
	 2. 参数注入：方法接收一个`RedisConnectionFactory`类型的参数，这是Spring Data Redis用于创建与Redis服务器连接的工厂。
	 3. 序列化器配置：
	 - `StringRedisSerializer`：用于序列化和反序列化Redis的键（key）。
	 - `Jackson2JsonRedisSerializer`：用于序列化和反序列化Redis的值（value），这里使用Jackson库将对象序列化为JSON格式。
	 4. 创建RedisTemplate实例：
	 - 创建一个`RedisTemplate<String, Object>`实例，指定键和值的类型。
	 - 设置连接工厂`connectionFactory`。
	 - 设置键和值的序列化器。
	 - 设置哈希键和哈希值的序列化器。
	 - 调用`afterPropertiesSet()`方法，初始化模板。
	 5. 返回模板：返回配置好的`RedisTemplate`实例。
	 ### 用途
	 这段代码的用途是在Spring应用中配置一个`RedisTemplate`，用于与Redis数据库进行交互。通过配置`RedisTemplate`，
	 可以方便地进行各种Redis操作，如存储、获取、删除键值对等。
	 ### 注意事项
	 1. 序列化器选择：选择合适的序列化器对于性能和兼容性非常重要。在这个例子中，键使用`StringRedisSerializer`，值使用
	 `Jackson2JsonRedisSerializer`，适用于大多数场景。
	 2. Jackson依赖：使用`Jackson2JsonRedisSerializer`需要项目中包含Jackson库的依赖。
	 3. 配置文件：确保在Spring配置文件中启用了Redis支持，并且正确配置了Redis连接信息。
	 4. 线程安全：`RedisTemplate`是线程安全的，可以在多个线程中共享使用。
	 5. 异常处理：在实际应用中，需要对Redis操作可能抛出的异常进行适当的处理，例如网络异常、连接超时等。
	 通过这段代码，开发者可以轻松地在Spring应用中集成Redis，并利用`RedisTemplate`提供的丰富功能与Redis进行交互。
	 */
	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		Jackson2JsonRedisSerializer<Object> jacksonSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(connectionFactory);
		template.setKeySerializer(stringRedisSerializer);
		template.setValueSerializer(jacksonSerializer);

		template.setHashKeySerializer(stringRedisSerializer);
		template.setHashValueSerializer(jacksonSerializer);
		template.afterPropertiesSet();
		return template;
	}

	/**
	 * 这段Java代码定义了一个Spring Bean，用于创建并配置一个StringRedisTemplate实例。StringRedisTemplate是Spring Data Redis
	 * 提供的一个模板类，用于简化与Redis数据库的交互，
	 * 特别是针对键值对存储（即字符串类型）的操作。
	 * 实现原理
	 *
	 * @Bean注解：这个注解表明该方法将返回一个对象，该对象要注册为Spring应用上下文中的Bean。Spring容器会管理这个Bean的生命周期。 方法参数：RedisConnectionFactory是一个接口，用于创建和管理与Redis服务器的连接。Spring Data Redis提供了多个实现，
	 * 如JedisConnectionFactory或LettuceConnectionFactory。
	 * 创建StringRedisTemplate实例：通过调用new StringRedisTemplate()创建一个新的StringRedisTemplate实例。
	 * 设置连接工厂：通过调用template.setConnectionFactory(redisConnectionFactory)将传入的RedisConnectionFactory实例设置
	 * 到StringRedisTemplate中，
	 * 这样StringRedisTemplate就可以使用这个连接工厂来创建和管理与Redis服务器的连接。
	 * 返回模板实例：最后，方法返回配置好的StringRedisTemplate实例，Spring容器会将其注册为Bean。
	 * 用途
	 * StringRedisTemplate主要用于在Spring应用中与Redis进行字符串类型数据的操作，包括但不限于以下功能：
	 * 存储和获取字符串数据
	 * 执行Redis的字符串操作命令，如SET、GET、INCR等
	 * 支持Redis的发布/订阅功能
	 * 注意事项
	 * 依赖注入：确保在Spring配置文件或配置类中正确配置了RedisConnectionFactory，并且这个Bean可以被Spring容器找到。
	 * 连接池配置：如果使用JedisConnectionFactory，可以通过配置JedisPoolConfig来优化Redis连接池的行为。
	 * 序列化方式：StringRedisTemplate默认使用StringRedisSerializer来序列化和反序列化数据。如果需要处理复杂对象，可能需要自定义序列化器。
	 * 事务支持：StringRedisTemplate支持Redis事务，但需要注意的是，Redis事务在Spring Data Redis中是通过Lua脚本实现的，因此事务的隔离
	 * 级别和传统数据库事务有所不同。
	 * 通过这段代码，开发者可以方便地在Spring应用中集成Redis，并使用StringRedisTemplate进行高效的字符串类型数据操作。
	 */
	@Bean
	public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
		StringRedisTemplate template = new StringRedisTemplate();
		template.setConnectionFactory(redisConnectionFactory);
		return template;
	}

}
