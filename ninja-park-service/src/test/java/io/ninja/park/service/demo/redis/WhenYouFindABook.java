/**
 * 
 */
package io.ninja.park.service.demo.redis;

import static com.lordofthejars.nosqlunit.redis.ManagedRedis.ManagedRedisRuleBuilder.newManagedRedisRule;
import static com.lordofthejars.nosqlunit.redis.RedisRule.RedisRuleBuilder.newRedisRule;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
import com.lordofthejars.nosqlunit.core.LoadStrategyEnum;
import com.lordofthejars.nosqlunit.redis.ManagedRedis;
import com.lordofthejars.nosqlunit.redis.RedisRule;

import io.ninja.park.service.demo.redis.Book;
import io.ninja.park.service.demo.redis.BookManager;
import redis.clients.jedis.Jedis;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author haochong.z
 *
 */
public class WhenYouFindABook {
	@ClassRule
	public static ManagedRedis managedRedis = newManagedRedisRule().redisPath("/opt/devtools/redis-3.2.11").build();
	@Rule
	public RedisRule redisRule = newRedisRule().defaultManagedRedis();

	@Test
	@UsingDataSet(locations = "book.json", loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
	public void book_should_be_returned_if_title_is_in_database() {

		BookManager bookManager = new BookManager(new Jedis("localhost"));
		Book findBook = bookManager.findBookByTitle("The Hobbit");

		assertThat(findBook, is(new Book("The Hobbit", 293)));

	}

}
