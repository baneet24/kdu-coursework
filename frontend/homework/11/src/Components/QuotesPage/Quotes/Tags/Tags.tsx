import './Tags.scss';
import { TagsProps } from '../../../../Types/TagsProp';

export function Tags({ tags, onTagClick }: TagsProps) {
  return (
    <div className="tags">
      {tags.map((tag) => {
        return (
          <span key={tag} onClick={() => onTagClick(tag)}>
            {tag}
          </span>
        );
      })}
    </div>
  );
}
