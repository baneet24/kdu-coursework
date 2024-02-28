import * as React from "react";
import Box from "@mui/material/Box";
import Snackbar, { SnackbarOrigin } from "@mui/material/Snackbar";
import { useDispatch, useSelector } from "react-redux";
import { AppDispatch, RootState } from "../../state/store/store";
import { setShow } from "../../state/SnackbarSlice";

interface State extends SnackbarOrigin {
  open: boolean;
}

export default function SnackBar() {
  const [state] = React.useState<State>({
    open: false,
    vertical: "bottom",
    horizontal: "center",
  });
  const { vertical, horizontal } = state;

  const message = useSelector((state: RootState) => state.snackBar.message);
  const show = useSelector((state: RootState) => state.snackBar.show);
  const reduxDispatch: AppDispatch = useDispatch();

  const handleClose = () => {
    reduxDispatch(setShow(false));
  };

  return (
    <Box sx={{bgcolor: 'green' }} >
      <Snackbar
        anchorOrigin={{ vertical, horizontal }}
        open={show}
        onClose={handleClose}
        message={message}
        autoHideDuration={5000}
        key={vertical + horizontal}
      />
    </Box>
  );
}
