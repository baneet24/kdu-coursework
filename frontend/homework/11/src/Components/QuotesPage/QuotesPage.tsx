import "./QuotesPage.scss";
import { QuotesAPIResponse } from "../../Types/ApiInterface";
import { useEffect, useState } from "react";
import { Quote } from "./Quotes/Quotes";
import { SelectedTags } from "./SelectedTags/SelectedTags";
import { Button } from "./Quotes/Button";
import { fetchRandomQuoteFromAPI } from "../../Utils/QuoteApi";

export const QuotesPage = () => {
  const [quotes, setQuotes] = useState<QuotesAPIResponse[]>([]);
  const [tagFilters, setTagFilters] = useState<string[]>([]);
  const [allQuotes, setAllQuotes] = useState<QuotesAPIResponse[]>([]);

  useEffect(() => {
    fetch("https://api.quotable.io/quotes/random?limit=3")
      .then((response) => response.json())
      .then((data: QuotesAPIResponse[]) => {
        setAllQuotes(data);
      });
  }, []);

  useEffect(() => {
    setQuotes(
      allQuotes.filter((quote) =>
        tagFilters.every((tag) => quote.tags.includes(tag))
      )
    );
  }, [tagFilters, allQuotes]);

  const fetchNewQuote = async () => {
    const newQuote = await fetchRandomQuoteFromAPI();
    setAllQuotes([newQuote, ...allQuotes]);
  };

  const handleAddTag = (tag: string) => {
    if (!tagFilters.includes(tag)) {
      setTagFilters([...tagFilters, tag]);
    }
  };

  const handleRemoveTag = (tag: string) => {
    setTagFilters(tagFilters.filter((t) => t !== tag));
  };

  return (
    <div className="App">
      <Button addOnClick={fetchNewQuote} />
      <SelectedTags tags={tagFilters} onRemoveTag={handleRemoveTag} />

      <div className="Quotes">
        {quotes.map((quote) => {
          return (
            <Quote key={quote._id} quote={quote} onTagClick={handleAddTag} />
          );
        })}
      </div>
    </div>
  );
};
