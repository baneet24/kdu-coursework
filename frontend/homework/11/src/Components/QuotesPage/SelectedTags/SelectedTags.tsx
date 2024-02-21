import "./SelectedTags.scss";
import { SelectedTagsProps } from "../../../Types/SelectedTagsProps";



export const SelectedTags = ({ tags, onRemoveTag }: SelectedTagsProps) => {
  return (
    <div className="SelectedTags">
      Filters
      {tags.map((tag) => (
        <span
          key={tag}
          onClick={() => onRemoveTag(tag)}
          className="SelectedTag"
        >
          {tag} &#x2715;
        </span>
      ))}
    </div>
  );
};
