package API.parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sentiment.analysis.SentimentAnalyzer;

public class APIResponseParser {
    private static final Logger logger = LoggerFactory.getLogger(SentimentAnalyzer.class);
    public static Book parse(String response) {
        Book book = new Book();
        String startTitle = "<title>";
        String endTitle = "<";
        String title = parse(response, startTitle, endTitle);
        book.setTitle(title);

        String startAuthor = "<name>";
        String endAuthor = "<";
        String author = parse(response, startAuthor, endAuthor);
        book.setAuthor(author);


        String startPublicationYear = "<original_publication_year type=\"integer\">";
        String endPublicationYear = "<";
        String publicationYearStr = parse(response, startPublicationYear, endPublicationYear);
        int publicationYear = Integer.parseInt(publicationYearStr);
        book.setPublicationYear(publicationYear);


        String startAverageRating = "<average_rating>";
        String endAverageRating = "<";
        String averageRatingStr = parse(response, startAverageRating, endAverageRating);
        double averageRating = Double.parseDouble(averageRatingStr);
        book.setAverageRating(averageRating);

        String startRatingsCount = "<ratings_count type=\"integer\">";
        String endRatingsCount = "<";
        String ratingsCountStr = parse(response, startRatingsCount, endRatingsCount);

        int ratingsCount = Integer.parseInt(ratingsCountStr.replace(",", ""));
        book.setRatingsCount(ratingsCount);

        String startImageUrl = "<image_url>";
        String endImageUrl = "<";
        String imageUrl = parse(response, startImageUrl, endImageUrl);
        book.setImageUrl(imageUrl);

        return book;
    }

    private static String parse(String response, String startRule, String endRule) {
        int startIndex = response.indexOf(startRule) + startRule.length();
        int endIndex = response.indexOf(endRule, startIndex);
        return response.substring(startIndex, endIndex);
    }

    public static void main(String[] args) {
        String response = "<work>" +
                "<id type=\"integer\">2361393</id>" +
                "<books_count type=\"integer\">813</books_count>" +
                "<ratings_count type=\"integer\">1,16,315</ratings_count>" +
                "<text_reviews_count type=\"integer\">3439</text_reviews_count>" +
                "<original_publication_year type=\"integer\">1854</original_publication_year>" +
                "<original_publication_month type=\"integer\" nil=\"true\"/>" +
                "<original_publication_day type=\"integer\" nil=\"true\"/>" +
                "<average_rating>3.79</average_rating>" +
                "<best_book type=\"Book\">" +
                "<id type=\"integer\">16902</id>" +
                "<title>Walden</title>" +
                "<author>" +
                "<id type=\"integer\">10264</id>" +
                "<name>Henry David Thoreau</name>" +
                "</author>" +
                "<image_url>" +
                "http://images.gr-assets.com/books/1465675526m/16902.jpg" +
                "</image_url>" +
                "<small_image_url>" +
                "http://images.gr-assets.com/books/1465675526s/16902.jpg" +
                "</small_image_url>" +
                "</best_book>" +
                "</work>";
        Book parsedBook = APIResponseParser.parse(response);

        logger.info(parsedBook.getTitle());
        logger.info("Author: " + parsedBook.getAuthor());
        logger.info("Publication Year: " + parsedBook.getPublicationYear());
        logger.info("Average Rating: " + parsedBook.getAverageRating());
        logger.info("Ratings Count: " + parsedBook.getRatingsCount());
        logger.info("Image URL: " + parsedBook.getImageUrl());
    }
}

