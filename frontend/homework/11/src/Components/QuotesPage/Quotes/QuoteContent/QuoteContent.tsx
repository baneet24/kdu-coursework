import "./QuotesContent.scss";
import { QuoteContentProps } from "../../../../Types/QuoteContentProps";


export function QuoteContent({ content }: QuoteContentProps) {
  return <h1 className="content">{content}</h1>;
}
