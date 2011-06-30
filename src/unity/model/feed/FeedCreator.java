package unity.model.feed;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedOutput;

public class FeedCreator {
    private String feedType;
    private String title;
    private String link;
    private String description;
    private List<SyndEntry> entries;

    /**
     * デフォルトコンストラクタ。「RSS 2.0」形式でFeedを生成します。
     */
    public FeedCreator() {
        this("rss_2.0");
    }

    /**
     * コンストラクタ
     * 
     * @param feedType
     *            　フィードの形式 "rss_1.0" "rss_2.0" "rss_0.9" "rss_0.92" "atom_1.0"
     *            "atom_0.3"
     */
    public FeedCreator(String feedType) {
        this.feedType = feedType;
        entries = new ArrayList<SyndEntry>();
    }

    /**
     * フィードの形式を指定します。
     * 
     * @param feedType
     */
    public void setFeedType(String feedType) {
        this.feedType = feedType;
    }

    /**
     * フィードのタイトルを指定します。
     * 
     * @param title
     */
    public void setFeedTitle(String title) {
        this.title = title;
    }


    /**
     * 配信元サイトのURLを指定します。
     * 
     * @param link
     */
    public void setFeedLink(String link) {
        this.link = link;
    }

    /**
     * フィードの概要を指定します。
     * 
     * @param description
     */
    public void setFeedDescription(String description) {
        this.description = description;
    }

    // public void setImage(SyndImage image) {
    // this.image = image;
    // }

    /**
     * テキスト形式の詳細エントリーを指定します。
     * 
     * @param title
     * @param image
     * @param link
     * @param value
     * @param pubDate
     */
    public void setTextEntry(String title, String link, String value,
            Date pubDate) {

        SyndEntry entry;
        SyndContent description;

        entry = new SyndEntryImpl();
        entry.setTitle(title);
        entry.setLink(link);
        entry.setPublishedDate(pubDate);
        description = new SyndContentImpl();
        description.setType("text/plain");
        description.setValue(value);

        entry.setDescription(description);
        entries.add(entry);
    }

    /**
     * 指定された形式でフィードを出力します。 このサンプルでは標準出力に文字列として出力しています。
     * 
     * @return
     */

    public String createFeed() {
        SyndFeed feed = (SyndFeed) new SyndFeedImpl();
        feed.setFeedType(feedType);
        feed.setTitle(title);
        feed.setLink(link);
        feed.setDescription(description);
        feed.setPublishedDate(new Date());
        feed.setEntries(entries);
        String feedText = "";
        SyndFeedOutput output = new SyndFeedOutput();
        try {
            // サンプルとして文字列として生成。
            // ファイルとして出力するならoutputメソッドを利用。
            feedText = output.outputString(feed);

        } catch (FeedException e) {
            e.printStackTrace();
        }
        return feedText;
    }

}
