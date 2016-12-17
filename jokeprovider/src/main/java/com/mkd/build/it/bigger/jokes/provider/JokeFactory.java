package com.mkd.build.it.bigger.jokes.provider;

import java.util.Random;

public class JokeFactory {
    private final String[] jokes = {
        "Life is all about perspective. The sinking of the Titanic was a miracle to the lobsters in the ship's kitchen.",
            "My wife and I were happy for twenty years. Then we met.",
            "Relationships are a lot like algebra. Have you ever looked at your X and wondered Y?",
            "I'm great at multitasking. I can waste time, be unproductive, and procrastinate all at once.",
            "Team work is important; it helps to put the blame on someone else.",
            "The reward for a job well done is more work.",
            "Nothing ruins a Friday more than an understanding that today is Tuesday.",
            "Can I have your picture so I can show Santa what I want for Christmas?",
            "Me: Siri, why am I alone? Siri: *opens front facing camera*"
    } ;
    public String getAJoke() {
        Random r = new Random();
        return jokes[r.nextInt(jokes.length - 1)];
    }
}
