package com.logic.passinterview.search;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.ToString;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.Avg;
import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class PassinterviewSearchApplicationTests {

	@Qualifier("restHighLevelClient")
	@Autowired
	private RestHighLevelClient client;

	/**
	 * 测试 ES Client 自动加载
	 */
	@Test
	void contextLoads() {
		System.out.println(client);
	}

	@Test
	void testIndexData() throws IOException {
		IndexRequest request = new IndexRequest("users");
		request.id("1");//文档的id

		//构造 User 对象
		User user = new User();
		user.setUserName("PassInterview");
		user.setAge("18");
		user.setGender("Man");

		//将 User 对象转换为 JSON 数据
		String jsonString = JSON.toJSONString(user);

		//将 JSON 数据放入 request 中
		request.source(jsonString, XContentType.JSON);

		//执行插入操作
		IndexResponse response = client.index(request, RequestOptions.DEFAULT);

		System.out.println(response);
	}

	@Data
	class User {
		private String userName;
		private String age;
		private String gender;
	}

	@Test
	void testSearchData() throws IOException {
		SearchRequest request = new SearchRequest();
		request.indices("bank");
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		//1.1）address 中包含 road 的所有人
		sourceBuilder.query(QueryBuilders.matchQuery("address","road"));
		// 1.2）按照年龄分布进行聚合
		TermsAggregationBuilder ageAgg = AggregationBuilders.terms("ageAgg").field("age").size(10);
		sourceBuilder.aggregation(ageAgg);
		//1.3）计算平均薪资
		AvgAggregationBuilder balanceAvg = AggregationBuilders.avg("balanceAvg").field("balance");
		sourceBuilder.aggregation(balanceAvg);
		System.out.println("检索参数：" + sourceBuilder.toString());

		request.source(sourceBuilder);

		//2.执行检索
		SearchResponse response = client.search(request, RequestOptions.DEFAULT);

		//3.分析结果
		System.out.println(response.toString());
		// 3.1）获取查到的数据。
		SearchHits hits = response.getHits();
		// 3.2）获取真正命中的结果
		SearchHit[] searchHits = hits.getHits();
		// 3.3）遍历命中结果
		for (SearchHit hit : searchHits) {
			String hitStr = hit.getSourceAsString();
			BankMember bankMember = JSON.parseObject(hitStr, BankMember.class);
			System.out.println(bankMember);
		}
		//3.4）获取聚合信息
		Aggregations aggregations = response.getAggregations();
		Terms ageAgg1 = aggregations.get("ageAgg");
		for (Terms.Bucket bucket : ageAgg1.getBuckets()){
			String keyAsString = bucket.getKeyAsString();
			System.out.println("用户年龄：" + keyAsString + " 人数：" + bucket.getDocCount());
		}
		Avg balanceAvg1 = aggregations.get("balanceAvg");
		System.out.println("平均薪资：" + balanceAvg1.getValue());
	}

	@ToString
	@Data
	static class BankMember {
		private int account_number;
		private int balance;
		private String firstname;
		private String lastname;
		private int age;
		private String gender;
		private String address;
		private String employer;
		private String email;
		private String city;
		private String state;
	}
}
