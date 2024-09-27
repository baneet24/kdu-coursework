import './Quotes.scss';
import { QuoteContent } from './QuoteContent/QuoteContent';
import { Tags } from './Tags/Tags';
import { QuotesAPIResponse } from '../../../Types/ApiInterface';

interface QuoteProps {
  quote: QuotesAPIResponse;
  onTagClick: (tag: string) => void;
}

export function Quote({ quote, onTagClick }: QuoteProps) {
  return (
    <div className="Quote">
      <QuoteContent content={quote.content} />
      <p className="author">~{quote.author}</p>
      <p className="date-added">{quote.dateAdded}</p>

      <Tags tags={quote.tags} onTagClick={onTagClick} />
    </div>
  );
}
