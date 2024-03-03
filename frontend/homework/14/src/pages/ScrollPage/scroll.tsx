const styles = {
  scrollButtonDiv: {
    height: "250vh",
    display: "flex",
    flexDirection: "column",
    justifyContent: "center",
  },

  scrollButton: {
    height: "40px",
  },
};

export const ScrollToTopButton = () => {
  const scrollToTop = () => {
    window.scrollTo({
      top: 0,
      behavior: "smooth",
    });
  };

  return (
    <div style={styles.scrollButtonDiv}>
      <button onClick={scrollToTop} style={styles.scrollButton}>
        Scroll to Top
      </button>
    </div>
  );
};

