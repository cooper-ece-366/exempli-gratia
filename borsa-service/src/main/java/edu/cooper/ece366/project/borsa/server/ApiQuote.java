package edu.cooper.ece366.project.borsa.server;

import java.util.Collections;
import java.util.List;

public class ApiQuote {
    // from https://blog.hubspot.com/sales/famous-quotes
    private final List<String> QUOTE_LIST = Collections.unmodifiableList(List.of(
                    "The greatest glory in living lies not in never falling, but in rising every time we fall. -Nelson Mandela",
                    "The way to get started is to quit talking and begin doing. -Walt Disney",
                    "Your time is limited, so don't waste it living someone else's life. Don't be trapped by dogma – which is living with the results of other people's thinking. -Steve Jobs",
                    "If life were predictable it would cease to be life, and be without flavor. -Eleanor Roosevelt",
                    "If you look at what you have in life, you'll always have more. If you look at what you don't have in life, you'll never have enough. -Oprah Winfrey",
                    "If you set your goals ridiculously high and it's a failure, you will fail above everyone else's success. -James Cameron",
                    "Life is what happens when you're busy making other plans. -John Lennon",
                    "Spread love everywhere you go. Let no one ever come to you without leaving happier. -Mother Teresa",
                    "Tell me and I forget. Teach me and I remember. Involve me and I learn. -Benjamin Franklin",
                    "It is during our darkest moments that we must focus to see the light. -Aristotle",
                    "The best and most beautiful things in the world cannot be seen or even touched — they must be felt with the heart. -Helen Keller",
                    "Whoever is happy will make others happy too. -Anne Frank",
                    "Do not go where the path may lead, go instead where there is no path and leave a trail. -Ralph Waldo Emerson"
            )
    );

    String quote;
    String author;

    public ApiQuote() {
        int r = (int) (Math.random()*QUOTE_LIST.size());
        String q = QUOTE_LIST.get(r);
        String[] parts = q.split(" -", 2);
        this.quote = parts[0];
        this.author = parts[1];
    }

    public String getQuote() {
        return(this.quote);
    }

    public String getAuthor() {
        return(this.author);
    }

}
