import { useRef, useEffect } from "react";

export const FormPage = () => {
  const InputRef = useRef<HTMLInputElement>(null);

  useEffect(() => {
    if (InputRef.current) {
      InputRef.current.focus();
    }
  }, []);

  return (
    <form>
      <label>
        Column A: <input ref={InputRef} type="text" />
      </label>
      <label>
        Column B: <input type="text" />
      </label>
    </form>
  );
};

