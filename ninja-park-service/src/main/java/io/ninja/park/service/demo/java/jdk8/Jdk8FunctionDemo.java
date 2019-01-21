/**
 * 
 */
package io.ninja.park.service.demo.java.jdk8;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 函数接口
 * 
 * @author romgzy
 *
 */
public class Jdk8FunctionDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String prefix = "jdk8 function demo ";
		System.out.println("==============");
		// 不同姿势 Consumer消费型函数式接口
		// 代表了 接受一个输入参数并且无返回的操作
		Consumer<Integer> f = null;
		f = (p) -> System.out.println(prefix);
		f.accept(null);
		f = (p) -> System.out.println(prefix + p);
		f.accept(111);

		// Function功能型函数式接口
		// Function接口 接受一个输入参数T，返回一个结果R。
		Function<Integer, Integer> fun = p -> p + 1;
		System.out.println(fun.apply(100));

		// Predicate断言型函数式接口
		// 接受一个输入参数，返回一个布尔值结果。
		Predicate<Integer> p = x -> x > 0;
		System.out.println(p.test(100));
		System.out.println(p.test(0));

		// Supplier供给型函数式接口
		Supplier<String> s = () -> prefix;
		System.out.println(s.get());
		List<Data> list = new ArrayList();
		int k = 0;
		while (k < 100) {
			Data data = new Data();
			data.setAge(k);
			data.setName("name" + k);
			list.add(data);
			k++;
		}
		System.out.println(list);
		/**
		 * list -> map
		 */
		Map<String, Data> dataMap = list.stream()
				.collect(Collectors.toMap(y -> y.getName(), x -> x, (key1, key2) -> key1));
		System.out.println(new TreeMap(dataMap));
		System.out.println(dataMap.entrySet().size());
		/**
		 * list -> list
		 */
		List<Item> itemList = list.stream().map(x -> {
			Item item = new Item();
			item.setItemName(x.getName());
			item.setLabel(String.valueOf(x.getAge()));
			return item;
		}).collect(Collectors.toList());
		System.out.println(itemList);
		/**
		 * map -> list
		 */
		List<Item> itemList2 = dataMap.entrySet().stream().parallel().map(x -> {
			Item item = new Item();
			item.setItemName(x.getValue().getName());
			item.setLabel(String.valueOf(x.getValue().getAge()));
			return item;
		}).collect(Collectors.toList());
		System.out.println(itemList2);
	}

	private static class Item {
		private String itemName;
		private String label;

		public String getItemName() {
			return itemName;
		}

		public void setItemName(String itemName) {
			this.itemName = itemName;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}

		@Override
		public String toString() {
			return "Item [itemName=" + itemName + ", label=" + label + "]";
		}

	}

	private static class Data {
		private String name;
		private int age;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		@Override
		public String toString() {
			return "Data [name=" + name + ", age=" + age + "]";
		}

	}

}
