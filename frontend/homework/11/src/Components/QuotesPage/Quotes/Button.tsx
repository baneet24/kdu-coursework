
interface IButtonProps{
    addOnClick: () => void;
}

export const Button = ({addOnClick}: IButtonProps) => {
    return (
        <button onClick={addOnClick}>NEW QUOTE</button>
    )
}