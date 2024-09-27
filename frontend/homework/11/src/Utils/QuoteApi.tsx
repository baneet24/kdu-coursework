import { QuotesAPIResponse } from "../Types/ApiInterface";

export const fetchRandomQuoteFromAPI = (): Promise<QuotesAPIResponse> => {
  return fetch("https://api.quotable.io/quotes/random?limit=1")
    .then((response) => response.json())
    .then((data) => data[0]);
};
