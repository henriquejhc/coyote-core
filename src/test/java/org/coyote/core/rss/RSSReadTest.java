package org.coyote.core.rss;

import org.coyote.core.rss.model.Feed;
import org.coyote.core.rss.model.FeedMessage;
import org.coyote.core.rss.read.RSSFeedParser;

public class RSSReadTest {

	// public static String RSS_LINK = "http://www.vogella.com/article.rss";
	public static String RSS_LINK = "http://diariocatarinense.clicrbs.com.br/sc/ultimas-noticias-rss/";
	// public static String RSS_LINK = "http://estadao.feedsportal.com/c/33043/f/534105/index.rss";
	// public static String RSS_LINK = "http://www.terra.com.br/rss";
	// public static String RSS_LINK = "https://news.google.com.br/news/feeds";
	// public static String RSS_LINK = "http://feeds.guardian.co.uk/theguardian/rss";

	// public static String RSS_LINK = "http://www.guj.com.br/noticias/rss";

	public static void main(String[] args) {
		// RSSFeedParser parser = new RSSFeedParser("http://www.vogella.com/article.rss");

		RSSFeedParser parser = new RSSFeedParser(RSS_LINK);

		Feed feed = parser.readFeed();
		System.out.println(feed);
		for (FeedMessage message : feed.getMessages()) {
			System.out.println(message);

		}

	}

}